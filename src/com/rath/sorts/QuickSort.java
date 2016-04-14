/**
 * Graphical Sorter - QuickSort
 * @author Tim Backus
 * @version rev001
 * <p>
 * This class serves as an example of how to make sorting algorithms for this program.
 * </p>
 */

package com.rath.sorts;

import java.util.Random;

import com.rath.elem.ArrayMemberList;
import com.rath.sortext.RathSort;
import com.rath.sortext.QuickSortThread;

public class QuickSort extends RathSort {
  
  private final Random rand;
  private int pointerRule = 0;
  private int pivotRule = 0;
  private boolean multithreaded = false;
  private boolean ternaryMode = false;
  
  /**
   * Default constructor
   * @param argArray a reference to the ArrayMemberList to be sorted
   * All sorts must have a constructor Constructor(ArrayMemberList) to be considered valid.
   */
  public QuickSort(ArrayMemberList argArray) {
    array = argArray;
    optsString = new String[]{"pnts;sel;Pointer rule: |Left+Right|Left x2|Dual Pivot", "pivr;sel;Pivot rule: |First|Last|Middle|Random|Median of Three|Median of 2+Rand",
                              "mthd;chk;Multithreaded? ", "tern;chk;Ternary? "};
    enableOptions();
    rand = new Random();
  }
	
  /**
   * Perform the sort.
   */
  public void sort() {
    super.sort();
    pivotRule = opt.get("pivr").sel();
    pointerRule = opt.get("pnts").sel();
    multithreaded = opt.get("mthd").val();
    ternaryMode = opt.get("tern").val();
    
    if(multithreaded) {
      (new Thread(new QuickSortThread(array, this, 0, array.getSize() - 1))).start();
      try {
        Thread.sleep(100000);
      } catch (InterruptedException e) {}
    } else {
      switch(pointerRule) {
        
        // Left x2
        case 1:
          quicksortLL(0, array.getSize() - 1);
          break;
        default:
          quicksortLR(0, array.getSize() - 1);
      }
    }
  }
  
  /**
   * Selects the pivot
   * @param left the leftmost index
   * @param right the rightmost index
   * @return the pivot index as an int
   */
  public int getPivot(int left, int right) {
    switch(pivotRule) {
      // Last
      case 1:
        return right;
      // Mid
      case 2:
        return (right - left) / 2 + left;
      // Rand
      case 3:
        return rand.nextInt(right - left) + left;
      // Med3
      case 4:
        int mid = (left + right) / 2;
        
        // Sort the three pointers
        if(array.compare(right, left) < 0)
          array.swap(left, right);
        if(array.compare(mid, left) < 0)
          array.swap(mid, left);
        if(array.compare(right, mid) < 0)
          array.swap(right, mid);
        return mid;
      
      // My hybrid pivot choice
      case 5:
      
        // Choose a random index instead of the middle
        int rnd = rand.nextInt(right - left) + left;
        
        // Sort the three pointers
        if(array.compare(right, left) < 0)
          array.swap(left, right);
        if(array.compare(rnd, left) < 0)
          array.swap(rnd, left);
        if(array.compare(right, rnd) < 0)
          array.swap(right, rnd);
        return rnd;
        
      default:
        return left;
    }
  }
  
  /**
   * Left + Right pointer quicksort
   * @param left the left bound
   * @param right the right bound
   */
  private void quicksortLR(int left, int right) {
    int mid = partitionLR(left, right);
    if(left < mid - 1)
      quicksortLR(left, mid - 1);
    if(mid < right)
      quicksortLR(mid, right);
  }
  
  /**
   * Left + Right pointer partitioning
   * @param left the left bound
   * @param right the right bound
   */
  private int partitionLR(int left, int right) {
    int first = left;
    int last = right;
    int pivot = array.getValue(getPivot(left, right));
    
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
  
  // WIP - Does not work yet.
  private void quicksortLL(int left, int right) {
    if(left < right) {
      int mid = partitionLL(left, right);
      quicksortLL(left, mid - 1);
      quicksortLL(mid, right);
    }
  }
  
  private int partitionLL(int left, int right) {
    int first = left;
    int pivIdx = getPivot(left, right);
    int pivot = array.getValue(pivIdx);
    
    array.swap(pivot, right);
    
    int index = first;
    for(int i = first; i < right; i++) {
      if(array.compareIndexWithConstant(i, pivIdx) <= 0)
        array.swap(first, index++);
    }
    
    array.swap(index, right);
    return index;
  }
}
