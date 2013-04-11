/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;

import zedrl.actors.Actor;
import zedrl.actors.Item;
import zedrl.dungeon.Line;
import zedrl.dungeon.Location;

/**
 *
 * @author Brandon
 */
public class ThrowActionScreen extends TargetingScreen {
    
    private Item item;
    
    public ThrowActionScreen(Actor player, int sx, int sy, Item item){
        super(player, "Throw " + item.getName() + " at?", sx, sy);
        this.item = item;
        
    }
    @Override
    public boolean isAcceptable(int x, int y){
        if (!player.hasSightOf(x, y, player.getPosZ())){
            return false;
        }
        for(Location p : new Line(player.getPosX(), player.getPosY(), x, y)){
            if(!player.realTile(p.getX(), p.getY(), player.getPosZ()).isPassable()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void chooseDungeonCoordinate(int x, int y, int displayWidth, int displayHeight) {
        player.throwItem(item, x, y, player.getPosZ());
    }
    
}
