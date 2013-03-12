/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.dungeon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import zedrl.actors.Actor;
import zedrl.utilities.Roller;

/**
 *
 * @author Brandon
 */
public class Dungeon {

    private Tile[][] tiles;
    private Room[] roomList;
    private ArrayList<Actor> actorList;
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
        }
        while(!tile(x,y).isPassable() || getActor(x,y) != null);
        
        actor.setPosX(x);
        actor.setPosY(y);
        actorList.add(actor);
    }
    
    public Actor getActor(int x, int y){
        for (Actor actor : actorList){
            if (actor.getPosX() == x && actor.getPosY() == y){
                return actor;
            }
        }
        return null;
    }
    
    public void delete(Actor occupant){
        
        actorList.remove(occupant);
    }
    
    public void update(){
        ArrayList<Actor> updateList = new ArrayList<>(actorList);
        for (Actor actor : updateList){
            actor.update();
        }
    }

    public Dungeon(Tile[][] tiles) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        this.actorList =  new ArrayList<Actor>();
        
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
