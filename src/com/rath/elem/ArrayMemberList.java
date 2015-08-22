/**
 * Graphical Sorter - ArrayMemberList
 * @author Tim Backus
 * @version rev001
 * <p>
 * This class serves as an example of how to make sorting algorithms for this program.
 * </p>
 */

package com.rath.elem;

import com.rath.elem.ArrayMember;
 
public class ArrayMemberList {
  
  private final ArrayMember[] members;
  
  public ArrayMemberList(ArrayMember[] list) {
    members = list;
  }
  
  public void swap(int a, int b) {
    ArrayMember temp = members[a];
    members[a] = members[b];
    members[b] = temp;
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  public synchronized int getValue(int index) {
    return members[index].getValue();
  }
  
  public int getSize() {
    return members.length;
  }
  
  /**
   * Gets the highest value of the member list.
	 * @throws NullPointerException if the member list is null
   * @throws ArrayIndexOutOfBoundsException if the member list has 0 members
	 * @return an int containing the highest value in the array.
   */
	public int getMaxValue() {
		if(members == null)
			throw new NullPointerException();
    if(members.length <= 0)
      throw new ArrayIndexOutOfBoundsException();
		int result = 0;
		for(int i = 0; i < members.length; i++)
			if(members[i].getValue() < 0)
				throw new IllegalArgumentException("Array members must be positive");
			else if(members[i].getValue() > result)
				result = members[i].getValue();
		return result;
	}
  
  /**
   * Gets the lowest value of the passed ArrayMember[].
	 * @throws NullPointerException if the member list is null
   * @throws ArrayIndexOutOfBoundsException if the member list has 0 members
	 * @return an int containing the lowest value in the array.
   */
  public int getMinValue() {
    if(members == null)
      throw new NullPointerException();
    if(members.length <= 0)
      throw new ArrayIndexOutOfBoundsException();
    int result = Integer.MAX_VALUE;
    for(int i = 0; i < members.length; i++)
      if(members[i].getValue() < 0)
        throw new IllegalArgumentException("Array members must be positive");
      else if(members[i].getValue() < result)
        result = members[i].getValue();
    return result;
  }
  
}
