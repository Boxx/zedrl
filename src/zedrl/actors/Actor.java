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

    public Actor(Dungeon dungeon, char glyph, Color color) {
        this.dungeon = dungeon;
        this.glyph = glyph;
        this.color = color;
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
