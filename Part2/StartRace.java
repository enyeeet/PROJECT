package Part2;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;

public class StartRace extends JPanel{

    private HorseRaceGUI mainGUI;

    StartRace(HorseRaceGUI mainGUI){

        this.mainGUI = mainGUI;

        setLayout(new BorderLayout());

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> mainGUI.showStartMenu());
        add(backButton, BorderLayout.SOUTH);

    }
}
