import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.Random;



public class Main extends Application {

    private static final int WINDOW_WIDTH = 1920;
    private static final int WINDOW_HEIGHT = 1080;

    private static final int CANVAS_WIDTH = 1280;
    private static final int CANVAS_HEIGHT = 960;

    private static final int TICK_RATE = 5;

    private Random randGen = new Random();
    private Canvas canvas;

    private ArrayList<Smiley> smilies = new ArrayList<>();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = buildGUI();

        // register a tick method to be called periodically.
        // make a new timeline with one keyframe that triggers the tick method every half second.
        Timeline t = new Timeline(new KeyFrame(Duration.millis(TICK_RATE), event -> moveSmiley()));

        // loop the timeline forever
        t.setCycleCount(Animation.INDEFINITE);

        //start a timeline when a button is pressed.
        t.playFromStart();

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pane buildGUI() {
        BorderPane root = new BorderPane();

        VBox sidebar = new VBox();
        root.getChildren().add(sidebar);
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(10,10,10,10));

        Button addRandomCircleButton = new Button("Random Circle");
        addRandomCircleButton.setMaxWidth(Double.MAX_VALUE);
        addRandomCircleButton.setMinWidth(150);
        sidebar.getChildren().add(addRandomCircleButton);
        addRandomCircleButton.setOnAction(e -> addRandomCircle());

        Button addRandomRectangle = new Button("Random Rectangle");
        addRandomRectangle.setMaxWidth(Double.MAX_VALUE);
        addRandomRectangle.setMinWidth(150);
        sidebar.getChildren().add(addRandomRectangle);
        addRandomRectangle.setOnAction(e -> addRandomRectangle());

        Button addSmiley = new Button("add Smileyface");
        addSmiley.setMaxWidth(Double.MAX_VALUE);
        addSmiley.setMinWidth(150);
        sidebar.getChildren().add(addSmiley);
        addSmiley.setOnAction(e -> addSmiley());

        Button moveSmiley = new Button("move Smileyface");
        moveSmiley.setMaxWidth(Double.MAX_VALUE);
        moveSmiley.setMinWidth(150);
        sidebar.getChildren().add(moveSmiley);
        moveSmiley.setOnAction(e -> moveSmiley());

        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);



        return root;
    }

    private void drawAllSmileys() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0,0,CANVAS_WIDTH,CANVAS_HEIGHT);

        for (Smiley smile: smilies) {
            smile.draw(gc);
        }
    }

    private void moveSmiley() {
        for (Smiley smile: smilies) {
            smile.move();
        }
        drawAllSmileys();
    }



    private void addSmiley() {
        int x = randGen.nextInt(CANVAS_WIDTH);
        int y = randGen.nextInt(CANVAS_HEIGHT);
        Smiley s = new Smiley(x, y);
        smilies.add(s);

        drawAllSmileys();
    }


    public static void main (String[] args) {
        launch(args);

    }

    public void addRandomCircle() {
        int x = randGen.nextInt(CANVAS_WIDTH);
        int y = randGen.nextInt(CANVAS_HEIGHT);
        int size = randGen.nextInt(200);


        // get canvas context
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // set pen colour
        gc.setStroke(Color.CORAL);

        // draw circle
        gc.setLineWidth(5);
        gc.setLineDashes(30,15,20,20);
        gc.strokeOval(x, y, size, size);
        gc.fillOval(x, y, size, size);

    }

    public void addRandomRectangle() {
        int x = randGen.nextInt(CANVAS_WIDTH);
        int y = randGen.nextInt(CANVAS_HEIGHT);
        int width = randGen.nextInt(200);
        int height = randGen.nextInt(200);


        // get canvas context
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // set pen colour
        gc.setFill(randomColor());

        // draw Rectangle
        gc.setLineWidth(5);
        gc.fillRect(x, y, width, height);

    }

    private Color randomColor() {
        double r = randGen.nextDouble();
        double g = randGen.nextDouble();
        double b = randGen.nextDouble();
        double a = randGen.nextDouble();
        return new Color(r, g, b, a);
    }

}
