/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl;

import asciiPanel.AsciiPanel;
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
public class ZedRL implements KeyListener
{


    public Screen screen;
    /**
     * @param args the command line arguments
     */
    public ZedRL()
    {
        screen = new StartScreen();
        
    }

    public void repaint() {
        

    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        
    }
    

    public static void main(String[] args)
    {
        // TODO code application logic here
        ZedRL app = new ZedRL();
        

    }
}
