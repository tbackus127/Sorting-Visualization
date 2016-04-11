package com.rath.util;

import java.util.TreeMap;

import javax.swing.JComponent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import com.rath.gui.OptionComponent;

public class ComponentBuilder {
  
  public static TreeMap<String, OptionComponent> build(String[] opts) {
    TreeMap<String, OptionComponent> result = new TreeMap<String, OptionComponent>();
    for(int i = 0; i < opts.length; i++) {
      String optString = opts[i];
      String[] optTokens = optString.split(";");
      
      JComponent jcmp = null;
      OptionComponent comp = null;
      String key = optTokens[0];
      
      // Type
      switch(optTokens[1]) {
        case "chk":
          jcmp = new JCheckBox(optTokens[2]);
          comp = new OptionComponent(jcmp);
        break;
        case "sel":
          String[] choices = optTokens[2].split("\\|");
          jcmp = new JComboBox<String>(choices);
          comp = new OptionComponent(optTokens[2], jcmp);
        break;
        default:
          throw new IllegalArgumentException("Invalid syntax");
      }
      
      result.put(key, comp);
    }
    
    return result;
  }
}