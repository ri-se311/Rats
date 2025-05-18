package testers;

/**
 * set of directions for the rats to change face
 *
 */

public enum Direction {
    UP(270, new Position(0, -1)),
    RIGHT(0, new Position(1, 0)),
    DOWN(90, new Position(0, 1)),
    LEFT(180, new Position(-1, 0));

    private final int degOffset;
    private final Position moveOffset;

    /**
     * constructs Direction object
     *
     * @param degOffset  degrees offset
     * @param moveOffset position to offset
     */
    Direction(int degOffset, Position moveOffset) {
        this.degOffset = degOffset;
        this.moveOffset = moveOffset;
    }

    public int getDegOffset() {
        return degOffset;
    }

    public int getXOffset() {
        return this.moveOffset.getX();
    }

    public int getYOffset() {
        return this.moveOffset.getY();
    }

    public Direction cycle() {
        Direction[] values = Direction.values();
        int newOrdinal = (this.ordinal() + 1) % values.length;
        return values[newOrdinal];
    }

    public Direction getOpposite() {
        return this.cycle().cycle();
    }
}
