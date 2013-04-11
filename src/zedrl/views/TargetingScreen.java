/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.views;

import java.awt.Color;
import java.awt.event.KeyEvent;
import squidpony.squidcolor.SColor;
import squidpony.squidgrid.gui.swing.SwingPane;
import zedrl.actors.Actor;
import zedrl.dungeon.Line;
import zedrl.dungeon.Location;

/**
 *
 * @author Brandon
 */
public class TargetingScreen implements Screen{

    protected Actor player;
    protected String caption;
    private int sx;
    private int sy;
    private int x;
    private int y;
    
    public TargetingScreen(Actor player, String caption, int sx, int sy){
        this.player = player;
        this.caption = caption;
        this.sx = sx;
        this.sy = sy;
    }
    @Override
    public void displayOutput(SwingPane display) {
        display.repaint();
        for (Location p : new Line(sx, sy, sx + x, sy + y)){
            if(p.getX() < 0 || p.getX() >= display.getGridWidth() || p.getY() < 0 || p.getY() >= display.getGridHeight()){
                continue;
            }
            if(sx == p.getX() && sy == p.getY()){
                continue;
            }else{
                display.placeCharacter(p.getX(), p.getY(), '*', SColor.BRIGHT_PINK);
            }
            
            
        }
        player.sendMessage(caption);
        display.refresh();
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        int px = x;
        int py = y;
        
        switch(key.getKeyCode()){
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_NUMPAD4:
            case KeyEvent.VK_H: 
                x--; 
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_L:
            case KeyEvent.VK_NUMPAD6:
                x++; 
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_J:
            case KeyEvent.VK_NUMPAD8:
                y--; 
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_K: 
            case KeyEvent.VK_NUMPAD2:
                y++; 
                break;
            case KeyEvent.VK_Y: 
            case KeyEvent.VK_NUMPAD7:
                x--; 
                y--; 
                break;
            case KeyEvent.VK_U:
            case KeyEvent.VK_NUMPAD9:
                x++; 
                y--; 
                break;
            case KeyEvent.VK_B: 
            case KeyEvent.VK_NUMPAD1:
                x--; 
                y++; 
                break;
            case KeyEvent.VK_N:
            case KeyEvent.VK_NUMPAD3:
                x++; 
                y++; 
                break;
            case KeyEvent.VK_ENTER: chooseDungeonCoordinate(player.getPosX() + x, player.getPosY() + y, sx + x, sy + y); return null;
            case KeyEvent.VK_ESCAPE: return null;
        }
        if(!isAcceptable(player.getPosX() + x, player.getPosY() + y)){
            x = px;
            y = py;
        }
        enterDungeonCoordinate(player.getPosX() + x, player.getPosY() + y, sx + x, sy + y);
        
        return this;
    }

    public void chooseDungeonCoordinate(int x, int y, int displayWidth, int displayHeight) {
        
    }

    public boolean isAcceptable(int x, int y) {
        return true;
    }

    public void enterDungeonCoordinate(int x, int y, int screenX, int screenY) {
        
    }
    
}
