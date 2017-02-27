package com.xenonmolecule.alliancehelper.gui;

import com.xenonmolecule.alliancehelper.gui.ui.CustomScrollbarUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class ListPanel extends JPanel {
    private JPanel mainList;
    private JScrollPane scroll;
    private int listPosition = 0;

    public ListPanel(String title) {
        setLayout(new BorderLayout());
        resetList();

        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.lightGray);
        label.setBorder(new MatteBorder(1, 1, 0, 1, Color.GRAY));
        add(label, BorderLayout.NORTH);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 448);
    }

    public void resetList() {
        listPosition = 0;
        if (mainList != null)
            remove(mainList);
        if (scroll != null)
            remove(scroll);
        mainList = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        JPanel temp = new JPanel();
        mainList.add(temp, gbc);

        scroll = new JScrollPane(mainList);
        scroll.setBorder(new MatteBorder(1, 1, 0, 1, Color.GRAY));
        scroll.getVerticalScrollBar().setUnitIncrement(15);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getVerticalScrollBar().setUI(new CustomScrollbarUI());
        scroll.getVerticalScrollBar().setBorder(new MatteBorder(0, 1, 0, 0, Color.GRAY));
        add(scroll);
        revalidate();
        repaint();
    }

    public JPanel getMainList() {
        return mainList;
    }

    // SHOULD NOT be called to add to the end
    public void addItem(Component comp, int index) {
        listPosition++; // WILL CAUSE ISSUES if the index increments listPosition
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainList.add(comp, gbc, index);
        revalidate();
        repaint();
    }

    public void addItem(Component comp) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainList.add(comp, gbc, listPosition++);
        revalidate();
        repaint();
    }

    public void removeItem(Component comp) {
        mainList.remove(comp);
        listPosition -= 1;
        revalidate();
        repaint();
    }
}
