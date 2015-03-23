import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public class SortingWindow extends JPanel {
    
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
	setSize(w, h + 32);
	setLocation(0, h);
	this.screenWidth = w - 16;
	this.screenHeight = h - 16;
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
	this.screenHeight = h - 16;	// Allow 8px of padding on the top/bottom.
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
	}
    }
    
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	
	g.setColor(Color.WHITE);
	
	// Would give 40px for w=800, N=20
	double dx = this.screenWidth / this.arr.length;
	double scale = this.screenHeight / this.arr.length;
	
	// x, y, w, h
	for(int i = 0; i < arr.length; i++) {
	    
	    // Origin at top-left
	    g.fillRect((int)Math.ceil(8 + (dx * i)), (int)(this.screenHeight - this.arr[i].getValue() * scale + 15), (int)Math.ceil(dx - 1), (int)(this.arr[i].getValue() * scale));
	}
    }
}










