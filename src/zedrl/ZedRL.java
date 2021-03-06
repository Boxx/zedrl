/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import zedrl.views.Screen;
import zedrl.views.StartScreen;

/**
 *
 * @author Brandon
 */
public class ZedRL extends JFrame implements KeyListener
{

    private AsciiPanel term;
    private Screen screen;

    /**
     * @param args the command line arguments
     */
    public ZedRL()
    {
        super();
        term = new AsciiPanel();
        add(term);
        pack();
        screen = new StartScreen();
        addKeyListener(this);
        repaint();
    }

    @Override
    public void repaint() {
        term.clear();
        screen.displayOutput(term);
        super.repaint();
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
        app.setVisible(true);

    }
}
