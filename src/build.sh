#!/bin/bash
echo "Compiling app..."
javac com/rath/GraphicalSorter.java
javac com/rath/elem/*.java
javac com/rath/gui/*.java
echo "Compiling sorts..."
javac com/rath/sorts/*.java
