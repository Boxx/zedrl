/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import squidpony.squidgrid.gui.swing.SwingPane;

/**
 *
 * @author Brandon
 */
class WinScreen implements Screen, KeyListener {
    
    private JFrame frame;
    public WinScreen(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void displayOutput(SwingPane display) {
        for(int i = 0; i < display.getGridHeight(); i++){
            for(int j = 0; j < display.getGridWidth(); j++){
                display.clearCell(j, i);
            }
        }
        display.placeHorizontalString(5, 5, "You won the game!", Color.WHITE, Color.BLACK);
        display.placeHorizontalString(5, 6, "Press [Enter] to play again", Color.WHITE, Color.BLACK);
        display.refresh();
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ENTER){
            return new PlayScreen(frame);
        }else{
            return this;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            respondToUserInput(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
