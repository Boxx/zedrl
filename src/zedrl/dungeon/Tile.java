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
public class Tile
{
    

    
    private char glyph;
    private Color color;
    private Location connection;
    public boolean passable;

    
    public static Tile floor(){
        return new Tile((char) 250, AsciiPanel.yellow, true);
    }
    public static Tile wall(){
        return new Tile((char) 177, AsciiPanel.white, false);
    }
    public static Tile oob(){
        return new Tile('x', AsciiPanel.brightBlack, false);
    }
    public static Tile up(){
        return new Tile('<', AsciiPanel.white, true);
    }
    public static Tile down(){
        return new Tile('>', AsciiPanel.white, true);
    }
    public static Tile unknown(){
        return new Tile(' ', AsciiPanel.white, true);
    }
    public char getGlyph()
    {
        return glyph;
    }

    public Color getColor()
    {
        return color;
    }

    Tile(char glyph, Color color, boolean passable)
    {
        this.glyph = glyph;
        this.color = color;
        this.passable = passable;
    }
    public boolean isPassable(){
        return passable;
    }
    public boolean isUpStair(){
        return this.glyph == '<';
    }
    public boolean isDownStair(){
        return this.glyph == '>';
    }
    public boolean isStair(){
        return this.glyph == '<' || this.glyph == '>';
    }

    
    public void setConnection(int x, int y, int z){
        //System.out.println("Setting connection with coords x:" + x + " y: " + y + " z: " + z);
        connection = new Location(x,y,z);
        //System.out.println(connection.getX() +" "+ connection.getY() +" "+ connection.getZ());

    }
    public Location getConnection(){
        //System.out.println("inside the getter");
        //System.out.println(connection.getX() +" "+ connection.getY() +" "+ connection.getZ());
        return connection;
    }

}
