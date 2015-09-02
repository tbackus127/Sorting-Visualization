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
  
  private static final int DEFAULT_LIFE = 75;  // Dependent on monitor refresh rate?
  
  private final ArrayMember[] members;
  private final int[] memberStates;
  private final int[] markLife;
  private final int delayTime;
  
  public ArrayMemberList(ArrayMember[] list, int delay) {
    members = list;
    memberStates = new int[list.length];
    markLife = new int[list.length];
    delayTime = delay;
  }
  
  public void swap(int a, int b) {
    ArrayMember temp = members[a];
    members[a] = members[b];
    members[b] = temp;
    mark(a, STATE_WRITE);
    mark(b, STATE_WRITE);
    delay(delayTime);
  }
  
  // 1 if a > b, -1 if a < b, 0 if a = b
  public int compare(int a, int b) {
    mark(a, STATE_READ);
    mark(b, STATE_READ);
    delay(delayTime);
    int valA = members[a].getValue();
    int valB = members[b].getValue();
    return (valA > valB) ? 1 : (valA == valB) ? 0 : -1;
  }
  
  public int compareIndexWithConstant(int index, int num) {
    mark(index, STATE_READ);
    delay(delayTime);
    int val = members[index].getValue();
    return (val > num) ? 1 : (val == num) ? 0 : -1;
  }
  
  public void setValue(int index, int value) {
    members[index].setValue(value);
    mark(index, STATE_WRITE);
    delay(delayTime);
  }
  
  public int getValue(int index) {
    return members[index].getValue();
  }
  
  public int getSize() {
    return members.length;
  }
  
  public int getState(int index) {
    return memberStates[index];
  }
  
  public int getStateLife(int index) {
    return markLife[index];
  }
  
  public ArrayMember getMember(int index) {
    return members[index];
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
  
  public void tickLife() {
    // System.err.println("tick()");
    for(int i = 0; i < memberStates.length; i++) {
      if(--markLife[i] < 1) {
        memberStates[i] = STATE_NONE;
      }
    }
  }
  
  public void resetStates() {
    for(int i = 0; i < memberStates.length; i++) {
      memberStates[i] = 0;
      markLife[i] = 0;
    }
  }
  
  private void mark(int index, int markState) {
    memberStates[index] = markState;
    markLife[index] = DEFAULT_LIFE;
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
