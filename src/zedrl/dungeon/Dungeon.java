/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.dungeon;

import java.awt.Color;
import zedrl.actors.Actor;
import zedrl.utilities.Roller;

/**
 *
 * @author Brandon
 */
public class Dungeon {

    private Tile[][] tiles;
    private Room[] roomList;
    private int width;
    private int height;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    public void addActor(Actor actor){
        int x;
        int y;
        
        do{
            x = (int)(Math.random() * width);
            y = (int)(Math.random() * height);
            System.out.println(x);
            System.out.println(y);
        }
        while(!tile(x,y).isPassable());
        
        actor.setPosX(x);
        actor.setPosY(y);
    }

    public Dungeon(Tile[][] tiles, Room[] roomList) {
        this.tiles = tiles;
        this.roomList = roomList;
        this.width = tiles.length;
        this.height = tiles[0].length;
        
    }
    /*
     * Method for getting a tile at a given position while checking its bounds
     */

    public Tile tile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return Tile.OOB;
        } else {
            return tiles[x][y];
        }
    }
    /*
     * Returns the tile glyph at a given position
     */

    public char glyph(int x, int y) {
        return tile(x, y).getGlyph();
    }
    /*
     * Returns the tile color at a given position
     */

    public Color color(int x, int y) {
        return tile(x, y).getColor();
    }
}
