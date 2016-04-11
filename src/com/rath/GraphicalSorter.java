/**
 * Graphical Sorter - Main Class
 * @author Tim Backus
 * @version rev001
 * <p>
 * This program animated various sorting algorithms by displaying values of an array as vertical bars.</p>
 * <p>
 * Inspired by Sound of Sorting by Timo Bingmann.</p>
 */

package com.rath;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.rath.gui.GSortGUI;

public class GraphicalSorter {

  public static final int WIDTH = 1280;		    // The width of the window
  public static final int HEIGHT = 720;		    // The height of the window
  public static final int TOPBAR_HEIGHT = 32;	// The height of the toolbar at the top of the window

  /**
   * Main method. Creates the main window of this program.
   * @param args the runtime arguments. ** Currently unused **.
   */
  public static void main(String[] args) {

    SwingUtilities.invokeLater(new Runnable() {

      public void run() {

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
        frame.setVisible(true);
        frame.setResizable(false);
      }
    });
  }
}
















/*

- TODO List -
> Sort Options JComboBox; find some way to implement it only in the sort class. Ex: Quicksort's pointers, Radix LSB/MSB
> Show extra memory on bottom edge of screen?
> Bogo Sort
> Dual-Pivot Quick Sort
> N-Thread Quick Sort
> Shell Sort
> Tim Sort
> Counting Sort
> Radix Sort
> Shatter-Time Sort


Sorter Commands
    getValue(index) -> mark.READ
    setValue(index, n) -> mark.WRITE
    swap(indexA, indexB)
    compare(indexA, indexB) -> mark.COMP
    markPartition(low, high) -> mark.ACTIVE, mark.INACTIVE

Mark Types
> Read: Desaturated Red
> Write: Green
> Compare: Cyan
> Active Partition: White
> Inactive Partition: Gray
> Pointer: Yellow


*/
