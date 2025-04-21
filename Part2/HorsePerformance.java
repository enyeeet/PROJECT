package Part2;

import java.util.ArrayList;

public class HorsePerformance {
    private HorseRaceGUI mainGUI;
    private HorseData horseData;

    private String horseName;
    private double horseConfidence;
    private int totalRaces;
    private int racesWon;
    private double winRatio;

    private ArrayList<RaceResult> raceHistory;

    HorsePerformance(HorseData horseData, HorseRaceGUI mainGUI) {
        this.mainGUI = mainGUI;
        this.horseData = horseData;

        this.horseName = horseData.getName();
        this.totalRaces = 0;
        this.racesWon = 0;
        this.horseConfidence = horseData.getHorseConfidence();
        this.raceHistory = new ArrayList<>();
    }

    public void addResult(RaceResult result) {
        this.totalRaces++;
        if(result.isWinner()){
            this.racesWon++;
        }
        this.raceHistory.add(result);
        System.out.println("Added race for: " + horseName + ". Total races: " + raceHistory.size());
    }

    public double getWinRatio() {
        if(this.racesWon == 0){
            winRatio = 0.0;
        }
        else{
            winRatio = totalRaces == 0 ? 0 : (double) this.racesWon / this.totalRaces;
        }
        return winRatio;
    }

    public String getHorseName() {
        return this.horseName;
    }

    public double getHorseConfidence(){
        return this.horseConfidence;
    }

    public ArrayList<RaceResult> getRaceHistory() {
        return this.raceHistory;
    }
}
