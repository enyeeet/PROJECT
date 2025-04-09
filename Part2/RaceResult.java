package Part2;

public class RaceResult {
    private HorsePerformance horsePerformance;
    private double finishTime;
    private double avgSpeed;
    private double initialConfidence;
    private boolean isWinner;
    private boolean didNotFinish;
    private String trackShape;
    private String trackCondition;

    public RaceResult(double finishTime, double speed, double initialConfidence, boolean isWinner, String trackShape, String trackCondition, boolean didNotFinish) {
        this.finishTime = finishTime;
        this.avgSpeed = speed;
        this.initialConfidence = initialConfidence;
        this.isWinner = isWinner;
        this.trackShape = trackShape;
        this.trackCondition = trackCondition;
        this.didNotFinish = didNotFinish;
    }

    public double getFinishTime() { return finishTime; }
    public double getInitialConfidence() { return initialConfidence; }
    public boolean isWinner() { return isWinner; }
    public boolean didNotFinish() { return didNotFinish; }
    public String getTrackShape() { return trackShape; }
    public String getTrackCondition() { return trackCondition; }
}