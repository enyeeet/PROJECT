package Part2;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.lang.Math;

public class RaceAnimation {
    private HorseRaceGUI mainGUI;
    private int trackLength;
    private int noOfLanes;
    private ArrayList<InRaceHorse> horses;
    private HorseData horseData;
    private StartRace racePage;
    private InRaceHorse winningHorse = null;

    public RaceAnimation(HorseRaceGUI mainGUI, StartRace racePage) {
        // Initialize race length and horses list
        this.mainGUI = mainGUI;
        this.racePage = racePage;
        this.trackLength = determineTrackUnits(mainGUI.getTrackLength());
        horses = new ArrayList<>();
        initializeHorses();
    }

    private void initializeHorses() {
        ArrayList<HorseData> horseDataList = mainGUI.getHorseDataList();
        ArrayList<HorsePerformance> horsePerformanceList = mainGUI.getHorsePerformanceList();
        this.noOfLanes = horseDataList.size();  // Set number of lanes based on number of horses

        horses.clear();
        horsePerformanceList.clear();

        for (HorseData horseData : horseDataList) {
            InRaceHorse horse = new InRaceHorse(horseData.getSymbol(), horseData.getName(), horseData.getHorseConfidence(), horseData.getHorseSpeed());
            horses.add(horse);

            HorsePerformance horsePerformance = new HorsePerformance(horseData, mainGUI);
            horsePerformanceList.add(horsePerformance);
        }
    }

    private boolean allHorsesHaveValidConfidence() {
        for (InRaceHorse horse : horses) {
            if (horse.getConfidence() < 0.0 || horse.getConfidence() > 1.0) {
                return false;
            }
        }
        return true;
    }

    public void resetRace() {
        for (InRaceHorse horse : horses) {
            horse.goBackToStart();  // Reset the horse's position to the starting point
        }
    }

    public void startRace() {
        initializeHorses();

        if (!allHorsesHaveValidConfidence()) {
            racePage.updateRaceDisplay("Cannot start race as not all horses have a valid confidence rating.");
            return;
        }

        resetRace();

        boolean finished = false;
        String endRaceText = "";

        for (InRaceHorse horse : horses) {
            horse.startTime();
        }

        while (!finished) {
            for (InRaceHorse horse : horses) {
                moveHorse(horse);
            }

            updateRaceUI("");

            for (InRaceHorse horse : horses) {
                if (raceWonBy(horse)) {
                    horse.endTime();
                    finished = true;
                    winningHorse = horse;
                    endRaceText = "<br><br><b>Winner: " + horse.getName().toUpperCase() + "! 🏆</b>";
                    endRaceText += "<br><b>Finishing Time: </b>" + String.format("%.2f", horse.getFinishingTime()) + " seconds.";
                }
            }

            if (allHorsesFallen()) {
                finished = true;
                endRaceText = "<br><br><b>All horses have fallen! Race over. ❌</b>";
            }

            updateRaceUI(endRaceText);

            ArrayList<HorsePerformance> performanceList = mainGUI.getHorsePerformanceList();

            for (int i = 0; i < horses.size(); i++) {
                InRaceHorse raceHorse = horses.get(i);
                boolean isWinner = raceHorse == winningHorse;
                RaceResult result = raceHorse.generateResult(isWinner);

                if (i < performanceList.size()) {
                    performanceList.get(i).addResult(result);
                }
            }

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (Exception e) {}

            ArrayList<HorseData> dataList = mainGUI.getHorseDataList();
            for (int i = 0; i < horses.size(); i++) {
                InRaceHorse raceHorse = horses.get(i);
                if (i < dataList.size()) {
                    dataList.get(i).setConfidence(raceHorse.getConfidence());
                }
            }
        }
    }

    private void updateRaceUI(String endRaceMessage) {
        StringBuilder raceText = new StringBuilder();

        // Loop through each horse and get the text representation for each lane
        for (InRaceHorse horse : horses) {
            raceText.append(getLaneText(horse)).append("<br>");
        }

        // Update the display with the race progress
        racePage.updateRaceDisplay("<html><pre>" + raceText.toString() + endRaceMessage + "</pre></html>");
    }

    private String getLaneText(InRaceHorse theHorse) {
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = trackLength - theHorse.getDistanceTravelled();

        StringBuilder laneBuilder = new StringBuilder("|");

        for (int i = 0; i < spacesBefore; i++) {
            laneBuilder.append("&nbsp;");
        }

        if (theHorse.hasFallen()) {
            laneBuilder.append("❌");
        } else {
            laneBuilder.append(theHorse.getSymbol());
        }

        for (int i = 0; i < spacesAfter; i++) {
            laneBuilder.append("&nbsp;");
        }

        String lane = laneBuilder.toString();
        String formattedConfidence = String.format("%.2f", theHorse.getConfidence());
        String text = lane + "|&nbsp;" + theHorse.getName().toUpperCase() +
                "&nbsp;(Confidence:&nbsp;" + formattedConfidence + ")";

        if (theHorse == winningHorse) {
            return "<span style='background-color:yellow'>" + text + " 🥇🎊</span>";
        } else {
            return text;
        }
    }

    private boolean allHorsesFallen() {
        for (InRaceHorse horse : horses) {
            if (!horse.hasFallen()) {
                return false;
            }
        }
        return true;
    }

    private void moveHorse(InRaceHorse theHorse) {
        // If the horse has fallen, it cannot move.
        if (!theHorse.hasFallen()) {
            // The probability that the horse will move forward depends on the confidence.
            if (Math.random() < theHorse.getConfidence()) {
                theHorse.moveForward();
            }

            // The probability that the horse will fall is small, but depends on confidence.
            if (Math.random() < (0.1 * theHorse.getConfidence() * theHorse.getConfidence())) {
                theHorse.fall();
            }
        }

        if (raceWonBy(theHorse)) {
            theHorse.increaseConfidence(0.02);
        }
    }

    private boolean raceWonBy(InRaceHorse theHorse) {
        return theHorse.getDistanceTravelled() == trackLength;
    }

    private int determineTrackUnits(int trackLength) {
        return (int) Math.round((double) trackLength / 100.0);
    }
}

