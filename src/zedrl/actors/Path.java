/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

import java.util.ArrayList;
import zedrl.dungeon.Location;

/**
 *
 * @author Brandon
 */
public class Path {
    
    private static Pather pather = new Pather();
    private ArrayList<Location> locs;
    
    public Path(Actor actor, int x, int y){
        locs = pather.findPath(actor, new Location(actor.getPosX(),actor.getPosY(),actor.getPosZ()), new Location(x,y,actor.getPosZ()), 300);
    }

    public ArrayList<Location> getLocs() {
        return locs;
    }
    
    
    
}
