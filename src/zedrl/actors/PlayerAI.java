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
    private FieldOfView FOV;
    
    public PlayerAI(Actor actor, ArrayList<String> messageQueue, FieldOfView FOV){
        
        super(actor);
        this.messageQueue = messageQueue;
        this.FOV = FOV;
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
    
    public boolean canSee(int vx, int vy, int vz){
        return FOV.isVisable(vx, vy, vz);
    }

    @Override
    public Tile rememberedTile(int x, int y, int z) {
        return FOV.getTile(x, y, z);
    }
    
    
}
