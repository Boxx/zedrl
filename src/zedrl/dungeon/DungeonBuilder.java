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
    private Room[] roomsAtLevel;
    private int levelCounter;
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
        this.levelCounter = 0;
        for (int i = 0; i < dungeonDepth; i++) {
            cells = buildCells();
            roomsAtLevel = buildroomsAtLevel();
            //masterroomsAtLevel.addAll(Arrays.asList(roomsAtLevel));
            dungeonlevel = buildDungeon();
            buildStairs();
            for (int j = 0; j < dungeonlevel.length; j++) {
                for (int k = 0; k < dungeonlevel[0].length; k++) {
                    dungeon[k][j][i] = dungeonlevel[k][j];
                }
            }
            levelCounter++;
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
    private Room[] buildroomsAtLevel() {
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
     * @param roomsAtLevel
     * @return returns a dungeon
     */
    private Tile[][] buildDungeon() {

        /*
         * Iterate through dungeon and set all tiles to OOB initially
         */
        for (int i = 0; i < dungeonlevel.length; i++) {

            for (int j = 0; j < dungeonlevel[0].length; j++) {
                dungeonlevel[i][j] = Tile.WALL;
            }
        }

        /*
         * Seed room between 0 and roomsAtLevel length if we are in the seedroom
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
        
        
        
       for (int i = 0; i < roomsAtLevel.length; i++) { // Room list iterator

            for (int j = roomsAtLevel[i].getTopLeftRow(); j <= roomsAtLevel[i].getBotRightRow(); j++) {  // Dungeon rows

                for (int k = roomsAtLevel[i].getTopLeftCol(); k <= roomsAtLevel[i].getBotRightCol(); k++) {  // Dungeon columns

                    if (j == roomsAtLevel[i].getTopLeftRow() || j == roomsAtLevel[i].getBotRightRow()) {  // Handles the top and bottom sides of the rect

                        dungeonlevel[k][j] = Tile.WALL;
                    } else if (k == roomsAtLevel[i].getTopLeftCol() || k == roomsAtLevel[i].getBotRightCol()) { // Left and Right
                        dungeonlevel[k][j] = Tile.WALL;
                    } else {
                        dungeonlevel[k][j] = Tile.FLOOR;
                    }
                }
            }
        }

        for (int i = 0; i < roomsAtLevel.length - 1; i++) {
            
            int startX = roomsAtLevel[i].getCenterX();
            int startY = roomsAtLevel[i].getCenterY();
            int targetX = roomsAtLevel[i+1].getCenterX();
            int targetY = roomsAtLevel[i+1].getCenterY();
            
            
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
                roomsAtLevel[i].setIsConnected(true);
                roomsAtLevel[i+1].setIsConnected(true); 
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

    private void buildStairs(){
        if(levelCounter == 0){
            buildDownStairs();
        }
        else if(levelCounter == dungeonDepth){
            buildUpStairs();
        }else{
            buildDownStairs();
            buildUpStairs();
        }
    }
    private void buildUpStairs(){
        
        int seedRoomUp1 = (int) (Math.random() * roomsAtLevel.length - 1);
            int seedRoomUp2;
            do {
                seedRoomUp2 = (int) (Math.random() * roomsAtLevel.length - 1);
            } while (seedRoomUp1 == seedRoomUp2);
            
            for (int i = 0; i < roomsAtLevel.length; i++) { // Room list iterator

                if (i == seedRoomUp1 || i == seedRoomUp2){

                    int width = roomsAtLevel[i].getBotRightCol() - roomsAtLevel[i].getTopLeftCol() - 1;
                    int height = roomsAtLevel[i].getBotRightRow() - roomsAtLevel[i].getTopLeftRow() - 1;
                    int stairX = (int) (Math.random() * width + roomsAtLevel[i].getTopLeftCol()) + 1;
                    int stairY = (int) (Math.random() * height + roomsAtLevel[i].getTopLeftRow()) + 1;

                    dungeonlevel[stairX][stairY] = Tile.UP;
                    dungeonlevel[stairX][stairY].setConnection(stairX, stairY, levelCounter);
                }
            }
            
        
    }
    private void buildDownStairs(){
        int seedRoomDown1 = (int) (Math.random() * roomsAtLevel.length - 1);
            int seedRoomDown2;
            do {
                seedRoomDown2 = (int) (Math.random() * roomsAtLevel.length - 1);
            } while (seedRoomDown1 == seedRoomDown2);
            
            
            
            for (int i = 0; i < roomsAtLevel.length; i++) { // Room list iterator

                if (i == seedRoomDown1 || i == seedRoomDown2){

                    int width = roomsAtLevel[i].getBotRightCol() - roomsAtLevel[i].getTopLeftCol() - 1;
                    int height = roomsAtLevel[i].getBotRightRow() - roomsAtLevel[i].getTopLeftRow() - 1;
                    int stairX = (int) (Math.random() * width + roomsAtLevel[i].getTopLeftCol()) + 1;
                    int stairY = (int) (Math.random() * height + roomsAtLevel[i].getTopLeftRow()) + 1;

                    dungeonlevel[stairX][stairY] = Tile.DOWN;
                    dungeonlevel[stairX][stairY].setConnection(stairX, stairY, levelCounter);

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
