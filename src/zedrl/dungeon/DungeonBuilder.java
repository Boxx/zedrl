package zedrl.dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import zedrl.utilities.Roller;

/**
 *
 * @author Brandon
 */
public class DungeonBuilder {

    private Tile[][] dungeonlevel;
    private Tile[][][] dungeon;
    private Cell[][] cells;
    private Room[] roomList;
    private int curLevel;
    private int dungeonWidth;
    private int dungeonHeight;
    private int dungeonDepth;
    private int cellsHeight;
    private int cellsWidth;
    private int cellSize;

    // ROW, COLUMN
    /**
     * Constructor that takes in desired dungeon size and cell structure for the
     * grid segmentations
     *
     * @param height
     * @param width
     * @param cellSize
     */
    public DungeonBuilder(int height, int width, int cellSize) {
        this.dungeonWidth = width;
        this.dungeonHeight = height;
        this.cellSize = cellSize;
        this.dungeonDepth = 10;
        this.dungeonlevel = new Tile[dungeonHeight][dungeonWidth];
        this.dungeon = new Tile[dungeonHeight][dungeonWidth][dungeonDepth];
        for (int i = 0; i < dungeonDepth; i++) {
            cells = buildCells();
            roomList = buildRoomList();
            //masterRoomList.addAll(Arrays.asList(roomsAtLevel));
            dungeonlevel = buildDungeon();
            for (int j = 0; j < dungeonlevel.length; j++) {
                for (int k = 0; k < dungeonlevel[0].length; k++) {
                    dungeon[k][j][i] = dungeonlevel[k][j];
                }
            }
            curLevel++;
        }

    }

    /**
     * Builder method that constructs a dungeon using the "component" methods
     *
     * @return returns a dungeon and list of rooms
     */
    public Dungeon build() {
        return new Dungeon(dungeon);
    }

    /**
     *
     * @param cellSize
     * @return returns a grid of cells
     */
    private Cell[][] buildCells() {
        cellsWidth = dungeonWidth / cellSize;
        cellsHeight = dungeonHeight / cellSize;
        Cell[][] cells = new Cell[cellsHeight][cellsWidth];

        for (int i = 0; i < dungeonHeight; i += cellSize) {
            for (int j = 0; j < dungeonWidth; j += cellSize) {

                cells[i / cellSize][j / cellSize] = new Cell(i, j, i + cellSize - 1,
                        j + cellSize - 1);

            }
        }

        return cells;
    }

    /**
     * Takes in the cell grid and randomly selects cells as room candidates
     * Populates a room list with these cells
     *
     * @param cells
     * @return returns a list of rooms
     */
    public Room[] buildRoomList() {
        int cellCount = cellsWidth * cellsHeight;
        int ROOM_MIN = (int) (.3 * cellCount);
        int ROOM_THRESHHOLD = (int) (.5 * cellCount);
        int numberOfRooms = 0;
        while (numberOfRooms <= ROOM_MIN || numberOfRooms >= ROOM_THRESHHOLD) {
            numberOfRooms = (int) (Math.random() * cellCount);
        }
        Room[] rooms = new Room[numberOfRooms];
        int roomCounter = 0;
        while (roomCounter != numberOfRooms) {
            Cell curCell = cells[(int) (Math.random() * cellsHeight)][(int) (Math.random() * cellsWidth)];
            if (curCell.isRoom()) {
            } else {
                rooms[roomCounter] = new Room(curCell.getTopLeftRow(), curCell.getTopLeftCol(), curCell.getBotRightRow(), curCell.getBotRightCol());
                curCell.setRoom();
                roomCounter++;
            }
        }
        return rooms;
    }

    /**
     * Digs the dungeon, rooms, and corridors
     *
     * @param roomList
     * @return returns a dungeon
     */
    public Tile[][] buildDungeon() {

        /*
         * Iterate through dungeon and set all tiles to OOB initially
         */
        for (int i = 0; i < dungeonlevel.length; i++) {

            for (int j = 0; j < dungeonlevel[0].length; j++) {
                dungeonlevel[i][j] = Tile.WALL;
            }
        }

        /*
         * Seed room between 0 and roomlist length if we are in the seedroom
         * generate the coords and make a stair otherwise we'll skip over this
         * shit
         *
         * up seedroom and down seedroom
         */


        /*
         * This loop runs through the room list and digs out all the rooms
         * Future plans: use different ASCII characters to make rooms prettier
         *
         */
        
        
        
       for (int i = 0; i < roomList.length; i++) { // Room list iterator

            for (int j = roomList[i].getTopLeftRow(); j <= roomList[i].getBotRightRow(); j++) {  // Dungeon rows

                for (int k = roomList[i].getTopLeftCol(); k <= roomList[i].getBotRightCol(); k++) {  // Dungeon columns

                    if (j == roomList[i].getTopLeftRow() || j == roomList[i].getBotRightRow()) {  // Handles the top and bottom sides of the rect

                        dungeonlevel[k][j] = Tile.WALL;
                    } else if (k == roomList[i].getTopLeftCol() || k == roomList[i].getBotRightCol()) { // Left and Right
                        dungeonlevel[k][j] = Tile.WALL;
                    } else {
                        dungeonlevel[k][j] = Tile.FLOOR;
                    }
                }
            }
        }

        for (int i = 0; i < roomList.length - 1; i++) {
            
            int startX = roomList[i].getCenterX();
            int startY = roomList[i].getCenterY();
            int targetX = roomList[i+1].getCenterX();
            int targetY = roomList[i+1].getCenterY();
            
            
            if (startY < targetY){
                for(;startY < targetY; startY++){
                    dungeonlevel[startX][startY] = Tile.FLOOR;
                }
            }
            if (startY > targetY){
                for(;startY > targetY; startY--){
                    dungeonlevel[startX][startY] = Tile.FLOOR;
                }
            }
            if (startX < targetX){
                for(;startX < targetX; startX++){
                    dungeonlevel[startX][targetY] = Tile.FLOOR;
                }
            }
            if (startX > targetX){
                for(;startX > targetX; startX--){
                    dungeonlevel[startX][targetY] = Tile.FLOOR;
                }
            }
            if (startX == targetX && startY == targetY){
                roomList[i].setIsConnected(true);
                roomList[i+1].setIsConnected(true); 
            }
            
        }
        
        /*
         * This generates two seed rooms for the floor and places stairs in
         * them.  
         * 
         * @TODO Need to check the z level so up/down stairs aren't placed on 
         * first/last level
         */
        
        return dungeonlevel;
    }
    public void buildStairs(){
        int seedRoomDown1 = (int) (Math.random() * roomList.length - 1);
        int seedRoomDown2;
        do {
            seedRoomDown2 = (int) (Math.random() * roomList.length - 1);
        } while (seedRoomDown1 == seedRoomDown2);
        
        for (int i = 0; i < roomList.length; i++) { // Room list iterator
            
            if (i == seedRoomDown1 || i == seedRoomDown2){
                
                int width = roomList[i].getBotRightCol() - roomList[i].getTopLeftCol() - 1;
                int height = roomList[i].getBotRightRow() - roomList[i].getTopLeftRow() - 1;
                int stairX = (int) (Math.random() * width + roomList[i].getTopLeftCol()) + 1;
                int stairY = (int) (Math.random() * height + roomList[i].getTopLeftRow()) + 1;
                dungeonlevel[stairX][stairY] = Tile.DOWN;
                /*
                int stairUpX;
                int stairUpY;
                do{
                    stairUpX = (int)(Math.random() * dungeonWidth);
                    stairUpY = (int)(Math.random() * dungeonHeight);
                }while(!tile(stairUpX,stairUpY,curLevel+1).isPassable());
                
                dungeon[stairUpX][stairUpY][curLevel+1] = Tile.UP;
                * */
            }
        }
    }
    public void printDungeon() {
        for (int i = 0; i < dungeonlevel.length; i++) {

            for (int j = 0; j < dungeonlevel[0].length; j++) {
                System.out.print(dungeonlevel[i][j].getGlyph());
            }
            System.out.println();
        }
    }
    public Tile tile(int x, int y, int z) {
        if (x < 0 || x >= dungeonWidth || y < 0 || y >= dungeonHeight) {
            return Tile.OOB;
        } else {
            return dungeon[x][y][z];
        }
    }

    /**
     *
     * @param rooms
     */
    public void printRooms(Room[] rooms) {
        System.out.println("Inside printRooms method");
        System.out.println(rooms.length);
        for (int i = 0; i < rooms.length; i++) {
            System.out.println("Room " + i + ": " + rooms[i].getTopLeftCol() + "," + rooms[i].getTopLeftRow());
        }
    }

    /**
     *
     * @param cells
     */
    public void printCells(Cell[][] cells) {
        System.out.println("Cell Count: " + cells[0].length);
        for (int i = 0; i < cells.length; i++) {

            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j] == null) {
                    System.out.println("Null at " + i + "," + j);
                } else {
                    System.out.println("Top Left: " + cells[i][j].getTopLeftRow() + ","
                            + cells[i][j].getTopLeftCol() + " Bottom Right: "
                            + cells[i][j].getBotRightRow() + ","
                            + cells[i][j].getBotRightCol());
                }

            }
        }
    }

    /**
     *
     * @return
     */
    public int getWidth() {

        return dungeonWidth;

    }

    /**
     *
     * @return
     */
    public int getHeight() {

        return dungeonHeight;

    }

}
