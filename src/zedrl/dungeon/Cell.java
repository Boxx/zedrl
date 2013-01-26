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
    private int topRightRow;
    private int topRightCol;
    private int botLeftRow;
    private int botLeftCol;
    private int botRightRow;
    private int botRightCol;
    private boolean isRoom;

    public Cell(int topLeftRow, int topLeftCol, int topRightRow, int topRightCol, int botLeftRow, int botLeftCol, int botRightRow, int botRightCol)
    {
        this.topLeftRow = topLeftRow;
        this.topLeftCol = topLeftCol;
        this.topRightRow = topRightRow;
        this.topRightCol = topRightCol;
        this.botLeftRow = botLeftRow;
        this.botLeftCol = botLeftCol;
        this.botRightRow = botRightRow;
        this.botRightCol = botRightCol;
    }

    public int getBotLeftCol()
    {
        return botLeftCol;
    }

    public int getBotLeftRow()
    {
        return botLeftRow;
    }

    public int getBotRightCol()
    {
        return botRightCol;
    }

    public int getBotRightRow()
    {
        return botRightRow;
    }

    public boolean isIsRoom()
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

    public int getTopRightCol()
    {
        return topRightCol;
    }

    public int getTopRightRow()
    {
        return topRightRow;
    }
}
