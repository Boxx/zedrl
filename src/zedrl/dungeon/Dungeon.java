/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.dungeon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import squidpony.squidcolor.SColor;
import zedrl.actors.Actor;
import zedrl.actors.Item;
import zedrl.utilities.Roller;

/**
 *
 * @author Brandon
 */
public class Dungeon {

    private Tile[][][] tiles;
    //private List<Item>[][][] items;
   // private List<List<List<Item>>> items;
    private ArrayList<Item>[][][] items;
    private Room[] roomList;
    private ArrayList<Actor> actorList;
    private int width;
    private int height;
    private int depth;

    

    public Dungeon(Tile[][][] tiles) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        this.depth = tiles[0][0].length;
        this.actorList = new ArrayList<Actor>();
        this.items = new ArrayList[width][height][depth];

    }
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    public List<Item> getItems(int x, int y, int z){
        return items[x][y][z];
    }

    public void addActor(Actor actor, int z) {
        int x;
        int y;

        do {
            x = (int) (Math.random() * width);
            y = (int) (Math.random() * height);
        } while (!tile(x, y, z).isPassable() || getActor(x, y, z) != null);

        actor.setPosX(x);
        actor.setPosY(y);
        actor.setPosZ(z);
        actorList.add(actor);
    }
    public void addItem(Item item, int z){
        int x;
        int y;

        do {
            x = (int) (Math.random() * width);
            y = (int) (Math.random() * height);
        } while (!tile(x, y, z).isPassable() || getItems(x, y, z) != null);
        
        items[x][y][z] = new ArrayList<>();
        items[x][y][z].add(item);
    }

    public Actor getActor(int x, int y, int z) {
        for (Actor actor : actorList) {
            if (actor.getPosX() == x && actor.getPosY() == y && actor.getPosZ() == z) {
                return actor;
            }
        }
        return null;
    }

    public void deleteActor(Actor occupant) {

        actorList.remove(occupant);
    }
    public void deleteItem(List<Item> items, Item item, int x, int y, int z){
        for (int i = 0; i < items.size(); i++){
            if (items.get(i) == item){
                items.remove(i);
                break;
            }
        }
        if(items.isEmpty()){
            this.items[x][y][z] = null;
        }
    }

    public void update() {
        ArrayList<Actor> updateList = new ArrayList<>(actorList);
        for (Actor actor : updateList) {
            actor.update();
        }
    }
    /*
     * Method for getting a tile at a given position while checking its bounds
     */

    public Tile tile(int x, int y, int z) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return Tile.oob();
        } else {
            return tiles[x][y][z];
        }
    }
    /*
     * Returns the tile glyph at a given position
     */

    public char glyph(int x, int y, int z) {
        Actor actor = getActor(x, y, z);
        
        if(actor != null){
            return actor.getGlyph();
        }
        if(getItems(x,y,z) != null){
            return getItems(x,y,z).get(0).getGlyph();
        }
        return tile(x,y,z).getGlyph();
    }
    /*
     * Returns the tile color at a given position
     */

    public SColor color(int x, int y, int z) {
        Actor actor = getActor(x, y, z);
        if(actor != null){
            return actor.getColor();
        }
        if(getItems(x,y,z) != null){
            return getItems(x,y,z).get(0).getColor();
        }
        return tile(x,y,z).getColor();
    }

    public int getDepth() {
        return depth;
    }

}
