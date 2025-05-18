package testers;

import javafx.scene.Node;

/**
 * Class for making objects draggable
 *
 */

public class DraggableMaker {

    private double mouseAnchorX;
    private double mouseAnchorY;

    /**
     * makes objects moveable when dragging with mouse button
     *
     * @param node the object to make dragable
     */
    public void makeDraggable(Node node) {

        node.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
        });

        node.setOnMouseDragged(mouseEvent -> {
            node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            node.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
        });
    }
}
