/**
 * Graphical Sorter - MergeSort
 * @author Tim Backus
 * @version rev001
 * <p>
 * This class serves as an example of how to make sorting algorithms for this program.
 * </p>
 */

package com.rath.sorts;

import com.rath.elem.ArrayMemberList;

public class MergeSort extends RathSort {
  
  private final int[] helperArray;
  
  /**
   * Default constructor
   * @param argArray a reference to the ArrayMemberList to be sorted
   * All sorts must have a constructor Constructor(ArrayMemberList) to be considered valid.
   */
	public MergeSort(ArrayMemberList argArray) {
		array = argArray;
    helperArray = new int[array.getSize()];
	}
	
  /**
   * Perform the sort.
   */
  public void sort() {
    super.sort();
    mergeSort(0, array.getSize());
	}
  
  private void mergeSort(int startPos, int endPos) {
    if(endPos - startPos < 2)
      return;
    
    int midPos = (startPos + endPos) / 2;
    mergeSort(startPos, midPos);
    mergeSort(midPos, endPos);
    merge(startPos, midPos, endPos);
  }
  
  private void merge(int left, int mid, int right) {
    int leftPointer = left;
    int rightPointer = mid;
    
    for(int i = left; i < right; i++) {
      if(leftPointer < mid && (rightPointer >= right || array.compare(leftPointer, rightPointer) <= 0)) {
        helperArray[i] = array.getValue(leftPointer);
        leftPointer++;
      } else {
        helperArray[i] = array.getValue(rightPointer);
        rightPointer++;
      }
    }
    
    for(int i = left; i < right; i++) {
      array.setValue(i, helperArray[i]);
    }
  }
}

/* 

Options
- Checkbox: Multithreaded?
- Checkbox: In-place?

*/