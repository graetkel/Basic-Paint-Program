/*
 * TCSS 305 - Fall 2016
 * Assignment 5 - PowerPaint
 *
 */
package tools;

import java.awt.Shape;
import java.awt.geom.Path2D;

/**
 * This is the Pencil Tool class.
 * 
 * @author Keldon Fischer
 * @version 11/20/2016
 *
 */
public class PencilTool extends AbstractPaintTool {

    @Override
    public Shape getShape() {
        final Path2D.Double pencil = new Path2D.Double();
        pencil.moveTo(getStartPoint().getX(), getStartPoint().getY());
        pencil.lineTo(getEndPoint().getX(), getEndPoint().getY());
        return pencil;
    }
    
}
