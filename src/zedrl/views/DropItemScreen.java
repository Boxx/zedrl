/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;

import javax.swing.JFrame;
import squidpony.squidgrid.gui.swing.SwingPane;
import zedrl.actors.Actor;
import zedrl.actors.Item;

/**
 *
 * @author Brandon
 */
public class DropItemScreen extends ItemBasedScreen{
    
    public DropItemScreen(Actor player, JFrame frame){
        super(player, frame);
    }
    @Override
    protected String getVerb() {
        return "drop";
    }

    @Override
    protected boolean isAcceptable(Item item) {
        return true;
    }

    @Override
    protected Screen use(Item item) {
        player.drop(item);
        return null;
    }
    
    
}
