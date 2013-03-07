/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import zedrl.actors.Actor;
import zedrl.actors.ActorBuilder;
import zedrl.dungeon.Dungeon;
import zedrl.dungeon.DungeonBuilder;

/**
 *
 * @author Brandon
 */
class PlayScreen implements Screen {

    private Dungeon dungeon;
    private Actor player;
    private int cX;
    private int cY;
    private int screenW;
    private int screenH;

    public PlayScreen() {
        screenW = 50;
        screenH = 20;
        createDungeon();
        ActorBuilder ab = new ActorBuilder(dungeon);
        createActors(ab);
    }

    private void createDungeon() {
        dungeon = new DungeonBuilder(50, 50, 10).build();
    }
    private void createActors(ActorBuilder ab){
        player = ab.newPlayer();
        
        for (int i = 0; i < 10; i++){
            ab.newFungus();
        }
    }

    private void displayDungeon(AsciiPanel term, int left, int top) {
        for (int i = 0; i < screenW; i++) {
            for (int j = 0; j < screenH; j++) {
                int dx = i + left;
                int dy = j + top;
                term.write(dungeon.glyph(dx, dy), i, j, dungeon.color(dx, dy));
                
                Actor actor = dungeon.getActor(dx, dy);
                if(actor != null){
                    int ax = actor.getPosX();
                    int ay = actor.getPosY();
                    if(ax > left && ax < left+screenW && ay > top && ay < top+screenH){
                        term.write(actor.getGlyph(),ax - left,ay - top,actor.getColor());
                    }
                }
            }
        }
           
    }
    
    @Override
    public void displayOutput(AsciiPanel term) {
        int left = getScrollX();
        int top = getScrollY();
        displayDungeon(term, left, top);
        term.write(player.getGlyph(), player.getPosX() - left, player.getPosY() - top,
                player.getColor());
       
    }
    
    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_NUMPAD4:
                player.moveBy(-1,0); 
                break;
            case KeyEvent.VK_NUMPAD6:
                player.moveBy(1,0); 
                break;
            case KeyEvent.VK_NUMPAD8:
                player.moveBy(0,-1); 
                break;
            case KeyEvent.VK_NUMPAD2:
                player.moveBy(0,1); 
                break;
            case KeyEvent.VK_NUMPAD7:
                player.moveBy(-1,-1); 
                break;
            case KeyEvent.VK_NUMPAD9:
                player.moveBy(1,-1); 
                break;
            case KeyEvent.VK_NUMPAD1:
                player.moveBy(-1,1); 
                break;
            case KeyEvent.VK_NUMPAD3:
                player.moveBy(1,1); 
                break;
            
        }
        return this;
    }
    public int getScrollX() {
        return Math.max(0, Math.min(player.getPosX() - screenW/2, dungeon.getWidth() - screenW));
    }
    public int getScrollY() {
        return Math.max(0, Math.min(player.getPosY() - screenH/2, dungeon.getHeight() - screenH));
    }
}
