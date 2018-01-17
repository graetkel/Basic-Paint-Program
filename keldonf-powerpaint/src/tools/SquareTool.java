/*
 * TCSS 305 - Fall 2016
 * Assignment 5 - PowerPaint
 *
 */
package tools;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * This is the Square Tool class.
 * 
 * @author Keldon Fischer
 * @version 11/20/2016
 */
public class SquareTool extends AbstractPaintTool {

    @Override
    public Shape getShape() {
        
        
        final double spX = getStartPoint().getX();
        final double spY = getStartPoint().getY();
        final double epX = getEndPoint().getX();
        final double epY = getEndPoint().getY();
        
        
        Rectangle2D square = new Rectangle2D.Double(spX, spY, epX - spX, epX - spX);
  
        if (epX >= spX && epY >= spY) { 
            square = new Rectangle2D.Double(spX, spY, 
                                            epX - spX, epX - spX); //Bottom Right
          //======================================================================
        } else if (epX <= spX && epY <= spY) {
            square = new Rectangle2D.Double(epX, epY, 
                                            spX - epX, spY - epY); //Top Left doesn't work
            // ======================================================================
        } else if (epX >= spX && epY <= spY) {
            square = new Rectangle2D.Double(spX, epY, 
                                            spY - epY, spY - epY); //Top Right
            // ======================================================================
        } else if (epX <= spX && epY >= spY) {
            square = new Rectangle2D.Double(epX, spY, 
                                            spX - epX, spX - epX); //Bottom Left
        }
            //======================================================================

        return square;


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
