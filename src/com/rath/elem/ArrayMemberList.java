/**
 * Graphical Sorter - ArrayMemberList
 * @author Tim Backus
 * @version rev001
 * <p>
 * This class serves as an example of how to make sorting algorithms for this program.
 * </p>
 */

package com.rath.elem;

import java.util.ArrayList;

import com.rath.elem.ArrayMember;
import static com.rath.elem.ArrayMember.STATE_INACTIVE;
import static com.rath.elem.ArrayMember.STATE_NONE;
import static com.rath.elem.ArrayMember.STATE_READ;
import static com.rath.elem.ArrayMember.STATE_COMPARE;
import static com.rath.elem.ArrayMember.STATE_WRITE;
 
public class ArrayMemberList {
  
  private static final int DEFAULT_LIFE = 30;
  
  private final ArrayMember[] members;
  
  private ArrayList<Integer> memberStateIndeces;
  private ArrayList<Integer> memberStateIDs;
  private ArrayList<Integer> memberStateLife;
  
  public ArrayMemberList(ArrayMember[] list) {
    members = list;
    memberStateIndeces = new ArrayList<Integer>();
    memberStateIDs = new ArrayList<Integer>();
    memberStateLife = new ArrayList<Integer>();
  }
  
  public void swap(int a, int b) {
    ArrayMember temp = members[a];
    members[a] = members[b];
    members[b] = temp;
    // mark(a, STATE_WRITE);
    // mark(b, STATE_WRITE);
    delay(20);
    // tickLife();
  }  
  
  public int getValue(int index) {
    mark(index, STATE_READ);
    // tickLife();
    delay(10);
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
  
  private void mark(int index, int markState) {
    if(memberStateIndeces.contains(index)) {
       memberStateIDs.set(index, markState);
       memberStateLife.set(index, DEFAULT_LIFE);
    } else {
      memberStateIndeces.add(index);
      memberStateIDs.add(markState);
      memberStateLife.add(DEFAULT_LIFE);
    }
  }
  
  private void tickLife() {
    for(int i = 0; i < memberStateIndeces.size(); i++) {
      int currentLife = memberStateLife.get(i);
      if(currentLife < 1) {
        memberStateIndeces.remove(i);
        memberStateIDs.remove(i);
        memberStateLife.remove(i);
        i--;
      } else {
        memberStateLife.set(i, currentLife - 1);
      }
    }
  }
  
  private int getValueNoDelay(int index) {
    return members[index].getValue();
  }
  
  private void delay(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
}
