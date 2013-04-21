/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;

import asciiPanel.AsciiPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import squidpony.squidcolor.SColor;
import squidpony.squidgrid.gui.swing.SwingPane;
import zedrl.actors.*;
import zedrl.dungeon.Dungeon;
import zedrl.dungeon.DungeonBuilder;
import zedrl.dungeon.Tile;
import zedrl.utilities.Roller;

/**
 *
 * @author Brandon
 */
public class PlayScreen implements Screen, KeyListener {

    private Dungeon dungeon;
    private Screen sub;
    private Actor player;
    private ArrayList<String> messageQueue;
    private int screenW;
    private int screenH;
    private FieldOfView FOV;
    private JFrame frame;
    private SwingPane display;
    private StatusMessagePanel statusPanel;
    private PlayerInfoPanel infoPanel;
    private int tileWidth;
    private int tileHeight;
    private int width;
    private int height;
    private int depth;

    public PlayScreen(JFrame frame) {
        messageQueue = new ArrayList<>();
        createDungeon();
        screenW = 50;
        screenH = 24;
        width = 50;
        height = 24;
        FOV = new FieldOfView(dungeon);
        ActorBuilder ab = new ActorBuilder(dungeon);
        ItemBuilder ib = new ItemBuilder(dungeon);
        createActors(ab);
        createItems(ib);
        this.frame = frame;
        //this.display = display;
        depth = dungeon.getDepth();
        //frame.setLayout(new BorderLayout());
        
        statusPanel = new StatusMessagePanel();
        infoPanel = new PlayerInfoPanel();
        frame.add(infoPanel, BorderLayout.EAST);
        frame.add(statusPanel, BorderLayout.SOUTH); 
        frame.pack();
        frame.revalidate();
        frame.repaint();


        /*
         * frame.setVisible(true); frame.pack();
         * frame.setLocationRelativeTo(null); frame.repaint();
        *
         */
        //tileWidth = display.getCellDimension().width;
        //tileHeight = display.getCellDimension().height;

        //InputListener il = new InputListener();
        //frame.addKeyListener(this);
        //displayOutput();
        // if (sub != null) {
        //     sub.displayOutput(display);
        // }
    }

    private void createDungeon() {
        dungeon = new DungeonBuilder(50, 50, 10).build();
    }

    private void createActors(ActorBuilder ab) {
        player = ab.newPlayer(messageQueue, FOV);

        for (int z = 0; z < dungeon.getDepth(); z++) {
            if (z < 3){
                for (int i = 0; i < 10; i++) {
                    ab.newFungus(z);
                }
            }
            for (int i = 0; i < 5; i++) {
                ab.newBat(z);
            }
            if(z > 0 && z < 5){
                for (int i = 0; i < 5; i++){
                    ab.newGoblin(z, player);
                }
                ab.newOgre(z, player);
            }
            if(z > 4){
                for (int i = 0; i < 5; i++){
                    ab.newOrc(z, player);
                }
            }
            
        }

    }

    private void createItems(ItemBuilder ib) {
        for (int z = 0; z < dungeon.getDepth(); z++) {
            for (int i = 0; i < 5; i++) {
                ib.placeNewStone(z);
            }
        }
        for (int z = 0; z < dungeon.getDepth(); z++) {
            for(int i = 0; i < 5; i++){
                ib.pickRandArmor(z);
                ib.pickRandWeapon(z);
            }
        }
        for (int z = 0; z < dungeon.getDepth(); z++){
            for(int i = 0; i < 3; i++){
                ib.pickRandPotion(z);
            }
        }
        for(int z = 0; z < dungeon.getDepth(); z++){
            if (Roller.chance(25)){
                ib.pickRandMagicItem(z);
            }
            if (Roller.chance(10)){
                ib.pickRandMagicItem(z);
            }
        }
        ib.placeNewQuesoOfDoom(dungeon.getDepth() - 1);
    }

    private void displayDungeon(SwingPane display, int left, int top) {
        FOV.update(player.getPosX(), player.getPosY(), player.getPosZ(), player.getVisionRad());
        //display.initialize(screenW, screenH, new Font("Ariel", Font.BOLD, 18));
        for (int i = 0; i < screenW; i++) {
            for (int j = 0; j < screenH; j++) {
                int dx = i + left;
                int dy = j + top;
                if (player.hasSightOf(dx, dy, player.getPosZ())) {
                    display.placeCharacter(i, j, dungeon.glyph(dx, dy, player.getPosZ()), dungeon.color(dx, dy, player.getPosZ()), SColor.BLACK);
                    //term.write(dungeon.glyph(dx, dy, player.getPosZ()), i, j, dungeon.color(dx, dy, player.getPosZ()));
                } else {

                    display.placeCharacter(i, j, FOV.getTile(dx, dy, player.getPosZ()).getGlyph(), SColor.INK);
                    //term.write(FOV.getTile(dx, dy, player.getPosZ()).getGlyph(), i, j, Color.darkGray);
                }
            }
        }
        display.refresh();

    }
    /*
     * public void displayOutput() { if (sub != null) {
     * sub.displayOutput(display); } int left = getScrollX(); int top =
     * getScrollY(); displayDungeon(display, left, top); displayStats();
     * displayMessages(messageQueue);
     *
     * }
     *
     */

    public void clearDisplay() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                display.placeCharacter(x, y, ' ', Color.BLACK);
            }
        }
        display.refresh();
    }

    public void displayMessages(ArrayList<String> messageQueue) {


        for (int i = 0; i < messageQueue.size(); i++) {
            String msg = messageQueue.get(i);
            statusPanel.printStatus(msg);
            //term.write(msg, 1, 21 + i);
        }
        messageQueue.clear();
    }

    public void displayStats() {

        int hp = player.getCurHP();
        int totalhp = player.getTotalHP();
        infoPanel.setHPBar(hp,totalhp);
        infoPanel.updateInventory(player);
        //infoPanel.updateInventory(player.getInventory());
        //String stats = String.format(" %3d/%3d hp", player.getCurHP(), player.getTotalHP());
        //term.write(stats, 55, 2);

    }

    public int getScrollX() {
        return Math.max(0, Math.min(player.getPosX() - screenW / 2, dungeon.getWidth() - screenW));
    }

    public int getScrollY() {
        return Math.max(0, Math.min(player.getPosY() - screenH / 2, dungeon.getHeight() - screenH));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        respondToUserInput(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        if (sub != null) {
            sub = sub.respondToUserInput(key);
        } else {
            switch (key.getKeyCode()) {
                case KeyEvent.VK_D:
                    //sub = new DropItemScreen(player, frame);
                    sub = new SwingDropScreen(player,frame);
                    break;
                case KeyEvent.VK_Q:
                    sub = new SwingQuaffScreen(player,frame);
                    break;
                case KeyEvent.VK_W:
                    sub = new SwingEquipScreen(player,frame);
                    break;
                case KeyEvent.VK_I:

                    break;
                case KeyEvent.VK_T:
                    sub = new SwingThrowScreen(frame, player, player.getPosX() - getScrollX(), player.getPosY() - getScrollY());
                    break;
                case KeyEvent.VK_F:
                    if(player.getWeapon() == null || player.getWeapon().getRangedAtkVal() == 0){
                        player.sendMessage("You don't have a ranged weapon in your hands");
                    }
                    sub = new FireActionScreen(player, player.getPosX() - getScrollX(), player.getPosY() - getScrollY());
                case KeyEvent.VK_G:
                    player.pickUp();
                    break;
                //case KeyEvent.VK_COMMA:
               //     player.pickUp();
                //    break;
                case KeyEvent.VK_NUMPAD4:
                    player.moveBy(-1, 0, 0);
                    break;
                case KeyEvent.VK_LEFT:
                    player.moveBy(-1, 0, 0);
                    break;
                case KeyEvent.VK_H:
                    player.moveBy(-1, 0, 0);
                    break;
                case KeyEvent.VK_NUMPAD6:
                    player.moveBy(1, 0, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    player.moveBy(1, 0, 0);
                    break;
                case KeyEvent.VK_L:
                    player.moveBy(1, 0, 0);
                    break;
                case KeyEvent.VK_NUMPAD8:
                    player.moveBy(0, -1, 0);
                    break;
                case KeyEvent.VK_UP:
                    player.moveBy(0, -1, 0);
                    break;
                case KeyEvent.VK_K:
                    player.moveBy(0, -1, 0);
                    break;
                case KeyEvent.VK_NUMPAD2:
                    player.moveBy(0, 1, 0);
                    break;
                case KeyEvent.VK_DOWN:
                    player.moveBy(0, 1, 0);
                    break;
                case KeyEvent.VK_J:
                    player.moveBy(0, 1, 0);
                    break;
                case KeyEvent.VK_NUMPAD7:
                    player.moveBy(-1, -1, 0);
                    break;
                case KeyEvent.VK_Y:
                    player.moveBy(-1, -1, 0);
                    break;
                case KeyEvent.VK_NUMPAD9:
                    player.moveBy(1, -1, 0);
                    break;
                case KeyEvent.VK_U:
                    player.moveBy(1, -1, 0);
                    break;
                case KeyEvent.VK_NUMPAD1:
                    player.moveBy(-1, 1, 0);
                    break;
                case KeyEvent.VK_B:
                    player.moveBy(-1, 1, 0);
                    break;
                case KeyEvent.VK_NUMPAD3:
                    player.moveBy(1, 1, 0);
                    break;
                case KeyEvent.VK_N:
                    player.moveBy(1, 1, 0);
                    break;
                case KeyEvent.VK_PERIOD: // Waits a turn
                    dungeon.update();
                    player.sendMessage("You wait a turn");
                    break;

            }
            switch (key.getKeyChar()) {
                case '<':
                    if(playerExiting()){
                        return playerExitDungeon();
                    }else{
                        player.moveBy(0, 0, -1);
                        break;
                    }
                case '>':
                    player.moveBy(0, 0, 1);
                    break;
                case '?':
                    sub = new SwingHelpScreen(frame);
                    break;
                case ';':
                    sub = new LookScreen(player, "Looking", player.getPosX() - getScrollX(), player.getPosY() - getScrollY());
                    break;
            }
        }
        if(sub == null){
            dungeon.update();
        }
        if(player.getCurHP() < 1){
            frame.remove(statusPanel);
            frame.remove(infoPanel);
            return new DeathScreen(frame);
        }
        
        //displayOutput();
        //display.refresh();
        return this;

    }
    private boolean playerExiting(){
        return player.getPosZ() == 0 && dungeon.tile(player.getPosX(), player.getPosY(), player.getPosZ()).isUpStair();
    }
    private Screen playerExitDungeon(){
        for(Item item : player.getInventory().getItems()){
            if(item != null && item.getType().equals("victory")){
                return new WinScreen(frame);
            }
        }
        return new DeathScreen(frame);
    }
    @Override
    public void displayOutput(SwingPane display) {
        

        int left = getScrollX();
        int top = getScrollY();
        displayDungeon(display, left, top);
        displayStats();
        displayMessages(messageQueue);
        if (sub != null) {
            sub.displayOutput(display);
            displayMessages(messageQueue);
        if(sub instanceof ThrowActionScreen){
            display.setVisible(true);
        }
        }else{
            display.setVisible(true);
        }
    }
}