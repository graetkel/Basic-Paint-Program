/*
 * TCSS 305 - Fall 2016
 * Assignment 5 - PowerPaint
 *
 */
package tools;

import java.awt.Point;

/**
 * This is the abstract class for my tools, that implements PaintTool.
 * 
 * @author Keldon Fischer
 * @version 11/20/2016
 */
public abstract class AbstractPaintTool implements PaintTool {

    /** 
     * A point outside the drawing area.
     */
    public static final Point NO_POINT = new Point(-100, -100);

    /**
     * Initializes StartPoint.
     */
    private Point myStartPoint;
    
    /**
     * Initializes EndPoint.
     */
    private Point myEndPoint;
    
    /**
     * Initializes NewPoint.
     */
    private Point myNewPoint;

    /**
     * Constructs a paint tool.
     */
    public AbstractPaintTool() {
        myStartPoint = NO_POINT;
        myEndPoint = NO_POINT;
        myNewPoint = NO_POINT;
    }

    @Override
    public void setStartPoint(final Point thePoint) {      
        myStartPoint = thePoint;
        myEndPoint = thePoint;
        myNewPoint = thePoint;
    }

    /**
     * Gets the start point.
     * @return myStartPoint
     */
    protected Point getStartPoint() {
        return myStartPoint;
    }
    
    @Override
    public void setEndPoint(final Point thePoint) {      
        myEndPoint = thePoint;
    }

    /**
     * Gets the end point.
     * @return myEndPoint
     */
    protected Point getEndPoint() {
        return myEndPoint;
    }
    
    @Override
    public void setNewPoint(final Point thePoint) {
        myNewPoint = thePoint;
    }
    

    /**
     * Gets the new point.
     * @return myNewPoint
     */
    public Point getNewPoint() {
        return myNewPoint;
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}

