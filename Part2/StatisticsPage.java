package Part2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class StatisticsPage extends JFrame{
    private HorseRaceGUI mainGUI;
    private JComboBox <String> horseSelector;
    private JTable statsTable;
    private DefaultTableModel tableModel;

    private ArrayList<HorsePerformance> performanceList;
    private HorseData horseData;

    StatisticsPage(ArrayList<HorsePerformance> performanceList, HorseRaceGUI mainGUI) {
        super("Horse and Race Statistics");
        this.performanceList = performanceList;
        this.mainGUI = mainGUI;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setResizable(false);

        createStatsMenu();

        setVisible(true);
    }

    public void createStatsMenu(){
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        horseSelector = new JComboBox<>();

        for(HorsePerformance hp : performanceList){
            horseSelector.addItem(hp.getHorseName());
        }

        horseSelector.addActionListener(this::updateStatsTable);
        topPanel.add(new JLabel("Select Horse:"));
        topPanel.add(horseSelector);
        add(topPanel, BorderLayout.NORTH);

        // Stats Table Setup
        String[] columnNames = { "Race #", "Winner?", "Average Speed", "Finishing Time", "Win Ratio", "Confidence", "Track Shape", "Track Condition"};
        tableModel = new DefaultTableModel(columnNames, 0);
        statsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(statsTable);
        add(scrollPane, BorderLayout.CENTER);

        // Initially populate stats
        if (!performanceList.isEmpty()) {
            horseSelector.setSelectedIndex(0);
            updateStatsTable(null);
        }

    }

    private void updateStatsTable(ActionEvent e) {
        String selectedIndex = (String) horseSelector.getSelectedItem();
        if (selectedIndex != null) {
            HorsePerformance selectedHorse = mainGUI.getHorsePerformanceMap().get(selectedIndex);
            tableModel.setRowCount(0);

            ArrayList<RaceResult> raceHistory = selectedHorse.getRaceHistory();

            for (int i = 0; i < raceHistory.size(); i++) {
                RaceResult result = raceHistory.get(i);

                double avgSpeed = mainGUI.getTrackLength() / result.getFinishTime();
                if(result.getFinishTime() == 0){
                    avgSpeed = 0.0;
                }
                String speedStr = result.didNotFinish()? "DNF" : String.format("%.2f", avgSpeed);
                String timeStr = result.didNotFinish()? "DNF" : String.format("%.2f", result.getFinishTime());

                tableModel.addRow(new Object[]{
                        i + 1,  // Race number
                        result.isWinner() ? "Yes" : "No",
                        speedStr,
                        timeStr,
                        String.format("%.2f", selectedHorse.getWinRatio()),
                        String.format("%.2f", result.getInitialConfidence()),
                        result.getTrackShape(),
                        result.getTrackCondition()
                });
            }
        }
    }
}