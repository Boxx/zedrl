/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import zedrl.dungeon.Location;


/**
 *
 * @author Brandon
 */
public class Pather {
    private ArrayList<Location> open;
    private ArrayList<Location> closed;
    private HashMap<Location,Location> parents;
    private HashMap<Location,Integer> totalCost;
    
    public Pather(){
        this.open = new ArrayList<>();
        this.closed = new ArrayList<>();
        this.parents = new HashMap<>();
        this.totalCost = new HashMap<>();
    }
    private int hCost(Location from, Location to){
        return Math.max(Math.abs(from.getX() - to.getX()),Math.abs(from.getY() - to.getY()));
    }
    private int costTo(Location from){
        return parents.get(from) == null ? 0 : (1 + costTo(parents.get(from)));
    }
    private int totalCost(Location from, Location to){
        if (totalCost.containsKey(from)){
            return totalCost.get(from);
        }
        int c = costTo(from) + hCost(from,to);
        totalCost.put(from, c);
        return c;
    }
    private void reParent(Location child, Location parent){
        parents.put(child, parent);
        totalCost.remove(child);
    }
    public ArrayList<Location> findPath(Actor actor, Location start, Location end, int max){
        open.clear();
        closed.clear();
        parents.clear();
        totalCost.clear();
        
        open.add(start);
        
        for(int t = 0; t < max && open.size() > 0; t++){
            Location closest = getClosestLoc(end);
            
            open.remove(closest);
            closed.add(closest);
            
            if(closest.equals(end)){
                return createPath(start, closest);
            }else{
                checkNeightbors(actor, end, closest);
            }
        }
        return null;
            
            
        }

    private Location getClosestLoc(Location end) {
        Location closest = open.get(0);
        for (Location notThis : open){
            if(totalCost(notThis, end) < totalCost(closest, end)){
                closest = notThis;
            }
        }
        return closest;
    }



    private void checkNeightbors(Actor actor, Location end, Location closest) {
        for (Location neighbor : closest.getAdjPoints()){
            if(closed.contains(neighbor) || !actor.canEnter(neighbor.getX(), neighbor.getY(), actor.getPosZ()) && !neighbor.equals(end)){
                continue;
            }
            if(open.contains(neighbor)){
                reParentNeighborIfValid(closest,neighbor);
            }else{
                reParentNeighbor(closest,neighbor);
            }
        }
    }

    private void reParentNeighborIfValid(Location closest, Location neighbor) {
        Location origin = parents.get(neighbor);
        double curCost = costTo(neighbor);
        reParent(neighbor,closest);
        double reParentedCost = costTo(neighbor);
        
        if(reParentedCost < curCost){
            open.remove(neighbor);
        }else{
            reParent(neighbor,origin);
        }
    }

    private void reParentNeighbor(Location closest, Location neighbor) {
        reParent(neighbor,closest);
        open.add(neighbor);
    }

    private ArrayList<Location> createPath(Location start, Location end) {
        ArrayList<Location> path = new ArrayList<>();
        
        while(!end.equals(start)){
            path.add(end);
            end = parents.get(end);
        }
        Collections.reverse(path);
        return path;
    }
}
