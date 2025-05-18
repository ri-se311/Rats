package testers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * class for other classes to implement rats
 */
public abstract class AbstractRat {
    protected GameBoard board;
    /**
     * Current position of the Rat represented by 2 ints (x, y)
     */
    protected final Position position;
    /**
     * The Direction the Rat is currently facing; used for rendering and movement
     */
    protected Direction facing = Direction.UP;

    protected AbstractRat(Position position, GameBoard board) {
        this.position = position;
        this.board = board;
    }

    /**
     * @return the number of tiles that should be moved by the rat per game tick
     */
    public int getMoveSpeed() {
        return 1;
    }

    public abstract Image getSprite();

    private boolean isMovable(int x, int y) {
        if (x < 0 || y < 0) {
            return false;
        }

        //TODO: Also check for no-entry signs once item manager exists
        Tile tile = this.board.getTileManager().getTile(x, y);
        if (tile == null) {
            return false;
        }

        return tile.isMovable();
    }


    /**
     * Moves the Rat a single tile
     */
    protected void moveStep() {
        List<Direction> candidateDirs = new ArrayList<>(List.of(Direction.values()));
        candidateDirs.remove(this.facing.getOpposite());
        for (ListIterator<Direction> dirIter = candidateDirs.listIterator(); dirIter.hasNext(); ) {
            Direction dir = dirIter.next();

            int xMoveOffset = dir.getXOffset();
            int yMoveOffset = dir.getYOffset();

            int newX = this.position.getX() + xMoveOffset;
            int newY = this.position.getY() + yMoveOffset;

            if (!isMovable(newX, newY)) {
                dirIter.remove();
            }
        }

        Direction chosenDir;
        if (candidateDirs.isEmpty()) {
            chosenDir = this.facing.getOpposite();
        } else {
            chosenDir = candidateDirs.get(ThreadLocalRandom.current().nextInt(candidateDirs.size()));
        }

        int xMoveOffset = chosenDir.getXOffset();
        int yMoveOffset = chosenDir.getYOffset();

        int newX = this.position.getX() + xMoveOffset;
        int newY = this.position.getY() + yMoveOffset;

        this.facing = chosenDir;
        this.position.setX(newX);
        this.position.setY(newY);
    }

    /**
     * tick system for the game
     */
    public void tick() {
        for (int i = 0; i < getMoveSpeed(); i++) {
            moveStep();
        }
    }

    public int getX() {
        return this.position.getX();
    }

    public int getY() {
        return this.position.getY();
    }

    public void remove() {
        this.board.getRatManager().removeNextTick(this);
    }

    /**
     * renders the game to a region
     * @param gc
     */
    public void renderTo(GraphicsContext gc) {
        Tile curTile = this.board.getTileManager().getTile(getX(), getY());
        if (curTile.isPlacable()) {
            int xOffset = GameBoard.getRenderOffset(getX());
            int yOffset = GameBoard.getRenderOffset(getY());
            GraphicUtils.drawRotatedImage(gc, this.getSprite(), this.facing.getDegOffset(), xOffset, yOffset);
        }
    }

}
