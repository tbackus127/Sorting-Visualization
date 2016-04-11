
package com.rath.util;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class ComponentBuilder {

  /**
   * Builds a child of a JComponent from a string.
   * Valid syntaxes:
   *   "chk;<LABEL>" - Creates a JCheckBox with label <LABEL>
   *   "sel;<OPTS>" - Creats a JComboBox with options <OPTS> (delimited by |'s)
   * @param s the string to build a component from
   * @return an array of a child of a JComponent
   */
  public static List<JComponent> build(String s) {
    System.err.println("ComponentBuilder.build() on " + s);
    String[] opts = s.split(";");
    
    List<JComponent> comp = new ArrayList<JComponent>();
    
    switch(opts[0]) {
      case "chk":
        comp.add(new JCheckBox(opts[1]));
      break;
      case "sel":
        comp.add(new JLabel(opts[1]));
        String[] selOpts = opts[1].split("\\|");
        comp.add(new JComboBox<String>(selOpts));
      break;
      default:
        throw new IllegalArgumentException("Illegal option syntax");
    }
    
    return comp;
  }
}