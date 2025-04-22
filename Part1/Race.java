import java.util.concurrent.TimeUnit;
import java.lang.Math;

public class Race {
    private final int raceLength;
    private final Horse[] lanes;

    public Race(int raceLength, int numberOfLanes) {
        this.raceLength = raceLength;
        this.lanes = new Horse[numberOfLanes];
    }

    //
    public static void main(String[] args) {
        int raceLength   = 20;
        int lanesCount = 4;

        Race horseRace = new Race(raceLength, lanesCount);

        horseRace.addHorse(new Horse('A', "Thunder",   0.5), 1);
        horseRace.addHorse(new Horse('B', "Lightning", 0.4), 2);
        horseRace.addHorse(new Horse('C', "Blaze",     0.6), 3);

        horseRace.startRace();
    }

    public void addHorse(Horse horse, int laneNumber) {
        if (laneNumber < 1 || laneNumber > lanes.length) {
            System.out.println("Cannot add horse to lane " + laneNumber + ": invalid lane.");
        } else {
            lanes[laneNumber - 1] = horse;
        }
    }

    private boolean allHorsesHaveValidConfidence() {
        for (Horse h : lanes) {
            if (h != null) {
                double c = h.getConfidence();
                if (c <= 0.0 || c > 1.0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void startRace() {
        if (!allHorsesHaveValidConfidence()) {
            System.out.println("Cannot start race as not all horses have valid confidence.");
            return;
        }

        for (Horse h : lanes) {
            if (h != null) h.goBackToStart();
        }

        Horse winner = null;
        boolean finished = false;

        while (!finished) {
            for (Horse h : lanes) {
                if (h != null) {
                    moveHorse(h);
                }
            }

            printRace();

            for (Horse h : lanes) {
                if (h != null && raceWonBy(h)) {
                    winner = h;
                    finished = true;
                    break;
                }
            }

            if (!finished) {
                boolean allFallen = true;
                for (Horse h : lanes) {
                    if (h != null && !h.hasFallen()) {
                        allFallen = false;
                        break;
                    }
                }
                if (allFallen) {
                    System.out.println("All horses have fallen! The race is over.");
                    finished = true;
                }
            }

            if (!finished) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException ignored) {}
            }
        }

        if (winner != null) {
            System.out.println("And the winner is... " + winner.getName().toUpperCase() + "!!!");
        }
    }

    private void moveHorse(Horse h) {
        if (h.hasFallen()) return;

        if (Math.random() < h.getConfidence()) {
            h.moveForward();
        }

        if (Math.random() < (0.1 * h.getConfidence() * h.getConfidence())) {
            h.fall();
        }

        if (raceWonBy(h)) {
            h.increaseConfidence(0.05);
        }
    }

    private boolean raceWonBy(Horse h) {
        return !h.hasFallen() && h.getDistanceTravelled() == raceLength;
    }

    private void printRace() {
        multiplePrint('=', raceLength + 3);
        System.out.println();

        for (Horse h : lanes) {
            if (h != null) {
                printLane(h);
            } else {
                System.out.print('|');
                multiplePrint(' ', raceLength);
                System.out.print(" |  [empty lane]");
            }
            System.out.println();
        }

        multiplePrint('=', raceLength + 3);
        System.out.println();
    }

    private void printLane(Horse h) {
        int before = h.getDistanceTravelled();
        int after  = raceLength - before;

        System.out.print('|');
        multiplePrint(' ', before);

        System.out.print(h.hasFallen() ? 'X' : h.getSymbol());

        multiplePrint(' ', after);
        System.out.print("|  " + h.getName().toUpperCase()
                + " (Current confidence " + h.getConfidence() + ")");
    }

    private void multiplePrint(char c, int times) {
        for (int i = 0; i < times; i++) {
            System.out.print(c);
        }
    }
}
