import java.util.Scanner;

/**
 * Write a description of class Horse here.
 * 
 * @author Lee En Yee 
 * @version 1
 */
public class Horse
{
    //Fields of class Horse
    private char horseSymbol;
    private String horseName;
    private double horseConfidence;
    private boolean fallen;
    private int distanceTravelled;
    

    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
       this.horseSymbol = horseSymbol;
       this.horseName = horseName;
       setConfidence(horseConfidence);
       this.fallen = false;
       this.distanceTravelled = 0;
    }
    
    //Other methods of class Horse
    public void fall()
    {
        this.fallen = true;
        decreaseConfidence(0.05);
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
    
    public char getSymbol()
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
    
    public void setSymbol(char newSymbol)
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
    }
}
