package sorts;

public class SelectionSort extends GSortMember {
    
	public SelectionSort(ArrayMember[] argArray) {
		super(argArray);
	}
	
    protected void sort() {
	
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