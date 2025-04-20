package Part2;

public class InRaceHorse
{
    private HorseRaceGUI mainGUI;
    private HorseData horseData;

    private String horseSymbol;
    private String horseName;
    private double horseConfidence;
    private boolean fallen;
    private int distanceTravelled;
    private double horseSpeed;

    private long startTime;
    private long endTime;
    private double finishingTime;


    public InRaceHorse(HorseRaceGUI mainGUI, String horseSymbol, String horseName, double horseConfidence, double horseSpeed)
    {
        this.mainGUI = mainGUI;
        this.horseSymbol = horseSymbol;
        this.horseName = horseName;
        this.horseSpeed = horseSpeed;
        setConfidence(horseConfidence);
        this.fallen = false;
        this.distanceTravelled = 0;
        this.finishingTime = 0.0;
    }

    public RaceResult generateResult(boolean isWinner, boolean didNotFinish) {
        double finishTime = didNotFinish ? 0 : getFinishingTime();
        double avgSpeed = didNotFinish ? 0 : mainGUI.getTrackLength() / finishTime;

        return new RaceResult(finishTime, avgSpeed, this.horseConfidence, isWinner, mainGUI.getTrackShape(), mainGUI.getTrackCondition(), didNotFinish);
    }

    public void startTime(){
        this.startTime = System.nanoTime();
    }

    public void endTime(){
        this.endTime = System.nanoTime();
        this.finishingTime = (this.endTime - this.startTime) / 1000000000.0;
    }

    public void fall()
    {
        this.fallen = true;
        decreaseConfidence(0.02);
    }

    public double getFinishingTime(){
        return (this.finishingTime * 10);
    }

    public double getConfidence()
    {
        return this.horseConfidence;
    }

    public int getDistanceTravelled()
    {
        return this.distanceTravelled;
    }

    public String getName()
    {
        return this.horseName;
    }

    public String getSymbol()
    {
        return this.horseSymbol;
    }

    public void goBackToStart()
    {
        this.fallen = false;
        this.distanceTravelled = 0;
    }

    public boolean hasFallen()
    {
        return this.fallen;
    }

    public void moveForward()
    {
        if(!this.fallen){
            this.distanceTravelled++;
        }
    }

    public void setConfidence(double newConfidence)
    {
        if(newConfidence < 0.0 || newConfidence > 1.0){
            System.out.println("Confidence rating must be between 0.0 and 1.0.");
        }

        this.horseConfidence = newConfidence;
    }

    public void setSymbol(String newSymbol)
    {
        this.horseSymbol = newSymbol;
    }

    public void increaseConfidence(double amount) {
        if (this.horseConfidence + amount > 1.0) {
            this.horseConfidence = 1.0;
            System.out.println(this.getName() + " already has maximum confidence.");
        }
        else {
            this.horseConfidence += amount;
        }
        this.horseConfidence = roundUpToTwoDP(this.horseConfidence);
    }

    // Decrease confidence after falling
    public void decreaseConfidence(double amount) {
        if (this.horseConfidence - amount < 0.0) {
            this.horseConfidence = 0.0;
            System.out.println(this.getName() + " already has no confidence....");
        }
        else {
            this.horseConfidence -= amount;
        }
        this.horseConfidence = roundUpToTwoDP(this.horseConfidence);
    }

    private double roundUpToTwoDP(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}