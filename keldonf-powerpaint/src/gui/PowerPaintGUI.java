/*
 * TCSS 305 - Fall 2016
 * Assignment 5 - PowerPaint
 *
 */

package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * This is the PowerPaintGUI class.
 * @author Keldon Fischer
 * @version 11/08/2016
 *
 */
public class PowerPaintGUI extends JFrame {
    
    /**
     * This is the PowerPaintGUI's serial number.
     */
    private static final long serialVersionUID = 7812913807281196318L;
    
    /** 
     * Initializes a drawing panel. 
     * */
    private final DrawingPanel myPanel;
    
    /**
     * Initializes an action list 
     * which I will use to store all of my different tool actions.
     */
    private List<Action> myToolActions;
    
    /**
     * This is the default constructor for PowerPaintGUI.
     */
    public PowerPaintGUI() {
        super();
        myPanel = new DrawingPanel();      
    }

    /**
     * this runs my GUI.
     */
    public void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final ImageIcon img = new ImageIcon("images//Lion.png");
        setIconImage(img.getImage());
        super.setTitle("PowerPaint");

        setupActions();
        final MenuBar mB = new MenuBar();
        mB.makeMenuBar(myToolActions, myPanel);
        this.setJMenuBar(mB);
               
        add(toolBar(), BorderLayout.SOUTH);
        add(myPanel, BorderLayout.CENTER);
        
        pack();
        this.setMinimumSize(getMinimumSize());
        setVisible(true);
        
    }
    
    /**
     * This method makes all of my actions and adds them to 
     * an ArrayList of actions.
     */
    private void setupActions() {
        myToolActions = new ArrayList<Action>();
        
        //================================================================
        final ToolAction pencilAction =
                        new ToolAction("Pencil", 
                                       new ImageIcon("./images/pencil_bw.gif"),
                                       myPanel);
        myToolActions.add(pencilAction);
        //================================================================
        final ToolAction lineAction =
                        new ToolAction("Line", 
                                       new ImageIcon("./images/line_bw.gif"), 
                                       myPanel);
        myToolActions.add(lineAction);
        //================================================================
        final ToolAction rectangleAction =
                        new ToolAction("Rectangle", 
                                       new ImageIcon("./images/rectangle_bw.gif"),
                                       myPanel);
        myToolActions.add(rectangleAction);
        //================================================================
        final ToolAction ellipseAction =
                        new ToolAction("Ellipse", 
                                       new ImageIcon("./images/ellipse_bw.gif"),
                                       myPanel);
        myToolActions.add(ellipseAction);
        //================================================================
        final ToolAction squareAction =
                        new ToolAction("Square", 
                                       new ImageIcon("./images/square_bw.gif"),
                                       myPanel);
        myToolActions.add(squareAction);
        //================================================================
        final ToolAction circleAction =
                        new ToolAction("Circle", 
                                       new ImageIcon("./images/circle_bw.gif"),
                                       myPanel);
        myToolActions.add(circleAction);
        //================================================================

    }
    
    
    
    //------------------------------ ToolBar ---------------
    
    /**
     * This is the Tool Bar method.
     * @return toolBar which is a JToolBar.
     */
    private JToolBar toolBar() {
        
        final JToolBar toolBar = new JToolBar("ToolBar");       
        final ButtonGroup bg = new ButtonGroup();

        for (final Action toolAction : myToolActions) {
            final JToggleButton tb = new JToggleButton(toolAction);
            bg.add(tb);
            toolBar.add(tb);

        }

        return toolBar;
    } //------------------------ End of ToolBar -------------------
      
    
    
} //End of class
