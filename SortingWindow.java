import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public class SortingWindow extends JPanel {
    
	private static final int HORIZONTAL_MARGIN = 8;
	private static final int TOP_MARGIN = 8;
	
    private ArrayMember[] arr;
    private int screenWidth;
    private int screenHeight;
    private int sortType;
    private int distribution;
    
    /**
     * Default constructor. Will create a new blank SortingWindow.
     * @param w the width of the sorting window.
     * @param h the height of the sorting window.
     */
    public SortingWindow(int w, int h) {
		super();
		setSize(w, h);
		setLocation(0, h);
		this.screenWidth = w;
		this.screenHeight = h;
		setBackground(Color.BLACK);
    }
    
    /**
     *	@param type: Specifies the sorting algorithm to use.
     * 	@param arraySize: Specifies the size of the array. 
     * 	@param distr: Specifies the initial distribution of the array.
     */
    public SortingWindow(int w, int h, int type, int arraySize, int distr) {
		this(w, h);
		this.screenWidth = w;
		this.screenHeight = h;	// Allow 8px of padding on the top/bottom.
		buildArray(arraySize, distr);
	}
		
	public final void setOptions(int type, int arraySize, int distr) {
		this.sortType = type;
		this.distribution = distr;
		buildArray(arraySize, distr);
	}
	
	
	/**
     * Builds the array with the specified metrics.
	 * @param size the number of elements in the array (8-1264)
	 * @param distr the distribution of the elements (see String[] distrOptions in GSortGUI.java)
     */
	private void buildArray(int size, int distr) {
	
		this.arr = new ArrayMember[size];
	 
		// For rand, asc, nearly sorted
		if(distr >= 0 && distr <= 2) {
			
			// Build the list ascending
			for(int i = 0; i < size; i++) {
				arr[i] = new ArrayMember(i + 1);
			}
			
			// Random shuffle
			if(distr == 0) {
			
				// Shuffle the array
				Random rand = new Random();
				for(int i = 0; i < size; i++) {
					int swapPos = Math.abs(rand.nextInt() % size);
					ArrayMember temp = this.arr[i];
					this.arr[i] = this.arr[swapPos];
					this.arr[swapPos] = temp;
				}
				
			// Nearly sorted
			} else if (distr == 2) {
				Random rand = new Random();
				for(int i = 0; i < size; i++) {
					int swapPos = i + ((rand.nextInt() % 5) - 2);
					if(swapPos < 0) 
						swapPos = 0;
					if(swapPos > size - 1)
						swapPos = size - 1;
					ArrayMember temp = this.arr[i];
					this.arr[i] = this.arr[swapPos];
					this.arr[swapPos] = temp;
				}
			}
			
		// Descending
		} else if (distr == 3) {
			for(int i = 0; i < size; i++) {
				arr[i] = new ArrayMember(size - i);
			}
		
		// Equal N-2
		} else if (distr == 4) {
			
			// Set all members to 32
			for(int i = 0; i < size; i++) {
				arr[i] = new ArrayMember(32);
			}
			
			Random rand = new Random();
			
			// Get two indexes; one to set to 16, and the other to 48
			int a = Math.abs(rand.nextInt() % size);
			int b = Math.abs(rand.nextInt() % size);
			
			// Don't let a and b share the same index.
			while(b == a)
				b = rand.nextInt() % size;
			
			// Set the values
			arr[a].setValue(16);
			arr[b].setValue(48);
		
		// Pyramid
		} else if (distr == 5) {
			
			// If the array has an odd number of members
			// boolean odd = size % 2 == 1;
			
			// Build the list with odd numbers ascending
			for(int i = 0; i < (size / 2); i++) {
				arr[i] = new ArrayMember(2 * i + 1);
			}
			
			// Build the list with even numbers descending
			for(int i = (size / 2); i < size; i++) {
				arr[i] = new ArrayMember(-2 * i + (size * 2));
			}
		
		// Shuffled Cubic
		} else if (distr == 6) {
			for(int i = 0; i < size; i++) {
				
				// Bound x from -1 to +1
				double x = (2.0 * (double)i / size) - 1.0;
				
				// Plot v = x^3
				double v = Math.pow(x, 3);
				
				// Scale these values to our array size
				double w = (v + 1.0) / 2.0 * size + 1;
				
				// Set the values
				arr[i] = new ArrayMember((int)w + 1);
			}
			
			// Shuffle the array
			Random rand = new Random();
			for(int i = 0; i < size; i++) {
				int swapPos = Math.abs(rand.nextInt() % size);
				ArrayMember temp = this.arr[i];
				this.arr[i] = this.arr[swapPos];
				this.arr[swapPos] = temp;
			}
		}
    }
    
	/**
     * Paints the sorting window.
	 * @param g Graphics object
     */
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.WHITE);
		
		// n = array length
		int n = this.arr.length;
		
		// Width/height in pixels of the drawing area
		int areaWidth = this.screenWidth - (HORIZONTAL_MARGIN * 2);
		int areaHeight = this.screenHeight - (TOP_MARGIN * 2);
		
		// How many pixels between bar origins
		double dx = (areaWidth) / (double)n;
		
		// Scale multiplier for extending the array to the full height
		// getMaxValue() must be casted to a double for precision.
		double scale = areaHeight / (double)getMaxValue(this.arr);
		
		for(int i = 0; i < arr.length; i++) {
			
			int value = this.arr[i].getValue();
			
			// Horizontal location of the bar's origin (draws from top-left)
			// Start with the side margin as an offset, and multiply bar width by which bar we are drawing
			int x = (int)((dx * i) + HORIZONTAL_MARGIN);
			
			// Vertical location of the bar's origin
			// Offset by the top margin, scale the bar's height, and subtract this from our area.
			int y = (int)(areaHeight - (value * scale) + TOP_MARGIN * 2 + 1);
			
			// How wide to make the bar
			// Subtract 1 from the bar's width to get a "border" between bars.
			// However, we can't let this reach 0px, or no bars will be drawn, so use 1 if the calculated value is less than 1.
			int w = (int)(Math.max(1.0, (double)(dx - 1.0)));
			
			// How tall to make the bar
			int h = (int)((double)value * scale);
			
			// Draw the rectangle with x/y/w/h specified
			g.fillRect(x, y, w, h);
		} 	
    }
	
	/**
     * Gets the highest value of the passed ArrayMember[].
	 * @param a ArrayMember array to test.
	 * @throws NullPointerException if a is null
	 * @throws IllegalArgumentException if the array is of size 0, or if one or more members are negative.
	 * @return an int containing the highest value in the array.
     */
	private static int getMaxValue(ArrayMember[] a) {
		if(a == null)
			throw new NullPointerException();
		else if(a.length == 0)
			throw new IllegalArgumentException("Array size is zero");
		int result = 0;
		for(int i = 0; i < a.length; i++)
			if(a[i].getValue() < 0)
				throw new IllegalArgumentException("Array members must be positive");
			else if(a[i].getValue() > result)
				result = a[i].getValue();
		return result;
	}
	
	/**
     * Gets the ArrayMember[] arr.
	 * @return a reference to ArrayMember[] arr
     */
	public ArrayMember[] getArray() {
		return this.arr;
	}
}










