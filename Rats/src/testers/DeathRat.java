package testers;

import javafx.scene.image.Image;

import java.util.List;

/**
 * Deathrat Class implementation of AbstractRat
 *
 * @see testers.AbstractRat
 */
public class DeathRat extends AbstractRat {
    private static final int MAX_KILLS = 5;
    private static final Image SPRITE = new Image("DeathRat.png");

    /**
     * Represents the number of {@link AntagonistRat}s that this DeathRat has already killed
     */
    private int ratsKilled;

    /**
     * @param position position of the deathrat in the game board
     * @param board    the game board
     */
    public DeathRat(Position position, GameBoard board) {
        super(position, board);
    }

    /**
     * get the deathrat image
     *
     * @return image of death rat
     */
    @Override
    public Image getSprite() {
        return SPRITE;
    }

    /**
     * method for moving the deathrat
     */
    @Override
    protected void moveStep() {
        super.moveStep();

        List<AbstractRat> targets = this.board.getRatManager().getRatsAt(this.position);
        for (AbstractRat target : targets) {
            if (target instanceof AntagonistRat) {
                killRat((AntagonistRat) target);

                if (this.ratsKilled >= DeathRat.MAX_KILLS) {
                    break;
                }
            }
        }
    }

    /**
     * method for killing rat
     *
     * @param target the target to kill
     */
    public void killRat(AntagonistRat target) {
        if (this.ratsKilled < DeathRat.MAX_KILLS) {
            target.remove();
        }

        this.ratsKilled++;
        if (this.ratsKilled >= DeathRat.MAX_KILLS) {
            this.remove();
        }
    }
}

