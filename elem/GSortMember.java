package elem;

import elem.ArrayMember;

public abstract class GSortMember {
    
    protected ArrayMember[] array;
	
	public GSortMember(ArrayMember[] am) {
		this.array = am;
	}
	
	public abstract void sort();
    
    protected void swap(int firstIndex, int secondIndex) {
		ArrayMember temp = this.array[firstIndex];
		this.array[firstIndex] = this.array[secondIndex];
		this.array[secondIndex] = temp;
    }
	
}


/* 

> Detect sorting algorithms in /algorithms folder
	- Get file list check if .java files have a sort() method & extends GSortMember









*/