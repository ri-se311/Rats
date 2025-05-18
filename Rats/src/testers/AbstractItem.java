package testers;

/**
 * This is the abstract class which all items inherit from, it is the most basic behaviour any item can have.
 *
 * @version 1.6
 */
public abstract class AbstractItem {
    private Position position;
    protected GameBoard board;

    /**
     * Constructor for an item, taking the X and Y co-ordiante to create it at,
     * and the board that it will sit on.
     *
     * @param x     x coordinate to set for a item.
     * @param y     y coordinate to set for a item.
     * @param board the GameBoard object the item is being placed onto.
     */
    public AbstractItem(int x, int y, GameBoard board) {
        this.position = new Position(x, y);
        this.board = board;
    }

    /**
     * returns the position of the item.
     *
     * @return the position of the item
     */
    public Position getPosition() {
        return position;
    }

    /**
     * returns the GameBoard the item is sitting on.
     *
     * @return the GameBoard object the item is on.
     */
    public GameBoard getBoard() {
        return board;
    }

    /**
     * This is the abstract method that every item will implement to function.
     * The implementation varies from class to class.
     */
    public abstract void tick();

}
