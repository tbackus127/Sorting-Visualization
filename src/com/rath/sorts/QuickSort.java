/**
 * Graphical Sorter - QuickSort
 * @author Tim Backus
 * @version rev001
 * <p>
 * This class serves as an example of how to make sorting algorithms for this program.
 * </p>
 */

package com.rath.sorts;

import com.rath.elem.ArrayMemberList;

public class QuickSort {
  
  private final ArrayMemberList array;
  
  /**
   * Default constructor
   * @param argArray a reference to the ArrayMemberList to be sorted
   * All sorts must have a constructor Constructor(ArrayMemberList) to be considered valid.
   */
	public QuickSort(ArrayMemberList argArray) {
		array = argArray;
	}
	
  /**
   * Perform the sort.
   */
  public void sort() {
    quicksort(0, array.getSize() - 1);
	}
  
  private void quicksort(int left, int right) {
    int mid = partition(left, right);
    if(left < mid - 1)
      quicksort(left, mid - 1);
    if(mid < right)
      quicksort(mid, right);
  }
  
  private int partition(int left, int right) {
    int first = left;
    int last = right;
    int pivot = array.getValue(left);
    
    while(first <= last) {
      while (array.compareIndexWithConstant(first, pivot) < 0)
        first++;
      while (array.compareIndexWithConstant(last, pivot) > 0)
        last--;
      if(first <= last) {
        int temp = array.getValue(first);
        array.setValue(first, array.getValue(last));
        array.setValue(last, temp);
        first++;
        last--;
      }
    }
    return first;
  }
}