/**
 * Graphical Sorter - ArrayMember
 * @author Tim Backus
 * @version rev001
 * <p>
 * This class contains the values of each array member, and any metadata the program may need.
 */

package elem;
 
public class ArrayMember {

    private int value;		// The number value of the ArrayMember (for comparisons)
    private int[] colorRGB;	// The color of the ArrayMember (for highlighting reads/swaps/etc.)
    
    /**
     * Default constructor. Will create a new ArrayMember with the specified value, and a color of (255,255,255).
     * @param val the value the ArrayMember will have.
     */
    public ArrayMember(int val) {
		this.value = val;
		colorRGB = new int[3];
		for(int i = 0; i < colorRGB.length; i++)
			colorRGB[i] = 255;
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
    
    /**
     * Returns the color of the ArrayMember.
     * @return 	an int[] containing the RGB values of the ArrayMember's color.
     */
    public int[] getColor() {
		return this.colorRGB;
    }
    
    /**
     * Sets the ArrayMember to the specified color.
     * @param colRGB an int[] of size 3 containing the red, green, and blue values of the ArrayMember, respectively.
     */
    public void setColor(int[] colRGB) {
		this.colorRGB = colRGB;
    }
}