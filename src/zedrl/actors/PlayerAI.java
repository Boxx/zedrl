/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

import java.util.ArrayList;
import zedrl.dungeon.Tile;

/**
 *
 * @author Boxx
 */
public class PlayerAI extends ActorAI {
    
    private ArrayList<String> messageQueue;
    
    public PlayerAI(Actor actor, ArrayList<String> messageQueue){
        
        super(actor);
        this.messageQueue = messageQueue;
    }
    
    @Override
    public void enterTile(int x, int y, int z, Tile tile){
            
        if (tile.isPassable()){
            actor.setPosX(x);
            actor.setPosY(y);
            actor.setPosZ(z);
        }else{
            
        }
    }
    
    @Override
    public void getMessage(String msg){
        messageQueue.add(msg);
    }
    
}
