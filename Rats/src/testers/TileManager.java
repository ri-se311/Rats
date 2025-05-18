package testers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class is a manager of tiles, storing all tiles from a level file as a queryable grid.
 *
 * @version 1.5
 */
public class TileManager {
    private final Tile[][] managedTiles;
    private final int width;
    private final int height;

    /**
     * Consrtuctor, assigning the tiles to be managed.
     *
     * @param managedTiles the 2D array of tiles to be managed.
     */
    public TileManager(Tile[][] managedTiles) {
        this.managedTiles = managedTiles;

        this.width = managedTiles.length;
        this.height = managedTiles[0].length;
    }


    /**
     * Get a tile at a specific coordinate
     *
     * @param x x coordinate to query for a tile
     * @param y y coordinate to query for a tile
     * @return the tile at given coordinates or null if out of bounds
     */
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0) {
            return null;
        }

        if (x >= this.width || y >= this.height) {
            return null;
        }

        return this.managedTiles[x][y];
    }

    /**
     * This renders all tiles to a GraphicsContext (typically a canvas.)
     *
     * @param gc The GraphicsContext you are rendering to.
     */
    public void renderAll(GraphicsContext gc) {
        //2 for loops to go through the entire grid.
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                Image tileSprite = this.managedTiles[x][y].getSprite();

                // Calculate the co-ordinates to draw by
                int xOffset = GameBoard.getRenderOffset(x);
                int yOffset = GameBoard.getRenderOffset(y);
                // finally draw the image with the image and altered co-ordinate values.
                gc.drawImage(tileSprite, xOffset, yOffset);
            }
        }
    }
}
