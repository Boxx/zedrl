package zedrl.dungeon;

import zedrl.utilities.Roller;

/**
 *
 * @author Brandon
 */
public class DungeonBuilder {

    private Tile[][][] dungeon;
    private int[][][] areas;
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
    }

    /**
     * Builder method that constructs a dungeon using the "component" methods
     *
     * @return returns a dungeon and list of rooms
     */
    public Dungeon build() {
        dungeon = new Tile[dungeonHeight][dungeonWidth][dungeonDepth];
        Cell[][][] cells = buildCells(cellSize);

        Room[] roomList = buildRoomList(cells);
        dungeon = buildDungeon(roomList);
        return new Dungeon(dungeon);
    }
    
    private DungeonBuilder buildAreas(){
        
        areas = new int[dungeonWidth][dungeonHeight][dungeonDepth];
        
        for(int dz = 0; dz < dungeonDepth; dz++){
            for(int dx = 0; dx < dungeonWidth; dx++){
                for(int dy = 0; dy < dungeonHeight; dy++){
                    
                }
            }
        }
        return this;
    }

    /**
     *
     * @param cellSize
     * @return returns a grid of cells
     */
    private Cell[][][] buildCells(int cellSize) {
        cellsWidth = dungeonWidth / cellSize;
        cellsHeight = dungeonHeight / cellSize;
        Cell[][][] cells = new Cell[cellsHeight][cellsWidth][dungeonDepth];
        for (int z = 0; z < dungeonDepth; z++){
            for (int i = 0; i < dungeonHeight; i += cellSize) {
                for (int j = 0; j < dungeonWidth; j += cellSize) {

                    cells[i / cellSize][j / cellSize][z] = new Cell(i, j, i + cellSize - 1,
                            j + cellSize - 1, z);

                }
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
    public Room[] buildRoomList(Cell[][][] cells) {
        int cellCount = cellsWidth * cellsHeight;
        int ROOM_MIN = (int) (.3 * cellCount * dungeonDepth);
        int ROOM_THRESHHOLD = (int) (.5 * cellCount * dungeonDepth);
        int numberOfRooms = 0;
        while (numberOfRooms <= ROOM_MIN || numberOfRooms >= ROOM_THRESHHOLD) {
            numberOfRooms = (int) (Math.random() * cellCount * dungeonDepth);
        }
        System.out.println(numberOfRooms);
        Room[] rooms = new Room[numberOfRooms];
        /*
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
        * */
        for(int z = 0; z < dungeonDepth; z++){
            for (int roomCounter = 0; roomCounter != numberOfRooms; roomCounter++){
                Cell curCell = cells[(int) (Math.random() * cellsHeight)][(int) (Math.random() * cellsWidth)][z];
                if (curCell.isRoom()) {
                } else {
                rooms[roomCounter] = new Room(curCell.getTopLeftRow(), 
                        curCell.getTopLeftCol(), curCell.getBotRightRow(), curCell.getBotRightCol(), curCell.getDepth());
                curCell.setRoom();
                roomCounter++;
                }
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
    public Tile[][][] buildDungeon(Room[] roomList) {

        /*
         * Iterate through dungeon and set all tiles to OOB initially
         */
        for (int x = 0; x < dungeon.length; x++) {
            for (int y = 0; y < dungeon[0].length; y++) {
                for(int z = 0; z < dungeon[0][0].length; z++){
                    
                    dungeon[x][y][z] = Tile.WALL;
                }
                
            }
        }

        /*
         * This loop runs through the room list and digs out all the rooms
         * Future plans: use different ASCII characters to make rooms prettier
         *
         */
            for(int z = 0; z < dungeonDepth; z++){
                System.out.println(z);
                for (int i = 0; i < roomList.length; i++) { // Room list iterator
                    System.out.println(i);
                    for (int j = roomList[i].getTopLeftRow(); j <= roomList[i].getBotRightRow(); j++) {  // Dungeon rows
                        System.out.println(j);
                        for (int k = roomList[i].getTopLeftCol(); k <= roomList[i].getBotRightCol(); k++) {  // Dungeon columns
                            System.out.println(k);
                            if (j == roomList[i].getTopLeftRow() || j == roomList[i].getBotRightRow()) {  // Handles the top and bottom sides of the rect

                                dungeon[k][j][z] = Tile.WALL;
                            } else if (k == roomList[i].getTopLeftCol() || k == roomList[i].getBotRightCol()) { // Left and Right
                                dungeon[k][j][z] = Tile.WALL;
                            } else {
                                dungeon[k][j][z] = Tile.FLOOR;
                            }
                        }
                    }
                }
            }

        for (int i = 0; i < roomList.length - 1; i++) {
            
            int startX = roomList[i].getTopLeftCol() + (roomList[i].getBotRightCol() - roomList[i].getTopLeftCol())/2;
            int startY = roomList[i].getTopLeftRow() + (roomList[i].getBotRightRow() - roomList[i].getTopLeftRow())/2;
            int targetX = roomList[i+1].getTopLeftCol() + (roomList[i+1].getBotRightCol() - roomList[i+1].getTopLeftCol())/2;
            int targetY = roomList[i+1].getTopLeftRow() + (roomList[i+1].getBotRightRow() - roomList[i+1].getTopLeftRow())/2;
            int startDepth = roomList[i].getDepthLevel();
            int targetDepth = roomList[i].getDepthLevel();
            if(startDepth == targetDepth){
                if (startY < targetY){
                    for(;startY < targetY; startY++){
                        dungeon[startX][startY][targetDepth] = Tile.FLOOR;
                    }
                }
                if (startY > targetY){
                    for(;startY > targetY; startY--){
                        dungeon[startX][startY][targetDepth] = Tile.FLOOR;
                    }
                }
                if (startX < targetX){
                    for(;startX < targetX; startX++){
                        dungeon[startX][targetY][targetDepth] = Tile.FLOOR;
                    }
                }
                if (startX > targetX){
                    for(;startX > targetX; startX--){
                        dungeon[startX][targetY][targetDepth] = Tile.FLOOR;
                    }
                }
                if (startX == targetX && startY == targetY){
                    roomList[i].setIsConnected(true);
                    roomList[i+1].setIsConnected(true); 
                }
            } else {
                System.out.println("Uh oh these rooms are on different Z levels");
            }
        }
        return dungeon;
    }
/*
    public void printDungeon() {
        for (int i = 0; i < dungeon.length; i++) {

            for (int j = 0; j < dungeon[0].length; j++) {
                System.out.print(dungeon[i][j].getGlyph());
            }
            System.out.println();
        }
    }
*/
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
