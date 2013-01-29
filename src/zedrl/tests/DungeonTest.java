/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.tests;

import zedrl.dungeon.DungeonBuilder;

/**
 *
 * @author Brandon
 */
public class DungeonTest
{

    public static void main(String[] args)
    {
        // Begin DungeonBuilder test case #1
        {
            DungeonBuilder db = new DungeonBuilder(50, 50, 10);
            db.printRooms();
        }
    }
}
