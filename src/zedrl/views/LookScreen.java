/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;

import zedrl.actors.Actor;
import zedrl.actors.Item;
import zedrl.dungeon.Tile;

/**
 *
 * @author Brandon
 */
public class LookScreen extends TargetingScreen{
    
    public LookScreen(Actor player, String caption, int sx, int sy){
        super(player,caption,sx,sy);
    }


    public void enterDungeonCoordinate(int x, int y, int displayWidth, int displayHeight) {
        Actor actor = player.findActor(x, y, player.getPosZ());
        if(actor != null){
            caption = "  " + actor.getGlyph() + ":  " + actor.getDetails();
            return;
        }
        Item item = player.findItem(x, y, player.getPosZ());
        if(item != null){
            caption = "  " + item.getGlyph() + ":  " + item.getName() + item.getDetails();
            return;
        }
        Tile tile = player.lookAt(x, y, player.getPosZ());
        caption = "  " + tile.getGlyph() + ":  " + tile.getDetails();
    }
    
    
}
