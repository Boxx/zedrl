/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

/**
 *
 * @author Boxx
 */
public class FungusAI extends ActorAI {
    
    private ActorBuilder ab;
    private int counter;
    
    public FungusAI(Actor actor, ActorBuilder ab){
        super(actor);
        this.ab = ab;
    }
    
    @Override
    public void update(){
        
        if(counter < 5 && Math.random() < .001){
            makeBabies();
        }
        
    }

    private void makeBabies() {
        int x = actor.getPosX() + (int)(Math.random() * 11) - 5;
        int y = actor.getPosY() + (int)(Math.random() * 11) - 5;
        int z = actor.getPosZ();
        if(!actor.canEnter(x, y, z)){
            return;
        }
        
        Actor baby = ab.newFungus();
        baby.setPosX(x);
        baby.setPosY(y);
        counter++;
    }
}
