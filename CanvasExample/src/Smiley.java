import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Smiley {
    private int x;
    private int y;
    private int dx;
    private int dy;

    public Smiley(int x, int y) {
        Random r = new Random();
        this.x = x;
        this.y = y;
        this.dx = r.nextInt(10) - 5;
        this.dy = r.nextInt(10) - 5;

    }

    public void draw (GraphicsContext gc) {
        // set pen colour
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.YELLOW);

        // draw circle
        gc.setLineWidth(2);
        gc.strokeOval(x, y, 100, 100);
        gc.fillOval(x, y, 100, 100);

        drawEye(x + 30, y + 20, gc);
        drawEye(x + 60, y + 20, gc);
        drawMouth(x + 25, y + 50, gc);

    }

    private void drawEye (int x, int y, GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillOval(x, y, 10, 10);

    }

    private void drawMouth (int x, int y, GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillOval(x, y, 50, 20);

    }

    public void move() {
        x = (x + dx) % 500;
        y = (y + dy) % 500;
    }
}
