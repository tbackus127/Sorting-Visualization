/**
 * Graphical Sorter - BubbleSort
 * @author Hunter Quant
 * @version rev002
 */

package com.rath.sorts;

import java.util.HashMap;

import com.rath.elem.ArrayMemberList;
import com.rath.gui.OptionComponent;

public class BubbleSort extends RathSort {

  /**
  * Default constructor
  * @param argArray a reference to the ArrayMemberList to be sorted
  * All sorts must have a constructor Constructor(ArrayMemberList) to be considered valid.
  */
  public BubbleSort(ArrayMemberList argArray) {
    array = argArray;
    
    // All that's required to enable options is a String array for each option, and calling enableOptions().
    // This will create a TreeMap that maps a key to an OptionComponent in TreeMap<String, OptionComponent> opt.
    // Option String syntax: "<Label>;<Type>;<Choice1|Choice2|Choice3|...>"
    //    Label: What the user calls the option
    //    Type: The type of component:
    //      "chk": JCheckBox
    //      "sel": JComboBox
    //    Choices: A String delimited by vertical bars (|) with the first being its label. Each subsequent
    //      token will be a separate choice in the JComboBox
    optsString = new String[]{"smart;chk;Smart iterations?", "test;sel;Label|Choice1|Choice2|Choice3"};
    enableOptions();
  }

  /**
  * Preforms a BubbleSort algorithm.
  * In order enable interrupting the algorithm with the [Stop] button, sort() must call super.sort().
  */
  @Override
  public void sort() {
    super.sort();
    
    int n = array.getSize();
    
    System.err.println("Sel: " + opt.get("test").sel());
    //True is swap has been done in the most recent pass.
    boolean needSwap = true;
    while (needSwap) {
      
      //Set false to check for swap.
      needSwap = false;
      for (int j = 0; j < n - 1; j++) {
        
        //If j is > j + 1 swap and make needSwap true.
        if (array.compare(j, j + 1) == 1) {
          array.swap(j, j + 1);
          needSwap = true;
        }
      }
      
      // Stop the algorithm from going through the already sorted portion
      // To get an OptionComponent's value, use "opt.get(<Key>)" to access it from the map,
      //    and use val() if it is a JCheckBox (returns a boolean), or sel() to get the selected
      //    index (returns an int).
      OptionComponent c = opt.get("smart");
      System.err.println(c);
      if(opt.get("smart").val()) {
        n--;
      }
    }
  }
}