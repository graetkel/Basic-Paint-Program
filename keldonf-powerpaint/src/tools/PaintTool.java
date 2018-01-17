/*
 * TCSS 305 - Fall 2016
 * Assignment 5 - PowerPaint
 *
 */
package tools;

import java.awt.Point;
import java.awt.Shape;

/**
 * This is an Interface for my tools.
 * 
 * @author Keldon Fischer
 * @version 11/20/2016
 *
 */
public interface PaintTool {

    /**
     * Gets the Shape form the drawing panel.
     * @return Shape
     */
    Shape getShape();

    /**
     * Sets a new starting point for the Shape.
     * @param thePoint the start point.
     */
    void setStartPoint(Point thePoint);
    
    /**
     * Sets a new end point.
     * @param thePoint the end point.
     */
    void setEndPoint(Point thePoint);
    
    /**
     * Sets a new new point.
     * @param thePoint the new point.
     */
    void setNewPoint(Point thePoint);
    
    /**
     * get a String.
     * @return String
     */
    String toString();

}