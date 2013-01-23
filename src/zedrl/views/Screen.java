/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;
import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
/**
 *
 * @author Brandon
 */
public interface Screen
{
    public void displayOutput(AsciiPanel term);
    
    public Screen respondToUserInput(KeyEvent key);
}
