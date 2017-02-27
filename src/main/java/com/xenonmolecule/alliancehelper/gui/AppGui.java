package com.xenonmolecule.alliancehelper.gui;

import com.xenonmolecule.alliancehelper.gui.ui.SelectionTeamContainerPanel;
import com.xenonmolecule.alliancehelper.gui.ui.TeamContainerPanel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class AppGui {
    public AppGui() {
        // Built the frame and layout
        JFrame frame = new JFrame("[2616E] Alliance Helper");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 700));
        frame.setResizable(false);
        frame.setMinimumSize(frame.getPreferredSize());
        BorderLayout layout = (BorderLayout) frame.getLayout();
        layout.setVgap(0);
        layout.setHgap(0);

        // Build the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        layout = (BorderLayout) mainPanel.getLayout();
        layout.setVgap(0);
        layout.setHgap(0);

        // Build a JPanel with team list and info
        JPanel teamPanel = new JPanel();
        teamPanel.setLayout(new BorderLayout());

        // --> Build a list panel for teams
        ListPanel listPanel = new ListPanel("Team List");
        listPanel.addItem(new TeamContainerPanel("2616E","2616H","2616D", Color.GRAY));
        listPanel.addItem(new TeamContainerPanel("2616C","2616J","2616P", Color.GRAY));
        listPanel.addItem(new TeamContainerPanel("3815M","3815A","3815J", Color.GRAY));
        listPanel.addItem(new TeamContainerPanel("2616M","2616K","2616L", Color.GRAY));
        listPanel.addItem(new TeamContainerPanel("2616E","2616H","2616D", Color.GRAY));
        listPanel.addItem(new TeamContainerPanel("2616C","2616J","2616P", Color.GRAY));
        listPanel.addItem(new TeamContainerPanel("3815M","3815A","3815J", Color.GRAY));
        listPanel.addItem(new TeamContainerPanel("2616M","2616K","2616L", Color.GRAY));
        listPanel.addItem(new TeamContainerPanel("2616E","2616H","2616D", Color.GRAY));
        listPanel.addItem(new TeamContainerPanel("2616C","2616J","2616P", Color.GRAY));
        listPanel.addItem(new TeamContainerPanel("3815M","3815A","3815J", Color.GRAY));
        listPanel.addItem(new TeamContainerPanel("2616M","2616K","2616L", Color.GRAY));
        listPanel.addItem(new TeamContainerPanel("2616E","2616H","2616D", Color.GRAY));
        listPanel.addItem(new TeamContainerPanel("2616C","2616J","2616P", Color.GRAY));
        listPanel.addItem(new TeamContainerPanel("3815M","3815A","3815J", Color.GRAY));
        listPanel.addItem(new TeamContainerPanel("2616M","2616K","2616L", Color.GRAY));

        // --> Build a lower panel with team info
        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
        infoPanel.setPreferredSize(new Dimension(400,200));
        infoPanel.add(new JLabel("Test"));

        // --> Add everything to super panel
        teamPanel.add(listPanel,BorderLayout.NORTH);
        teamPanel.add(infoPanel, BorderLayout.SOUTH);
        mainPanel.add(teamPanel, BorderLayout.WEST);

        // Create the selectors for the autons
        // --> Create container panels
        JPanel selectFillerCont = new JPanel();
        selectFillerCont.setLayout(new BorderLayout());
        JPanel selectCont = new JPanel();
        selectCont.setLayout(new GridBagLayout());
        // --> Create GBC
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        // -- > Create and add red selector
        SelectionTeamContainerPanel red = new SelectionTeamContainerPanel("Red Alliance",Color.RED);
        selectCont.add(red,gbc);
        // --> Create and add blue selector
        gbc.gridy = 1;
        SelectionTeamContainerPanel blue = new SelectionTeamContainerPanel("Blue Alliance",Color.BLUE);
        selectCont.add(blue,gbc);
        // --> Create empty JPanel to push up the selectors
        JPanel filler = new JPanel();
        filler.setMaximumSize(new Dimension(600, 200));
        filler.setPreferredSize(new Dimension(600, 200));
        selectFillerCont.add(filler, BorderLayout.SOUTH);
        selectFillerCont.add(selectCont, BorderLayout.CENTER);
        // --> Add container to main panel
        mainPanel.add(selectFillerCont,BorderLayout.CENTER);

        // Build the menu
        // --> Menu creation
        JMenu fileMenu = new JMenu("File");

        JMenuItem clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.setActionCommand("Clear");
        clearMenuItem.addActionListener(l -> {
            System.out.println("Pressed clear");
        });
        fileMenu.add(clearMenuItem);

        // Menu bar creation
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        // Pack and display the frame
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
