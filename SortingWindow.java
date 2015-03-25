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
		}
    }
    
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.WHITE);
		
		// n = array length
		int n = this.arr.length;
		
		// Width in px of the drawing area
		int areaWidth = this.screenWidth - (HORIZONTAL_MARGIN * 4);
		int areaHeight = this.screenHeight - TOP_MARGIN * 2;
		
		// How many pixels between bar origins (remove border if we must)
		double dx = (areaWidth) / (double)n;
		
		// Scale multiplier for extending the array to the full height
		double scale = areaHeight / (double)getMaxValue(this.arr);
		
		// g.setColor(Color.RED);
		// g.fillRect(248, 248, 248, 248);
		g.setColor(Color.WHITE);
		
		// x, y, w, h
		for(int i = 0; i < arr.length; i++) {
			
			int value = this.arr[i].getValue();
			int x = (int)((dx * i) + HORIZONTAL_MARGIN);
			int y = (int)(areaHeight - (value * scale) + TOP_MARGIN * 2);
			int w = (int)(Math.max(1.0, (double)(dx - 1.0)));
			int h = (int)((double)value * scale);
			
			// Origin at top-left
			g.fillRect(x, y, w, h);
		} 	
    }
	
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
}










