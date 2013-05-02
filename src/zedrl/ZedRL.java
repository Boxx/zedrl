/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl;

import asciiPanel.AsciiPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import squidpony.squidgrid.gui.swing.SwingPane;
import zedrl.views.Screen;
import zedrl.views.StartScreen;
import zedrl.views.PlayScreen;

/**
 *
 * @author Brandon
 */
public class ZedRL extends JFrame implements KeyListener
{


    private Screen screen;
    private SwingPane display;
    /**
     * @param args the command line arguments
     */
    public ZedRL()
    {
        
        super("ZedRL");
        getContentPane().setBackground(Color.black);
        display = new SwingPane(50,24, new Font("Arial Black", Font.PLAIN, 14));
        setLayout(new BorderLayout());
        add(display);
        pack();
        Dimension size = getSize();
        setMinimumSize(size);
        screen = new PlayScreen(this);
        addKeyListener(this);
        repaint();
        
    }

    public void repaint() {
        //clearDisplay();
        screen.displayOutput(display);
        super.repaint();

    }
    public void clearDisplay() {
        for (int x = 0; x < display.getCellWidth(); x++) {
            for (int y = 0; y < display.getCellHeight(); y++) {
                display.placeCharacter(x, y, ' ', Color.BLACK);
            }
        }
        display.refresh();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        screen = screen.respondToUserInput(e);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        
    }
    

    public static void main(String[] args)
    {
        // TODO code application logic here
        ZedRL app = new ZedRL();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setBackground(Color.BLACK);
        app.setVisible(true);
        app.setLocationRelativeTo(null);

    }
}
