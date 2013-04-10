/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

import zedrl.dungeon.Line;
import zedrl.dungeon.Location;
import zedrl.dungeon.Tile;

/**
 *
 * @author Boxx
 */
public class ActorAI {
    protected Actor actor;
    
    public ActorAI(Actor actor){
        this.actor = actor;
        this.actor.setAI(this);
    }
    public void enterTile(int x, int y, int z, Tile tile){
        
        if(tile.isPassable()){
            actor.setPosX(x);
            actor.setPosY(y);
            actor.setPosZ(z);
        }else{
            
        }
    }
    public void roam(){
        int rx = (int)(Math.random() * 3) - 1;
        int ry = (int)(Math.random() * 3) - 1;
        
        Actor occupant = actor.findActor(actor.getPosX() + rx, actor.getPosY() + ry,actor.getPosZ());
        if(occupant != null && occupant.getGlyph() == actor.getGlyph()){
            return;
        }else{
            actor.moveBy(rx, ry, 0);
        }
    }

    public void update() {
        
    }
    
    public void getMessage(String msg){
        

    }

    public boolean hasSightOf(int x, int y, int z) {
        if (actor.getPosZ() != z){
            return false;
        }
        if ((actor.getPosX() - x) * (actor.getPosX() - x) + (actor.getPosY() - y) * 
                (actor.getPosY() - y) > actor.getVisionRad() * actor.getVisionRad()){
            return false;
        }
        
        for(Location p : new Line(actor.getPosX(), actor.getPosY(), x, y)){
            if(actor.lookAt(p.getX(), p.getY(), z).isPassable() || p.getX() == x && p.getY() == y){
                continue;
            }
            return false;
        }
        return true;
    }

    public void onLevelUp() {
        new LevelUpController().autoLevel(actor);
    }

}
