/*
 * TCSS 305 - Fall 2016
 * Assignment 5 - PowerPaint
 *
 */
package tools;

import java.awt.Shape;
import java.awt.geom.Line2D;


/**
 * This is the Line Tool class.
 * 
 * @author Keldon Fischer
 * @version 11/20/2016
 */
public class LineTool extends AbstractPaintTool {

    @Override
    public Shape getShape() {
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }

}
