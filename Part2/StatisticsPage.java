package Part2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class StatisticsPage extends JFrame{
    private JComboBox <String> horseSelector;
    private JTable statsTable;
    private DefaultTableModel tableModel;

    private ArrayList<HorsePerformance> performanceList;
    private HorseData horseData;

    StatisticsPage(ArrayList<HorsePerformance> performanceList){
        super("Horse and Race Statistics");
        this.performanceList = performanceList;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
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
        String[] columnNames = { "Average Speed", "Finishing Time", "Win Ratio", "Confidence" };
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
        int selectedIndex = horseSelector.getSelectedIndex();
        if (selectedIndex >= 0) {
            HorsePerformance hp = performanceList.get(selectedIndex);

            tableModel.setRowCount(0); // clear previous
            tableModel.addRow(new Object[] {
                    String.format("%.2f", hp.getAvgSpeed()),
                    String.format("%.2f", hp.getFinishingTime()),
                    String.format("%.2f", hp.getWinRatio()),
                    String.format("%.2f", hp.getHorseConfidence())
            });
        }
    }
}
