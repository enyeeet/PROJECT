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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HorseDesignPage extends JPanel{

    private HorseRaceGUI mainGUI;
    private int noOfHorses;

    JTextField horseNameInput;
    JComboBox<String> breedInput;
    JComboBox<String> coatColourInput;
    JComboBox<String> symbolInput;
    JComboBox<String> saddleDesignInput;
    JComboBox<String> saddleColourInput;
    JComboBox<String> horseShoesInput;
    JComboBox<String> bridleInput;
    JComboBox<String> hatInput;

    Font labelFont = new Font("Calibri", Font.BOLD, 20);
    Font comboBoxFont = new Font(null, Font.PLAIN, 15);

    HorseDesignPage(HorseRaceGUI mainGUI, int noOfHorses){
        this.mainGUI = mainGUI;
        this.noOfHorses = noOfHorses;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        createHorse();
        addEquipment();

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
            int confirm = JOptionPane.showConfirmDialog(this, "Save settings and move to the next horse?", "Confirm Save", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                saveHorseSettings();
                if (noOfHorses < mainGUI.getTotalHorseDesignPages().size()) {
                    mainGUI.cardLayout.show(mainGUI.mainPanel, "HORSE DESIGN PAGE " + (noOfHorses + 1));
                }
            }
        });

        bottomRightPanel.add(nextButton);
        bottomPanel.add(bottomRightPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void createHorse(){
        JLabel horseTitle = new JLabel("Horses");
        horseTitle.setFont(new Font("Ink Free", Font.BOLD, 60));

        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setPreferredSize(new Dimension(200, 120));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(90, 0, 0, 0));

        titlePanel.add(horseTitle);
        add(titlePanel, BorderLayout.NORTH);

        JPanel leftInputPanel = new JPanel(new GridBagLayout());
        leftInputPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,15,15,15);

        JLabel sectionLabel = new JLabel("<html><u>Base Attributes<u><html>");
        sectionLabel.setFont(new Font(null, Font.PLAIN, 15));

        JLabel horseName = new JLabel("Name:");
        horseName.setFont(labelFont);
        horseNameInput = new JTextField(9);
        horseNameInput.setFont(new Font(null, Font.PLAIN, 15));
        horseNameInput.setText("Horse " + noOfHorses);

        JLabel horseBreed = new JLabel("Breed:");
        horseBreed.setFont(labelFont);
        breedInput = new JComboBox<>(new String[]{"Thoroughbred", "Arabian", "Quarter Horse"});
        breedInput.setPreferredSize(new Dimension(120, 25));
        breedInput.setFont(comboBoxFont);

        JLabel coatColour = new JLabel("Coat Colour:");
        coatColour.setFont(labelFont);
        coatColourInput = new JComboBox<>(new String[]{"Brown", "Black", "Grey", "White"});
        coatColourInput.setPreferredSize(new Dimension(120,25));
        coatColourInput.setFont(comboBoxFont);

        JLabel symbol = new JLabel("Symbol:");
        symbol.setFont(labelFont);
        symbolInput = new JComboBox<>(new String[]{"🏇", "🦄", "♞"});
        symbolInput.setPreferredSize(new Dimension(120, 25));
        symbolInput.setFont(new Font(null, Font.PLAIN, 20));

        JLabel breedAttributes = new JLabel("<html><u>Breed Attributes<u><html>");
        breedAttributes.setForeground(Color.BLUE);
        breedAttributes.setCursor(new Cursor(Cursor.HAND_CURSOR));

        breedAttributes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                JOptionPane.showMessageDialog(breedInput, """
                        Thoroughbred:  Speed ↓  Stamina ↑
                        Arabian:  Speed ↑  Stamina ↓ Horse confidence ↑
                        Quarter Horse:   Speed ↑

                        """, "Breed Attributes", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        ////////////////////
        /// 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        leftInputPanel.add(sectionLabel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        leftInputPanel.add(horseName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        leftInputPanel.add(horseNameInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        leftInputPanel.add(horseBreed, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        leftInputPanel.add(breedInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        leftInputPanel.add(coatColour, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        leftInputPanel.add(coatColourInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        leftInputPanel.add(symbol, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        leftInputPanel.add(symbolInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        leftInputPanel.add(breedAttributes, gbc);

        ////////////////////
        
        add(leftInputPanel, BorderLayout.WEST);
    }

    private void addEquipment(){
        
        JPanel rightInputPanel = new JPanel(new GridBagLayout());
        rightInputPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,15,15,15);

        JLabel saddleTypeLabel = new JLabel("<html><u>Design<u><html>");
        saddleTypeLabel.setFont(new Font(null, Font.PLAIN, 15));
        JLabel saddleColourLabel = new JLabel("<html><u>Colour<u><html>");
        saddleColourLabel.setFont(new Font(null, Font.PLAIN, 15));
        
        JLabel saddle = new JLabel("Saddle:");
        saddle.setFont(labelFont);
        saddleDesignInput = new JComboBox<>(new String[]{"Dressage", "Jumping", "All-Purpose", "Endurance", "Racing", "Polo"});
        saddleDesignInput.setFont(comboBoxFont);
        saddleDesignInput.setPreferredSize(new Dimension(120,25));
        
        saddleColourInput = new JComboBox<>(new String[]{"Black", "White", "Pink", "Brown"});
        saddleColourInput.setFont(comboBoxFont);
        saddleColourInput.setPreferredSize(new Dimension(80,24));
        

        JLabel horseShoes = new JLabel("Horseshoes:");
        horseShoes.setFont(labelFont);
        horseShoesInput = new JComboBox<>(new String[]{"Regular", "Aluminum", "Steel", "Stick-On"});
        horseShoesInput.setFont(comboBoxFont);
        horseShoesInput.setPreferredSize(new Dimension(120,25));

        JLabel bridle = new JLabel("Bridle:");
        bridle.setFont(labelFont);
        bridleInput = new JComboBox<>(new String[]{"Snaffle", "Double", "Bitless"});
        bridleInput.setFont(comboBoxFont);
        bridleInput.setPreferredSize(new Dimension(120,25));

        JLabel hat = new JLabel("Hat:");
        hat.setFont(labelFont);
        hatInput = new JComboBox<>(new String[]{"Top Hat 🎩", "Cowboy Hat", "Straw Hat 👒", "Sombrero"});
        hatInput.setFont(comboBoxFont);
        hatInput.setPreferredSize(new Dimension(120,25));

        JLabel equipmentEffects = new JLabel("<html><u>Horseshoe Effects<u><html>");
        equipmentEffects.setForeground(Color.BLUE);
        equipmentEffects.setCursor(new Cursor(Cursor.HAND_CURSOR));

        equipmentEffects.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                JOptionPane.showMessageDialog(horseShoesInput, """
                        Regular:  *No effect on original attributes*
                        Aluminum:  Speed ↑  Risk of falling ↑
                        Steel:   Stamina ↓ Risk of falling ↓
                        Stick-On: Stamina ↑  Horse confidence ↑

                        """, "Horseshoe Effects", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        ///////////////////
        /// 
        gbc.gridx = 1;
        gbc.gridy = 0;
        rightInputPanel.add(saddleTypeLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        rightInputPanel.add(saddleColourLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        rightInputPanel.add(saddle, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        rightInputPanel.add(saddleDesignInput, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        rightInputPanel.add(saddleColourInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        rightInputPanel.add(horseShoes,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        rightInputPanel.add(horseShoesInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        rightInputPanel.add(bridle, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        rightInputPanel.add(bridleInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        rightInputPanel.add(hat, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        rightInputPanel.add(hatInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        rightInputPanel.add(equipmentEffects, gbc);
        //////////////////////

        add(rightInputPanel, BorderLayout.EAST);
    }

    private void saveHorseSettings() {
        String horseName = horseNameInput.getText();
        if(horseName == null){
            System.out.println("Name field cannot be empty.");
            return;
        }
        String breed = (String) breedInput.getSelectedItem();
        String coatColor = (String) coatColourInput.getSelectedItem();
        String symbol = (String) symbolInput.getSelectedItem();
        String saddleDesign = (String) saddleDesignInput.getSelectedItem();
        String saddleColor = (String) saddleColourInput.getSelectedItem();
        String horseShoes = (String) horseShoesInput.getSelectedItem();
        String bridle = (String) bridleInput.getSelectedItem();
        String hat = (String) hatInput.getSelectedItem();
    
        HorseData horseData = new HorseData(horseName, breed, coatColor, symbol, saddleDesign, saddleColor, horseShoes, bridle, hat);
        mainGUI.saveHorseSettings(horseData, noOfHorses);
    }
}
