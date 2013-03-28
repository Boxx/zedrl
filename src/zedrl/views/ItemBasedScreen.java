/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;

import java.awt.Color;
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
public abstract class ItemBasedScreen implements Screen {
    
    protected Actor player;
    private String letters;

    public ItemBasedScreen(Actor player, JFrame frame, SwingPane display) {
        this.player = player;
        this.letters = "abcdefghijklmnopqrstuvwxyz";
    }
    
    protected abstract String getVerb();
    protected abstract boolean isAcceptable(Item item);
    protected abstract Screen use(Item item);
    @Override
    public void displayOutput(SwingPane display) {
        
        ArrayList<String> lines = getList();
        
        int y = display.getGridHeight() - lines.size();
        int x = 3;
        
        for (String line : lines){
            display.placeHorizontalString(x, y, line, Color.WHITE, Color.BLACK);
        }
        display.repaint();
        
        
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private ArrayList<String> getList() {
        ArrayList<String> lines = new ArrayList<>();
        Item[] inventory = player.getInventory().getItems();
        
        for(int i = 0; i < inventory.length; i++){
            Item item = inventory[i];
            
            if(item == null || !isAcceptable(item)){
                continue;
            }
            
            String line = letters.charAt(i) + "  -  " + item.getGlyph() + " " + item.getName();
            lines.add(line);
            
        }
        return lines;
    }
    
}
