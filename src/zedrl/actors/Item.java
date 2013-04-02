/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

import squidpony.squidcolor.SColor;

/**
 *
 * @author Brandon
 */
public class Item {
    
    private char glyph;
    private SColor color;
    private String name;
    private boolean isStackable;
    private int stacks;

    public Item(char glyph, SColor color, String name, boolean isStackable) {
        this.glyph = glyph;
        this.color = color;
        this.name = name;
        this.isStackable = isStackable;
        this.stacks = 1;
        
    }

    
    
    public SColor getColor() {
        return color;
    }

    public char getGlyph() {
        return glyph;
    }

    public String getName() {
        return name;
    }
    public boolean isStackable(){
        return isStackable;
    }
    public int getStacks(){
        return stacks;
    }
    public void addStack(){
        stacks++;
    }
    public void removeStack(){
        stacks--;
    }
    public void setStack(int x){
        stacks += x;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + '}';
    }
    
    
}
