/*
 * TCSS 305 - Fall 2016
 * Assignment 5 - PowerPaint
 *
 */
package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * This is the Menu Bar class.
 * @author Keldon Fischer
 * @version 11/20/2016
 *
 */
public class MenuBar extends JMenuBar {

    /**
     * This is the Serial Number for MenuBar.
     */
    private static final long serialVersionUID = 5066672946233867331L;
    
    /**
     * Initializes string for draw.
     */
    private static final String DRAW_COLOR = "Draw Color..."; 
    
    /**
     * Initializes string for fill.
     */
    private static final String FILL_COLOR = "Fill Color...";
    
    /**
     * Initializes the color UW Purple.
     */
    private static final Color UW_PURPLE = Color.decode("#4b2e83");
    
    /**
     * Initializes the color UW Gold.
     */
    private static final Color UW_GOLD = Color.decode("#b7a57a");
    
    /**
     * Initializes the icon size.
     */
    private static final int ICON_SIZE = 10;
    
    /**
     * Initializes the major tick spacing.
     */
    private static final int MAJOR_TICK_SPACING = 5;
    
    /**
     * Initializes the minor tick spacing.
     */
    private static final int MINOR_TICK_SPACING = 1;
    

    
    /**
     * Initializes myTrue.
     */
    private boolean myTrue;
    
    
    /**
     * This is the default constructor for MenuBar.
     */
    public MenuBar() {
        super();
        myTrue = false;
    }
    
    /**
     * This makes all of the sub menus for my menu bar.
     * @param theToolActions this is the list of all my tool actions.
     * @param thePanel this is the drawing panel.
     */
    public void makeMenuBar(final List<Action> theToolActions, final DrawingPanel thePanel) {
        add(fileMenu(thePanel));
        add(optionMenu(thePanel));
        add(toolsMenu(theToolActions));
        add(helpMenu());
    }
    
    /**
     * This method makes everything inside the sub menu "file".
     * @param thePanel the drawing panel.
     * @return FileMenu
     */
    private JMenu fileMenu(final DrawingPanel thePanel) {
        myTrue = false;
        
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F); 
        
        
        
        final JMenuItem fileClear = new JMenuItem("Clear");
        fileClear.setMnemonic(KeyEvent.VK_C);
        fileClear.setEnabled(thePanel.isThereShapes());
        
        fileMenu.addChangeListener((theEvent) -> {
            myTrue = thePanel.isThereShapes();
            fileClear.setEnabled(myTrue);
        });
        
        fileClear.addActionListener((theEvent) -> {
            if (fileClear.isEnabled()) {
                thePanel.clearShapes();
                thePanel.isClear();
            }
        });
        
        fileMenu.add(fileClear);
        fileMenu.addSeparator();
        final JMenuItem fileQuit = new JMenuItem("Quit");
        fileQuit.setMnemonic(KeyEvent.VK_Q);
        fileQuit.addActionListener((theEvent) -> {
            System.exit(0);
        });
        fileMenu.add(fileQuit);
        
        return fileMenu;
    }
    
    /**
     * This method makes everything inside the sub menu "option".
     * @param thePanel this is the drawing panel.
     * @return otionMenu
     */
    private JMenu optionMenu(final DrawingPanel thePanel) {
        final JMenu optionMenu = new JMenu("Option");
        optionMenu.setMnemonic(KeyEvent.VK_O);
        
        final JMenu optionThickness = new JMenu("Thickness");
        optionThickness.setMnemonic(KeyEvent.VK_T);
        
        optionThickness.add(thicknessSlider(thePanel));
        optionMenu.add(optionThickness);
        
        optionMenu.addSeparator(); //add line in the middle
        final JMenuItem optionDrawColor = new JMenuItem(DRAW_COLOR);
        optionDrawColor.setMnemonic(KeyEvent.VK_D);
        optionDrawColor.setIcon(new ColorIcon(UW_PURPLE));
        optionDrawColor.addActionListener((theEvent) -> {
            final Color drawColor = JColorChooser.showDialog(optionDrawColor, 
                                                             DRAW_COLOR, 
                                                       UW_PURPLE);
            thePanel.setDrawColor(drawColor);
            optionDrawColor.setIcon(new ColorIcon(drawColor));
            repaint();
        });
        optionMenu.add(optionDrawColor);
        
        
        final JMenuItem optionFillColor = new JMenuItem(FILL_COLOR);
        optionFillColor.setMnemonic(KeyEvent.VK_F);
        optionFillColor.setIcon(new ColorIcon(UW_GOLD));
        optionFillColor.addActionListener((theEvent) -> {
            final Color fillColor = JColorChooser.showDialog(optionFillColor, 
                                                             FILL_COLOR, 
                                                             UW_GOLD);
            thePanel.setFillColor(fillColor);
            optionFillColor.setIcon(new ColorIcon(fillColor));
            repaint();
        });
        
        optionMenu.add(optionFillColor);
        optionMenu.addSeparator(); //add line in the middle
        final JCheckBox optionFill = new JCheckBox("Fill");
        optionFill.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final boolean isTruelyFilled = optionFill.isSelected();    
                    thePanel.isFill(isTruelyFilled);
            }
        });

        optionMenu.add(optionFill);
        
        return optionMenu;
    }
    
    /**
     * This is the thickness slider for the width.
     * @param thePanel this is the drawing panel
     * @return slider
     */
    private JSlider thicknessSlider(final DrawingPanel thePanel) {
        final JSlider slider = new JSlider(JSlider.HORIZONTAL,
                                       0, 20, 4);
        slider.setMajorTickSpacing(MAJOR_TICK_SPACING);
        slider.setMinorTickSpacing(MINOR_TICK_SPACING);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final int value = slider.getValue();
                if (value > 0) {
                    thePanel.setStrokeWidth(value);
                }
            }
        });
        return slider;
    }
    
    /**
     * This method makes everything inside the sub menu "tools".
     * @param theToolActions the actions list
     * @return tools
     */
    public JMenu toolsMenu(final List<Action> theToolActions) {
        final JMenu toolsMenu = new JMenu("Tools");
        toolsMenu.setMnemonic(KeyEvent.VK_T);

        final ButtonGroup btngrp = new ButtonGroup();
        try {
            for (final Action ca : theToolActions) {
                final JRadioButtonMenuItem btn = new JRadioButtonMenuItem(ca);
                btngrp.add(btn);
                toolsMenu.add(btn);

            }
        } catch (final NullPointerException e) {
            toolsMenu.add(new JRadioButton());
        }
        return toolsMenu;
    }
    
    /**
     * This method makes everything inside the sub menu "help".
     * @return help
     */
    public JMenu helpMenu() {
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        final JMenuItem about = new JMenuItem("About...");
        about.setMnemonic(KeyEvent.VK_A);
        about.addActionListener((theEvent) -> {
            final ImageIcon img = new ImageIcon("images//Lion.png");
            final String sb = "TCSS 305\nAutumn 2016\nKeldon Fischer\n";
            JOptionPane.showMessageDialog(null, sb, 
                                          "About", 
                                          JOptionPane.PLAIN_MESSAGE, img);
        });
        helpMenu.add(about);
        
        return helpMenu;
    }
    
    /**
     * This class makes my icons for the draw and fill JMenuItems.
     * @author Keldon Fischer
     * @version 11/20/2016
     *
     */
    final class ColorIcon implements Icon {
        
        /**
         * Initializes the color.
         */
        private Color myColor;

        /**
         * This is the Default constructor for the color icon.
         * @param theColor this is the icon's main color.
         */
        private ColorIcon(final Color theColor) {
            this.myColor = theColor;
        }

        @Override
        public void paintIcon(final Component theComponent, final Graphics theGraphics, 
                              final int theX, final int theY) {
            final Graphics2D g2D = (Graphics2D) theGraphics;
            final Color tempColor = g2D.getColor();
            g2D.setColor(Color.BLACK);
            g2D.setStroke(new BasicStroke(2));
            g2D.drawRect(theX, theY, getIconWidth(), getIconHeight());
            g2D.setColor(myColor);
            g2D.fillRect(theX, theY, getIconWidth(), getIconHeight());
            g2D.setColor(tempColor);
        }

        /**
         * This is the getter for the icon's width.
         * @return the width
         */
        public int getIconWidth() {
            return ICON_SIZE;
        }

        /**
         * This is the getter for the icon's height.
         * @return the height
         */
        public int getIconHeight() {
            return ICON_SIZE;
        }

    }
    
}
