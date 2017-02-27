package com.xenonmolecule.alliancehelper.gui.ui;

import javax.swing.*;
import java.awt.*;

public class TeamContainerPanel extends JPanel {
    private TeamPanel[] panels = {new TeamPanel(null), new TeamPanel(null),
            new TeamPanel(null)};

    private GridBagConstraints gbc = new GridBagConstraints();

    public TeamContainerPanel() {
        this(0);
    }

    protected TeamContainerPanel(int y) {
        setLayout(new GridBagLayout());
        gbc.gridy = y;
        gbc.weightx = 1;
        gbc.weighty = 1;
        for (int i = 0; i < panels.length; i++)
            setTeamPanel(i, panels[i]);
    }

    public TeamContainerPanel(String team1, String team2, String team3) {
        this();
        setTeamPanel(0, new TeamPanel(team1));
        setTeamPanel(1, new TeamPanel(team2));
        setTeamPanel(2, new TeamPanel(team3));
    }

    public TeamContainerPanel(String team1, String team2, String team3, Color enabledColor) {
        this();
        setTeamPanel(0, new TeamPanel(team1, enabledColor));
        setTeamPanel(1, new TeamPanel(team2, enabledColor));
        setTeamPanel(2, new TeamPanel(team3, enabledColor));
    }

    public Dimension getPreferredSize() {
        return new Dimension(100, 35);
    }

    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    public void setTeamPanel(int pos, TeamPanel teamPanel) {
        if (panels[pos] != null)
            remove(panels[pos]);
        panels[pos] = teamPanel;
        gbc.gridx = pos;
        add(teamPanel, gbc);
        repaint();
    }

    public TeamPanel getTeamPanel(int pos) {
        return panels[pos];
    }

    protected GridBagConstraints getGbc() {
        return gbc;
    }
}
