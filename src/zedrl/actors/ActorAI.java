/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

/**
 *
 * @author Boxx
 */
class ActorAI {
    private Actor actor;
    
    public ActorAI(Actor actor){
        this.actor = actor;
        this.actor.setAI(this);
    }
}
