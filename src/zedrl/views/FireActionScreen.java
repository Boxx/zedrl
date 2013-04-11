/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;

import zedrl.actors.Actor;
import zedrl.dungeon.Line;
import zedrl.dungeon.Location;

/**
 *
 * @author Brandon
 */
public class FireActionScreen extends TargetingScreen{

    public FireActionScreen(Actor player,int sx, int sy) {
        super(player, "Fire " + player.getWeapon().getName() + " at?", sx, sy);
    }
    public boolean isAcceptable(int x, int y){
        if (!player.hasSightOf(x, y, player.getPosZ())){
            return false;
        }
        for(Location p : new Line(player.getPosX(),player.getPosY(),x,y)){
            if(!player.realTile(p.getX(), p.getY(), player.getPosZ()).isPassable()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void chooseDungeonCoordinate(int x, int y, int displayWidth, int displayHeight) {
        Actor target = player.findActor(x, y, player.getPosZ());
        
        if(target == null){
            player.sendMessage("There's no one there to fire at");
        }else{
            player.rangedAttack(target);
        }
    }
    
}
