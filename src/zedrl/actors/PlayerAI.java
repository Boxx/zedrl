/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

import zedrl.dungeon.Tile;

/**
 *
 * @author Boxx
 */
public class PlayerAI extends ActorAI {
    
    public PlayerAI(Actor actor){
        
        super(actor);
    }
    
    @Override
    public void enterTile(int x, int y, Tile tile){
            
        if (tile.isPassable()){
            actor.setPosX(x);
            actor.setPosY(y);
        }else{
            
        }
    }
    
}
