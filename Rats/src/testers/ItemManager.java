package testers;

import java.util.ArrayList;

/**
 * This is a class which holds all items currently existing on a
 * GameBoard, and can answer queries about those items.
 *
 * @version 1.1
 */
public class ItemManager {

    private ArrayList<AbstractItem> items;

    /**
     * Constructor to create an empty ArrayList of items.
     */
    public ItemManager() {
        this.items = new ArrayList<AbstractItem>();
    }

    /**
     * This method returns the item object stored at a certain (x, y) co-ordinate.
     *
     * @param x The X co-ordinate.
     * @param y The Y co-ordinate.
     * @return The item at those co-ordinates.
     */
    public AbstractItem findItemAtPos(int x, int y) {
        Position positionToFind = new Position(x, y);
        AbstractItem queriedItem = null;

        // only query the item list if there is something in it.
        if (items.size() > 0) {
            for (AbstractItem item : items) {
                queriedItem = item;
                // Attempt to match any item's position in the item ArrayList
                // to an item, if they match, return the item
                if (queriedItem.getPosition().equalTo(positionToFind)) {
                    return queriedItem;
                } else { //if false, reset to null for the final queriedItem return.
                    queriedItem = null;
                }
            }
        }
        return null;
    }

    /**
     * This method adds a new item to the items ArrayList.
     *
     * @param item The item to be added to the list.
     */
    public void addNewItem(AbstractItem item) {
        items.add(item);
    }

    /**
     * This method removes a specific item from the ArrayList.
     *
     * @param item The item to remove from the list.
     */
    public void removeItem(AbstractItem item) {
        items.removeIf(item::equals);
    }

    /**
     * This method removes the item which is at a certain position.
     *
     * @param position The position to remove the item from.
     */
    public void removeItemAtPosition(Position position) {
        items.removeIf(item -> item.getPosition().equalTo(position));
    }

    /**
     * This method invokes every items tick method. This will be called once
     * per game tick to make all items attempt to function once per game tick.
     */
    public void tick() {
        for (AbstractItem item : items) {
            item.tick();
        }
    }
}
