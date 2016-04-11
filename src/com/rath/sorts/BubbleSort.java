/**
 * Graphical Sorter - BubbleSort
 * @author Hunter Quant
 * @version rev001
 */

package com.rath.sorts;

import com.rath.elem.ArrayMemberList;

public class BubbleSort extends RathSort {

  private String[] opts = {"chk;Smart iterations?"};

  // private final ArrayMemberList array;

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
    System.err.println("Called BubbleSort:sort()");
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
  
  @Override
  public String[] getOptions() {
    return opts;
  }
}