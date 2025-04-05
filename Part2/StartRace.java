package Part2;

import javax.swing.*;

import java.awt.*;

public class StartRace extends JPanel{

    private HorseRaceGUI mainGUI;
    private RaceAnimation raceAnimation;
    JLabel raceDisplay;
    JButton backButton;
    JButton startRaceButton;

    private boolean isFirstRace = true;
    
    StartRace(HorseRaceGUI mainGUI){

        this.mainGUI = mainGUI;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        raceAnimation = new RaceAnimation(mainGUI, this);

        raceDisplay = new JLabel("Horses are ready to roll....");
        raceDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        raceDisplay.setFont(new Font("Monospaced", Font.PLAIN, 20));
        add(raceDisplay, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel bottomRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        startRaceButton = new JButton("Start Race");
        startRaceButton.addActionListener(e -> startRace());
        bottomRightPanel.add(startRaceButton, BorderLayout.SOUTH);
        bottomPanel.add(bottomRightPanel, BorderLayout.EAST);


        JPanel bottomLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));   //flowlayout assigns left by default
        backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> mainGUI.showStartMenu());
        bottomLeftPanel.add(backButton);
        bottomPanel.add(bottomLeftPanel, BorderLayout.WEST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void startRace(){

        if(isFirstRace){
            raceAnimation = new RaceAnimation(mainGUI,this);
            isFirstRace = false;
        }
        else {
            raceAnimation.resetRace();
        }

        startRaceButton.setEnabled(false);

        new Thread(() -> {
            raceAnimation.startRace(); // Pass StartRacePage for updating UI
            SwingUtilities.invokeLater(() -> startRaceButton.setEnabled(true)); // Re-enable after race
        }).start();
    }

    public void updateRaceDisplay(String raceText){
        raceDisplay.setText(raceText);
    }
}
