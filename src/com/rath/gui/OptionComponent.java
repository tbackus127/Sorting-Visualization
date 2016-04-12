package com.rath.gui;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class OptionComponent {
  
  private final String label;
  private final JLabel labelComp;
  
  private final JCheckBox chk;
  private final JComboBox cmb;
  
  public OptionComponent(JComponent comp) {
    this(null, comp);
  }
  
  public OptionComponent(String label, JComponent comp) {
    this.label = label;
    if(label == null)
      this.labelComp = null;
    else
      this.labelComp = new JLabel(this.label);
    if(comp instanceof JCheckBox) {
      chk = (JCheckBox)comp;
      cmb = null;
    } else if(comp instanceof JComboBox) {
      cmb = (JComboBox)comp;
      chk = null;
    } else {
      cmb = null;
      chk = null;
    }
    
  }
  
  public JLabel getLabel() {
    return this.labelComp;
  }
  
  public JComponent getComponent() {
    if(chk == null)
      return cmb;
    return chk;
  }
  
  public boolean val() {
    return chk.isSelected();
  }
  
  public int sel() {
    return cmb.getSelectedIndex();
  }
  
  public String toString() {
    String result = "[Opt:" + label + ":";
    if(chk == null)
      result += System.identityHashCode(cmb);
    else
      result += System.identityHashCode(chk);
    return result;
  }
}
