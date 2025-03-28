package Part2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class HorseDesignPage extends JPanel{

    private HorseRaceGUI mainGUI;
    private int noOfHorses;


    JPanel titlePanel;
    JPanel inputPanel;

    HorseDesignPage(HorseRaceGUI mainGUI, int noOfHorses){
        this.mainGUI = mainGUI;
        this.noOfHorses = noOfHorses;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        createHorse();

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel bottomLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            if(noOfHorses > 1){
                mainGUI.cardLayout.show(mainGUI.mainPanel, "HORSE DESIGN PAGE " + (noOfHorses - 1));
            }
            else {
                // If on the first page, go back to the "LANE DESIGN PAGE"
                int cfmGoBack = JOptionPane.showConfirmDialog(this, "Go back to lane design page?", "Confirm Action", JOptionPane.YES_NO_OPTION);
                if(cfmGoBack == JOptionPane.YES_OPTION){
                    mainGUI.cardLayout.show(mainGUI.mainPanel, "LANE DESIGN PAGE");
                }
            }
        });
        bottomLeftPanel.add(backButton);
        bottomPanel.add(bottomLeftPanel, BorderLayout.WEST);

        JPanel bottomRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            if (noOfHorses < mainGUI.getTotalHorseDesignPages().size()) {
                mainGUI.cardLayout.show(mainGUI.mainPanel, "HORSE DESIGN PAGE " + (noOfHorses + 1));
            }
        });

        bottomRightPanel.add(nextButton);
        bottomPanel.add(bottomRightPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void createHorse(){
        JLabel horseTitle = new JLabel("Step 2: Horses");
        horseTitle.setFont(new Font(null, Font.ITALIC, 30));

        titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setBackground(Color.LIGHT_GRAY);
        titlePanel.setPreferredSize(new Dimension(200, 120));

        titlePanel.add(horseTitle);
        add(titlePanel, BorderLayout.NORTH);

        inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,15,15,15);

        Font labelFont = new Font("Calibri", Font.BOLD, 20);

        JLabel horseName = new JLabel("Horse Name:   ");
        horseName.setFont(labelFont);
        JTextField horseNameInput = new JTextField(8);
        horseNameInput.setFont(new Font("Arial", Font.PLAIN, 15));
        horseNameInput.setText("Horse " + noOfHorses);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(horseName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(horseNameInput);

        add(inputPanel, BorderLayout.CENTER);
    }
}
