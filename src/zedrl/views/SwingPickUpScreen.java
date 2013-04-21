/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import squidpony.squidgrid.gui.swing.SwingPane;
import zedrl.actors.Actor;
import zedrl.actors.Item;

/**
 *
 * @author Brandon
 */
public class SwingPickUpScreen extends ItemBasedScreen{
    private Item[] items;
    private String letters;
    public SwingPickUpScreen(JFrame frame, Actor player, Item[] items){
        super(player,frame);
        this.items = items;
        this.letters = "abcdefghijklmnopqrstuvwxyz";
    }
    @Override
    protected String getVerb() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected boolean isAcceptable(Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected Screen use(Item item) {
        return null;
    }


    @Override
    public Screen respondToUserInput(KeyEvent key) {
        char c = key.getKeyChar();
        
        if(letters.indexOf(c) > -1 && items.length > letters.indexOf(c) &&
                items[letters.indexOf(c)] != null && isAcceptable(items[letters.indexOf(c)])){
            return use(items[letters.indexOf(c)]);
        }else if(key.getKeyCode() == KeyEvent.VK_ESCAPE){
            return null;
        }else{
            return this;
        }
    }


    @Override
    public ArrayList<String> getList() {
        
        ArrayList<String> lines = new ArrayList<>();
        
        for(int i = 0; i < items.length; i++){
            Item item = items[i];
            
            if(item.isStackable() && item.getStacks() > 1){
                String line = letters.charAt(i) + "  -  " + item.getStacks() + " " + item.getName() + "s";
                lines.add(line);
            }else{
                String line = letters.charAt(i) + "  -  " + item.getName();
                lines.add(line);
            }
        }
        return lines;
    }
    
    
}
