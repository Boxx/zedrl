package zedrl.dungeon;

import zedrl.utilities.Roller;

/**
 *
 * @author Brandon
 */
public class DungeonBuilder {

    private Tile[][] dungeon;
    private int dungeonWidth;
    private int dungeonHeight;
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
    }

    /**
     * Builder method that constructs a dungeon using the "component" methods
     *
     * @return returns a dungeon and list of rooms
     */
    public Dungeon build() {
        dungeon = new Tile[dungeonHeight][dungeonWidth];
        Cell[][] cells = buildCells(cellSize);
        Room[] roomList = buildRoomList(cells);
        dungeon = buildDungeon(roomList);
        return new Dungeon(dungeon, roomList);
    }

    /**
     *
     * @param cellSize
     * @return returns a grid of cells
     */
    private Cell[][] buildCells(int cellSize) {
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
    public Room[] buildRoomList(Cell[][] cells) {
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
    public Tile[][] buildDungeon(Room[] roomList) {

        /*
         * Iterate through dungeon and set all tiles to OOB initially
         */
        for (int i = 0; i < dungeon.length; i++) {

            for (int j = 0; j < dungeon[0].length; j++) {
                dungeon[i][j] = Tile.WALL;
            }
        }

        /*
         * This loop runs through the room list and digs out all the rooms
         * Future plans: use different ASCII characters to make rooms prettier
         *
         */
        for (int i = 0; i < roomList.length; i++) { // Room list iterator

            for (int j = roomList[i].getTopLeftRow(); j <= roomList[i].getBotRightRow(); j++) {  // Dungeon rows

                for (int k = roomList[i].getTopLeftCol(); k <= roomList[i].getBotRightCol(); k++) {  // Dungeon columns

                    if (j == roomList[i].getTopLeftRow() || j == roomList[i].getBotRightRow()) {  // Handles the top and bottom sides of the rect

                        dungeon[k][j] = Tile.WALL;
                    } else if (k == roomList[i].getTopLeftCol() || k == roomList[i].getBotRightCol()) { // Left and Right
                        dungeon[k][j] = Tile.WALL;
                    } else {
                        dungeon[k][j] = Tile.FLOOR;
                    }
                }
            }
        }

        for (int i = 0; i < roomList.length - 1; i++) {
            
            /*
            int startX = Roller.randomInt(roomList[i].getTopLeftCol(), roomList[i].getBotRightCol());
            int startY = Roller.randomInt(roomList[i].getTopLeftRow(), roomList[i].getBotRightRow());
            int targetX = Roller.randomInt(roomList[i + 1].getTopLeftCol(), roomList[i + 1].getBotRightCol());
            int targetY = Roller.randomInt(roomList[i + 1].getTopLeftRow(), roomList[i + 1].getBotRightRow());
            */
            
            int startX = roomList[i].getTopLeftCol() + (roomList[i].getBotRightCol() - roomList[i].getTopLeftCol())/2;
            int startY = roomList[i].getTopLeftRow() + (roomList[i].getBotRightRow() - roomList[i].getTopLeftRow())/2;
            int targetX = roomList[i+1].getTopLeftCol() + (roomList[i+1].getBotRightCol() - roomList[i+1].getTopLeftCol())/2;
            int targetY = roomList[i+1].getTopLeftRow() + (roomList[i+1].getBotRightRow() - roomList[i+1].getTopLeftRow())/2;
            
            
            if (startY < targetY){
                for(;startY < targetY; startY++){
                    dungeon[startX][startY] = Tile.FLOOR;
                }
            }
            if (startY > targetY){
                for(;startY > targetY; startY--){
                    dungeon[startX][startY] = Tile.FLOOR;
                }
            }
            if (startX < targetX){
                for(;startX < targetX; startX++){
                    dungeon[startX][targetY] = Tile.FLOOR;
                }
            }
            if (startX > targetX){
                for(;startX > targetX; startX--){
                    dungeon[startX][targetY] = Tile.FLOOR;
                }
            }
            if (startX == targetX && startY == targetY){
                roomList[i].setIsConnected(true);
                roomList[i+1].setIsConnected(true); 
            }
        }
        return dungeon;
    }

    public void printDungeon() {
        for (int i = 0; i < dungeon.length; i++) {

            for (int j = 0; j < dungeon[0].length; j++) {
                System.out.print(dungeon[i][j].getGlyph());
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
