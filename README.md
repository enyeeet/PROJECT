DEPENDENCIES FOR BOTH MODULES
1. Java Development Kit (JDK) 23 is required. You can install the respective version depending on your operating system here: https://www.oracle.com/java/technologies/javase/jdk23-archive-downloads.html
   Make sure to configure your IDE accordingly if using an older version previously.
2. And of course, make sure your IDE is configured to support Java. If not, download the required extension for your respective IDE.
   VS Code:
   - In the extensions page, search up "Extention Pack for Java". It should be the one by Microsoft.
   - Install the extension and restart your IDE if needed.
   
   Intellij:
   - No additional setup needed! It is made for Java anyways hence why I would recommend using this.
    

PART 1
1. Install the folder named "Part1" onto your computer. There should be 2 files contained in it. (Horse.java and Race.java)
2. If using an IDE, import the folder and open both java files. Create a main method (either within one of the provided classes or a separate class) with your desired horse attributes like so:
   
   public class Main {
    public static void main(String[] args) {
        Race horseRace = new Race(20);    // Create the Race object with a track length of 20 units
        horseRace.addHorse(new Horse('A', "Thunder", 0.5), 1);
        horseRace.addHorse(new Horse('B', "Lightning", 0.4), 2);
        horseRace.addHorse(new Horse('C', "Blaze", 0.6), 3);
        horseRace.startRace();
      }
  }
  
3. If using command line, navigate to the directory where your files are downloaded, compile the java files into class files using this line: javac Horse.java Race.java Main.java
4. Then, run the main method using: java Main
5. Note that this textual version is limited to a three lane/horse race.


PART 2
1. Install the folder named Part2 onto your computer. There should be a total of 12 java files.
2. Import the folder into an IDE, and open the java file named "HorseRaceGUI".
3. Run the file. The application should start on your computer.
4. Upon startup, you will see that the "Start Race" button is not clickable. You will have to first design your lanes and horses before you can start a race.
5. The "Statistics" and "Place Your Bets" button will also only work after you have run at least one race.
6. If you decide to add more lanes and horses later on, go back to the "Design Your Lanes & Horses" page and makes the changes accordingly. It will update in the race display and statistics page while retaining the data for your previous horses.
7. There will be hyperlinks (i.e underlined blue text) whenever there is a condition that will affect your horse attributes. Click on the hyperlink to learn more.
8. Have fun playing! I did what I could with java swing with how the UI looks (and also due to time constraints I can't be too detailed). There will be bugs here and there which I don't manage to fish out but it should not have a major impact on the functionality of the game.
