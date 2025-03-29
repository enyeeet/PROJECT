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
}
