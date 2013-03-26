/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;
import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import squidpony.squidgrid.gui.swing.SwingPane;
/**
 *
 * @author Brandon
 */
public interface Screen
{
    public void displayOutput(SwingPane display);
    
    public Screen respondToUserInput(KeyEvent key);
}
