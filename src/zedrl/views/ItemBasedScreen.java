/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import squidpony.squidcolor.SColor;
import squidpony.squidgrid.gui.swing.SwingPane;
import zedrl.actors.Actor;
import zedrl.actors.Item;

/**
 *
 * @author Brandon
 */
public abstract class ItemBasedScreen implements Screen, KeyListener {
    
    protected Actor player;
    private String letters;
    private JFrame frame;
    private SwingPane display;
    private JPanel panel;
    private JTextPane text;

    public ItemBasedScreen(Actor player,JFrame frame) {
        this.player = player;
        this.frame = frame;
        this.letters = "abcdefghijklmnopqrstuvwxyz";
    }
    
    protected abstract String getVerb();
    protected abstract boolean isAcceptable(Item item);
    protected abstract Screen use(Item item);
    @Override
    public void displayOutput(SwingPane display) {
        
       
        
        ArrayList<String> lines = getList();
        int y = 4;
        int x = 1;
        if (lines.size() > 0){
            
            clearDisplay(display);
        }
        for (String line : lines){
            if(y == display.getGridHeight()){
                y = 4;
                x = 25;
            }
            display.placeHorizontalString(x, y++, line, Color.WHITE, Color.BLACK);
        }
        player.sendMessage("What would you like to " + getVerb() + "? [Select item by pressing its associated key]");
        display.repaint();
        display.refresh();
        
        
    }

    @Override
    public Screen respondToUserInput(KeyEvent key)
    {
        char c = key.getKeyChar();
        
        Item[] items = player.getInventory().getItems();
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

    public ArrayList<String> getList() {
        ArrayList<String> lines = new ArrayList<>();
        Item[] inventory = player.getInventory().getItems();
        
        for(int i = 0; i < inventory.length; i++){
            Item item = inventory[i];
            if(item != null){
                System.out.println(item.getStacks());
            }
            
            if(item == null || !isAcceptable(item)){
                continue;
            }
            if(item.isStackable() && item.getStacks() > 1){
                String line = letters.charAt(i) + "  -  " + item.getStacks() + " " + item.getName() + "s";
                lines.add(line);
            }else{
                String line = letters.charAt(i) + "  -  " + item.getName();
                if(item.isEquipped()){
                    line += " (equipped)";
                }
                lines.add(line);
            }
            
            
        }
        return lines;
    }
    public void clearDisplay(SwingPane display) {
        for (int x = 0; x < display.getGridWidth(); x++) {
            for (int y = 0; y < display.getGridHeight(); y++) {
                display.placeCharacter(x, y, ' ', Color.BLACK);
            }
        }
        display.placeHorizontalString(1, 1, "Inventory", SColor.WHITE, SColor.BLACK);
        display.placeHorizontalString(1, 2, "_________________________________________________", SColor.WHITE, SColor.BLACK);
        /*
        for (int x = 0; x < display.getGridWidth(); x++) {
            for (int y = 0; y < display.getGridHeight(); y++) {
                if(x == 0 && y == 0){
                    display.placeCharacter(x, y, (char)2544, SColor.WHITE);
                }
                else if(x == 0 && y == display.getGridHeight()){
                    display.placeCharacter(x, y, (char)255, SColor.WHITE);
                }
                else if(x == 0 || y == display.getGridHeight()){
                    display.placeCharacter(x, y, (char)2550, SColor.WHITE);
                }
                else if(x == display.getGridWidth() && y == 0){
                    display.placeCharacter(x, y, (char)2557, SColor.WHITE);
                }
                else if(x == display.getGridWidth() && y == display.getGridHeight()){
                    display.placeCharacter(x, y, (char)255, SColor.WHITE);
                }
            }
        }
        * 
        */
        display.refresh();
    }
    
}
