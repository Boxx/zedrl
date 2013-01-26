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
class LoseScreen implements Screen
{

    public LoseScreen()
    {
    }

    @Override
    public void displayOutput(AsciiPanel term)
    {
        term.write("You lost the game!", 1, 1);
        term.writeCenter("Press [ENTER] to play this awesome game again", 22);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key)
    {
        if (key.getKeyCode() == KeyEvent.VK_ENTER)
        {
            return new PlayScreen();
        } else
        {
            return this;
        }
    }
}
