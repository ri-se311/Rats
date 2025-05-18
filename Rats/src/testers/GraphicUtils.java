package testers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;

/**
 * Utility class to handle graphics of the game
 *
 */

public class GraphicUtils {
    private GraphicUtils() {
        throw new UnsupportedOperationException("GraphicUtils has a private constructor and should not be instantiated");
    }

    /**
     * @param gc       graphics context that the rotated image should be drawn to
     * @param img      image to rotate and draw at provided coords
     * @param rotation angle in degrees
     * @param x        coord to draw the image
     * @param y        coord to draw the image
     */
    public static void drawRotatedImage(GraphicsContext gc, Image img, int rotation, int x, int y) {
        gc.save();

        int imgMidX = (int) (img.getWidth() / 2);
        int imgMidY = (int) (img.getHeight() / 2);

        int pivotX = imgMidX + x;
        int pivotY = imgMidY + y;

        Affine rotAffine = new Affine();
        rotAffine.appendRotation(rotation, pivotX, pivotY);
        gc.setTransform(rotAffine);
        gc.drawImage(img, x, y);

        gc.restore();
    }
}
