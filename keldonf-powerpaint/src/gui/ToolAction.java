/*
 * TCSS 305 - Fall 2016
 * Assignment 5 - PowerPaint
 *
 */

package gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import tools.CircleTool;
import tools.EllipseTool;
import tools.LineTool;
import tools.PencilTool;
import tools.RectangleTool;
import tools.SquareTool;

/**
 * Sets the background of the panel to the specified color.
 * 
 * @author Keldon Fischer
 * @version 11/20/2016
 */
public class ToolAction extends AbstractAction {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = 5378597116905801274L;
    
    /**
     * Initializes myName.
     */
    private final String myName;
    
    /**
     * Initializes the pencil tool.
     */
    private final PencilTool myPencilTool;   

    /**
     * Initializes the line tool.
     */
    private final LineTool myLineTool; 

    /**
     * Initializes the rectangle tool.
     */
    private final RectangleTool myRectangleTool;
    
    /**
     * Initializes the ellipse tool.
     */
    private final EllipseTool myEllipseTool;
    
    /**
     * Initializes the square tool.
     */
    private final SquareTool mySquareTool;
    
    /**
     * Initializes the circle tool.
     */
    private final CircleTool myCircleTool;
    
    
    /** The component to work on in this action. */
    private final DrawingPanel myComponent;

    /**
     * Constructs an action with the specified name and icon to set the
     * panel to the specified color.
     * 
     * @param theName The name.
     * @param theIcon The icon.
     * @param theComponent The component to alter.
     */
    ToolAction(final String theName, final Icon theIcon,
                final DrawingPanel theComponent) {
        super(theName);
        
        myComponent = theComponent;
        
        putKeys(theName, theIcon);

        myName = theName;
        myPencilTool = new PencilTool();
        myLineTool = new LineTool();
        myRectangleTool = new RectangleTool();
        mySquareTool = new SquareTool();
        myCircleTool = new CircleTool();
        myEllipseTool = new EllipseTool();
    }
    
    /**
     * Helper to set up the putValue for this action. 
     * @param theName The name.
     * @param theIcon The icon.
     */
    private void putKeys(final String theName, final Icon theIcon) {
        
        final ImageIcon icon = (ImageIcon) theIcon;
        final Image largeImage =
            icon.getImage().getScaledInstance(15, -1, java.awt.Image.SCALE_SMOOTH);
        final ImageIcon largeIcon = new ImageIcon(largeImage);
        putValue(Action.LARGE_ICON_KEY, largeIcon);
        
        putValue(Action.MNEMONIC_KEY,
                 KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
        
        putValue(Action.SHORT_DESCRIPTION, theName);
        
        putValue(Action.SELECTED_KEY, true);
        
    }

    /**
     * Sets the panel to the specified color.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        System.out.println(myName);
        ((DrawingPanel) myComponent).setCurrentTool(myLineTool);
        
        if ("Pencil".equals(myName)) {
            ((DrawingPanel) myComponent).setCurrentTool(myPencilTool);
        } else if ("Line".equals(myName)) {
            ((DrawingPanel) myComponent).setCurrentTool(myLineTool);
        } else if ("Rectangle".equals(myName)) {
            ((DrawingPanel) myComponent).setCurrentTool(myRectangleTool);
        } else if ("Ellipse".equals(myName)) {
            ((DrawingPanel) myComponent).setCurrentTool(myEllipseTool);
        } else if ("Square".equals(myName)) {
            ((DrawingPanel) myComponent).setCurrentTool(mySquareTool);
        } else if ("Circle".equals(myName)) {
            ((DrawingPanel) myComponent).setCurrentTool(myCircleTool);
        }
        
        
    }
    
    /**
     * This method gets myName.
     * @return myName
     */
    public String getName() {
        return myName;
    }
    
    
}
