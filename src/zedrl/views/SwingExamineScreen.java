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
public class SwingExamineScreen extends SwingItemBasedScreen{
    
    public SwingExamineScreen(JFrame frame, Actor player){
        super(frame, player);
    }

    @Override
    protected String getVerb() {
        return "examine";
    }

    @Override
    protected boolean isAcceptable(Item item) {
        return true;
    }

    @Override
    protected Screen use(Item item) {
        String article = "aeiou".contains(item.getName().subSequence(0, 1)) ? "an " : "a ";
        player.sendMessage("It's " + article + item.getName() + "." + item.getDetails());
        return null;
    }
}
