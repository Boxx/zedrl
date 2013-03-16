/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
    private ArrayList<String> messageQueue;
    private int screenW;
    private int screenH;

    public PlayScreen() {
        screenW = 50;
        screenH = 20;
        messageQueue = new ArrayList<>();
        createDungeon();
        ActorBuilder ab = new ActorBuilder(dungeon);
        createActors(ab);
    }

    private void createDungeon() {
        dungeon = new DungeonBuilder(50, 50, 10).build();
    }
    private void createActors(ActorBuilder ab){
        player = ab.newPlayer(messageQueue);
        
        for (int i = 0; i < 10; i++){
            ab.newFungus();
        }
    }

    private void displayDungeon(AsciiPanel term, int left, int top) {
        for (int i = 0; i < screenW; i++) {
            for (int j = 0; j < screenH; j++) {
                int dx = i + left;
                int dy = j + top;
                Actor actor = dungeon.getActor(dx, dy, player.getPosZ());
                if(actor != null){
                    term.write(actor.getGlyph(), actor.getPosX() - left, actor.getPosY() - top, actor.getColor());
                }else{
                    term.write(dungeon.glyph(dx, dy, player.getPosZ()),i,j,dungeon.color(dx, dy, player.getPosZ()));
                }
            }
        }
           
    }
    
    @Override
    public void displayOutput(AsciiPanel term) {
        int left = getScrollX();
        int top = getScrollY();
        displayDungeon(term, left, top);
        displayStats(term);
        displayMessages(term, messageQueue);
        
    }
    
    public void displayMessages(AsciiPanel term, ArrayList<String> messageQueue){
        
        for (int i = 0; i < messageQueue.size(); i++){
            String msg = messageQueue.get(i);
            term.write(msg, 1, 21 + i);
        }
        messageQueue.clear();
    }
    public void displayStats(AsciiPanel term){
        
        String stats = String.format(" %3d/%3d hp", player.getCurHP(), player.getTotalHP());
        term.write(stats, 55, 2);
    }
    

    @Override
    public Screen respondToUserInput(KeyEvent key) {
            switch (key.getKeyCode()) {
            case KeyEvent.VK_NUMPAD4:
                player.moveBy(-1,0,0); 
                break;
            case KeyEvent.VK_NUMPAD6:
                player.moveBy(1,0,0); 
                break;
            case KeyEvent.VK_NUMPAD8:
                player.moveBy(0,-1,0); 
                break;
            case KeyEvent.VK_NUMPAD2:
                player.moveBy(0,1,0); 
                break;
            case KeyEvent.VK_NUMPAD7:
                player.moveBy(-1,-1,0); 
                break;
            case KeyEvent.VK_NUMPAD9:
                player.moveBy(1,-1,0); 
                break;
            case KeyEvent.VK_NUMPAD1:
                player.moveBy(-1,1,0); 
                break;
            case KeyEvent.VK_NUMPAD3:
                player.moveBy(1,1,0); 
                break;
            
            } 
            switch (key.getKeyChar()){
                case '<': 
                    player.moveBy(0, 0, -1);
                    break;
                case '>':
                    player.moveBy(0, 0, 1);
                    break;
            }
        
        dungeon.update();
        return this;
    }
    public int getScrollX() {
        return Math.max(0, Math.min(player.getPosX() - screenW/2, dungeon.getWidth() - screenW));
    }
    public int getScrollY() {
        return Math.max(0, Math.min(player.getPosY() - screenH/2, dungeon.getHeight() - screenH));
    }
}
