/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.dungeon;

import squidpony.squidcolor.SColor;
import zedrl.dungeon.Location;

/**
 *
 * @author Brandon
 */
public class Tile
{
    

    
    private char glyph;
    private SColor color;
    private Location connection;
    private String details;
    public boolean passable;
    private float resistance = 0f;

    
    public static Tile floor(){
        return new Tile('.', SColor.SLATE_GRAY,0f, true, "The cold dungeon floor");
    }
    public static Tile wall(){
        return new Tile((char)2588, SColor.YELLOW,1f, false, "An earthen wall carved by some terrible force");
    }
    public static Tile oob(){
        return new Tile('x', SColor.BLACK,1f, false, "???");
    }
    public static Tile up(){
        return new Tile('<', SColor.YELLOW,0f, true, "A rock staircase leading you back to the previous floor");
    }
    public static Tile down(){
        return new Tile('>', SColor.YELLOW,0f, true, "A rock staircase leading you down to the next level");
    }
    public static Tile unknown(){
        return new Tile(' ', SColor.WHITE,1f, true, "Out of your vision");
    }
    public char getGlyph()
    {
        return glyph;
    }

    public SColor getColor()
    {
        return color;
    }

    Tile(char glyph, SColor color, float resistance, boolean passable, String details)
    {
        this.glyph = glyph;
        this.color = color;
        this.passable = passable;
        this.resistance = resistance;
        this.details = details;
        
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

    public float getResistance() {
        return resistance;
    }

    public String getDetails() {
        return details;
    }
    
}
