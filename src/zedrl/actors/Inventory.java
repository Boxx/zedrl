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
    
    
    public Inventory(int max){
        items = new Item[max];
    }

    public Item[] getItems() {
        return items;
    }
    
    public void add(Item item){
        for (int i = 0; i < items.length; i++){
            if (items[i] == null){
                items[i] = item;
                break;
            }
        }
    }
    public void remove(Item item){
        for (int i = 0; i < items.length; i++){
            if (items[i] == item){
                items[i] = null;
                return;
            }
        }
    }
}
