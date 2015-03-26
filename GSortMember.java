public abstract class GSortMember {
    
    // Even indexes are the class file, odd indexes are the sort's name to be displayed in the dropdown menu.
    protected static final String[] ALGORITHMS = {
		"SelectionSort.java", "Selection Sort"
		//"InsertionSort.java", "Insertion Sort"
		//"DPQuickSort.java", "Quick Sort (dual-pivot)"
    };
    
    protected ArrayMemberList arrayMembers;
    
    public GSortMember(ArrayMemberList aml) {
		this.arrayMembers = aml;
    }
    
    public abstract void sort(ArrayMemberList aml);
    
    public void swap() {
		System.out.println("swap");
    }
}