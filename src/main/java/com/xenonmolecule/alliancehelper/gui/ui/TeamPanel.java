package com.xenonmolecule.alliancehelper.gui.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TeamPanel extends JPanel {
    private Color disabledColor = Color.DARK_GRAY;
    private Color enabledColor;
    private boolean enabled = false;
    private String teamName;

    public TeamPanel(String teamName) {
        this.teamName = teamName;
        if (teamName != null) {
            add(new JLabel("<html><font color='white'>" + teamName + "</font></html>\""));
            setBackground(disabledColor);
            addMouseListener(new ToggleMouseListener());
        }
    }

    public TeamPanel(String teamName, Color enabledColor) {
        this(teamName);
        this.enabledColor = enabledColor;
        setBackground(enabledColor);
        enabled = true;
    }

    public void paintComponent(Graphics g) {
        if (teamName != null)
            super.paintComponent(g);
    }

    public Dimension getPreferredSize() {
        return new Dimension(100, 25);
    }

    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    public void setEnabled(boolean enabled) {
        if (enabled) {
            if (enabledColor != null) {
                this.enabled = enabled;
                setBackground(enabledColor);
            }
        } else {
            this.enabled = enabled;
            setBackground(disabledColor);
        }

        repaint();
    }

    class ToggleMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            // If right click
            if (e.getButton() == MouseEvent.BUTTON3)
                setEnabled(!enabled);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
