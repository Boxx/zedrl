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
        Item stone = new Item(',', SColor.DOVE_FEATHER_GREY, "stone", true);
        dungeon.addItemRand(stone, z);
        return stone;
    }
}
