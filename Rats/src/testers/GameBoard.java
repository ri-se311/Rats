package testers;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;

/**
 * Gameboard class that generates a game board for the game
 *
 */
public class GameBoard {
    //size of the grid tiles
    private static final int GRID_SIZE_PX = 40;

    /**
     * get the new render offset
     *
     * @param baseCoord base coordinates as input
     * @return returns the basecord * the size of the grid
     */
    public static int getRenderOffset(int baseCoord) {
        return baseCoord * GRID_SIZE_PX;
    }

    private TileManager tileManager;
    private ItemManager itemManager;
    private RatManager ratManager;

    private final Canvas canvas;

    /**
     * make a level based on a textfile
     *
     * @param filePath the path of the saved file
     * @param canvas   canvas input
     */
    public GameBoard(String filePath, Canvas canvas) {
        try {
            File levelFile = new File(filePath);
            TileManager tiles = new TileManager(GridMaker.makeGrid(levelFile));
            tileManager = tiles;
        } catch (Exception e) {
            System.out.println("File not found!");
        }
        itemManager = new ItemManager();
        this.ratManager = new RatManager();
        this.canvas = canvas;
    }

    public void placeItem(AbstractItem item, int x, int y) {
        Position positionForPlacing = new Position(x, y);
        if (this.tileManager.getTile(x, y).isPlacable()) {
            if (this.itemManager.findItemAtPos(x, y) != null) {
                itemManager.removeItem(itemManager.findItemAtPos(x, y));
                itemManager.addNewItem(item);
            }
            itemManager.addNewItem(item);
        }
    }

    /**
     * gets the current tile manager
     *
     * @return returns the current item manager
     */
    public TileManager getTileManager() {
        return this.tileManager;
    }

    /**
     * gets the current item manager
     *
     * @return returns current item manager
     */
    public ItemManager getItemManager() {
        return this.itemManager;
    }

    /**
     * gets a rat manager
     *
     * @return returns current rat manager
     */
    public RatManager getRatManager() {
        return this.ratManager;
    }

    /**
     * makes the gameboard use the tick system
     */
    public void tick() {
        this.itemManager.tick();
        this.ratManager.tick();

        this.render();
    }

    /**
     * renders the graphics of the level
     */
    private void render() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        this.tileManager.renderAll(gc);
        this.ratManager.renderAll(gc);
    }
}
