/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.dungeon;

/**
 *
 * @author Brandon
 */
public class Cell
{

    private int topLeftRow;
    private int topLeftCol;
    private int botRightRow;
    private int botRightCol;
    private int depth;
    private boolean isRoom;

    public Cell(int topLeftRow, int topLeftCol, int botRightRow, int botRightCol, int depth)
    {
        this.topLeftRow = topLeftRow;
        this.topLeftCol = topLeftCol;
        this.botRightRow = botRightRow;
        this.botRightCol = botRightCol;
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
    
    public int getBotRightCol()
    {
        return botRightCol;
    }

    public int getBotRightRow()
    {
        return botRightRow;
    }

    public boolean isRoom()
    {
        return isRoom;
    }

    public int getTopLeftCol()
    {
        return topLeftCol;
    }

    public int getTopLeftRow()
    {
        return topLeftRow;
    }
    public void setRoom()
    {
        isRoom = true;
    }
}
