/*
 * TCSS 305 - Fall 2016
 * Assignment 5 - PowerPaint
 *
 */
package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import tools.PaintTool;
import tools.PencilTool;

/**
 * This is the DrawingPanel class and this class
 * produces all of the drawings. 
 * 
 * @author Keldon Fischer
 * @version 11/20/2016
 */
public class DrawingPanel extends JPanel {
    

    
    /**
     * this is the serial version UID for my Drawing Panel.
     */
    private static final long serialVersionUID = 6262428639011721049L;

    /**
     * Initializes the size of the drawing panel.
     */
    private static final Dimension SIZE = new Dimension(640, 320);
    
    /**
     * Initializes the width of 4.
     */
    private static final int STARTING_WIDTH = 4; 
    
    /**
     * Initializes the color UW Purple.
     */
    private static final Color UW_PURPLE = Color.decode("#4b2e83");
    
    /**
     * Initializes the color UW Gold.
     */
    private static final Color UW_GOLD = Color.decode("#b7a57a");
    
    /**
     * Initializes the stroke width.
     */
    private int myStrokeWidth = STARTING_WIDTH;
    
    /**
     * Initializes the fill.
     */
    private boolean myFill;
    
    /**
     * Initializes the draw color.
     */
    private Color myDrawColor = UW_PURPLE;
    
    /**
     * Initializes the fill color.
     */
    private Color myFillColor = UW_GOLD;
    
    /**
     * Initializes the  don't draw.
     */
    private boolean myDontDraw;
    
    /**
     *  Initializes the current tool.
     */
    private PaintTool myCurrentTool;
    
    /**
     * Array list for all of my old shapes.
     */
    private final List<Shape> myPreviousShapes = new ArrayList<>();
    
    /**
     * Array list for all of my old widths.
     */
    private final List<Integer> myPreviousWidth = new ArrayList<>();
    
    /**
     * Array list for all of my old fills.
     */
    private final List<Boolean> myPreviousFill = new ArrayList<>();
    
    /**
     * Array list for all of my old draw colors.
     */
    private final List<Color> myPreviousDrawColor = new ArrayList<>();
    
    /**
     * Array list for all of my old fill colors.
     */
    private final List<Color> myPreviousFillColor = new ArrayList<>();

    /**
     * This is the default constructor for drawing panel.
     */
    public DrawingPanel() {
        super();
        
        myFill = false;
        myDontDraw = false;
        
        initializePanel();
        myCurrentTool = new PencilTool();  
    }

    /**
     * This add all of the basic things.
     */
    private void initializePanel() {
        setPreferredSize(SIZE);
        setBackground(Color.WHITE);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        
        // setup the mouse listener
        final MouseInputAdapter mouseHandler = new MyMouseHandler();
        final MouseMotionListener mML = new MyMouseMotionListener();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        addMouseMotionListener(mML);
    }
    

    /**
     * This method set the current tool.
     * @param theTool is the new current tool.
     */
    public void setCurrentTool(final PaintTool theTool) {
        myCurrentTool = theTool;
    }
    

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2D = (Graphics2D) theGraphics;

        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        // Color
        g2D.setPaint(myDrawColor); // UW Purple
        g2D.setBackground(myFillColor); // UW Gold

        if (!myDontDraw) {

            for (int i = 0; i < myPreviousShapes.size(); i++) {

                g2D.setStroke(new BasicStroke(myPreviousWidth.get(i)));
                g2D.setPaint(myPreviousDrawColor.get(i));
                g2D.draw(myPreviousShapes.get(i));
                if (myPreviousFill.get(i)) {
                    g2D.setPaint(myPreviousFillColor.get(i));
                    g2D.fill(myPreviousShapes.get(i));
                }
            }

            g2D.setStroke(new BasicStroke(myStrokeWidth));
            g2D.setPaint(myDrawColor);
            g2D.draw(myCurrentTool.getShape());

            if (myFill) {
                g2D.setPaint(myFillColor);
                g2D.fill(myCurrentTool.getShape());
            }
        }
    }
    
    
    

    
    /**
     * This method sets the stroke width.
     * @param theStrokeWidth is the new stroke width.
     */
    public void setStrokeWidth(final int theStrokeWidth) {
        myStrokeWidth = theStrokeWidth;
    }
    
    /**
     * This method sets is fill to either true or false.
     * @param theFilled will return either true or false.
     */
    public void isFill(final boolean theFilled) {
        myFill = theFilled;
    }
    
    /**
     * this is a setter for DrawColor.
     * @param theColor is set as the new draw color.
     */
    public void setDrawColor(final Color theColor) {
        myDrawColor = theColor;
    }
    
    /**
     * this is a setter for myFillCollor.
     * @param theColor is set as the new fill color.
     */
    public void setFillColor(final Color theColor) {
        myFillColor = theColor;
    }
    
    /**
     * This method clears all of the array list.
     */
    public void clearShapes() {
        myPreviousShapes.clear();
        myPreviousWidth.clear();
        myPreviousFill.clear();
        myPreviousDrawColor.clear();
        myPreviousFillColor.clear();
    }
    
    /**
     * this set don't draw to true.
     */
    public void isClear() {
        myDontDraw = true;
    }
    
    /**
     * This checks to see if there are any shapes on the drawing panel.
     * @return true if there is a shape in the myPreviousShapes ArrayList.
     */
    public boolean isThereShapes() {
        boolean isTrue = false;
        if (!myPreviousShapes.isEmpty()) {
            isTrue = true;
        }
        repaint();
        return isTrue;
        
    }
    
    /**
     * This is a listener for all of the classes including
     * the pencil tool.
     * @author Keldon Fischer
     * @version 11/19/2016
     *
     */
    private class MyMouseHandler extends MouseInputAdapter {

        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myDontDraw = false;
            myCurrentTool.setStartPoint(theEvent.getPoint());
            repaint();
        }

        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            myCurrentTool.setEndPoint(theEvent.getPoint());
            repaint(); 
        }

        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            myPreviousWidth.add(myStrokeWidth); // saves last spot
            
            myPreviousDrawColor.add(myDrawColor);
            myPreviousShapes.add(myCurrentTool.getShape());
                     
            myPreviousFillColor.add(myFillColor);
            myPreviousFill.add(myFill);
        }
        
    }
    
    /**
     * This is a listener for the pencil.
     * @author Keldon Fischer
     * @version 11/19/2016
     *
     */
    public class MyMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            if (myCurrentTool.toString().equals("PencilTool")) {
                
                myCurrentTool.setEndPoint(theEvent.getPoint()); //adds last spot
                myPreviousWidth.add(myStrokeWidth); //saves last width
                
                myPreviousDrawColor.add(myDrawColor); //saves last color
                myPreviousShapes.add(myCurrentTool.getShape()); //saves last spot
                

                myPreviousFillColor.add(myFillColor); //saves last fill color.
                myPreviousFill.add(false); //saves as false since pencils don't have a fill.
                
                myCurrentTool.setStartPoint(theEvent.getPoint()); //adds new spot
                repaint();   
            }
        }

        @Override
        public void mouseMoved(final MouseEvent theEvent) { }
    }
    
    
    
}
