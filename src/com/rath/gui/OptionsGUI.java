package com.rath.gui;

import com.rath.gui.OptionComponent;
import javax.swing.JPanel;

public class OptionsGUI {
  
  private final JPanel optionsPanel;
  
  public OptionsGUI(int width, int height) {
    optionsPanel = new JPanel();
    optionsPanel.setSize(width, height);
  }
  
  public void addComponent(OptionComponent comp) {
    this.optionsPanel.add(comp.getLabelComponent());
  }
  
  public JPanel getPanel() {
    return this.optionsPanel;
  }
}