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
 * @author Boxx
 */
public class SwingEquipScreen extends SwingItemBasedScreen{
    
    public SwingEquipScreen(Actor player, JFrame frame){
        super(frame, player);
    }

    @Override
    protected String getVerb() {
        return "wear or wield";
    }

    @Override
    protected boolean isAcceptable(Item item) {
        if(item.isWearable() && !item.isEquipped()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected Screen use(Item item) {
        player.equip(item);
        return null;
    }
}
