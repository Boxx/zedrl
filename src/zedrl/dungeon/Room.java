/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.dungeon;

/**
 *
 * @author Brandon
 */
public class Room
{
    private boolean isConnected;
    private int topLeftRow;
    private int topLeftCol;
    private int botRightRow;
    private int botRightCol;
    private int depthLevel;

    public Room(int topLeftRow, int topLeftCol, int botRightRow, int botRightCol, int depthLevel)
    {
        this.topLeftRow = topLeftRow;
        this.topLeftCol = topLeftCol;
        this.botRightRow = botRightRow;
        this.botRightCol = botRightCol;
        this.depthLevel = depthLevel;
    }

    public int getDepthLevel() {
        return depthLevel;
    }

    public void setDepthLevel(int depthLevel) {
        this.depthLevel = depthLevel;
    }
    
    
    public int getBotRightCol()
    {
        return botRightCol;
    }

    public int getBotRightRow()
    {
        return botRightRow;
    }

    public boolean isIsConnected()
    {
        return isConnected;
    }

    public int getTopLeftCol()
    {
        return topLeftCol;
    }

    public int getTopLeftRow()
    {
        return topLeftRow;
    }

    public void setIsConnected(boolean isConnected)
    {
        this.isConnected = isConnected;
    }
    
}
