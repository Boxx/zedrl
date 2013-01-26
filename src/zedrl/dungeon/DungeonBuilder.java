/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.dungeon;

/**
 *
 * @author Brandon
 */
public class DungeonBuilder
{

    private Tile[][] dungeon;
    private Cell[] cells;
    private int cellCount;
    private int cellCounter = 0;
    private int width;
    private int height;

    // ROW, COLUMN
    public DungeonBuilder(int height, int width, int cellWidth)
    {

        this.width = width;
        this.height = height;
        dungeon = new Tile[height][width];
        cellCount = (width / cellWidth) * (height / cellWidth);
        cells = new Cell[cellCount];
        buildDungeon(cellWidth);

    }

    public int getWidth()
    {

        return width;

    }

    public int getHeight()
    {

        return height;

    }

    // Generate a random number from 0 to 1/2 # of CELLS
    // That's how many rooms we want
    // Then for i = 0 up to randomRoomNumber, generate
    // a random number and the corresponding cell will be a ROOM
    // Then fill out the room with trash and Roshans
    private void buildDungeon(int cellWidth)
    {

        // This is where the magic happens
        while(cellCounter <= cellCount)
        {
            for (int x = 0; x < dungeon.length - cellWidth; x += cellWidth)
            {
                for (int y = 0; y < dungeon[0].length - cellWidth; y += cellWidth)
                {
                    /**
                    * These are the constructor params topLeftRow = x; topLeftCol =
                    * y; topRightRow = x; topRightCol = y + cellWidth; botLeftRow =
                    * x + cellWidth; botLeftCol = y; botRightRow = x + cellWidth;
                    * botRightCol = y + cellWidth;
                    */
                    System.out.println(cellCounter);
                    System.out.println(x + "," + y);
                    Cell curCell = new Cell(x, y, x, y + cellWidth, x + cellWidth, y,
                            x + cellWidth, y + cellWidth);
                    System.out.println("--Constructed a Cell--");
                    cells[cellCounter] = curCell;
                    System.out.println("--Put the Cell in the array with starting"
                            + "point " + cells[cellCounter].getTopLeftRow() + "," 
                            + cells[cellCounter].getTopLeftCol() + "--");
                    cellCounter++;
                }
            }
        }
    }
    public void buildCell(int topLeftRow, int topLeftCol, int topRightRow, 
            int topRightCol, int botLeftRow, int botLeftCol, int botRightRow, 
            int botRightCol){
        
        Cell curCell = new Cell(topLeftRow, topLeftCol, topRightRow, 
                topRightCol, botLeftRow, botLeftCol, botRightRow, botRightCol);
        
        
        
    }

    public Tile[][] returnProduct()
    {

        return dungeon;

    }
    // need a way to generate a random-sized room
    // (random height/width less than cell width)
    // so:
    // Divide the array up into cells of some height/width
    // We have a "cell" Object that we create which has
    // the boundary locations of the cell in it
}