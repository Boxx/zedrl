/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

/**
 *
 * @author Boxx
 */
public class Effect {
    
    protected int duration;

    public Effect(int duration) {
        this.duration = duration;
    }
    
    public boolean expired(){
        return duration < 1;
    }
    public void update(Actor actor){
        duration--;
    }
    public void applyEffect(Actor actor){
        
    }
    public void endEffect(Actor actor){
        
    }
    
}
