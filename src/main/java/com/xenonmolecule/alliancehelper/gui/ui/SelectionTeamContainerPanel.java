package com.xenonmolecule.alliancehelper.gui.ui;

import javax.swing.*;
import java.awt.*;

public class SelectionTeamContainerPanel extends TeamContainerPanel {

    public SelectionTeamContainerPanel(String title, Color color) {
        super(1);
        JLabel label = new JLabel("<html><font color='white'>" + title + "</font></html>");
        label.setFont(label.getFont().deriveFont(20f));
        getGbc().gridy = 0;
        getGbc().gridx = 1;
        add(label,getGbc());
        getGbc().gridy = 1;
        setBackground(color);
        TeamPanel temp = new TeamPanel("Not selected");
        temp.setEnabled(false);
        setTeamPanel(0, temp);
        temp = new TeamPanel("Not selected");
        temp.setEnabled(false);
        setTeamPanel(1, temp);
        temp = new TeamPanel("Not selected");
        temp.setEnabled(false);
        setTeamPanel(2, temp);
    }

    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.setSize(d.getWidth()*5,d.getHeight()+75);
        return d;
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
}
