package Part2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DesignPage extends JPanel{

    private HorseRaceGUI mainGUI;
    JPanel titlePanel;
    JPanel inputPanel;
    JTextField laneInput;

    DesignPage(HorseRaceGUI mainGUI){

        this.mainGUI = mainGUI;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        createLanes();

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel bottomLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> mainGUI.showStartMenu());
        bottomLeftPanel.add(backButton);
        bottomPanel.add(bottomLeftPanel, BorderLayout.WEST);

        JPanel bottomRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton horseNextButton = new JButton("Next: Horses");
        bottomRightPanel.add(horseNextButton);
        bottomPanel.add(bottomRightPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void createLanes(){
        JLabel laneTitle = new JLabel("LANES");
        laneTitle.setFont(new Font(null, Font.BOLD, 30));

        titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setPreferredSize(new Dimension(200, 120));

        titlePanel.add(laneTitle);
        add(titlePanel, BorderLayout.NORTH);

        inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel laneLabel = new JLabel("Enter number of lanes:  ");
        laneLabel.setFont(new Font(null, Font.BOLD, 15));
        laneInput = new JTextField(5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(laneLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(laneInput, gbc);

        add(inputPanel, BorderLayout.CENTER);

        
    }
}
