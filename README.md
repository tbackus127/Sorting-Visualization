# Tim's Sorting Algorithm Visualizer
A visual representation of various sorting algorithms. Inspired by Sound of Sorting by Timo Bingmann.

#####*WARNING: If you have epilepsy, I'd advise you not to run this visualization; it's very flashy.*

#### Features
+ Modular algorithms: Any pre-compiled .class files can be dragged and dropped into the /sorts directory and are automatically loaded.
+ Color marking: Array elements that are being read are red, and writes are green. More to come.
+ Algorithm options: Want the option to give users the option of choosing variations of the algorithm? All that's required is a String array, and the option building system takes care of the rest. Accessing the options is as easy as accessing a value from a Map.
+ 60 frames-per-second animation: I could've made a slideshow for the most "cinematic" experience, but oh well. Trade-offs.

#### Planned features
+ Display number of reads/writes
+ Active partition highlighting for recursive algorithms.
+ Algorithm step button

#### How to use
1. Make sure you have the latest version of Java installed and that both the 'java' and 'javac' commands work on your command line.
2. Clone this repository.
3. Run build.bat (Windows), or build.sh (Linux) to compile.
4. Run run.bat (Windows), or run.sh (Linus) to launch the app.
5. Select the algorithm to simulate from the first dropdown menu.
6. Enter an array size (defaults to 48) to perform the algorithm on (8 - 1264, inclusive).
7. Select an array distribution (how it's layed out).
8. Select the sorting speed. 'Very Fast' is recommended for any array size over 100 with an O(n^2) or worse algorithm.
9. Click 'Build' to build the array and load the algorithm options (if any).
10. Click 'Options' to configure the algorithm before running.
11. Click '<' to return back.
12. Click 'Sort' to simulate the algorithm.
13. To stop an already running simulation, click 'Stop'. The array will have to be rebuilt to run another simulation.
