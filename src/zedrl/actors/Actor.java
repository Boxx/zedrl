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
import zedrl.dungeon.Line;
import zedrl.dungeon.Location;
import zedrl.dungeon.Tile;
import zedrl.utilities.Roller;

/**
 *
 * @author Boxx
 */
public class Actor {

    private Dungeon dungeon;
    private static ItemBuilder ib;
    private ActorAI AI;
    private int posX;
    private int posY;
    private int posZ;
    private char glyph;
    private SColor color;
    private Inventory inventory;
    private int visionRad;
    public int totalHP;
    public String name;
    public int curHP;
    public int attackVal;
    public int defenseVal;
    private int level;
    private int xp;
    public int str;
    public int strmod = str > 10 ? (str - 10) / 2 : 0;
    public int dex;
    public int dexmod = dex > 10 ? (str - 10) / 2 : 0;
    public int intel;
    public int intmod = intel > 10 ? (str - 10) / 2 : 0;
    public int wis;
    public int wismod = wis > 10 ? (str - 10) / 2 : 0;
    public int con;
    public int conmod = con > 10 ? (str - 10) / 2 : 0;
    public int cha;
    public int chamod = cha > 10 ? (str - 10) / 2 : 0;
    private Item weapon;
    private Item shield;
    private Item chestArmor;
    private Item cloak;
    private Item helm;
    private Item leftRing;
    private Item rightRing;
    private Item amulet;
    private String details;
    private ArrayList<Effect> effects;
    

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
        this.ib = new ItemBuilder(dungeon);
        this.effects = new ArrayList<>();

    }

    public void setModifiers() {
        strmod = str > 10 ? (str - 10) / 2 : 0;
        dexmod = dex > 10 ? (dex - 10) / 2 : 0;
        intmod = intel > 10 ? (intel - 10) / 2 : 0;
        wismod = wis > 10 ? (wis - 10) / 2 : 0;
        conmod = con > 10 ? (con - 10) / 2 : 0;
        chamod = cha > 10 ? (cha - 10) / 2 : 0;
        totalHP = totalHP + conmod;

    }

    public void moveBy(int mx, int my, int mz) {
        if (mx == 0 && my == 0 && mz == 0) {
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

        if (this.name.equals("Zedman") && dungeon.getItems(posX + mx, posY + my, posZ + mz) != null) {
            if(dungeon.getItems(posX + mx, posY + my, posZ + mz).size() > 1){
                doAction("see several items here");
            }
            doAction("see a " + dungeon.getItems(posX + mx, posY + my, posZ + mz).get(0).getName() + " here");
        }
        if (mz == 0 && occupant == null) {
            targetTile = dungeon.tile(posX + mx, posY + my, posZ + mz);


            AI.enterTile(posX + mx, posY + my, posZ + mz, targetTile);
        } else if (mz == 0 && occupant != null) {
            if (name.equals("Zedman") || occupant.name.equals("Zedman")) {
                attack(occupant);
            }

        }

    }

    public Actor findActor(int x, int y, int z) {
        if (hasSightOf(x, y, z)) {
            return dungeon.getActor(x, y, z);
        } else {
            return null;
        }

    }

    public Item findItem(int x, int y, int z) {
        if (hasSightOf(x, y, z) && dungeon.getItems(x, y, z) != null) {
            return dungeon.getItems(x, y, z).get(0);
        } else {
            return null;
        }
    }
    public void meleeAttack(Actor occupant){
        genericAttack(occupant, getAtkVal(), "hit the %s for %d damage", occupant.name);
    }
    public void throwAttack(Item item, Actor occupant){
        genericAttack(occupant, item.getThrowAtkVal(), "throw the %s at the %s and hit for %d damage", item.getName(), occupant.name);
    }
    /*
    public void rangedAttack(Actor occupant){
        genericAttack(occupant, getAtkVal(), "fire the %s at the %s and hit for %d damage", weapon.getName(), occupant.name);
    }
    */
    private void genericAttack(Actor occupant, int atk, String verb, Object ... params){
        int d20roll = Roller.randomInt(20);
        int dmg = 0;
        if(d20roll + strmod > occupant.defenseVal){
            dmg = Roller.randomInt(1, atk + strmod);
        }
        Object[] params2 = new Object[params.length + 1];
        System.arraycopy(params, 0, params2, 0, params.length);
        params2[params2.length - 1] = dmg;
        
        doAction(verb, params2);
        occupant.setHP(-dmg);
        
        if(occupant.getCurHP() <= 0){
            doAction("killed the %s", occupant.name);
            gainXP(occupant);
        }
    }
    
    public void attack(Actor occupant) {
        /**
         * Hit Calculation
         */
        int d20roll = Roller.randomInt(20);
        if (d20roll + strmod + (weapon != null ? weapon.getAtkBonus() : 0) > occupant.defenseVal) {
            int dmg = Roller.randomInt(1, getAtkVal()) + strmod + (weapon != null ? weapon.getAtkBonus() : 0);
            occupant.setHP(-dmg);
            doAction("hit the %s for %d damage", occupant.name, dmg);
            if (occupant.getCurHP() <= 0) {
                doAction("killed the %s", occupant.name);
                gainXP(occupant);
            }
        } else {
            doAction("miss the %s", occupant.name);
        }
    }
    public void thrownAttack(Item item, Actor target){
        int d20roll = Roller.randomInt(20);
        if (d20roll + dexmod > target.defenseVal) {
            int dmg = Roller.randomInt(1, item.getThrowAtkVal()) + strmod;
            target.setHP(-dmg);
            doAction("throw a %s and hit the %s for %d damage", item.getName(), target.name, dmg);
            if (target.getCurHP() <= 0) {
                doAction("killed the %s", target.name);
                gainXP(target);
            }
        } else {
            doAction("miss the %s", target.name);
        }
    }
    public void rangedAttack(Actor target){
        int d20roll = Roller.randomInt(20);
        if (d20roll + dexmod > target.defenseVal) {
            int dmg = Roller.randomInt(1, getAtkVal()) + strmod;
            target.setHP(-dmg);
            doAction("fire a %s and hit the %s for %d damage", weapon.getName(), target.name, dmg);
            if (target.getCurHP() <= 0) {
                doAction("killed the %s", target.name);
                gainXP(target);
            }
        } else {
            doAction("miss the %s", target.name);
        }
    }
    public void throwItem(Item item, int tx, int ty, int tz){
        Location end = new Location(posX,posY,0);
        
        for (Location p : new Line(posX, posY, tx, ty)){
            if(!realTile(p.getX(),p.getY(),posZ).isPassable()){
                break;
            }
            end = p;
        }
        tx = end.getX();
        ty = end.getY();
        
        Actor target = findActor(tx,ty,tz);
        
        if(target != null){
            thrownAttack(item, target);
        }else{
            doAction("throw the %s", item.getName());
        }
        unequip(item);
        inventory.remove(item);
        dungeon.addItemAt(item, tx, ty, tz);
    }

    public void pickUp() {
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

    public void unequip(Item item) {
        if (item == null) {
            return;
        }

        if (item == weapon) {
            doAction("unequip a " + item.getName());
            weapon = null;
        } else if (item == chestArmor) {
            doAction("take off a " + item.getName());
            chestArmor = null;
        }
        item.setIsEquipped(false);
        // @TODO
        // Add the rest of the item unequip stuff
    }

    public void equip(Item item) {
        if (!inventory.contains(item)){
            inventory.add(item);
        }
        if (item.getAtkVal() == 0 && item.getDefVal() == 0) {
            return;
        }
        if (item.getType().equals("weapon")) {
            unequip(weapon);
            doAction("wield a " + item.getName());
            weapon = item;
        } else if (item.getType().equals("chestArmor")) {
            unequip(chestArmor);
            doAction("put on a " + item.getName());
            chestArmor = item;
        }
        item.setIsEquipped(true);
        // @TODO
        // Add the rest of item equip stuff
    }
    
    public void quaff(Item potion){
        doAction("quaff a " + potion.getName());
        consume(potion);
    }
    public void eat(Item food){
        doAction("eat a " + food.getName());
        consume(food);
    }
    public void consume(Item item){
        
        addEffect(item.getQuaffEffect());
        
        dispose(item);
    }
    public void update() {
        checkEffects();
        AI.update();
        
    }
    private void die(){
        Item corpse = new Item('%', color, name + " corpse", "corpse", false);
        dungeon.addItemAt(corpse, posX, posY, posZ);
        for(Item item : inventory.getItems()){
            if(item != null){
                drop(item);
            }
        }
    }
    private void addEffect(Effect effect){
        if(effect == null){
            return;
        }else{
            effect.applyEffect(this);
            effects.add(effect);
        }
        
    }
    private void checkEffects(){
        ArrayList<Effect> expired = new ArrayList<>();
        
        for(Effect effect : effects){
            effect.update(this);
            if (effect.expired()){
                effect.endEffect(this);
                expired.add(effect);
            }
        }
        effects.removeAll(expired);
    }

    public boolean canEnter(int wx, int wy, int wz) {
        return dungeon.tile(wx, wy, wz).isPassable() && dungeon.getActor(wx, wy, wz) == null;
    }

    public void setHP(int value) {
        
        if(curHP + value > totalHP){
            curHP = totalHP;
        }else{
            curHP += value;
        }
        
        if (curHP < 1) {
            die();
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

    public Inventory getInventory() {
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
        return defenseVal + dexmod + (weapon == null ? 0 : weapon.getDefVal())
                + (chestArmor == null ? 0 : chestArmor.getDefVal() + chestArmor.getDefBonus());
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
        if (hasSightOf(x, y, z)) {
            return dungeon.tile(x, y, z);
        } else {
            return AI.rememberedTile(x, y, z);
        }
    }

    public Tile realTile(int wx, int wy, int wz) {
        return dungeon.tile(wx, wy, wz);
    }

    public Item getAmulet() {
        return amulet;
    }

    public Item getChestArmor() {
        return chestArmor;
    }

    public Item getCloak() {
        return cloak;
    }

    public Item getHelm() {
        return helm;
    }

    public Item getLeftRing() {
        return leftRing;
    }

    public Item getRightRing() {
        return rightRing;
    }

    public Item getShield() {
        return shield;
    }

    public Item getWeapon() {
        return weapon;
    }

    public void setAmulet(Item amulet) {
        this.amulet = amulet;
    }

    public void setCha(int cha) {
        this.cha = cha;
    }

    public void setChestArmor(Item chestArmor) {
        this.chestArmor = chestArmor;
    }

    public void setCloak(Item cloak) {
        this.cloak = cloak;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public void setHelm(Item helm) {
        this.helm = helm;
    }

    public void setIntel(int intel) {
        this.intel = intel;
    }

    public void setLeftRing(Item leftRing) {
        this.leftRing = leftRing;
    }

    public void setRightRing(Item rightRing) {
        this.rightRing = rightRing;
    }

    public void setShield(Item shield) {
        this.shield = shield;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }

    public int getChamod() {
        return chamod;
    }

    public void setChamod(int chamod) {
        this.chamod = chamod;
    }

    public int getConmod() {
        return conmod;
    }

    public void setConmod(int conmod) {
        this.conmod = conmod;
    }

    public int getDexmod() {
        return dexmod;
    }

    public void setDexmod(int dexmod) {
        this.dexmod = dexmod;
    }

    public int getIntmod() {
        return intmod;
    }

    public void setIntmod(int intmod) {
        this.intmod = intmod;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStrmod() {
        return strmod;
    }

    public void setStrmod(int strmod) {
        this.strmod = strmod;
    }

    public int getWismod() {
        return wismod;
    }

    public void setWismod(int wismod) {
        this.wismod = wismod;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void modXP(int x) {

        xp += x;

        sendMessage("You %s %d xp", x < 0 ? "lose" : "gain", x);

        while (xp > (int) (Math.pow(level, 1.5) * 20)) {
            level++;
            doAction("advance to level %d", level);
            AI.onLevelUp();
            gainHP(level * 2);
            curHP = totalHP;
        }
    }

    public void setTotalHP(int totalHP) {
        this.totalHP = totalHP;
    }

    private void gainXP(Actor occupant) {
        int xp = occupant.getTotalHP() + occupant.getAtkVal() + occupant.getDefVal() - level * 2;

        if (xp > 0) {
            modXP(xp);
        }
    }

    public void gainHP(int x) {
        totalHP += x;
    }

    void gainSTR() {
        str += 1;
        doAction("look a little stronger");
    }

    void gainDEX() {
        dex += 1;
        doAction("look a little more agile");
    }

    void gainINT() {
        intel += 1;
        doAction("look a little wiser");
    }

    void gainCON() {
        con += 1;
        doAction("look a little healthier");
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    private void dispose(Item item) {
        inventory.remove(item);
        unequip(item);
    }
    
}
