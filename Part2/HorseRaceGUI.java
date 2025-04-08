package Part2;

import java.util.ArrayList;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class HorseRaceGUI {

    private StartRace racePage;

    JFrame frame;
    JPanel titlePanel;
    JPanel mainPanel;
    JPanel startMenu;
    JPanel buttonPanel;
    JLabel gameTitle;
    JButton startRaceButton;
    JButton designButton;
    JButton betButton;
    JButton statsButton;
    CardLayout cardLayout;
    private int noOfLanes;
    private int trackLength;
    private String trackLengthUnit;
    private String trackShape;
    private String trackCondition;
    private boolean lanesAndHorsesDesigned = false;

    private ArrayList<HorseDesignPage> totalHorseDesignPages;
    private ArrayList<HorseData> horseDataList;
    private ArrayList<HorsePerformance> horsePerformanceList = new ArrayList<>();

    HorseRaceGUI(){
        frame = new JFrame("Horse Racing Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 650);
        frame.setResizable(false);
        
        cardLayout = new CardLayout();   //switching between 'pages'
        mainPanel = new JPanel();
        mainPanel.setLayout(cardLayout);

        totalHorseDesignPages = new ArrayList<>();
        horseDataList = new ArrayList<>();

        createStartMenu();

        mainPanel.add(startMenu, "START MENU");

        racePage = new StartRace(this);
        mainPanel.add(racePage, "RACE PAGE");

        mainPanel.add(new LaneDesignPage(this), "LANE DESIGN PAGE");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void createStartMenu(){
        gameTitle = new JLabel("Horse Racing", SwingConstants.CENTER);
        gameTitle.setFont(new Font("Ink Free", Font.BOLD, 60));
        
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setPreferredSize(new Dimension(200, 140));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(90, 0, 0, 0));

        titlePanel.add(gameTitle);
    

        startMenu = new JPanel();
        startMenu.setLayout(new BorderLayout());
        startMenu.setBackground(Color.WHITE);
        startMenu.setPreferredSize(new Dimension(50, 450));

        ///////////BUTTONS

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 15, 15));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(90, 240, 90, 240));

        Font buttonFont = new Font("Arial", Font.BOLD, 15);

        startRaceButton = new JButton("Start A Race");
        startRaceButton.setFocusable(false);
        startRaceButton.setFont(buttonFont);
        startRaceButton.setEnabled(false);

        designButton = new JButton("Design Your Lanes & Horses");
        designButton.setFocusable(false);
        designButton.setFont(buttonFont);
        
        betButton = new JButton("Place Your Bets");
        betButton.setFocusable(false);
        betButton.setFont(buttonFont);

        statsButton = new JButton("Statistics");
        statsButton.setFocusable(false);
        statsButton.setFont(buttonFont);
        statsButton.addActionListener(e -> {
            if (horsePerformanceList != null && !horsePerformanceList.isEmpty()) {
                statsButton.setEnabled(false);  // Disable the button while the stats page is open

                // Open StatisticsPage if horsePerformanceList is populated
                JFrame statsFrame = new StatisticsPage(horsePerformanceList);
                statsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                statsFrame.setVisible(true);

                // Re-enable the stats button when the stats page is closed
                statsFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        statsButton.setEnabled(true);
                    }
                });
            } else {
                // Display a message if the list is empty
                JOptionPane.showMessageDialog(betButton, "No horse data available for statistics.");
            }
        });

        buttonPanel.add(startRaceButton);
        buttonPanel.add(designButton);
        buttonPanel.add(betButton);
        buttonPanel.add(statsButton);

        startRaceButton.addActionListener(e -> cardLayout.show(mainPanel, "RACE PAGE"));
        designButton.addActionListener(e -> cardLayout.show(mainPanel, "LANE DESIGN PAGE"));
        
        ////////////////////////////////
        
        startMenu.add(titlePanel, BorderLayout.NORTH);
        startMenu.add(buttonPanel, BorderLayout.CENTER);
    }

    public void saveLaneSettings(int noOfLanes, int trackLength, String trackLengthUnit, String trackShape, String trackCondition){
        this.noOfLanes = noOfLanes;
        this.trackLength = trackLength;
        this.trackLengthUnit = trackLengthUnit;
        this.trackShape = trackShape;
        this.trackCondition = trackCondition;

        createHorseDesignPage();
    }

    public void createHorseDesignPage(){
        int currentHorseCount = totalHorseDesignPages.size();

        if (currentHorseCount < noOfLanes){
            for (int i = currentHorseCount + 1; i <= noOfLanes; i++){
                HorseDesignPage horseDesignPage = new HorseDesignPage(this, i);
                totalHorseDesignPages.add(horseDesignPage);
                mainPanel.add(horseDesignPage, "HORSE DESIGN PAGE " + i);
            }
        }
    }

    public void saveHorseSettings(HorseData horseData, int index) {
        if (index - 1 < horseDataList.size()){
            horseDataList.set(index - 1, horseData);
        }
        else{
            horseDataList.add(horseData);
        }

        lanesAndHorsesDesigned = true;
        startRaceButton.setEnabled(true);

        if(racePage != null){
            racePage.refreshRace();
        }
    }

    public HorseData getHorseData(int index){
        if(index >= 1 && index <= horseDataList.size()){
            return horseDataList.get(index - 1);
        }
        return null;
    }

    public ArrayList<HorsePerformance> getHorsePerformanceList() {
        return horsePerformanceList;
    }

    public void setHorsePerformanceList(ArrayList<HorsePerformance> list) {
        this.horsePerformanceList = list;
    }

    public void loadHorseData(ArrayList<HorseData> dataList) {
        this.horseDataList = dataList;

        horsePerformanceList.clear();
        for (HorseData data : dataList) {
            horsePerformanceList.add(new HorsePerformance(data, this));
        }
    }

    public String getTrackShape(){
        return trackShape;
    }

    public String getTrackCondition(){
        return trackCondition;
    }

    public int getTrackLength(){ return trackLength; }
    
    public ArrayList<HorseData> getHorseDataList() {
        return horseDataList;
    }

    public ArrayList<HorseDesignPage> getTotalHorseDesignPages() {
        return totalHorseDesignPages;
    }
    
    public void showStartMenu(){
        cardLayout.show(mainPanel, "START MENU");
    }

    public void showHorseDesignPage(){
        cardLayout.show(mainPanel, "HORSE DESIGN PAGE 1");
    }

    public void showLaneDesignPage(){
        cardLayout.show(mainPanel, "LANE DESIGN PAGE");
    }
}
