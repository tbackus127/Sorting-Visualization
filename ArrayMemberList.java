import java.util.ArrayList;
import java.util.HashSet;

public class ArrayMemberList {
    
    private ArrayList<ArrayMember> values;
    private HashSet<Integer> pointers;
    
    public ArrayMemberList() {
		values = new ArrayList<ArrayMember>();
		pointers = new HashSet<Integer>();
    }
    
    public int getSize() {
		return values.size();
    }
    
    public void add(ArrayMember am) {
		values.add(am);
    }
    
    public ArrayMember get(int index) {
		return values.get(index);
    }
    
    public void swap(int indexA, int indexB) {
		ArrayMember temp = values.get(indexB);
		values.set(indexB, values.get(indexA));
		values.set(indexA, temp);
    }
    
    public void setPointer(int index) {
		pointers.add(index);
    }
    
    public void removePointer(int index) {
		pointers.remove(index);
    }
}