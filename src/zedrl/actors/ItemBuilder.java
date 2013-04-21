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
    public Item placeNewHealthPotion(int z){
        Item healthpot = new Item('!', SColor.RED_PIGMENT, "health potion", "potion", true);
        healthpot.setQuaffEffect(new Effect(1){
            @Override
                public void applyEffect(Actor actor){
                    if(actor.getCurHP() == actor.getTotalHP()){
                        return;
                    }
                    actor.setHP(20);
                    actor.doAction("feel life course through your body");
                }
        });
        dungeon.addItemRand(healthpot, z);
        return healthpot;
    }
    public Item placeNewQuesoOfDoom(int z){
        Item cheese = new Item('*', SColor.BRIGHT_GOLDEN_YELLOW, "queso of doom", "victory", false);
        dungeon.addItemRand(cheese, z);
        return cheese;
    }
    public Item placeNewPoisonPotion(int z){
        Item poisonpot = new Item('!', SColor.GREEN, "venom potion", "potion", true);
        poisonpot.setQuaffEffect(new Effect(10){
            @Override
            public void applyEffect(Actor actor){
                actor.doAction("writhe in pain");
            }
            @Override
            public void update(Actor actor){
                super.update(actor);
                actor.setHP(-1);
            }
        });
        dungeon.addItemRand(poisonpot, z);
        return poisonpot;
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
        dagger.setThrowAtkVal(10);
        dungeon.addItemRand(dagger, z);
        return dagger;
    }
    public Item placeNewDaggerp1(int z){
        Item dagger = new Item(')',SColor.WHITE_OAK, "dagger +1", "weapon", false);
        dagger.setIsWearable(true);
        dagger.setAtkVal(4);
        dagger.setAtkBonus(1);
        dagger.setThrowAtkVal(10);
        dungeon.addItemRand(dagger, z);
        return dagger;
    }
    public Item placeNewDaggerp2(int z){
        Item dagger = new Item(')',SColor.WHITE_OAK, "dagger +2", "weapon", false);
        dagger.setIsWearable(true);
        dagger.setAtkVal(4);
        dagger.setAtkBonus(2);
        dagger.setThrowAtkVal(10);
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
    public Item placeNewMacep1(int z){
        Item mace = new Item(')',SColor.ALOEWOOD_BROWN, "mace +1","weapon", false);
        mace.setIsWearable(true);
        mace.setAtkVal(6);
        mace.setAtkBonus(1);
        dungeon.addItemRand(mace, z);
        return mace;
    }
    public Item placeNewMacep2(int z){
        Item mace = new Item(')',SColor.ALOEWOOD_BROWN, "mace +2","weapon", false);
        mace.setIsWearable(true);
        mace.setAtkVal(6);
        mace.setAtkBonus(2);
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
    public Item placeNewShortSwordp1(int z){
        Item shortsword = new Item(')',SColor.BRONZE, "shortsword +1","weapon", false);
        shortsword.setIsWearable(true);
        shortsword.setAtkVal(6);
        shortsword.setAtkBonus(1);
        dungeon.addItemRand(shortsword, z);
        return shortsword;
    }
    public Item placeNewShortSwordp2(int z){
        Item shortsword = new Item(')',SColor.BRONZE, "shortsword +2","weapon", false);
        shortsword.setIsWearable(true);
        shortsword.setAtkVal(6);
        shortsword.setAtkBonus(2);
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
    public Item placeNewLongSwordp1(int z){
        Item longsword = new Item(')',SColor.BRONZE, "longsword +1","weapon", false);
        longsword.setIsWearable(true);
        longsword.setAtkVal(8);
        longsword.setAtkBonus(1);
        dungeon.addItemRand(longsword, z);
        return longsword;
    }
    public Item placeNewLongSwordp2(int z){
        Item longsword = new Item(')',SColor.BRONZE, "longsword +2","weapon", false);
        longsword.setIsWearable(true);
        longsword.setAtkVal(8);
        longsword.setAtkBonus(2);
        dungeon.addItemRand(longsword, z);
        return longsword;
    }
    public Item placeNewGreatClub(int z){
        Item greatclub = new Item(')',SColor.BRIGHT_GOLD_BROWN, "great club", "weapon", false);
        greatclub.setIsWearable(true);
        greatclub.setAtkVal(10);
        dungeon.addItemRand(greatclub, z);
        return greatclub;
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
    public Item placeNewClothRobep1(int z){
        Item robe = new Item('[',SColor.AZUL, "cloth robe +1", "chestArmor", false);
        robe.setIsWearable(true);
        robe.setDefVal(1);
        robe.setDefBonus(1);
        dungeon.addItemRand(robe, z);
        return robe;
    }
    public Item placeNewClothRobep2(int z){
        Item robe = new Item('[',SColor.AZUL, "cloth robe +2", "chestArmor", false);
        robe.setIsWearable(true);
        robe.setDefVal(1);
        robe.setDefBonus(2);
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
    public Item placeNewLeatherArmorp1(int z){
        Item leather = new Item('[',SColor.BRIGHT_GOLD_BROWN, "leather armor +1", "chestArmor",false);
        leather.setIsWearable(true);
        leather.setDefVal(2);
        leather.setDefBonus(1);
        dungeon.addItemRand(leather, z);
        return leather;
    }
    public Item placeNewLeatherArmorp2(int z){
        Item leather = new Item('[',SColor.BRIGHT_GOLD_BROWN, "leather armor +2", "chestArmor",false);
        leather.setIsWearable(true);
        leather.setDefVal(2);
        leather.setDefBonus(2);
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
    public Item placeNewChainMailp1(int z){
        Item chain = new Item('[',SColor.SILVER_GREY, "chain mail +1", "chestArmor",false);
        chain.setIsWearable(true);
        chain.setDefVal(6);
        chain.setDefBonus(1);
        dungeon.addItemRand(chain, z);
        return chain;
    }
    public Item placeNewChainMailp2(int z){
        Item chain = new Item('[',SColor.SILVER_GREY, "chain mail +2", "chestArmor",false);
        chain.setIsWearable(true);
        chain.setDefVal(6);
        chain.setDefBonus(2);
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
    public Item placeNewPlateMailp1(int z){
        Item plate = new Item('[',SColor.INDIGO_INK_BROWN, "plate mail +1", "chestArmor",false);
        plate.setIsWearable(true);
        plate.setDefVal(10);
        plate.setDefBonus(1);
        dungeon.addItemRand(plate, z);
        return plate;
    }
    public Item placeNewPlateMailp2(int z){
        Item plate = new Item('[',SColor.INDIGO_INK_BROWN, "plate mail +2", "chestArmor",false);
        plate.setIsWearable(true);
        plate.setDefVal(10);
        plate.setDefBonus(2);
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
    public Item getNewGreatClub(){
        Item greatclub = new Item(')',SColor.BRIGHT_GOLD_BROWN, "great club", "weapon", false);
        greatclub.setIsWearable(true);
        greatclub.setAtkVal(10);
        return greatclub;
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
    public Item pickRandPotion(int z){
        int rand = (int)(Math.random() * 3);
        switch(rand){
            case 0:
                return placeNewHealthPotion(z);
            case 1:
                return placeNewPoisonPotion(z);
            default:
                return placeNewHealthPotion(z);
        }
    }
    public Item pickRandMagicItem(int z){
        int rand = (int)(Math.random() * 15); //Change this based on number of items
        switch(rand){
            case 0:
                return placeNewDaggerp1(z);
            case 1:
                return placeNewDaggerp2(z);
            case 2:
                return placeNewMacep1(z);
            case 3:
                return placeNewMacep2(z);
            case 4:
                return placeNewShortSwordp1(z);
            case 5:
                return placeNewShortSwordp2(z);
            case 6:
                return placeNewLongSwordp1(z);
            case 7:
                return placeNewLongSwordp2(z);
            case 8:
                return placeNewClothRobep1(z);
            case 9:
                return placeNewClothRobep2(z);
            case 10:
                return placeNewChainMailp1(z);
            case 11:
                return placeNewChainMailp2(z);
            case 12:
                return placeNewLeatherArmorp1(z);
            case 13:
                return placeNewLeatherArmorp2(z);
            case 14:
                return placeNewPlateMailp1(z);
            case 15:
                return placeNewPlateMailp2(z);
            default:
                return placeNewDaggerp1(z);
                
                
        }
    }
}
