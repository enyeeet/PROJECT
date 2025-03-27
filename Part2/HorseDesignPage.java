package Part2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HorseDesignPage extends JPanel{

    private HorseRaceGUI mainGUI;

    HorseDesignPage(HorseRaceGUI mainGUI){
        this.mainGUI = mainGUI;
        setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> mainGUI.showDesignPage());
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
