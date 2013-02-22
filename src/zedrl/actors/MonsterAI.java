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
public class MonsterAI {
    private Actor actor;
    
    public MonsterAI(Actor actor){
        this.actor = actor;
        this.actor.setAI(this);
    }
    public void enterTile(int x, int y, Tile tile){
        
    }
}
