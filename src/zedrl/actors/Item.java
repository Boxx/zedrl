/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

import squidpony.squidcolor.SColor;

/**
 *
 * @author Brandon
 */
public class Item {
    
    private char glyph;
    private SColor color;
    private String name;
    private boolean isStackable;
    private int stacks;
    private int atkVal;
    private int defVal;
    private int throwAtkVal;
    private int rangedAtkVal;
    private String type;
    private boolean isWearable;
    private boolean isEquipped;

    public Item(char glyph, SColor color, String name, String type, boolean isStackable) {
        this.glyph = glyph;
        this.color = color;
        this.name = name;
        this.type = type;
        this.isStackable = isStackable;
        this.stacks = 1;
        
    }

    public int getAtkVal() {
        return atkVal;
    }

    public void setAtkVal(int atkVal) {
        this.atkVal = atkVal;
    }

    public int getDefVal() {
        return defVal;
    }

    public void setDefVal(int defVal) {
        this.defVal = defVal;
    }

    public boolean isIsStackable() {
        return isStackable;
    }

    public void setIsStackable(boolean isStackable) {
        this.isStackable = isStackable;
    }

    public int getThrowAtkVal() {
        return throwAtkVal;
    }

    public void setThrowAtkVal(int throwAtkVal) {
        this.throwAtkVal = throwAtkVal;
    }

    public int getRangedAtkVal() {
        return rangedAtkVal;
    }

    public void setRangedAtkVal(int rangedAtkVal) {
        this.rangedAtkVal = rangedAtkVal;
    }
    
    
    public boolean isWearable() {
        return isWearable;
    }

    public void setIsWearable(boolean isWearable) {
        this.isWearable = isWearable;
    }
    
    
    
    public SColor getColor() {
        return color;
    }

    public char getGlyph() {
        return glyph;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    public boolean isStackable(){
        return isStackable;
    }
    public int getStacks(){
        return stacks;
    }
    public void addStack(){
        stacks++;
    }
    public void removeStack(){
        stacks--;
    }
    public void setStack(int x){
        stacks += x;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + '}';
    }

    public boolean isEquipped() {
        return isEquipped;
    }

    public void setIsEquipped(boolean isEquipped) {
        this.isEquipped = isEquipped;
    }

    public String getDetails() {
        String details = "";
        
        if(atkVal != 0){
            details += "    damage: " + "1d" + atkVal;
        }
        if(defVal != 0){
            details += "    AC: " + defVal;
        }
        
        return details;
    }
    
    
}
