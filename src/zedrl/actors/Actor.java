/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;
import java.awt.Color;
import zedrl.dungeon.Dungeon;
/**
 *
 * @author Boxx
 */
public class Actor {
    
    private Dungeon dungeon;
    private ActorAI AI;
    private int posX;
    private int posY;
    private char glyph;
    private Color color;

    public String name;
    public int totalHP;
    public int curHP;
    public int atkVal;
    public int defVal;
    

    public Actor(Dungeon dungeon, char glyph, Color color) {
        this.dungeon = dungeon;
        this.glyph = glyph;
        this.color = color;
    }

    public Actor(Dungeon dungeon, char glyph, Color color, String name, int totalHP, int atkVal, int defVal) {
        this.dungeon = dungeon;
        this.glyph = glyph;
        this.color = color;
        this.name = name;
        this.totalHP = totalHP;
        this.atkVal = atkVal;
        this.defVal = defVal;
        this.curHP = totalHP;
    }

    
    public void moveBy(int mx, int my){
        
        Actor occupant = dungeon.getActor(posX + mx, posY + my);
        
        if(occupant == null){
           AI.enterTile(posX + mx, posY + my, dungeon.tile(posX + mx, posY + my)); 
        }else{
            attack(occupant);
        }
        
    }
    
    public void attack(Actor occupant){
        
        int dmg = Math.max(0, getAtkVal() - occupant.getDefVal());
        dmg = (int)(Math.random() * dmg) + 1;
        occupant.setHP(-dmg);
        
        sendMessage("You hit the %s for %d damage", occupant.name, dmg);
        if(occupant.getCurHP() <= 0){
            sendMessage("You killed the %s!", occupant.name);
        }
        occupant.sendMessage("%s hits you!  It strikes for %d damage.", name, dmg);

    }
    public void update() {
        AI.update();
    }
    public boolean canEnter(int wx, int wy) {
        return dungeon.tile(wx, wy).isPassable() && dungeon.getActor(wx, wy) == null;
    }
    
    public void setHP(int value){
        curHP += value;
        
        if (curHP < 1){
            dungeon.delete(this);
        }
    }
    public void sendMessage(String message, Object ... params){
        
        AI.getMessage(String.format(message, params));
    }
    
    public int getAtkVal() {
        return atkVal;
    }

    public int getCurHP() {
        return curHP;
    }

    public int getDefVal() {
        return defVal;
    }

    public int getTotalHP() {
        return totalHP;
    }
    public char getGlyph() {
        return glyph;
    }

    public Color getColor() {
        return color;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setAI(ActorAI AI) {
        this.AI = AI;
    }

    
    
}
