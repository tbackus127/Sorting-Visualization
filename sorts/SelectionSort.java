/**
 * Graphical Sorter - SelectionSort
 * @author Tim Backus
 * @version rev001
 * <p>
 * This class serves as an example of how to make sorting algorithms for this program.
 * </p>
 */

package sorts;

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
   * Perform the sort. Must be public void.
   */
  public void sort() {
	
		for(int i = 0; i < array.length; i++) {
			int selection = 0;
			int comp = array.getValue(i);
			for(int j = i+1; j < array.length; j++) {
        int curr = array.getValue(j);
        if(curr < comp)
          swap(i, j);
			}
		}
	}
}