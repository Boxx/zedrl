/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

/**
 *
 * @author Brandon
 */
public class LevelUpController {
    
    private static LevelUpOption[] options = new LevelUpOption[]{
        new LevelUpOption("Increased strength"){
            public void invoke(Actor actor){
                actor.gainSTR();
            }
        },
        new LevelUpOption("Increased dexterity"){
            public void invoke(Actor actor){
                actor.gainDEX();
            }
        },
        new LevelUpOption("Increased intelligence"){
            public void invoke(Actor actor){
                actor.gainINT();
            }
        },
        new LevelUpOption("Increased constitution"){
            public void invoke(Actor actor){
                actor.gainCON();
            }
        }
        
    };
    void autoLevel(Actor actor){
        options[(int)(Math.random() * options.length)].invoke(actor);
    }

}
