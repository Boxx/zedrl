/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import squidpony.squidgrid.gui.swing.SwingPane;

/**
 *
 * @author Boxx
 */
public class InventoryScreen implements Screen{
    
    private JFrame frame;
    private SwingPane playDisplay;
    
    public InventoryScreen(JFrame frame, SwingPane display){
        this.frame = frame;
        this.playDisplay = display;
        
        
        
        
    }
    @Override
    public void displayOutput(SwingPane display) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
