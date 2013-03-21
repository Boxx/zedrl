/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.dungeon;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Brandon
 */
public class Location {
    

    private int x;
    private int y;
    private int z;

    public Location(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.z != other.z) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.x;
        hash = 67 * hash + this.y;
        hash = 67 * hash + this.z;
        return hash;
    }
    
    public ArrayList<Location> getAdjPoints(){
        
        ArrayList<Location> localPoints = new ArrayList<>();
        
        
        for(int dx = -1; dx < 2; dx++){
            for(int dy = -1; dy < 2; dy++){
                if(dx == 0 && dy == 0){
                    continue;
                }
                localPoints.add(new Location(x+dx, y+dy, z));
            }
        }
        Collections.shuffle(localPoints);
        return localPoints;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Location{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }



    
    
    
}
