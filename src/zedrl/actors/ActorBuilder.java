/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

import asciiPanel.AsciiPanel;
import java.util.ArrayList;
import squidpony.squidcolor.SColor;
import zedrl.dungeon.Dungeon;

/**
 *
 * @author Boxx
 */
public class ActorBuilder {
    private Dungeon dungeon;
    
    public ActorBuilder(Dungeon dungeon){
        this.dungeon = dungeon;
    }
    
    public Actor newPlayer(ArrayList<String> messageQueue, FieldOfView FOV){
        Actor player = new Actor(dungeon, '@', SColor.WHITE, "Zedman", 100, 25, 5, 8);
        dungeon.addActor(player, 0);
        new PlayerAI(player, messageQueue, FOV);
        return player;
    }
    
    public Actor newFungus(int z){
        Actor fungus = new Actor(dungeon, 'f', SColor.GREEN, "fungus", 10, 0, 0, 0);
        dungeon.addActor(fungus, z);
        new FungusAI(fungus, this);
        return fungus;
    }
    
    public Actor newGoblin(int z){
        
        Actor goblin = new Actor(dungeon, 'g', SColor.RED, "goblin", 15, 5, 1, 4);
        dungeon.addActor(goblin, 0);
        
        return null;
        
    }
    
    public Actor newBat(int z){
        
        Actor bat = new Actor(dungeon, 'b', SColor.BROWNER, "bat", 5, 1, 1, 10);
        dungeon.addActor(bat, z);
        new BatAI(bat);
        return bat;
        
        
    }
}
