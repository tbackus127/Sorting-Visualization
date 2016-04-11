package com.rath.gui;

import com.rath.gui.OptionComponent;

import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.JButton;

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
  }
  
  public JPanel getPanel() {
    return this.optionsPanel;
  }
}