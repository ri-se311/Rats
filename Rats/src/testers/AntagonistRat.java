package testers;

import javafx.scene.image.Image;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class to represent enemy rats to kill
 */
public class AntagonistRat extends AbstractRat {
    private static final Image MALE_SPRITE = new Image("MaleRat.png");
    private static final Image FEMALE_SPRITE = new Image("FemaleRat.png");
    private static final Image BABY_SPRITE = new Image("BabyRat.png");
    /**
     * The {@link AntagonistRat#age} that a rat should start to be considered an adult from
     */
    private static final int ADULT_AGE_THRESHOLD = 5;
    private static final int TOTAL_PREGNANCY_TICKS = 12;

    private boolean isMale;
    private int pregnancyTicks = -1;
    private boolean sterile = false;

    /**
     * Represents the age of the rat in game ticks
     */
    private int age = 0;

    public AntagonistRat(Position position, GameBoard board, boolean isMale) {
        super(position, board);
        this.isMale = isMale;
    }

    public Position getPosition() {
        return this.position;
    }

    public void markSterile() {
        this.sterile = true;
    }

    public void setMale(boolean male) {
        this.isMale = male;
    }

    private boolean isBaby() {
        return this.age < AntagonistRat.ADULT_AGE_THRESHOLD;
    }

    @Override
    public Image getSprite() {
        if (this.isBaby()) {
            return AntagonistRat.BABY_SPRITE;
        } else if (this.isMale) {
            return AntagonistRat.MALE_SPRITE;
        } else {
            return AntagonistRat.FEMALE_SPRITE;
        }
    }

    private boolean canMateWith(AbstractRat other) {
        if (this == other) return false;
        if (!(other instanceof AntagonistRat otherAntagonist)) return false;

        // Ensure that rats are of different sex
        if (this.isMale != otherAntagonist.isMale) return false;

        if (!this.canMate() || otherAntagonist.canMate()) return false;

        return true;
    }

    private boolean canMate() {
        if (this.isPregnant()) return false;
        if (this.sterile) return false;
        if (this.isBaby()) return false;

        return true;
    }

    @Override
    protected void moveStep() {
        super.moveStep();

        List<AbstractRat> otherRats = this.board.getRatManager().getRatsAt(this.position);
        for (AbstractRat otherRat : otherRats) {
            if (otherRat instanceof AntagonistRat && this.canMateWith(otherRat)) {
                if (this.isMale) {
                    ((AntagonistRat) otherRat).markPregnant();
                } else {
                    this.markPregnant();
                }

                // Only allow for mating with 1 other
                break;
            } else if (otherRat instanceof DeathRat deathRat) {
                deathRat.killRat(this);
            }
        }
    }

    private boolean isPregnant() {
        return this.pregnancyTicks >= 0;
    }

    private void markPregnant() {
        this.pregnancyTicks = 0;
    }

    private void resetPregnancy() {
        this.pregnancyTicks = -1;
    }

    private AntagonistRat createChild() {
        boolean isChildMale = ThreadLocalRandom.current().nextBoolean();

        return new AntagonistRat(this.position, this.board, isChildMale);
    }

    @Override
    public int getMoveSpeed() {
        int base = super.getMoveSpeed();
        if (this.isBaby()) {
            return base * 2;
        }

        return base;
    }

    @Override
    public void tick() {
        super.tick();
        this.age++;

        if (this.isPregnant()) {
            this.pregnancyTicks++;

            // Possible if a rat moves onto a sterility item after beginning of pregnancy
            if (this.sterile) {
                this.resetPregnancy();
            }

            // Give birth
            if (this.pregnancyTicks >= AntagonistRat.TOTAL_PREGNANCY_TICKS) {
                this.resetPregnancy();

                this.board.getRatManager().addNextTick(this.createChild());
            }
        }
    }

    /*public static void main(String[] args) throws FileNotFoundException {
        GameBoard board = new GameBoard("Rats/resources/SampleGrid.txt");
        AntagonistRat testRat = new AntagonistRat(new Position(1, 1), board, true);

        System.out.println(testRat.getPosition().getX() + " + " + testRat.getPosition().getY());
        testRat.moveStep();
        System.out.println(testRat.getPosition().getX() + " + " + testRat.getPosition().getY());
        testRat.moveStep();
        System.out.println(testRat.getPosition().getX() + " + " + testRat.getPosition().getY());
        testRat.moveStep();
        System.out.println(testRat.getPosition().getX() + " + " + testRat.getPosition().getY());
        testRat.moveStep();
        System.out.println(testRat.getPosition().getX() + " + " + testRat.getPosition().getY());
        System.out.println(testRat.getClass());
    }*/
}
