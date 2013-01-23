/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.dungeon;

/**
 *
 * @author Brandon
 */
public class DungeonBuilder
{
    private int mapwidth;
    private int mapheight;

    private Tile[][] tiles;
    
    public DungeonBuilder(int mapwidth, int mapheight){
        this.mapwidth = mapwidth;
        this.mapheight = mapheight;
        this.tiles = new Tile[mapwidth][mapheight];
    }
    public Dungeon build(){
        return new Dungeon(tiles);
    }
}
