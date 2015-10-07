/**
 * Graphical Sorter - BubbleSort
 * @author Hunter Quant
 * @version rev001
 */

package com.rath.sorts;

import com.rath.elem.ArrayMemberList;
import com.rath.gui.OptionComponent;

public class BubbleSort {

  public int optionCount = 1;

  private final ArrayMemberList array;
  private final OptionComponent[] options;

  /**
  * Default constructor
  * @param argArray a reference to the ArrayMemberList to be sorted
  * All sorts must have a constructor Constructor(ArrayMemberList) to be considered valid.
  */
  public BubbleSort(ArrayMemberList argArray) {
    array = argArray;
    options = new OptionComponent[1];
    options[0] = new OptionComponent();
  }

  /**
  * Preforms a BubbleSort algorithm.
  */
  public void sort() {
    int n = array.getSize();

    //True is swap has been done in the most recent pass.
    boolean needSwap = true;
    while (needSwap) {
      //Set false to check for swap.
      needSwap = false;
      for (int j = 0; j < n - 1; j++) {
        //If j is > j + 1 swap and make needSwap true.
        if (array.compare(j, j + 1) == 1) {
          array.swap(j,j+1);
          needSwap = true;
        }
      }
      // Stop the algorithm from going through the already sorted portion
      n--;
    }
  }
}