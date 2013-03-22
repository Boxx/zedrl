/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.dungeon;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Brandon
 */
public class Line implements Iterable<Location> {
    
    private ArrayList<Location> points;
    
    public Line(int xa, int ya, int xb, int yb){
        points = new ArrayList<>();
        
        int dx = Math.abs(xb - xa);
        int dy = Math.abs(yb - ya);
        
        int sx = xa < xb ? 1 : -1;
        int sy = ya < yb ? 1 : -1;
        int err = dx - dy;
        
        while(true){
            points.add(new Location(xa, ya, 0));
            
            if (xa == xb && ya == yb)
                break;
            
            
            int e2 = err * 2;
            if (e2 > -dx){
                err -= dy;
                xa += sx;
            }
            if(e2 < dx){
                err += dx;
                ya += sy;
            }
        }
        
    }

    @Override
    public Iterator<Location> iterator() {
        return points.iterator();
    }

    public ArrayList<Location> getPoints() {
        return points;
    }
    
}
