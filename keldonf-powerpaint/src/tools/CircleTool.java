/*
 * TCSS 305 - Fall 2016
 * Assignment 5 - PowerPaint
 *
 */
package tools;


import java.awt.Shape;
import java.awt.geom.Ellipse2D;


/**
 * This is the Circle Tool class.
 * 
 * @author Keldon Fischer
 * @version 11/20/2016
 */
public class CircleTool extends AbstractPaintTool {
    
    @Override
    public Shape getShape() {      
        
        final double spX = getStartPoint().getX();
        final double spY = getStartPoint().getY();
        final double epX = getEndPoint().getX();
        final double epY = getEndPoint().getY();
        
  
        Ellipse2D circle = new Ellipse2D.Double();
        if (epX >= spX && epY >= spY) { 
            circle = new Ellipse2D.Double(spX, spY, 
                                          epX - spX, epX - spX); //Bottom Right
        } else if (epX <= spX && epY <= spY) {
            circle = new Ellipse2D.Double(epX, epY, 
                                          spX - epX, spY - epY); //Top Left doesn't work
        } else if (epX >= spX && epY <= spY) {
            circle = new Ellipse2D.Double(spX, epY, 
                                          spY - epY, spY - epY); //Top Right
        } else if (epX <= spX && epY >= spY) {
            circle = new Ellipse2D.Double(epX, spY, 
                                          spX - epX, spX - epX); //Bottom Left
        } else {
            circle = new Ellipse2D.Double(spX, spY, 
                                          epX, epY);
        }
        
        return circle;
    }
    
    /**
     * This grabs the x from start point.
     * @return x
     */
    public double getX() {
        return getStartPoint().getX();
    }
    
    /**
     * This grabs the y from start point.
     * @return y
     */
    public double getY() {
        return getStartPoint().getX();
    }
}
