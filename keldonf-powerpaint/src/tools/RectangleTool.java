/*
 * TCSS 305 - Fall 2016
 * Assignment 5 - PowerPaint
 *
 */
package tools;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * This is the Rectangle Tool class.
 * 
 * @author Keldon Fischer
 * @version 11/20/2016
 *
 */
public class RectangleTool extends AbstractPaintTool {

    @Override
    public Shape getShape() {
        
        final Rectangle2D rectangle = new Rectangle2D.Double(); 
        rectangle.setFrameFromDiagonal(getStartPoint(), getEndPoint());
               
        return rectangle;
    }
}
