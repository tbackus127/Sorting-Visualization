/**
 * Graphical Sorter - BubbleSort
 * @author Hunter Quant
 * @version rev001
 */

package com.rath.sorts;

import com.rath.elem.ArrayMemberList;

public class BubbleSort {

  private final ArrayMemberList array;

  /**
  * Default constructor
  * @param argArray a reference to the ArrayMemberList to be sorted
  * All sorts must have a constructor Constructor(ArrayMemberList) to be considered valid.
  */
  public BubbleSort(ArrayMemberList argArray) {
    array = argArray;
  }

  /**
  * Preforms a BubbleSort algorithm.
  */
  public void sort() {
    int n = array.getSize();
    boolean needSwap = true;
    while (needSwap) {
      needSwap = false;
      for (int j = 0; j < n - 1; j++) {
        if (array.compare(j, j + 1) == 1) {
          array.swap(j,j+1);
          needSwap = true;
        }
      }
    }
  }
}
