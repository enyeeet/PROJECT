package Part2;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

public class HorseRaceGUI {

    JFrame frame;
    JPanel title;
    JPanel startMenu;
    JLabel gameTitle;
    JButton startRaceButton;
    JButton designButton;
    JButton betButton;
    JButton statsButton;

    HorseRaceGUI(){
        frame = new JFrame("Horse Racing Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(600, 600);
        
        gameTitle = new JLabel("TITLE", SwingConstants.CENTER);
        gameTitle.setFont(new Font(null, Font.BOLD, 40));
        
        title = new JPanel();
        title.setLayout(new GridBagLayout());
        title.setBackground(Color.GRAY);
        title.setPreferredSize(new Dimension(200, 120));
        

        //Buttons

        startMenu = new JPanel();
        startMenu.setLayout(null);
        startMenu.setBackground(Color.WHITE);
        startMenu.setPreferredSize(new Dimension(50, 450));

        startRaceButton = new JButton();
        startRaceButton.setBounds(200,70,200,70);
        startRaceButton.setFocusable(false);
        startRaceButton.setText("Start A Race");

        designButton = new JButton();
        designButton.setBounds(200, 150, 200, 70);
        designButton.setFocusable(false);
        designButton.setText("Design Your Lanes & Horses");

        betButton = new JButton();
        betButton.setBounds(200, 230, 200, 70);
        betButton.setFocusable(false);
        betButton.setText("Place Your Bets");

        statsButton = new JButton();
        statsButton.setBounds(200, 310, 200, 70);
        statsButton.setFocusable(false);
        statsButton.setText("Statistics");

        //////////////////////

        title.add(gameTitle);

        startMenu.add(startRaceButton);
        startMenu.add(designButton);
        startMenu.add(betButton);
        startMenu.add(statsButton);

        frame.add(title, BorderLayout.NORTH);
        frame.add(startMenu, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
