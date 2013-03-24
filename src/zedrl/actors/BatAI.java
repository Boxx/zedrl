/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

/**
 *
 * @author Brandon
 */
public class BatAI extends ActorAI {
    
    public BatAI(Actor actor){
        super(actor);
    }
    public void update(){
        roam();
        roam();
    }
}
