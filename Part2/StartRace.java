package Part2;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class StartRace extends JPanel{

    private HorseRaceGUI mainGUI;
    
    StartRace(HorseRaceGUI mainGUI){

        this.mainGUI = mainGUI;
    
        setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));   //flowlayout assigns left by default
        JButton backButton = new JButton("Back to Menu");

        backButton.addActionListener(e -> mainGUI.showStartMenu());
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);              
    }
}
