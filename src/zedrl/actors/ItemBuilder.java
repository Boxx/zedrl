/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

import squidpony.squidcolor.SColor;
import zedrl.dungeon.Dungeon;

/**
 *
 * @author Brandon
 */
public class ItemBuilder {
    
    private Dungeon dungeon;
    
    public ItemBuilder(Dungeon dungeon){
        this.dungeon = dungeon;
    }
    public Item placeNewStone(int z){
        Item stone = new Item(',', SColor.DOVE_FEATHER_GREY, "stone", "ammo", true);
        dungeon.addItemRand(stone, z);
        return stone;
    }
    public Item placeNewDagger(int z){
        Item dagger = new Item(')',SColor.WHITE_OAK, "dagger", "weapon", false);
        dagger.setIsWearable(true);
        dagger.setAtkVal(4);
        dungeon.addItemRand(dagger, z);
        return dagger;
    }
    public Item placeNewMace(int z){
        Item mace = new Item(')',SColor.ALOEWOOD_BROWN, "mace","weapon", false);
        mace.setIsWearable(true);
        mace.setAtkVal(6);
        dungeon.addItemRand(mace, z);
        return mace;
    }
    public Item placeNewShortSword(int z){
        Item shortsword = new Item(')',SColor.BRONZE, "shortsword","weapon", false);
        shortsword.setIsWearable(true);
        shortsword.setAtkVal(6);
        dungeon.addItemRand(shortsword, z);
        return shortsword;
    }
    public Item placeNewLongSword(int z){
        Item longsword = new Item(')',SColor.BRONZE, "longsword","weapon", false);
        longsword.setIsWearable(true);
        longsword.setAtkVal(8);
        dungeon.addItemRand(longsword, z);
        return longsword;
    }
    public Item placeNewShortBow(int z){
        Item shortbow = new Item(')',SColor.YELLOW_GREEN, "shortbow","weapon", false);
        shortbow.setIsWearable(true);
        shortbow.setRangedAtkVal(6);
        dungeon.addItemRand(shortbow, z);
        return shortbow;
    }
    public Item placeNewClothRobe(int z){
        Item robe = new Item('[',SColor.AZUL, "cloth robe", "chestArmor", false);
        robe.setIsWearable(true);
        robe.setDefVal(1);
        dungeon.addItemRand(robe, z);
        return robe;
    }
    public Item placeNewLeatherArmor(int z){
        Item leather = new Item('[',SColor.BRIGHT_GOLD_BROWN, "leather armor", "chestArmor",false);
        leather.setIsWearable(true);
        leather.setDefVal(2);
        dungeon.addItemRand(leather, z);
        return leather;
    }
    public Item placeNewChainMail(int z){
        Item chain = new Item('[',SColor.SILVER_GREY, "chain mail", "chestArmor",false);
        chain.setIsWearable(true);
        chain.setDefVal(6);
        dungeon.addItemRand(chain, z);
        return chain;
    }
    public Item placeNewPlateMail(int z){
        Item plate = new Item('[',SColor.INDIGO_INK_BROWN, "plate mail", "chestArmor",false);
        plate.setIsWearable(true);
        plate.setDefVal(10);
        dungeon.addItemRand(plate, z);
        return plate;
    }
    public Item getNewStone(){
        Item stone = new Item(',', SColor.DOVE_FEATHER_GREY, "stone", "ammo", true);
        //dungeon.addItemRand(stone, z);
        return stone;
    }
    public Item getNewDagger(){
        Item dagger = new Item(')',SColor.WHITE_OAK, "dagger", "weapon", false);
        dagger.setIsWearable(true);
        dagger.setAtkVal(3);
        //dungeon.addItemRand(dagger, z);
        return dagger;
    }
    public Item getNewMace(){
        Item mace = new Item(')',SColor.ALOEWOOD_BROWN, "mace","weapon", false);
        mace.setIsWearable(true);
        mace.setAtkVal(5);
        //dungeon.addItemRand(mace, z);
        return mace;
    }
    public Item getNewShortSword(){
        Item shortsword = new Item(')',SColor.BRONZE, "shortsword","weapon", false);
        shortsword.setIsWearable(true);
        shortsword.setAtkVal(4);
        //dungeon.addItemRand(shortsword, z);
        return shortsword;
    }
    public Item getNewLongSword(){
        Item longsword = new Item(')',SColor.BRONZE, "longsword","weapon", false);
        longsword.setIsWearable(true);
        longsword.setAtkVal(8);
        //dungeon.addItemRand(longsword, z);
        return longsword;
    }
    public Item getNewClothRobe(){
        Item robe = new Item('[',SColor.AZUL, "cloth robe", "chestArmor", false);
        robe.setIsWearable(true);
        robe.setDefVal(1);
        //dungeon.addItemRand(robe, z);
        return robe;
    }
    public Item getNewLeatherArmor(){
        Item leather = new Item('[',SColor.BRIGHT_GOLD_BROWN, "leather armor", "chestArmor",false);
        leather.setIsWearable(true);
        leather.setDefVal(2);
       // dungeon.addItemRand(leather, z);
        return leather;
    }
    public Item getNewChainMail(){
        Item chain = new Item('[',SColor.SILVER_GREY, "chain mail", "chestArmor",false);
        chain.setIsWearable(true);
        chain.setDefVal(6);
        //dungeon.addItemRand(chain, z);
        return chain;
    }
    public Item getNewPlateMail(){
        Item plate = new Item('[',SColor.INDIGO_INK_BROWN, "plate mail", "chestArmor",false);
        plate.setIsWearable(true);
        plate.setDefVal(10);
        //dungeon.addItemRand(plate, z);
        return plate;
    }
    public Item pickRandWeapon(int z){
        int rand = (int)(Math.random() * 3);
        switch(rand){
            case 0:
                return placeNewDagger(z);
            case 1:
                return placeNewLongSword(z);
            case 2:
                return placeNewMace(z);
            default:
                return placeNewDagger(z);
        }
    }
    public Item pickRandArmor(int z){
        int rand = (int)(Math.random() * 4);
        switch(rand){
            case 0:
                return placeNewLeatherArmor(z);
            case 1:
                return placeNewChainMail(z);
            case 2:
                return placeNewPlateMail(z);
            case 3:
                return placeNewClothRobe(z);
            default:
                return placeNewClothRobe(z);
        }
    }
}
