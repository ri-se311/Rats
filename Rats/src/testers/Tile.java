package testers;

import javafx.scene.image.Image;

/**
 * an enumerated type to detail specific constructed instances of a Tile object.
 *
 * @version 1.3
 */
public enum Tile {
    // Specific instantiations for grass, road and tunnel tiles.
    GRASS(new Image("Grass.PNG"), false, false),
    ROAD(new Image("Road.PNG"), true, true),
    TUNNEL(new Image("Tunnel.PNG"), true, false);

    private final Image sprite;
    private final boolean isMovable;
    private final boolean isPlacable;

    /**
     * Sets the feature for the tile.
     *
     * @param isMovable  tile movable or not.
     * @param isPlacable tile placable or not.
     */
    Tile(Image sprite, boolean isMovable, boolean isPlacable) {
        this.sprite = sprite;
        this.isMovable = isMovable;
        this.isPlacable = isPlacable;
    }

    /**
     * Check the tile is moveable or not.
     *
     * @return a boolean for tile is movable or not
     */
    public boolean isMovable() {
        return isMovable;
    }

    /**
     * Check the tile is placable or not.
     *
     * @return a boolean for tile is placable or not
     */
    public boolean isPlacable() {
        return isPlacable;
    }

    /**
     * return the image that represents a tile, its sprite.
     *
     * @return The sprite as an Image object.
     */
    public Image getSprite() {
        return this.sprite;
    }
}