public abstract class GSortMember {
    
    // Even indexes are the class file, odd indexes are the sort's name.
    protected static final String[] ALGORITHMS = {
	"SelectionSort.java", "Selection Sort"
	//"InsertionSort.java", "Insertion Sort"
	//"DPQuickSort.java", "Quick Sort (dual-pivot)"
    }
    
    protected ArrayMemberList array;
    
    public GSortMember(ArrayMemberList arr) {
	this.array = arr;
    }
    
    public void sort(ArrayMemberList array);
    
    public void swap() {
	
    }
}