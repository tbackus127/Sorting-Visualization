
package com.rath.sorts;

import java.util.TreeMap;

import com.rath.elem.ArrayMemberList;
import com.rath.gui.OptionComponent;
import com.rath.util.ComponentBuilder;

public class RathSort {
  
  protected ArrayMemberList array;
  protected Thread sortThread;
  
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
  
  public void sort() {
    sortThread = Thread.currentThread();
  }
  
  protected void enableOptions() {
    opt = ComponentBuilder.build(optsString);
  }
  
  protected void setThread(Thread t) {
    sortThread = t;
  }
  
  // Yes, I know Thread.stop() is deprecated and unsafe. However, it's the only way it'll actually stop.
  // We can't have bogosort running until completion every time we want an algorithm to stop.
  public void stop() {
    sortThread.stop();
  }
}