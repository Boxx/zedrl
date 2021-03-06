/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

import asciiPanel.AsciiPanel;
import java.util.ArrayList;
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
    
    public Actor newPlayer(ArrayList<String> messageQueue){
        Actor player = new Actor(dungeon, '@', AsciiPanel.brightWhite, "Zedman", 100, 25, 5);
        dungeon.addActor(player);
        new PlayerAI(player, messageQueue);
        return player;
    }
    
    public Actor newFungus(){
        Actor fungus = new Actor(dungeon, 'f', AsciiPanel.green, "fungus", 10, 0, 0);
        dungeon.addActor(fungus);
        new FungusAI(fungus, this);
        return fungus;
    }
}
