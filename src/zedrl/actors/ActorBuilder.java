/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

import asciiPanel.AsciiPanel;
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
    
    public Actor newPlayer(){
        Actor player = new Actor(dungeon, '@', AsciiPanel.brightWhite);
        dungeon.addActor(player);
        new PlayerAI(player);
        return player;
    }
    
    public Actor newFungus(){
        Actor fungus = new Actor(dungeon, 'f', AsciiPanel.green);
        dungeon.addActor(fungus);
        new FungusAI(fungus);
        return fungus;
    }
}
