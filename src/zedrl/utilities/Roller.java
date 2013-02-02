/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.utilities;
import java.util.Random;

/*
 Created 27/08/2007 20:21:35
 */

/**
 * Allows probability and dice rolls.
 */
public final class Roller
{

    private Roller()
    {

    }

    private final static Random rand = new Random();

    /**
     * Generates a pseudo-random uniformly distributed int between 0 and 99
     * (both inclusive).
     * 
     * @return The int generated.
     */
    public static int percent()
    {
        return randomInt(99);
    }

    /**
     * Generates a pseudo-random uniformly distributed int between 0 and 99
     * (both inclusive) and compares it with chancePercent.
     * 
     * @param chancePercent
     *            percentage to be compared to a random int between 0 and 99.
     * @return true if the random int is smaller than chancePercent; false
     *         otherwise.
     */
    public static boolean chance(int chancePercent)
    {
        return ((Math.abs(rand.nextInt() % 100)) < chancePercent);
    }

    /**
     * Generates a pseudo-random uniformly distributed int between 0 and max
     * (both inclusive).
     * 
     * @param max
     * 
     * @return
     */
    public static int randomInt(int max) // from 0 to max
    {
        return Math.abs(rand.nextInt() % (max + 1));
    }

    /**
     * Generates a pseudo-random uniformly distributed int between lowLimit and
     * highLimit.
     * 
     * @param lowLimit
     * @param highLimit
     * @return
     */
    public static int randomInt(int lowLimit, int highLimit)
    {
        return lowLimit + randomInt(highLimit - lowLimit);
    }

}
