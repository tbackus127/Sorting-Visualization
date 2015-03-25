/**
 * Graphical Sorter - Main Class
 * @author Tim Backus
 * @version rev001
 * <p>
 * This program animated various sorting algorithms by displaying values of an array as vertical bars.
 * <p>
 * Inspired by Sound of Sorting by Timo Bingmann.
 */

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class GraphicalSorter {
    
    public static final int WIDTH = 1280;		// The width of the window
    public static final int HEIGHT = 720;		// The height of the window
    public static final int TOPBAR_HEIGHT = 32;	// The height of the toolbar at the top of the window

    /**
     * Main method. Creates the main window of this program.
     * @param args the runtime arguments. ** Currently unused **.
     */
    public static void main(String[] args) {
	
	// Initialize a new JFrame for the program
	JFrame frame = new JFrame("Tim's Graphical Sorter");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(WIDTH, HEIGHT + TOPBAR_HEIGHT);
	frame.setLayout(new BorderLayout(0,0));
	
	// Create the toolbar and add it to the window
	GSortGUI input = new GSortGUI(WIDTH, HEIGHT, TOPBAR_HEIGHT, frame);
	frame.add(input.getGUIPanel(), BorderLayout.PAGE_START);
	
	// Finalize the window
	frame.setLocationRelativeTo(null);
	frame.setResizable(false);
	frame.setVisible(true);
    }
}


/*

Sorter Commands
    getValue(index)
    setValue(index, value)
    swap(indexA, indexB)
    setPointer(id, index)
    getPointer(id)
    




*/