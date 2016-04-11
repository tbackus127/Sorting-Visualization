package com.rath.gui;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class OptionComponent {
  
  private final String label;
  private final JLabel labelComp;
  private final JComponent comp;
  
  public OptionComponent(JComponent comp) {
    this(null, comp);
  }
  
  public OptionComponent(String label, JComponent comp) {
    this.label = label;
    if(label == null)
      this.labelComp = null;
    else
      this.labelComp = new JLabel(this.label);
    this.comp = comp;
  }
  
  public JLabel getLabel() {
    return this.labelComp;
  }
  
  public JComponent getComponent() {
    return this.comp;
  }
  
  public boolean val() {
    if(comp instanceof JCheckBox)
      return ((JCheckBox)comp).isSelected();
    throw new RuntimeException("val() called on non-JCheckBox component!");
  }
  
  public int sel() {
    if(comp instanceof JComboBox)
      return ((JComboBox)comp).getSelectedIndex();
    throw new RuntimeException("sel() called on non-JComboBox component!");
  }
}





/*

Only allowed:
> Single checkboxes
> Dropdown menus

Use JComponent.isSelected() for true/false


*/