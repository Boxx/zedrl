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
    private Cell[][] cells;
    private Room[] rooms;
    private int dungeonWidth;
    private int dungeonHeight;
    private int cellsWidth;
    private int cellsHeight;
    private int cellCount;
    private int ROOM_THRESHHOLD;
    private int ROOM_MIN;
    private int numberOfRooms = 0;

    // ROW, COLUMN
    public DungeonBuilder(int height, int width, int cellSize)
    {

        this.dungeonWidth = width;
        this.dungeonHeight = height;
        cellsWidth = width / cellSize;
        cellsHeight = height / cellSize;
        dungeon = new Tile[height][width];
        cellCount = cellsWidth * cellsHeight;
        ROOM_MIN = (int) (.3 * cellCount);
        ROOM_THRESHHOLD = (int) (.5 * cellCount);
        while (numberOfRooms <= ROOM_MIN || numberOfRooms >= ROOM_THRESHHOLD)
        {
            numberOfRooms = (int) (Math.random() * cellCount);
        }

        cells = new Cell[cellsHeight][cellsWidth];
        cells = buildCells(cellSize);
        buildRoomList();

    }

    // Generate a random number from 0 to 1/2 # of CELLS
    // That's how many rooms we want
    // Then for i = 0 up to randomRoomNumber, generate
    // a random number and the corresponding cell will be a ROOM
    // Then fill out the room with trash and Roshans
    private Cell[][] buildCells(int cellSize)
    {
        for (int i = 0; i < dungeonHeight; i += cellSize)
        {
            for (int j = 0; j < dungeonWidth; j += cellSize)
            {

                cells[i / cellSize][j / cellSize] = new Cell(i, j, i + cellSize - 1,
                        j + cellSize - 1);

            }
        }

        return cells;
    }

    public void printCells()
    {
        System.out.println("Cell Count: " + cells[0].length);
        for (int i = 0; i < cells.length; i++)
        {

            for (int j = 0; j < cells[0].length; j++)
            {
                if (cells[i][j] == null)
                {
                    System.out.println("Null at " + i + "," + j);
                } else
                {
                    System.out.println("Top Left: " + cells[i][j].getTopLeftRow() + ","
                            + cells[i][j].getTopLeftCol() + " Bottom Right: "
                            + cells[i][j].getBotRightRow() + ","
                            + cells[i][j].getBotRightCol());
                }

            }
        }
    }

    public Room[] buildRoomList()
    {
        rooms = new Room[numberOfRooms];
        System.out.println("Inside selectRooms method");
        System.out.println("numberOfRooms = " + numberOfRooms);
        int roomCounter = 0;
        while (roomCounter != numberOfRooms)
        {
            Cell curCell = cells[(int) (Math.random() * cellsHeight)][(int) (Math.random() * cellsHeight)];
            if (curCell.isRoom())
            {
            } else
            {
                System.out.println("Room Counter: " + roomCounter);
                rooms[roomCounter] = new Room(curCell.getTopLeftRow(), curCell.getTopLeftCol(), curCell.getBotRightRow(), curCell.getBotRightCol());
                curCell.setRoom();
                roomCounter++;
            }
        }
        return rooms;
    }

    public void printRooms()
    {
        System.out.println("Inside printRooms method");
        System.out.println(rooms.length);
        for (int i = 0; i < rooms.length; i++)
        {
            System.out.println("Room " + i + ": " + rooms[i].getTopLeftCol() + "," + rooms[i].getTopLeftRow());
        }
    }

    public int numberOfRooms()
    {
        numberOfRooms = (int) Math.random() * cells.length;
        return numberOfRooms;
    }

    public int getNumberOfRooms()
    {
        return numberOfRooms;
    }

    public Tile[][] returnProduct()
    {

        return dungeon;

    }

    public int getWidth()
    {

        return dungeonWidth;

    }

    public int getHeight()
    {

        return dungeonHeight;

    }
    // need a way to generate a random-sized room
    // (random height/width less than cell width)
    // so:
    // Divide the array up into cells of some height/width
    // We have a "cell" Object that we create which has
    // the boundary locations of the cell in it
}