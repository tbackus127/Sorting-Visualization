/**
 * Graphical Sorter - SelectionSort
 * @author Tim Backus
 * @version rev001
 * <p>
 * This class serves as an example of how to make sorting algorithms for this program.
 * </p>
 */

package com.rath.sorts;

import com.rath.elem.ArrayMemberList;

public class SelectionSort {
  
  private final ArrayMemberList array;
  
  
  /**
   * Default constructor
   * @param argArray a reference to the ArrayMemberList to be sorted
   * All sorts must have a constructor Constructor(ArrayMemberList) to be considered valid.
   */
	public SelectionSort(ArrayMemberList argArray) {
		array = argArray;
	}
	
  /**
   * Perform the sort.
   */
  public void sort() {
	
    // i = index to select for
		for(int i = 0; i < array.getSize() - 1; i++) {
      
      // Default the position of the minimum value to the position we're selecting for.
      int minPos = i;
      
      // Find the lowest value in the array for the rest of it up from index i
			for(int j = i + 1; j < array.getSize(); j++) {
        
        // Use compare(a, b) instead of getValue(i) for animation compatibility. 
        // Returns 1 if a > b, -1 if a < b, or 0 if a = b.
        if(array.compare(minPos, j) >= 1)
          
          // We've found a value that is smaller than the current one, so use that instead.
          minPos = j;
			}
      
      // Swap the current position with the smallest value we could find.
      array.swap(i, minPos);
		}
	}
}