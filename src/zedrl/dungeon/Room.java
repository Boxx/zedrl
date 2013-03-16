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
    private int topLeftY;
    private int topLeftX;
    private int botRightY;
    private int botRightX;
    private int centerY;
    private int centerX;
    private int depthLevel;

    public Room(int topLeftRow, int topLeftCol, int botRightRow, int botRightCol, int depthLevel)
    {
        this.topLeftY = topLeftRow;
        this.topLeftX = topLeftCol;
        this.botRightY = botRightRow;
        this.botRightX = botRightCol;
        this.depthLevel = depthLevel;
    }
    public Room(int topLeftRow, int topLeftCol, int botRightRow, int botRightCol)
    {
        this.topLeftY = topLeftRow;
        this.topLeftX = topLeftCol;
        this.botRightY = botRightRow;
        this.botRightX = botRightCol;
        this.centerY = topLeftY + (botRightY - topLeftY)/2;
        this.centerX = topLeftX + (botRightX - topLeftX)/2;
        
    }

    public int getDepthLevel() {
        return depthLevel;
    }

    public void setDepthLevel(int depthLevel) {
        this.depthLevel = depthLevel;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterCol(int centerCol) {
        this.centerX = centerCol;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterRow(int centerRow) {
        this.centerY = centerRow;
    }
    
    
    public int getBotRightCol()
    {
        return botRightX;
    }

    public int getBotRightRow()
    {
        return botRightY;
    }

    public boolean isIsConnected()
    {
        return isConnected;
    }

    public int getTopLeftCol()
    {
        return topLeftX;
    }

    public int getTopLeftRow()
    {
        return topLeftY;
    }

    public void setIsConnected(boolean isConnected)
    {
        this.isConnected = isConnected;
    }

    @Override
    public String toString() {
        return "Room{" + "isConnected=" + isConnected + ", topLeftRow=" + topLeftY + ", topLeftCol=" + topLeftX + ", botRightRow=" + botRightY + ", botRightCol=" + botRightX + ", depthLevel=" + depthLevel + '}';
    }
    
}
