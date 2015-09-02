/**
 * Graphical Sorter - InsertionSort
 * @author Tim Backus
 * @version rev001
 * <p>
 * This class serves as an example of how to make sorting algorithms for this program.
 * </p>
 */

package com.rath.sorts;

import com.rath.elem.ArrayMemberList;

public class InsertionSort {
  
  private final ArrayMemberList array;
  
  /**
   * Default constructor
   * @param argArray a reference to the ArrayMemberList to be sorted
   * All sorts must have a constructor Constructor(ArrayMemberList) to be considered valid.
   */
	public InsertionSort(ArrayMemberList argArray) {
		array = argArray;
	}
	
  /**
   * Perform the sort.
   */
  public void sort() {
    
    // Iterate through the unsorted portion of the array.
    for(int i = 1; i < array.getSize(); i++) {
      
      // Get the first member of the unsorted portion.
      int currentVal = array.getValue(i);
      
      // Position to begin insertion.
      int j = i - 1;
      
      // While we haven't inserted it in the proper position, keep looking.
      while(j >= 0 && array.compareIndexWithConstant(j, currentVal) >= 1) {
        array.setValue(j + 1, array.getValue(j));
        j--;
      }
      
      // Once we've found the proper position, stick it in.
      array.setValue(j + 1, currentVal);
    }
	}
}