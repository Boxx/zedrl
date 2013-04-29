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
public abstract class SwingItemBasedScreen extends JPanel implements Screen, KeyListener  {
    
    protected Actor player;
    protected String letters;
    private JFrame frame;
    private SwingPane display;
    
    protected abstract String getVerb();
    protected abstract boolean isAcceptable(Item item);
    protected abstract Screen use(Item item);
    /**
     * Creates new form TestScreen
     */
    public SwingItemBasedScreen(JFrame frame,Actor player) {
        this.player = player;
        this.frame = frame;
        this.letters = "abcdefghijklmnopqrstuvwxyz";
        initComponents();
    }
    public void displayOutput(SwingPane display) {
        display.setVisible(false);
        this.setBounds(display.bounds());
        frame.add(this);
        
        
        ArrayList<String> lines = getList();
        int y = 4;
        int x = 1;
        if (lines.size() >= 0){
            clearDisplay();
        }
        for (String line : lines){
            
            text.append("   " + line + "\n");
            
        }
        player.sendMessage("What would you like to " + getVerb() + "? [Select item to drop by pressing its associated key]");
        this.repaint();
        
        
    }
    public Screen respondToUserInput(KeyEvent key)
    {
        char c = key.getKeyChar();
        
        Item[] items = player.getInventory().getItems();
        if(letters.indexOf(c) > -1 && items.length > letters.indexOf(c) &&
                items[letters.indexOf(c)] != null && isAcceptable(items[letters.indexOf(c)])){
            frame.remove(this);
            return use(items[letters.indexOf(c)]);
        }else if(key.getKeyCode() == KeyEvent.VK_ESCAPE){
            frame.remove(this);
            return null;
        }else{
            return this;
        }
    }
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

    private ArrayList<String> getList() {
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
    public void clearDisplay() {
        text.setText("");
        text.append("Inventory\n");
        text.append("_____________________________________________\n");
        //display.placeHorizontalString(1, 1, "Inventory", SColor.WHITE, SColor.BLACK);
        //display.placeHorizontalString(1, 2, "_________________________________________________", SColor.WHITE, SColor.BLACK);
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();

        text.setBackground(new java.awt.Color(0, 0, 0));
        text.setColumns(20);
        text.setForeground(new java.awt.Color(255, 255, 255));
        text.setRows(5);
        text.setFocusable(false);
        text.setRequestFocusEnabled(false);
        jScrollPane2.setViewportView(text);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea text;
    // End of variables declaration//GEN-END:variables
}
