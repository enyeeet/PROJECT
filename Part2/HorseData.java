package Part2;

public class HorseData {
    private String name;
    private String breed;
    private String coatColor;
    private String symbol;
    private String saddleDesign;
    private String saddleColor;
    private String horseShoes;
    private String bridle;
    private String hat;

    private double horseConfidence;
    private double horseSpeed;
    private double horseStamina;

    private String trackCondition;
    private String trackShape;

    HorseData(String name, String breed, String coatColor, String symbol, String saddleDesign, String saddleColor,
                     String horseShoes, String bridle, String hat) {
        this.name = name;
        this.breed = breed;
        this.coatColor = coatColor;
        this.symbol = symbol;
        this.saddleDesign = saddleDesign;
        this.saddleColor = saddleColor;
        this.horseShoes = horseShoes;
        this.bridle = bridle;
        this.hat = hat;

        switch (this.breed) {
            case "Thoroughbred" -> {
                this.horseSpeed = 0.5;
                this.horseStamina = 0.6;
                this.horseConfidence = 0.3;
            }
            case "Arabian" -> {
                this.horseSpeed = 0.7;
                this.horseStamina = 0.5;
                this.horseConfidence = 0.5;
            }
            case "Quarter Horse" -> {
                this.horseSpeed = 0.4;
                this.horseStamina = 0.6;
                this.horseConfidence = 0.4;
            }
        }
    }

    public void finaliseHorseStats(){
        trackConditionEffect();
        trackShapeEffect();
        horseShoeEffect();
    }

    public void trackConditionEffect(){
        switch(trackCondition){
            case "Muddy" -> {
                this.horseSpeed -= 0.2;
                this.horseStamina -= 0.2;
            }
            case "Icy" -> {
                this.horseConfidence -= 0.3;
            }
            case "Dry" -> {
                this.horseSpeed += 0.2;
                this.horseConfidence += 0.2;
            }
        }
    }

    public void trackShapeEffect(){
        if(trackShape.equals("Oval")){
            this.horseSpeed += 0.1;
            this.horseConfidence += 0.1;
        }
        else if(trackShape.equals("Figure Eight")){
            this.horseSpeed += 0.2;
            this.horseConfidence += 0.2;
        }
    }

    public void horseShoeEffect(){
        switch(horseShoes){
            case "Aluminum" -> {
                this.horseSpeed += 0.2;
                this.horseConfidence += 0.2;
            }
            case "Steel" -> {
                this.horseStamina -= 0.2;
                this.horseConfidence -= 0.1;
            }
            case "Stick-On" -> {
                this.horseStamina += 0.3;
                this.horseConfidence -= 0.2;
            }
        }
    }

    public void setTrackCondition(String trackCondition) {
        this.trackCondition = trackCondition;
    }

    public void setTrackShape(String trackShape) {
        this.trackShape = trackShape;
    }

    public void setConfidence(double newConfidence)
    {
        if(newConfidence < 0.0 || newConfidence > 1.0){
            System.out.println("Confidence rating must be between 0.0 and 1.0.");
        }

        this.horseConfidence = newConfidence;
    }

    // Getters
    public String getName() { return name; }
    public String getBreed() { return breed; }
    public String getCoatColor() { return coatColor; }
    public String getSymbol() { return symbol; }
    public String getSaddleDesign() { return saddleDesign; }
    public String getSaddleColor() { return saddleColor; }
    public String getHorseShoes() { return horseShoes; }
    public String getBridle() { return bridle; }
    public String getHat() { return hat; }
    public double getHorseConfidence() { return horseConfidence; }
    public double getHorseSpeed() { return horseSpeed; }
    public double getHorseStamina() { return horseStamina; }

}
