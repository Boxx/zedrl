/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import zedrl.actors.Actor;
import zedrl.actors.Item;

/**
 *
 * @author Brandon
 */
public class SwingThrowScreen extends SwingItemBasedScreen{
    
    private int tx;
    private int ty;
    public SwingThrowScreen(JFrame frame, Actor player, int tx, int ty) {
        super(frame, player);
        this.tx = tx;
        this.ty = ty;
    }
    
    
    @Override
    protected String getVerb() {
        return "throw";
    }

    @Override
    protected boolean isAcceptable(Item item) {
        return true;
    }

    @Override
    protected Screen use(Item item) {
        return new ThrowActionScreen(player, tx, ty, item);
        
    }

    
    
}
