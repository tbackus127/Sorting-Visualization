
package com.rath.sorts;

import java.util.TreeMap;

import com.rath.elem.ArrayMemberList;
import com.rath.gui.OptionComponent;
import com.rath.util.ComponentBuilder;

public class RathSort {
  
  protected ArrayMemberList array;
  
  // key;type;label;[choices]
  protected String[] optsString;
  protected TreeMap<String, OptionComponent> opt = null;
  
  public OptionComponent[] getOptions() {
    OptionComponent[] result = new OptionComponent[opt.size()];
    int i = 0;
    for(OptionComponent c : opt.values()) {
      result[i++] = c;
    }
    return result;
  }
  
  protected void enableOptions() {
    opt = ComponentBuilder.build(optsString);
  }
}