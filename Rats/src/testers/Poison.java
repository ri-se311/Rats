package testers;

import java.util.List;

/**
 * This class represents the poison item.
 *
 * @version 1.1
 */
public class Poison extends AbstractItem {
    /**
     * This is the constructor for a poison object, inherited from the superclass.
     *
     * @param x     The X co-ordinate to spawn the poison at.
     * @param y     The Y co-ordinate to spawn the poison at.
     * @param board The GameBoard to spawn the poison on.
     */
    public Poison(int x, int y, GameBoard board) {
        super(x, y, board);
    }

    @Override
    public void tick() {

        // Get a list of all rats at a certain position
        List<AbstractRat> rats = board.getRatManager().getRatsAt(this.getPosition());

        //if it is a DeathRat, don't include it.
        for (AbstractRat rat : rats) {
            if (rat instanceof DeathRat) {
                rats.remove(rat);
            }
        }

        // If there are any non-death rats left, queue the first one
        // found in the list for removal, and then remove the item.
        if (!rats.isEmpty()) {
            board.getRatManager().removeNextTick(rats.get(0));
            board.getItemManager().removeItemAtPosition(this.getPosition());
        }
    }
}
