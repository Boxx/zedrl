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
class PlayScreen implements Screen
{

    public PlayScreen()
    {
    }

    @Override
    public void displayOutput(AsciiPanel term)
    {
        term.write("LOOK AT ALL THE FUN YOU ARE HAVING!", 1, 1);
        term.writeCenter("[Esc] kills you and pressing [ENTER] will win the game!", 22);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key)
    {
        switch (key.getKeyCode())
        {
            case KeyEvent.VK_ESCAPE:
                return new LoseScreen();
            case KeyEvent.VK_ENTER:
                return new WinScreen();
        }
        return this;
    }
}
