package com.rath.gui;

import java.io.File;
import java.io.FilenameFilter;
import java.io.FileNotFoundException;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.rath.gui.SortingWindow;
import com.rath.elem.ArrayMemberList;

public class GSortGUI {
    
  private static final String DEFAULT_ARRAY_SIZE = "256";
	private static final File SORT_DIR = new File("./com/rath/sorts/");
    
  // Toolbar panel
  private JPanel guiPanel;
  
  // Label and selection dropdown box
  private JLabel labelSelect;
  private JComboBox sortSelect;
  private String[] sortOptions;
  private File[] sortFiles;
        
  // Label and array size field
  private JLabel labelArraySize;
  private JTextField arraySize;
  
  // Label and array distribution dropdown box
  private JLabel labelDistr;
  private JComboBox distrSelect;
  private String[] distrOptions = {"Random", "Ascending", "Nearly Sorted", "Descending", "Equal N-2", "Pyramid", "Shuffled Cubic"};
  
  // Label and sorting speed dropdown box
  private JLabel labelSpeeds;
  private JComboBox speedSelect;
  private String[] speedOptions = {"Very slow", "Slow", "Normal", "Fast", "Very fast"};
  
  private JLabel labelSpacing;
  
  private JButton buttonBuild;
  private JButton buttonSort;
  
  private SortingWindow sortWin;
	private ArrayMemberList memberList;
	
	private int maxArraySize;
  
  /**
   * Default constructor. Will create a new JPanel toolbar at the top of the main window, as well as an empty space for the sorting window.
   * @param width the width of the program window.
   * @param height the height of the sorting window.
   * @param topbarHeight the height of the toolbar.
   * @param frame a reference to the main JFrame.
   */
  public GSortGUI(final int width, final int height, final int topbarHeight, final JFrame frame) {

    this.sortOptions = getAvailableSorts();
    
    guiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    guiPanel.setSize(width, topbarHeight);
    
    // Label: Sort:
    this.labelSelect = new JLabel("Sort:");
    guiPanel.add(this.labelSelect);
    
    // Dropdown: Sort select
    this.sortSelect = new JComboBox<String>(this.sortOptions);
    this.sortSelect.setToolTipText("Selects which sorting algorithm to use.");
    guiPanel.add(this.sortSelect);
    
    // Label: Size
    this.labelArraySize = new JLabel("Array size:");
    guiPanel.add(this.labelArraySize);
    
    // Field: Array Size
    this.arraySize = new JTextField(4);
    this.arraySize.setText(DEFAULT_ARRAY_SIZE);
    this.arraySize.setToolTipText("Specifies how many values will be sorted (8-" + (width - 16) + ").");
    guiPanel.add(this.arraySize);
    
    // Label: Distr
    this.labelDistr = new JLabel("Distribution:");
    guiPanel.add(this.labelDistr);
    
    // Dropdown: Distribution type
    this.distrSelect = new JComboBox<String>(this.distrOptions);
    this.distrSelect.setToolTipText("Selects how the array is initially arranged.");
    guiPanel.add(this.distrSelect);
    
    // Label: Speed
    this.labelSpeeds = new JLabel("Sort speed:");
    guiPanel.add(this.labelSpeeds);
    
    // Dropdown: Speed
    this.speedSelect = new JComboBox<String>(this.speedOptions);
    this.speedSelect.setSelectedItem("Normal");
    this.speedSelect.setToolTipText("Selects the speed at which the algorithm will execute:\n - Very slow: 100ms\n - Slow: 75ms\n - Normal: 40ms\n - Fast: 20ms\n - Very fast: 10ms");
    guiPanel.add(this.speedSelect);
    
    // Label for spacing
    this.labelSpacing = new JLabel(" ");
    guiPanel.add(this.labelSpacing);
    
    // Build button
    this.buttonBuild = new JButton("Build");
    this.buttonBuild.setToolTipText("Creates the array to be sorted and renders it as a series of bars at a height corresponding to their value.");
    this.buttonBuild.addActionListener(new ActionListener() {
      
      // On click:
      public void actionPerformed(ActionEvent e) {
      
        int desiredArraySize = Integer.parseInt(arraySize.getText());
        int distr = distrSelect.getSelectedIndex();
      
        // Array Size must be between 8 and <area size> pixels.
        if(desiredArraySize >= 8 && desiredArraySize <= width - 16) {
          
          // If there's already a sorting window, remove it
          if(sortWin != null)
            frame.remove(sortWin);
          
          // Create a new sorting window with the indicated parameters, and add it to the main window. (w, h, sortType, arraySize, distribution)
          sortWin = new SortingWindow(width, height - topbarHeight, 0, desiredArraySize, distr);
          frame.add(sortWin);
          frame.validate();
          sortWin.validate();
          sortWin.repaint();
          
          // Enable the sort button.
          buttonSort.setEnabled(true);
          memberList = sortWin.getArrayMembers();
          
        } else {
          buttonSort.setEnabled(false);
        }
      
      }
    });
    guiPanel.add(this.buttonBuild);
    
    // Sort button
    this.buttonSort = new JButton("Sort");
    this.buttonSort.setToolTipText("Begins the sorting process on the array. ** Build must be clicked before sorting! **");
    this.buttonSort.setEnabled(false);
    this.buttonSort.addActionListener(new ActionListener() {
      
      // On click:
      public void actionPerformed(ActionEvent e)  {
        
        // Perform the indicated sortint algorithm.
        executeSort();
      }
    });
    guiPanel.add(this.buttonSort);
  }
    
  /**
   * Performs the selected sort from JComboBox sortSelect.
   */
  private void executeSort() {
    int sortID = sortSelect.getSelectedIndex();
    int sortDelay = 100;	// ms
      
    ClassLoader loader = GSortGUI.class.getClassLoader();
    try {
      // Class sortClass = loader.loadClass("com.rath.sorts." + sortSelect.getItemAt(sortID));
      Class<?> sortClass = loader.loadClass("com.rath.sorts.SelectionSort");
      
      Constructor constr = sortClass.getConstructor(ArrayMemberList.class);
      Object sortInstance = constr.newInstance(memberList);
      
      Method sortMethod = sortClass.getMethod("sort");
      sortMethod.invoke(sortInstance);
      
      System.out.println("Loaded successfully.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    
    // memberList.swap(0, 1);
    sortWin.repaint();
    
    // Use "new Color(r, g, b)" to change color;
  }
    
	private String[] getAvailableSorts() {
		File dir = SORT_DIR;
		File[] sortFiles = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".class");
			}
		});
		
    if(sortFiles.length == 0) {
      System.out.println("No valid algorithms compiled.");
    }
    
		String[] result = new String[sortFiles.length];
		for(int i = 0; i < sortFiles.length; i++){
			String fileName = sortFiles[i].getName();
			int pos = fileName.lastIndexOf(".");
			result[i] = fileName.substring(0, pos);
		}
		
		return result;
	}
	
	// private boolean isValidSort(File f) {
		
	// }
	
  /**
   * Gets a reference to the toolbar JPanel.
   * @return the reference to the toolbar.
   */
  public JPanel getGUIPanel() {
		return this.guiPanel;
  }
}



/* SCRATCH PAD **

jcomponent.setToolTipText("");

Algorithm: 	[SELECT BOX]			
		- BubbleSort
		- Selection Sort
		- Insertion Sort
		- QuickSort
		- QuickSort (multithreaded)
		- Dual-Pivot Quick Sort (multithreaded)
		- Merge Sort (multithreaded)
		- Cocktail Shaker Sort
		- Bogo Sort
		- Shell Sort
		- HeapSort
		- TimSort
		- SleepSort
		- Radix Sort
		- Bitonic Sort
		- Comb Sort
		- Stooge Sort
		- LazySort (enables user to drag and drop values)
		
*/