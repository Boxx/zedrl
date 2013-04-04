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
    public Item newStone(int z){
        Item stone = new Item(',', SColor.DOVE_FEATHER_GREY, "stone", "ammo", true);
        dungeon.addItemRand(stone, z);
        return stone;
    }
    public Item newDagger(int z){
        Item dagger = new Item(')',SColor.WHITE_OAK, "dagger", "weapon", false);
        dagger.setIsWearable(true);
        dagger.setAtkVal(3);
        dungeon.addItemRand(dagger, z);
        return dagger;
    }
    public Item newMace(int z){
        Item mace = new Item(')',SColor.ALOEWOOD_BROWN, "mace","weapon", false);
        mace.setIsWearable(true);
        mace.setAtkVal(5);
        dungeon.addItemRand(mace, z);
        return mace;
    }
    public Item newLongSword(int z){
        Item longsword = new Item(')',SColor.BRONZE, "longsword","weapon", false);
        longsword.setIsWearable(true);
        longsword.setAtkVal(8);
        dungeon.addItemRand(longsword, z);
        return longsword;
    }
    public Item newClothRobe(int z){
        Item robe = new Item('[',SColor.AZUL, "leather armor", "chestArmor", false);
        robe.setIsWearable(true);
        robe.setDefVal(1);
        dungeon.addItemRand(robe, z);
        return robe;
    }
    public Item newLeatherArmor(int z){
        Item leather = new Item('[',SColor.BRIGHT_GOLD_BROWN, "leather armor", "chestArmor",false);
        leather.setIsWearable(true);
        leather.setDefVal(2);
        dungeon.addItemRand(leather, z);
        return leather;
    }
    public Item newChainMail(int z){
        Item chain = new Item('[',SColor.SILVER_GREY, "chain mail", "chestArmor",false);
        chain.setIsWearable(true);
        chain.setDefVal(6);
        dungeon.addItemRand(chain, z);
        return chain;
    }
    public Item newPlateMail(int z){
        Item plate = new Item('[',SColor.INDIGO_INK_BROWN, "plate mail", "chestArmor",false);
        plate.setIsWearable(true);
        plate.setDefVal(10);
        dungeon.addItemRand(plate, z);
        return plate;
    }
    public Item pickRandWeapon(int z){
        int rand = (int)(Math.random() * 3);
        System.out.println(rand);
        switch(rand){
            case 0:
                return newDagger(z);
            case 1:
                return newLongSword(z);
            case 2:
                return newMace(z);
            default:
                return newDagger(z);
        }
    }
    public Item pickRandArmor(int z){
        switch((int)Math.random() * 4){
            case 0:
                return newLeatherArmor(z);
            case 1:
                return newChainMail(z);
            case 2:
                return newPlateMail(z);
            case 3:
                return newClothRobe(z);
            default:
                return newClothRobe(z);
        }
    }
}
