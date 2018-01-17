/*
 * TCSS 305 - Fall 2016
 * Assignment 5 - PowerPaint
 *
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * This is the Ellipse Tool class.
 * 
 * @author Keldon Fischer
 * @version 11/20/2016
 */
public class EllipseTool extends AbstractPaintTool {
    
    @Override
    public Shape getShape() {
        
        final Ellipse2D ellipse = new Ellipse2D.Double(); 
        ellipse.setFrameFromDiagonal(getStartPoint(), getEndPoint());
               
        return ellipse;
    }

}
