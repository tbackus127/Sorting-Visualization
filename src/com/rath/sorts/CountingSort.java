/**
 * Graphical Sorter - Counting Sort
 * @author Tim Backus
 * @version rev001
 * <p>
 * This class serves as an example of how to make sorting algorithms for this program.
 * </p>
 */

package com.rath.sorts;

import java.util.Arrays;

import com.rath.elem.ArrayMemberList;
import com.rath.sortext.RathSort;

public class CountingSort extends RathSort {
  
  /**
   * Default constructor
   * @param argArray a reference to the ArrayMemberList to be sorted
   * All sorts must have a constructor Constructor(ArrayMemberList) to be considered valid.
   */
	public CountingSort(ArrayMemberList argArray) {
		array = argArray;
	}
	
  /**
   * Perform the sort.
   */
  public void sort() {
    super.sort();
    try {
      countingSort();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void countingSort() {
    
    // Initialize counting array
    int[] count = new int[16];
    
    // Go through each member of the array
    for(int i = 0; i < this.array.getSize(); i++) {
      
      // While we have a value at index i that is higher than our counting array's highest index, expand it.
      while(array.compareIndexWithConstant(i, count.length - 1) > 0)
        count = expandArray(count);
      
      // Increment the counter for that value
      count[array.getValue(i)]++;
    }
    
    // Go through each number we've counted
    int arrIndex = 0;
    for(int i = 0; i < count.length; i++) {
      
      // If we've counted one, put it in the array in order
      while(count[i]-- > 0)
        array.setValue(arrIndex++, i);
    }
	}
  
  /**
   * Double the length of the passed array.
   * @param arr the array to copy and expand.
   * @return a copy of the passed array (expanded).
   */
  private int[] expandArray(int[] arr) {
    
    // Initialize a new array with double length
    int[] result = new int[arr.length * 2];
    
    // Copy the previous members over and return the result
    System.arraycopy(arr, 0, result, 0, arr.length);
    return result;
  }
}