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

public class LaneDesignPage extends JPanel{

    private HorseRaceGUI mainGUI;
    JPanel titlePanel;
    JPanel inputPanel;
    JTextField laneInput;
    JTextField lengthInput;
    JButton designYourOwn;
    JComboBox<String> lengthUnit;
    JComboBox<String> shapeInput;
    JComboBox<String> trackConditions;
    private int noOfLanes;
    private int trackLength;
    private String trackLengthUnit;
    private String trackShape;
    private String trackCondition;

    LaneDesignPage(HorseRaceGUI mainGUI){

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
        JButton saveButton = new JButton("Save & Continue");
        saveButton.addActionListener(e -> saveSelections());
        bottomRightPanel.add(saveButton);
        bottomPanel.add(bottomRightPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void createLanes(){

        //Panel for title
        //
        JLabel laneTitle = new JLabel("Step 1: Lanes");
        laneTitle.setFont(new Font(null, Font.ITALIC, 30));

        titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setBackground(Color.LIGHT_GRAY);
        titlePanel.setPreferredSize(new Dimension(200, 120));

        titlePanel.add(laneTitle);
        add(titlePanel, BorderLayout.NORTH);

        
        //Panel for all inputs
        //
        inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        Font labelFont = new Font("Calibri", Font.BOLD, 20);

        //Number of lanes
        //
        JLabel laneLabel = new JLabel("Number of lanes:  ");
        laneLabel.setFont(labelFont);
        laneInput = new JTextField(8);
        laneInput.setFont(new Font("Arial", Font.PLAIN, 15));
        laneInput.setHorizontalAlignment(JTextField.CENTER);
        laneInput.setPreferredSize(new Dimension(100,25));


        //Length of track
        //
        JLabel lengthLabel = new JLabel("Length of track:   ");
        lengthLabel.setFont(labelFont);
        lengthInput = new JTextField(8);
        lengthInput.setFont(new Font("Arial", Font.PLAIN, 15));
        lengthInput.setHorizontalAlignment(JTextField.CENTER);
        lengthInput.setPreferredSize(new Dimension(100,25));
        lengthUnit = new JComboBox<>(new String[]{"m", "ft"});
        lengthUnit.setPreferredSize(new Dimension(50,25));


        //Shape of track
        //
        JLabel shapeLabel = new JLabel("Shape of track:   ");
        shapeLabel.setFont(labelFont);
        shapeInput = new JComboBox<>(new String[]{"Oval", "Figure Eight"});
        shapeInput.setPreferredSize(new Dimension(110,25));
        JLabel orLabel = new JLabel("OR");
        orLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        designYourOwn = new JButton("Design Your Own");



        //Track conditions
        //
        JLabel trackConditionsLabel = new JLabel("Track conditions:   ");
        trackConditionsLabel.setFont(labelFont);
        trackConditions = new JComboBox<>(new String[]{"Muddy", "Dry", "Icy"});
        trackConditions.setPreferredSize(new Dimension(110,25));

        JLabel trackConditionEffects = new JLabel("<html><u>How does this affect my horse?<u><html>");
        trackConditionEffects.setForeground(Color.BLUE);
        trackConditionEffects.setCursor(new Cursor(Cursor.HAND_CURSOR));

        trackConditionEffects.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                JOptionPane.showMessageDialog(shapeInput, """
                        Muddy:  Speed ↓
                        Icy:  Risk of falling ↑  Horse confidence ↓
                        Dry:  Speed ↑

                        """, "Effects Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        

        //////////POSITIONING
        /// 
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(laneLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(laneInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(lengthLabel,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(lengthInput,gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        inputPanel.add(lengthUnit,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(shapeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(shapeInput, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        inputPanel.add(orLabel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        inputPanel.add(designYourOwn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(trackConditionsLabel,gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        inputPanel.add(trackConditions,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(trackConditionEffects, gbc);
        ///
        ///////////////////////////////////////

        add(inputPanel, BorderLayout.CENTER);
    }
        

    private void saveSelections(){

        if (laneInput.getText().trim().isEmpty() || 
        lengthInput.getText().trim().isEmpty() || 
        shapeInput.getSelectedItem() == null || 
        trackConditions.getSelectedItem() == null) {
        
        JOptionPane.showMessageDialog(this, "Please fill in all fields before saving.", "Incomplete Fields", JOptionPane.WARNING_MESSAGE);
        return; 
        }

        try{
            noOfLanes = Integer.parseInt(laneInput.getText().trim());
            trackLength = Integer.parseInt(lengthInput.getText().trim());
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Please enter integer values for lanes and length.", "Input Error", JOptionPane.ERROR_MESSAGE);

            return;
        }

        trackLengthUnit = (String) lengthUnit.getSelectedItem();
        trackShape = (String) shapeInput.getSelectedItem();
        trackCondition = (String) trackConditions.getSelectedItem();

        int choice = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to save these settings?\n"
            + "Lanes: " + noOfLanes + "\n"
            + "Track Length: " + trackLength + " " + trackLengthUnit + "\n"
            + "Track Shape: " + trackShape + "\n"
            + "Track Condition: " + trackCondition,
            "Confirm Selection", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Selections Saved Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE); 
            mainGUI.showHorseDesignPage();                                                       
        }
    }
}
