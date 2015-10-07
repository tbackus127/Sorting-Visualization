package com.rath.gui;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class OptionComponent {
  
  private final String label;
  private final JLabel labelComp;
  private final JComponent comp;
  
  public OptionComponent(String label, JComponent comp) {
    this.label = label;
    if(label == null)
      this.labelComp = null;
    else
      this.labelComp = new JLabel(this.label);
    this.comp = comp;
  }
  
  public JLabel getLabelComponent() {
    return this.labelComp;
  }
  
  public JComponent getComponent() {
    return this.comp;
  }
}





/*

Only allowed:
> Single checkboxes
> Dropdown menus

Use JComponent.isSelected() for true/false


*/