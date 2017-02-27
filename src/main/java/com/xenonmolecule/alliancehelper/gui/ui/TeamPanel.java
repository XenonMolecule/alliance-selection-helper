package com.xenonmolecule.alliancehelper.gui.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TeamPanel extends JPanel {
    private Color disabledColor = Color.DARK_GRAY;
    private Color enabledColor;
    private boolean enabled = false;
    private boolean selected = false;
    private JLabel label;
    private String teamName;

    public TeamPanel(String teamName) {
        this.teamName = teamName;
        if (teamName != null) {
            label = new JLabel();
            add(label);
            setEnabled(false);
            addMouseListener(new ToggleMouseListener());
        }
    }

    public TeamPanel(String teamName, Color enabledColor) {
        this(teamName);
        this.enabledColor = enabledColor;
        setEnabled(true);
    }

    public void paintComponent(Graphics g) {
        if (teamName != null) {
            super.paintComponent(g);
            if (selected) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(5));
                g2.setColor(Color.YELLOW);
                g2.drawRect(0, 0, 100, 25);
            }
        }
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
                label.setText("<html><font color='white'>" + teamName + "</font></html>");
            }
        } else {
            this.enabled = enabled;
            setBackground(disabledColor);
            setSelected(false);
            label.setText("<html><font color='gray'>" + teamName + "</font></html>");
        }
        repaint();
    }

    public void setSelected(boolean selected) {
        if (enabled) {
            this.selected = selected;
        } else
            this.selected = false;
        repaint();
    }

    class ToggleMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            // If right click
            if (e.getButton() == MouseEvent.BUTTON3)
                setEnabled(!enabled);
            if (e.getButton() == MouseEvent.BUTTON1)
                setSelected(!selected); // TODO Communicate with manager
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
