package com.rath.gui;

import java.util.List;

import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;

public class OptionsGUI {
  
  private final JPanel optionsPanel;
  
  public OptionsGUI(GSortGUI gui, int width, int height) {
    optionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    optionsPanel.setSize(width, height);
    
    JButton buttonBack = new JButton("<");
    buttonBack.setToolTipText("Return to the main menu.");
    buttonBack.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        gui.closeOptionPanel();
      }
    });
    optionsPanel.add(buttonBack);
    System.err.println("Options GUI created.");
  }
  
  public void addOptions(String[] optStr) {
    System.err.println("OptionsGUI.addOptions()");
    
    // TODO
    
    optionsPanel.validate();
    optionsPanel.repaint();
    
  }
  
  public JPanel getPanel() {
    return this.optionsPanel;
  }
}