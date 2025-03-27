package Part2;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class HorseRaceGUI {

    JFrame frame;
    JPanel titlePanel;
    JPanel mainPanel;
    JPanel startMenu;
    JPanel buttonPanel;
    JLabel gameTitle;
    JButton startRaceButton;
    JButton designButton;
    JButton betButton;
    JButton statsButton;
    CardLayout cardLayout;

    HorseRaceGUI(){
        frame = new JFrame("Horse Racing Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setResizable(false);
        
        cardLayout = new CardLayout();   //switching between 'pages'
        mainPanel = new JPanel();
        mainPanel.setLayout(cardLayout);

        createStartMenu();

        mainPanel.add(startMenu, "START MENU");
        mainPanel.add(new StartRace(this), "RACE PAGE");
        mainPanel.add(new DesignPage(this), "LANE DESIGN PAGE");
        mainPanel.add(new HorseDesignPage(this), "HORSE DESIGN PAGE");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void createStartMenu(){
        gameTitle = new JLabel("TITLE", SwingConstants.CENTER);
        gameTitle.setFont(new Font(null, Font.BOLD, 40));
        
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setBackground(Color.GRAY);
        titlePanel.setPreferredSize(new Dimension(200, 120));

        titlePanel.add(gameTitle);
    

        startMenu = new JPanel();
        startMenu.setLayout(new BorderLayout());
        startMenu.setBackground(Color.WHITE);
        startMenu.setPreferredSize(new Dimension(50, 450));

        ///////////BUTTONS

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 200, 20, 200));

        Font buttonFont = new Font("Arial", Font.BOLD, 15);

        startRaceButton = new JButton("Start A Race");
        startRaceButton.setFocusable(false);
        startRaceButton.setFont(buttonFont);

        designButton = new JButton("Design Your Lanes & Horses");
        designButton.setFocusable(false);
        designButton.setFont(buttonFont);
        
        betButton = new JButton("Place Your Bets");
        betButton.setFocusable(false);
        betButton.setFont(buttonFont);

        statsButton = new JButton("Statistics");
        statsButton.setFocusable(false);
        statsButton.setFont(buttonFont);

        buttonPanel.add(startRaceButton);
        buttonPanel.add(designButton);
        buttonPanel.add(betButton);
        buttonPanel.add(statsButton);

        startRaceButton.addActionListener(e -> cardLayout.show(mainPanel, "RACE PAGE"));
        designButton.addActionListener(e -> cardLayout.show(mainPanel, "LANE DESIGN PAGE"));

        ////////////////////////////////
        
        startMenu.add(titlePanel, BorderLayout.NORTH);
        startMenu.add(buttonPanel, BorderLayout.CENTER);
    }

    public void showStartMenu(){
        cardLayout.show(mainPanel, "START MENU");
    }

    public void showHorseDesignPage(){
        cardLayout.show(mainPanel, "HORSE DESIGN PAGE");
    }

    public void showDesignPage(){
        cardLayout.show(mainPanel, "LANE DESIGN PAGE");
    }
}
