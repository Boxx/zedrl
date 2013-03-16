/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;
import java.awt.Color;
import zedrl.dungeon.Dungeon;
import zedrl.dungeon.Tile;
/**
 *
 * @author Boxx
 */
public class Actor {
    
    private Dungeon dungeon;
    private ActorAI AI;
    private int posX;
    private int posY;
    private int posZ;
    private char glyph;
    private Color color;

    public String name;
    public int totalHP;
    public int curHP;
    public int attackVal;
    public int defenseVal;
    

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
        this.attackVal = atkVal;
        this.defenseVal = defVal;
        this.curHP = totalHP;
    }

    
    public void moveBy(int mx, int my, int mz){
        Tile targetTile = dungeon.tile(posX + mx, posY + my, posZ + mz);
        Actor occupant = dungeon.getActor(posX + mx, posY + my, posZ + mz);
        
        if(occupant == null){
           AI.enterTile(posX + mx, posY + my, posZ + mz, targetTile); 
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
    public boolean canEnter(int wx, int wy, int wz) {
        return dungeon.tile(wx, wy, wz).isPassable() && dungeon.getActor(wx, wy, wz) == null;
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
        return attackVal;
    }

    public int getCurHP() {
        return curHP;
    }

    public int getDefVal() {
        return defenseVal;
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

    void setPosZ(int posZ) {
        this.posZ = posZ;
    }

    public int getPosZ() {
        return posZ;
    }
    

    
    
}
