package testers;

import javafx.scene.canvas.GraphicsContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * this class maanges all rats on a given board and can add, remove and query rats.
 * It is also responsible for making all rats function consistently.
 *
 * @version 1.7
 */
public class RatManager {
    // 3 sets of current rats, rats to be added and rats to be removed.
    private final Set<AbstractRat> rats = new HashSet<>();
    private final Set<AbstractRat> markedRemoval = new HashSet<>();
    private final Set<AbstractRat> markedAddition = new HashSet<>();

    /**
     * This method will iterate add all new rats to the current rat list,
     * and remove all rats marked for removal from the current rats list.
     */
    public void tick() {
        this.rats.addAll(this.markedAddition);
        this.markedAddition.clear();

        for (AbstractRat rat : this.rats) {
            rat.tick();
        }

        for (AbstractRat markedRat : this.markedRemoval) {
            this.removeRat(markedRat);
        }
        this.markedRemoval.clear();
    }

    /**
     * This method invokes the rendering method of every rat in the current rats list.
     *
     * @param gc The graphical context to render all rats to.
     */
    public void renderAll(GraphicsContext gc) {
        for (AbstractRat rat : this.rats) {
            rat.renderTo(gc);
        }
    }

    /**
     * This method adds a new rat to the list of current rats.
     *
     * @param rat The rat to be added to the current rats list.
     */
    public void addRat(AbstractRat rat) {
        this.rats.add(rat);
    }

    /**
     * This method adds a rat to the list of rats to be added next tick.
     *
     * @param rat The rat to be added next tick.
     */
    public void addNextTick(AbstractRat rat) {
        this.markedAddition.add(rat);
    }

    /**
     * This method removes a rat from the current rats list.
     *
     * @param rat The rat that needs to be removed.
     */
    public void removeRat(AbstractRat rat) {
        this.rats.remove(rat);
    }

    /**
     * This method adds a rat to the list of rats that need to be removed next game tick.
     *
     * @param rat the rat that will be removed next tick.
     */
    public void removeNextTick(AbstractRat rat) {
        this.markedRemoval.add(rat);
    }

    /**
     * This method will return a list of rats that exist on a current game board position.
     *
     * @param pos The game board position being queried.
     * @return the list of rats that exist on the queried game board position.
     */
    public List<AbstractRat> getRatsAt(Position pos) {
        return this.getRatsAt(pos.getX(), pos.getY());
    }

    /**
     * This method returns a list of rats that exist on a (x, y) co-ordinate in a game board's tile grid.
     *
     * @param x The X co-ordinate of the query.
     * @param y The Y co-ordinate of the query.
     * @return The list of rats which exist on the (x, y) co-ordinate of the game board's tile grid.
     */
    public List<AbstractRat> getRatsAt(int x, int y) {
        // Grab a stream of AbstractRat objects filtered by a condition
        // (the x and y co-ordinate match the queried ones.), convert to list and return.
        return this.rats.stream()
                .filter((rat) -> rat.getX() == x && rat.getY() == y)
                .toList();
    }
}
