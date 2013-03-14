/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.dungeon;

import java.awt.Color;
import asciiPanel.AsciiPanel;

/**
 *
 * @author Brandon
 */
public enum Tile
{

    FLOOR((char) 250, AsciiPanel.yellow),
    WALL((char) 177, AsciiPanel.white),
    OOB('x', AsciiPanel.brightBlack),
    UP('<', AsciiPanel.white),
    DOWN('>', AsciiPanel.white);
    
    private char glyph;
    private Color color;

    public char getGlyph()
    {
        return glyph;
    }

    public Color getColor()
    {
        return color;
    }

    Tile(char glyph, Color color)
    {
        this.glyph = glyph;
        this.color = color;
    }
    public boolean isPassable(){
        return this != WALL && this != OOB;
    }
}
