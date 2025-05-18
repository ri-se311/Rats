package testers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that makes the level skeleton from an input file
 *
 */
public class GridMaker {

    public static Tile[][] makeGrid(File levelInfo) throws FileNotFoundException {
        Scanner gridReader = new Scanner(levelInfo);
        String levelSize = gridReader.nextLine();
        int gridX = Integer.parseInt(levelSize.split(",")[0]);
        int gridY = Integer.parseInt(levelSize.split(",")[1]);
        Tile[][] grid = new Tile[gridX][gridY];

        for (int y = 0; y < gridY; y++) {
            char[] tileRow = gridReader.nextLine().toCharArray();

            assert tileRow.length == gridX;

            for (int x = 0; x < gridX; x++) {
                char currentTileChar = tileRow[x];
                Tile readTile = switch (currentTileChar) {
                    case '#' -> Tile.GRASS;
                    case '=' -> Tile.ROAD;
                    case 'v' -> Tile.TUNNEL;
                    default -> throw new IllegalArgumentException(
                            "Invalid character to represent tile: " + currentTileChar);
                };
                grid[x][y] = readTile;
            }
        }
        return grid;
    }
}

