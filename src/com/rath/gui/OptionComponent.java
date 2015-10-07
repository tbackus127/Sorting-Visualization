package com.rath.gui;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class OptionComponent {
  
  private final String label;
  private final JLabel labelComp;
  private final JComponent comp;
  
  public OptionComponent(String label, JComponent comp) {
    this.label = label;
    this.labelComp = new JLabel(this.label);
    this.comp = comp;
  }
  
  public JLabel getLabelComponent() {
    return this.labelComp;
  }
}