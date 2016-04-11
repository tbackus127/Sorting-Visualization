
package com.rath.sorts;

import java.util.TreeMap;

import com.rath.elem.ArrayMemberList;
import com.rath.gui.OptionComponent;
import com.rath.util.ComponentBuilder;

public class RathSort {
  
  /** The array wrapper */
  protected ArrayMemberList array;
  
  /** Needed for stopping the algorithm, since SwingWorker.cancel() doesn't. */
  protected Thread sortThread;
  
  /** The option map (opt) is built from this 
   * Syntax: "key;type;label[|choice-N]"
   */
  protected String[] optsString;
  
  /** The option map. Use opt.get(KEY).<val() or sel()> to access */
  protected TreeMap<String, OptionComponent> opt = null;
  
  /**
   * Gets the components to add to the options GUI
   * @return an array of OptionComponents
   */
  public OptionComponent[] getOptions() {
    OptionComponent[] result = new OptionComponent[opt.size()];
    int i = 0;
    for(OptionComponent c : opt.values()) {
      result[i++] = c;
    }
    return result;
  }
  
  /**
   * Automatically builds the option components from a string array.
   */
  protected void enableOptions() {
    opt = ComponentBuilder.build(optsString);
  }
  
  /**
   * Called from child class to enable stopping with the [Stop] button.
   */
  public void sort() {
    sortThread = Thread.currentThread();
  }
  
  /**
   * Forcefully stops the sort, because SwingWorker.cancel() doesn't.
   */
  public void stop() {
    
    // Yes, I know Thread.stop() is deprecated and unsafe. However, it's the only way it'll actually stop.
    // We can't have bogosort running until completion every time we want an algorithm to stop.
    sortThread.stop();
  }
}