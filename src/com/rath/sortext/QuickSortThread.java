package com.rath.sortext;

import com.rath.elem.ArrayMemberList;
import com.rath.sorts.QuickSort;

public class QuickSortThread implements Runnable {
  
  private static int threadCount = 0;
  
  private ArrayMemberList array;
  private QuickSort qsRef;
  private int left, right;
  
  public QuickSortThread(ArrayMemberList argArray, QuickSort qsRef, int left, int right) {
    this.array = argArray;
    this.qsRef = qsRef;
    this.left = left;
    this.right = right;
  }
  
  @Override
  public void run() {
    sort(left, right);
  }
  
  private void sort(int left, int right) {
    int mid = partition(left, right);
    if(left < mid - 1)
      (new Thread(new QuickSortThread(array, qsRef, left, mid - 1))).start();
    if(mid < right)
      (new Thread(new QuickSortThread(array, qsRef, mid, right))).start();
  }
  
  private int partition(int left, int right) {
    int first = left;
    int last = right;
    int pivot = array.getValue(qsRef.getPivot(left, right));
    
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