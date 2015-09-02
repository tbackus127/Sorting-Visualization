/**
 * Graphical Sorter - ArrayMember
 * @author Tim Backus
 * @version rev001
 * <p>
 * This class contains the values of each array member, and any metadata the program may need.
 * </p>
 */

package com.rath.elem;
 
public class ArrayMember {

    public static final int STATE_INACTIVE = -1;
    public static final int STATE_NONE = 0;
    public static final int STATE_READ = 1;
    public static final int STATE_COMPARE = 2;
    public static final int STATE_WRITE = 3;

    private int value;		  // The number value of the ArrayMember (for comparisons)
    
    /**
     * Default constructor. Will create a new ArrayMember with the specified value, and a color of (255,255,255).
     * @param val the value the ArrayMember will have.
     */
    public ArrayMember(int val) {
      this.value = val;
    }
    
    /**
     * Returns the value of the ArrayMember.
     * @return 	the value the ArrayMember has.
     */
    public int getValue() {
      return this.value;
    }
    
    /**
     * Sets the ArrayMember to the specified value.
     * @param val the value the ArrayMember will have.
     */
    public void setValue(int val) {
      this.value = val;
    }
}