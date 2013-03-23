/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

import zedrl.dungeon.Dungeon;
import zedrl.dungeon.Line;
import zedrl.dungeon.Location;
import zedrl.dungeon.Tile;

/**
 *
 * @author Brandon
 */
public class FieldOfView {

    private Dungeon dungeon;
    private int depth;
    private boolean[][] visible;
    private Tile[][][] tiles;

    public FieldOfView(Dungeon dungeon) {
        this.dungeon = dungeon;
        this.visible = new boolean[dungeon.getWidth()][dungeon.getHeight()];
        this.tiles = new Tile[dungeon.getWidth()][dungeon.getHeight()][dungeon.getDepth()];

        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                for (int z = 0; z < dungeon.getDepth(); z++) {
                    tiles[x][y][z] = Tile.unknown();
                }
            }
        }

    }

    public void update(int vx, int vy, int vz, int r) {
        depth = vz;
        visible = new boolean[dungeon.getWidth()][dungeon.getHeight()];

        for (int x = -r; x < r; x++) {
            for (int y = -r; y < r; y++) {
                if (x * x + y * y > r * r) {
                    continue;
                }
                if (vx + x < 0 || vx + x >= dungeon.getWidth() || vy + y < 0
                        || vy + y >= dungeon.getHeight()) {
                    continue;
                }
                for (Location p : new Line(vx, vy, vx + x, vy + y)) {
                    Tile thisTile = dungeon.tile(p.getX(), p.getY(), vz);
                    visible[p.getX()][p.getY()] = true;
                    tiles[p.getX()][p.getY()][vz] = thisTile;

                    if (!thisTile.isPassable()) {
                        break;
                    }
                }
            }
        }


    }

    public boolean isVisable(int x, int y, int z) {
        return z == depth && x >= 0 && y >= 0 && x < visible.length && y < visible[0].length && visible[x][y];
    }

    public Tile getTile(int x, int y, int z) {
        return tiles[x][y][z];
    }

}
