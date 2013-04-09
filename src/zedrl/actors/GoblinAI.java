/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

import java.util.ArrayList;
import zedrl.dungeon.Location;

/**
 *
 * @author Boxx
 */
public class GoblinAI extends ActorAI {
    
    private Actor player;
    public GoblinAI(Actor actor, Actor player){
        super(actor);
        this.player = player;
    }
    @Override
    public void update(){

        if(actor.hasSightOf(player.getPosX(), player.getPosY(), player.getPosZ())){
            track(player);
        }else{
            roam();
        }
    }

    private void track(Actor target) {
        ArrayList<Location> locs = new Path(actor, target.getPosX(),target.getPosY()).getLocs();
        if(locs == null || locs.isEmpty()){
            return;
        }
        int mx = locs.get(0).getX() - actor.getPosX();
        int my = locs.get(0).getY() - actor.getPosY();
        actor.moveBy(mx, my, 0);
    }
}
