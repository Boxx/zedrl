/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import squidpony.squidcolor.SColor;
import zedrl.dungeon.Dungeon;
import zedrl.dungeon.Location;
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
    private SColor color;
    private Inventory inventory;
    private int visionRad;
    public String name;
    public int totalHP;
    public int curHP;
    public int attackVal;
    public int defenseVal;
    
    private Item weapon;
    private Item shield;
    private Item chestArmor;
    private Item cloak;
    private Item helm;
    private Item leftRing;
    private Item rightRing;
    private Item amulet;
    
    

    public Actor(Dungeon dungeon, char glyph, SColor color) {
        this.dungeon = dungeon;
        this.glyph = glyph;
        this.color = color;
    }

    public Actor(Dungeon dungeon, char glyph, SColor color, String name, int totalHP, int atkVal, int defVal, int visionRad) {
        this.dungeon = dungeon;
        this.glyph = glyph;
        this.color = color;
        this.name = name;
        this.totalHP = totalHP;
        this.attackVal = atkVal;
        this.defenseVal = defVal;
        this.curHP = totalHP;
        this.visionRad = visionRad;
        this.inventory = new Inventory(25);
    }

    public void moveBy(int mx, int my, int mz) {
        if(mx == 0 && my == 0 && mz == 0){
            return;
        }
        Tile targetTile;
        Tile thisTile = dungeon.tile(posX, posY, posZ);
        if (mz == -1) {
            if (thisTile.isUpStair()) {
                Location target = thisTile.getConnection();
                targetTile = dungeon.tile(target.getX(), target.getY(), target.getZ());
                doAction("walk up the stairs to level %d", posZ + mz + 1);
                AI.enterTile(target.getX(), target.getY(), target.getZ(), targetTile);
            } else {
                doAction("try to go up but are stopped by the ceiling");
                return;
            }
        } else if (mz == 1) {
            if (thisTile.isDownStair()) {
                Location target = thisTile.getConnection();
                targetTile = dungeon.tile(target.getX(), target.getY(), target.getZ());
                doAction("walk down the stairs to level %d", posZ + mz + 1);
                AI.enterTile(target.getX(), target.getY(), target.getZ(), targetTile);
            } else {
                doAction("try to go down but are stopped by the floor");
                return;
            }
        }
        Actor occupant = dungeon.getActor(posX + mx, posY + my, posZ + mz);

        if (mz == 0 && occupant == null) {
            targetTile = dungeon.tile(posX + mx, posY + my, posZ + mz);
            if (dungeon.getItems(posX + mx, posY + my, posZ + mz) != null && name.equals("Zedman")){
                doAction("see a " + dungeon.getItems(posX + mx, posY + my, posZ + mz).get(0).getName() + " here");
            }
            AI.enterTile(posX + mx, posY + my, posZ + mz, targetTile);
        } else if (mz == 0 && occupant != null) {
            attack(occupant);
        }

    }
    public Actor findActor(int x, int y, int z){
        return dungeon.getActor(x, y, z);
    }

    public void attack(Actor occupant) {
        if (occupant.getGlyph() != this.getGlyph()) {
            int dmg = Math.max(0, getAtkVal() - occupant.getDefVal());
            dmg = (int) (Math.random() * dmg) + 1;
            occupant.setHP(-dmg);

            doAction("hit the %s for %d damage", occupant.name, dmg);
            if (occupant.getCurHP() <= 0) {
                doAction("killed the %s", occupant.name);
            }
            //occupant.doAction("hit you!  It strikes for %d damage.",dmg);
        }


    }
    public void pickUp(){
        List<Item> itemsHere = dungeon.getItems(posX, posY, posZ);
        
        //if(itemsHere.size() == 1){
            doAction("pick up a %s", itemsHere.get(0).getName());
            Item thisItem = itemsHere.get(0);
            dungeon.deleteItem(itemsHere, itemsHere.get(0), posX, posY, posZ);
            inventory.add(thisItem);
        //}
    }
    public void drop(Item item) {
        doAction("drop a " + item.getName());
        inventory.remove(item);
        dungeon.addItemAt(item, posX, posY, posZ);
        unequip(item);
    }
    public void unequip(Item item){
        if(item == null){
            return;
        }
        
        if(item == weapon){
            doAction("unequip a " + item.getName());
            weapon = null;
        }
        else if(item == chestArmor){
            doAction("take off a " + item.getName());
            chestArmor = null;
        }
        item.setIsEquipped(false);
        // @TODO
        // Add the rest of the item unequip stuff
    }
    public void equip(Item item){
        if(item.getAtkVal() == 0 && item.getDefVal() == 0){
            return;
        }
        if(item.getType().equals("weapon")){
            unequip(weapon);
            doAction("wield a " + item.getName());
            weapon = item;
        }
        else if(item.getType().equals("chestArmor")){
            unequip(chestArmor);
            doAction("put on a " + item.getName());
            chestArmor = item;
        }
        item.setIsEquipped(true);
        // @TODO
        // Add the rest of item equip stuff
    }

    public void update() {
        AI.update();
    }

    public boolean canEnter(int wx, int wy, int wz) {
        return dungeon.tile(wx, wy, wz).isPassable() && dungeon.getActor(wx, wy, wz) == null;
    }

    public void setHP(int value) {
        curHP += value;

        if (curHP < 1) {
            dungeon.deleteActor(this);
        }
    }

    public void sendMessage(String message, Object... params) {

        AI.getMessage(String.format(message, params));
    }

    public void doAction(String message, Object... params) {
        int r = 9;
        for (int ox = -r; ox < r + 1; ox++) {
            for (int oy = -r; oy < r + 1; oy++) {
                if (ox * ox + oy * oy > r * r) {
                    continue;
                }

                Actor other = dungeon.getActor(posX + ox, posY + oy, posZ);

                if (other == null) {
                    continue;
                }

                if (other == this) {
                    other.sendMessage("You " + message + ".", params);
                } else {
                    other.sendMessage(String.format("The %s %s.", name, makeSecondPerson(message)), params);
                }
            }
        }
    }

    private String makeSecondPerson(String text) {
        String[] words = text.split(" ");
        words[0] = words[0] + "s";

        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(" ");
            builder.append(word);
        }

        return builder.toString().trim();
    }
    public Inventory getInventory(){
        return inventory;
    }

    public int getAtkVal() {
        return attackVal + (weapon == null ? 0 : weapon.getAtkVal())
        + (chestArmor == null ? 0 : chestArmor.getAtkVal());
        // @TODO
        // Factor in other item attack values and properties
    }

    public int getCurHP() {
        return curHP;
    }

    public int getDefVal() {
        return defenseVal + (weapon == null ? 0 : weapon.getDefVal())
        + (chestArmor == null ? 0 : chestArmor.getDefVal());
        // @TODO
        // Factor in other item attack values and properties
    }

    public int getTotalHP() {
        return totalHP;
    }

    public char getGlyph() {
        return glyph;
    }

    public SColor getColor() {
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

    public void setPosZ(int posZ) {
        this.posZ = posZ;
    }

    public int getPosZ() {
        return posZ;
    }

    public int getVisionRad() {
        return visionRad;
    }

    public boolean hasSightOf(int x, int y, int z) {
        return AI.hasSightOf(x, y, z);
    }

    public Tile lookAt(int x, int y, int z) {
        return dungeon.tile(x, y, z);
    }

    

}
