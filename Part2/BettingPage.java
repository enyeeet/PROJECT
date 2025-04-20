package Part2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BettingPage extends JPanel {
    private HorseRaceGUI mainGUI;
    private ArrayList<HorsePerformance> performanceList;
    JPanel titlePanel;
    JPanel inputPanel;
    JLabel horseNameLabel;
    JLabel balanceLabel;
    private JComboBox<String> horseSelector;
    private JTextField amountField;
    private double currencyBalance = 1000.0;

    BettingPage(HorseRaceGUI mainGUI, ArrayList<HorsePerformance> performanceList) {
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

    private void createBettingSlip() {
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
        horseSelector.setPreferredSize(new Dimension(170, 30));
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
        amountField.setPreferredSize(new Dimension(100, 25));

        balanceLabel = new JLabel("Balance: $" + currencyBalance);
        balanceLabel.setFont(labelFont);


        ////////////////////////
        ///
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(horseName, gbc);

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
        inputPanel.add(balance, gbc);
        /////////////////////////

        add(inputPanel, BorderLayout.CENTER);
    }
}

