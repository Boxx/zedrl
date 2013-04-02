/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

/**
 *
 * @author Brandon
 */
public class Inventory {

    private Item[] items;

    public Inventory(int max) {
        items = new Item[max];
    }

    public Item[] getItems() {
        return items;
    }

    public void add(Item item) {
        if (item.isStackable()) {
            for (int i = 0; i < items.length; i++) {
                if (items[i] != null) {
                    if (items[i].getName().equals(item.getName())) {
                        items[i].addStack();
                        return;
                    }
                }

            }
        }

        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                return;
            }
        }

    }

    public void remove(Item item) {
        if (item.isStackable()) {
            for (int i = 0; i < items.length; i++) {
                if (items[i].getName().equals(item.getName())) {
                    items[i].removeStack();
                    if (items[i].getStacks() == 0) {
                        items[i] = null;
                    }
                    return;
                }
            }
        } else {
            for (int i = 0; i < items.length; i++) {
                if (items[i] == item) {
                    items[i] = null;
                    return;
                }
            }
        }

    }

}
