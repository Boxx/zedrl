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

    public Item(char glyph, SColor color, String name) {
        this.glyph = glyph;
        this.color = color;
        this.name = name;
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

    @Override
    public String toString() {
        return "Item{" + "name=" + name + '}';
    }
    
    
}
