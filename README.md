DEPENDENCIES FOR BOTH MODULES
1. Java Development Kit (JDK) 23 is required. You can install the respective version depending on your operating system here: https://www.oracle.com/java/technologies/javase/jdk23-archive-downloads.html
   If using an IDE, make sure to configure your IDE accordingly if using an older version previously.
   However both modules are designed to work without the support of IDEs so only the command line is absolutely necessary.
2. Most importantly you need to know how to use the command line. This guide will only be for windows as this is the operating system I am using. In the search bar in start menu type in "cmd" and the command propmt option should appear. In the command line, navigate to where    your folder is located using "cd (file path)". For example if your folder is located in your desktop you would use "cd Desktop\HorseRaceSimulator". Once in the correct folder, you can start using java commands such as javac and java for compiling and running.
3. And of course for IDEs, make sure your IDE is configured to support Java. If not, download the required extension for your respective IDE.

   VS Code:
   - In the extensions page, search up "Extention Pack for Java". It should be the one by Microsoft.
   - Install the extension and restart your IDE if needed.
   
   Intellij:
   - No additional setup needed! It is made for Java anyways hence why I would recommend using this.
    

PART 1
1. Install the folder named "Part1" onto your computer. There should be a total of 2 java files.
2. If using an IDE, import the folder and open both java files. Run the Race class and the race should start.
3. If using command line, navigate to the directory where your files are downloaded, compile the java files into class files using this line: javac Horse.java Race.java
4. Then run the race by using: java Race
5. Note that this textual version is limited to a three lane/horse race.


PART 2
1. Install the folder named "Part2" onto your computer. There should be a total of 11 java files.
2. If using an IDE, import the folder and open the java file named "HorseRaceGUI".
3. Run the file. The application should start on your computer.
4. For command line, the setup is similar to that of Part1. Compile all the java files using javac (all 11 java files). Then run the main method by using: java HorseRaceGUI
5. Upon startup, you will see that the "Start Race" button is not clickable. You will have to first design your lanes and horses before you can start a race.
6. The "Statistics" and "Place Your Bets" button will also only work after you have run at least one race.
7. If you decide to add more lanes and horses later on, go back to the "Design Your Lanes & Horses" page and makes the changes accordingly. It will update in the race display and statistics page while retaining the data for your previous horses.
8. There will be hyperlinks (i.e underlined blue text) whenever there is a condition that will affect your horse attributes. Click on the hyperlink to learn more.
9. Have fun playing! I did what I could with java swing with how the UI looks (and also due to time constraints I can't be too detailed). There will be bugs here and there which I don't manage to fish out but it should not have a major impact on the functionality of the game.
