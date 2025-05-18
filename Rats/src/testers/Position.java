package testers;

/**
 * This class acts like a tuple of two numbers, representing a co-ordinate in a grid of tiles on a game board.
 *
 * @version 1.2
 */
public final class Position {
    private int x;
    private int y;

    /**
     * Constructor, sets the X and Y co-ordinate values.
     *
     * @param x x coordinate to set for an element.
     * @param y y coordinate to set for an element.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method gets the x co-ordinate of the position.
     *
     * @return Position the X co-ordinate
     */
    public int getX() {
        return x;
    }

    /**
     * This method gets the y co-ordinate of the position.
     *
     * @return Position y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * This method sets the x co-ordinate of the position.
     *
     * @param x The x co-ordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * This method sets the Y co-ordinate of the position.
     *
     * @param y The y co-ordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * This method checks whether 2 position objects are equal.
     *
     * @param position the position object to compare against.
     */
    public boolean equalTo(Position position) {
        if (position.getX() == this.getX() && position.getY() == this.getY()) {
            return true;
        } else {
            return false;
        }
    }
}
