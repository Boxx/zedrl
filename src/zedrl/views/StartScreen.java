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
public class StartScreen implements Screen
{

    @Override
    public void displayOutput(AsciiPanel term)
    {
        term.write("Zed RL", 1 , 1);
        term.writeCenter("Press [ENTER] to continue!", 22);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key)
    {
        if (key.getKeyCode() == KeyEvent.VK_ENTER){
            return new PlayScreen();
        }else{
            return this;
        }
    }

}
