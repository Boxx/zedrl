/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

/**
 *
 * @author Brandon
 */
public abstract class LevelUpOption {
    
    private String name;
    
    public LevelUpOption(String name){
        this.name = name;
    }
    public abstract void invoke(Actor actor);

    public String getName() {
        return name;
    }
    
}
