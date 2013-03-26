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
    public boolean passable;
    private float resistance = 0f;

    
    public static Tile floor(){
        return new Tile('.', SColor.SLATE_GRAY,0f, true);
    }
    public static Tile wall(){
        return new Tile('#', SColor.SLATE_GRAY,1f, false);
    }
    public static Tile oob(){
        return new Tile('x', SColor.BLACK,1f, false);
    }
    public static Tile up(){
        return new Tile('<', SColor.YELLOW,0f, true);
    }
    public static Tile down(){
        return new Tile('>', SColor.YELLOW,0f, true);
    }
    public static Tile unknown(){
        return new Tile(' ', SColor.WHITE,1f, true);
    }
    public char getGlyph()
    {
        return glyph;
    }

    public SColor getColor()
    {
        return color;
    }

    Tile(char glyph, SColor color, float resistance, boolean passable)
    {
        this.glyph = glyph;
        this.color = color;
        this.passable = passable;
        this.resistance = resistance;
        
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
    
}
