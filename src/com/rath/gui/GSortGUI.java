package com.rath.gui;

import java.io.File;
import java.io.FilenameFilter;
import java.io.FileNotFoundException;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import javax.swing.SwingConstants;

import com.rath.gui.SortingWindow;
import com.rath.gui.OptionsGUI;
import com.rath.elem.ArrayMemberList;
import com.rath.sorts.RathSort;

public class GSortGUI {
    
  private static final String DEFAULT_ARRAY_SIZE = "48";
  private static final File SORT_DIR = new File("./com/rath/sorts/");
  private static final int TARGET_FRAMERATE = 60;
  private static final int FRAMERATE_DELAY = Math.round(1000 / TARGET_FRAMERATE);
  
  private int animationWidth;
  private int animationHeight;
  
  // Toolbar panel
  private JPanel guiPanel;
  
  // Algorithm options panel
  private OptionsGUI optionsPanelObj;
  private JPanel optionsPanel;
  
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
  private int sortDelay;
  
  private JLabel labelSpacing;
  
  private JButton buttonBuild;
  private JButton buttonSort;
  private JButton buttonStop;
  
  private JButton buttonOptions;
  
  public SortingWindow sortWin;
	private ArrayMemberList memberList;
	
  private SwingWorker<Void, Void> sortWorker;
  
  private Timer repaintTimer;
  
  private int sortID;
  private ClassLoader classLoader;
  private Class<?> sortClass;
  private Constructor sortConstr;
  private Object sortInstance;
  
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
    
    this.animationWidth = width;
    this.animationHeight = height - topbarHeight;
    
    classLoader = GSortGUI.class.getClassLoader();
    
    // GUI Panel Section
    guiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    guiPanel.setSize(width, topbarHeight);
    
    // Label: Sort:
    this.labelSelect = new JLabel("Sort:");
    guiPanel.add(this.labelSelect);
    
    //-------------------------------------------------------------------------------------------------------------------------
    // Dropdown: Sort select
    this.sortSelect = new JComboBox<String>(this.sortOptions);
    this.sortSelect.setToolTipText("Selects which sorting algorithm to use.");
    
    // Sort select listener
    this.sortSelect.addActionListener(new ActionListener () {
      
      public void actionPerformed(ActionEvent actEvt) {
        
        System.err.println("Sort select changed");
        reloadSort();
      }
      
    });
    guiPanel.add(this.sortSelect);
    reloadSort();
    
    //-------------------------------------------------------------------------------------------------------------------------
    // Label: Size
    this.labelArraySize = new JLabel("Array size:");
    guiPanel.add(this.labelArraySize);
    
    // Field: Array Size
    this.arraySize = new JTextField(4);
    this.arraySize.setText(DEFAULT_ARRAY_SIZE);
    this.arraySize.setToolTipText("Specifies how many values will be sorted (8-" + (width - 16) + ").");
    guiPanel.add(this.arraySize);
    
    //-------------------------------------------------------------------------------------------------------------------------
    // Label: Distr
    this.labelDistr = new JLabel("Distribution:");
    guiPanel.add(this.labelDistr);
    
    // Dropdown: Distribution type
    this.distrSelect = new JComboBox<String>(this.distrOptions);
    this.distrSelect.setToolTipText("Selects how the array is initially arranged.");
    guiPanel.add(this.distrSelect);
    
    //-------------------------------------------------------------------------------------------------------------------------
    // Label: Speed
    this.labelSpeeds = new JLabel("Sort speed:");
    guiPanel.add(this.labelSpeeds);
    
    // Dropdown: Speed
    sortDelay = 10;
    this.speedSelect = new JComboBox<String>(this.speedOptions);
    this.speedSelect.setSelectedItem("Normal");
    this.speedSelect.setToolTipText("Selects the speed of execution (operation delay):\n - Very slow: 50ms\n - Slow: 30ms\n - Normal: 10ms\n - Fast: 3ms\n - Very fast: 1ms");
    this.speedSelect.addActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent ev) {
        // Calculate delays
        // 0 Very slow = 50ms
        // 1 Slow = 30ms
        // 2 Normal = 10ms
        // 3 Fast = 3ms
        // 4 Very Fast = 1ms
        System.err.println("Sort delay changed");
        switch(speedSelect.getSelectedIndex()) {
          case 0: sortDelay = 50; break;
          case 1: sortDelay = 30; break;
          case 3: sortDelay = 3; break;
          case 4: sortDelay = 1; break;
          default: sortDelay = 10;
        }
      }
    });
    guiPanel.add(this.speedSelect);
    
    //-------------------------------------------------------------------------------------------------------------------------
    // Label for spacing
    this.labelSpacing = new JLabel(" ");
    guiPanel.add(this.labelSpacing);
    
    //-------------------------------------------------------------------------------------------------------------------------
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
          sortWin = new SortingWindow(width, height - topbarHeight, 0, desiredArraySize, distr, sortDelay);
          frame.add(sortWin, BorderLayout.NORTH);
          
          // Validate and repaint
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
    
    //-------------------------------------------------------------------------------------------------------------------------
    // Sort button
    this.buttonSort = new JButton("Sort");
    this.buttonSort.setToolTipText("Begins the sorting process on the array. ** Build must be clicked before sorting! **");
    this.buttonSort.setEnabled(false);
    this.buttonSort.addActionListener(new ActionListener() {
      
      // On click:
      public void actionPerformed(ActionEvent e)  {
        
        // Perform the indicated sorting algorithm.
        executeSort();
        guiPanel.repaint();
      }
    });
    guiPanel.add(this.buttonSort);
    
    //-------------------------------------------------------------------------------------------------------------------------
    // Stop button
    this.buttonStop = new JButton("Stop");
    this.buttonStop.setToolTipText("Stops an already running algorithm.");
    this.buttonStop.setEnabled(false);
    this.buttonStop.addActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent e) {
        stopSort();
      }
    });
    guiPanel.add(this.buttonStop);
    
    //-------------------------------------------------------------------------------------------------------------------------
    // Empty JLabel for spacing
    JLabel spacer = new JLabel("");
    spacer.setPreferredSize(new Dimension(370, 24));
    guiPanel.add(spacer);
    
    //-------------------------------------------------------------------------------------------------------------------------
    // Create a new options panel (initially hidden)
    optionsPanelObj = new OptionsGUI(GSortGUI.this, width, topbarHeight);
    optionsPanel = optionsPanelObj.getPanel();
    optionsPanel.setVisible(false);
    frame.add(optionsPanel);
    
    //-------------------------------------------------------------------------------------------------------------------------
    // Options button (grey out if no options for current algorithm)
    this.buttonOptions = new JButton("Options");
    this.buttonOptions.setToolTipText("Configuration options for the selected algorithm, if any.");
    this.buttonOptions.addActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent e) {
        reloadOptions();
        guiPanel.setVisible(false);
        optionsPanel.setVisible(true);
        guiPanel.revalidate();
        guiPanel.repaint();
      }
      
    });
    this.buttonOptions.setHorizontalAlignment(SwingConstants.RIGHT);
    guiPanel.add(this.buttonOptions);
  }
  
  //===========================================================================================================================
  /**
   * Reloads the sorting algorithm to invoke
   */
  private void reloadSort() {
    sortID = sortSelect.getSelectedIndex();
    try {
      sortClass = classLoader.loadClass("com.rath.sorts." + sortSelect.getItemAt(sortID));
      sortConstr = sortClass.getConstructor(ArrayMemberList.class);
      sortInstance = sortConstr.newInstance(memberList);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Reloads options for the selected algorithm
   */
  private void reloadOptions() {
    RathSort inst = (RathSort) sortInstance;
    System.err.println("Options: " + inst.getOptions());
    optionsPanelObj.addOptions(inst.getOptions());
  }
  
  /**
   * Performs the selected sort from JComboBox sortSelect.
   */
  private void executeSort() {
    this.buttonBuild.setEnabled(false);
    this.buttonSort.setEnabled(false);
    this.buttonStop.setEnabled(true);
    this.buttonOptions.setEnabled(false);
    try {
      
      
      // Algorithm worker thread
      this.sortWorker = new SwingWorker<Void, Void>() {
        
        @Override
        protected Void doInBackground() throws Exception {
          Object sortInst = sortConstr.newInstance(memberList);
          Method sortMethod = sortClass.getMethod("sort");
          System.out.println("Loaded successfully.");
          sortMethod.invoke(sortInst);
          return null;
        }
        
        @Override
        protected void done() {
          System.out.println("Sort completed.");
          memberList.resetStates();
          buttonBuild.setEnabled(true);
          buttonSort.setEnabled(false);
          buttonStop.setEnabled(false);
          buttonOptions.setEnabled(true);
          sortWin.repaint();
        }
      };
      this.sortWorker.execute();
      
      // Repainting timer
      this.repaintTimer = new Timer(0, new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          if(sortWorker.isDone()) {
            repaintTimer.stop();
            return;
          }
          sortWin.repaint();
          memberList.tickLife();
        }
      });
      this.repaintTimer.setDelay(FRAMERATE_DELAY);
      this.repaintTimer.start();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  /**
   * Stops the currently running algorithm
   */
  private void stopSort() {
    this.repaintTimer.stop();
    this.sortWorker.cancel(true);
    this.sortWorker = null;
    this.memberList.resetStates();
    this.buttonBuild.setEnabled(true);
    this.buttonStop.setEnabled(false);
    this.buttonSort.setEnabled(false);
    this.buttonOptions.setEnabled(true);
    this.sortWin.repaint();
  }
  
  /**
   * Gets all available algorithms (must be precompiled .class files)
   * @return a String array of each class, minus ".class"
   */
	private String[] getAvailableSorts() {
		File dir = SORT_DIR;
		File[] sortFiles = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".class") && !name.startsWith("Rath");
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
	
  /**
   * Closes the options panel and returns to the main one
   */
	public void closeOptionPanel() {
    optionsPanel.setVisible(false);
    guiPanel.setVisible(true);
    guiPanel.revalidate();
    guiPanel.repaint();
  }
	
  /**
   * Gets a reference to the toolbar JPanel.
   * @return the reference to the toolbar.
   */
  public JPanel getGUIPanel() {
		return this.guiPanel;
  }
}



/* SCRATCH PAD **

Algorithm: 	[SELECT BOX]
		- Cocktail Shaker Sort
		- Bogo Sort
		- Shell Sort
		- TimSort
		- SleepSort
		- Radix Sort
		- Bitonic Sort
		- Comb Sort
		- Stooge Sort
		- LazySort (enables user to drag and drop values)
    - Shatter-Time Sort
		
*/