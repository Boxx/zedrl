/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;

import javax.swing.JFrame;
import zedrl.actors.Actor;
import zedrl.actors.Item;

/**
 *
 * @author Brandon
 */
public class SwingQuaffScreen extends SwingItemBasedScreen {
    
    public SwingQuaffScreen(Actor player, JFrame frame){
        super(frame,player);
    }

    @Override
    protected String getVerb() {
        return "quaff";
    }

    @Override
    protected boolean isAcceptable(Item item) {
        if(item.getQuaffEffect() != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected Screen use(Item item) {
        player.quaff(item);
        return null;
    }
}
