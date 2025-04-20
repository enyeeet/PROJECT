package Part2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BettingPage extends JPanel{
    private HorseRaceGUI mainGUI;
    private ArrayList<HorsePerformance> performanceList;
    JPanel titlePanel;
    JPanel inputPanel;
    JLabel horseNameLabel;
    JLabel balanceLabel;
    private JComboBox<String> horseSelector;
    private JTextField amountField;
    private double currencyBalance = 1000.0;

    BettingPage(HorseRaceGUI mainGUI, ArrayList<HorsePerformance> performanceList){
        this.mainGUI = mainGUI;
        this.performanceList = performanceList;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        createBettingSlip();

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel bottomLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> mainGUI.showStartMenu());
        bottomLeftPanel.add(backButton);

        JPanel bottomRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Place Bet");
        saveButton.addActionListener(e -> processBet());
        bottomRightPanel.add(saveButton);
        bottomPanel.add(bottomRightPanel, BorderLayout.EAST);

        bottomPanel.add(bottomLeftPanel, BorderLayout.WEST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void createBettingSlip(){
        JLabel title = new JLabel("Your Betting Slip");
        title.setFont(new Font("Ink Free", Font.BOLD, 60));

        titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setPreferredSize(new Dimension(250, 120));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(90, 0, 0, 0));

        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);

        inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        Font labelFont = new Font("Calibri", Font.BOLD, 30);

        horseNameLabel = new JLabel("Horse:");
        horseNameLabel.setFont(labelFont);

        horseSelector = new JComboBox<>();
        horseSelector.setPreferredSize(new Dimension(170,30));
        horseSelector.setFont(new Font(null, Font.PLAIN, 15));
        for (HorsePerformance hp : performanceList) {
            String odds = calculateOdds(hp);
            horseSelector.addItem(hp.getHorseName() + " (Odds: " + odds + ")");
        }

        JLabel amount = new JLabel("Amount:");
        amount.setFont(labelFont);

        amountField = new JTextField(10);
        amountField.setFont(new Font("Arial", Font.PLAIN, 15));
        amountField.setHorizontalAlignment(JTextField.CENTER);
        amountField.setPreferredSize(new Dimension(100,25));

        balanceLabel = new JLabel("Balance: $" + currencyBalance);
        balanceLabel.setFont(labelFont);


        ////////////////////////
        ///
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(horseNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(horseSelector, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(amount, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(balanceLabel, gbc);
        /////////////////////////

        add(inputPanel, BorderLayout.CENTER);
    }



    public int winRatioRating (HorsePerformance hp){
        double winRatio = hp.getWinRatio();
        int winRatioPoints;

        if(winRatio >= 0.0 && winRatio < 0.3){
            winRatioPoints = 1;
        }
        else if(winRatio > 0.3 && winRatio < 0.5){
            winRatioPoints = 2;
        }
        else if(winRatio > 0.5 && winRatio < 0.7){
            winRatioPoints = 3;
        }
        else if(winRatio > 0.7 && winRatio < 0.8){
            winRatioPoints = 4;
        }
        else if(winRatio > 0.8 && winRatio <= 1.0){
            winRatioPoints = 5;
        }
        else{
            System.out.println("Invalid win ratio.");
            return 0;
        }
        return winRatioPoints;
    }

    public int avgSpeedRating (HorsePerformance selectedHorse){
        ArrayList<RaceResult> raceHistory = selectedHorse.getRaceHistory();
        int avgSpeedPoints = 0;
        for(int i = 0; i < raceHistory.size(); i++){
            RaceResult result = raceHistory.get(i);

            double avgSpeed = mainGUI.getTrackLength() / result.getFinishTime();
            if(result.getFinishTime() == 0){
                avgSpeedPoints = -1;
            }
            else if(avgSpeed < 30){
                avgSpeedPoints =  1;
            }
            else if(avgSpeed < 35){
                avgSpeedPoints = 2;
            }
            else if(avgSpeed < 40){
                avgSpeedPoints = 3;
            }
            else if(avgSpeed < 45){
                avgSpeedPoints = 4;
            }
            else if(avgSpeed > 45){
                avgSpeedPoints = 5;
            }
        }
        return avgSpeedPoints;
    }

    public int finishTimeRating(HorsePerformance selectedHorse) {
        ArrayList<RaceResult> raceHistory = selectedHorse.getRaceHistory();
        if (raceHistory.isEmpty()) return 0;

        double totalTime = 0;
        int validRaces = 0;
        for (RaceResult result : raceHistory) {
            if (!result.didNotFinish()) {
                totalTime += result.getFinishTime();
                validRaces++;
            }
        }

        if (validRaces == 0) return 0;
        double avgFinishTime = totalTime / validRaces;

        if (avgFinishTime > 90) return 1;
        else if (avgFinishTime > 70) return 2;
        else if (avgFinishTime > 50) return 3;
        else if (avgFinishTime > 30) return 4;
        else return 5;
    }

    public String calculateOdds(HorsePerformance hp) {
        int winRatioPts = winRatioRating(hp);
        int avgSpeedPts = avgSpeedRating(hp);
        int finishTimePts = finishTimeRating(hp);

        int basePerformance = winRatioPts + avgSpeedPts + finishTimePts;

        double trackAdjustment = 0.0;
        switch(mainGUI.getTrackCondition()){
            case "Muddy" -> trackAdjustment = 0.5;
            case "Dry" -> trackAdjustment = 1.0;
            case "Icy" -> trackAdjustment = 0.7;
        }

        double rawOdds = (basePerformance * trackAdjustment) / 15.0;

        return String.format("%.2f", rawOdds);
    }
}

