package com.rath.gui;

import java.util.List;

import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;

public class OptionsGUI {
  
  private final JPanel optionsPanel;
  
  /**
   * Default constructor
   * @param gui reference to main GUI panel
   * @param width the width of the window
   * @param height the height of the top bar
   */
  public OptionsGUI(GSortGUI gui, int width, int height) {
    optionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    optionsPanel.setSize(width, height);
    
    //-------------------------------------------------------------------------------------------------------------------------
    // Back button
    JButton buttonBack = new JButton("<");
    buttonBack.setToolTipText("Return to the main menu.");
    buttonBack.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        gui.closeOptionPanel();
      }
    });
    optionsPanel.add(buttonBack);
    //-------------------------------------------------------------------------------------------------------------------------
    
    System.err.println("Options GUI created.");
  }
  
  /**
   * Adds the OptionComponents to the options panel
   * @param opts the array of OptionComponents to add
   */
  public void addOptions(OptionComponent[] opts) {
    System.err.println("OptionsGUI.addOptions()");
    
    for(int i = 0; i < opts.length; i++) {
      optionsPanel.add(opts[i].getComponent());
    }
    
    optionsPanel.validate();
    optionsPanel.repaint();
    
  }
  
  public JPanel getPanel() {
    return this.optionsPanel;
  }
}