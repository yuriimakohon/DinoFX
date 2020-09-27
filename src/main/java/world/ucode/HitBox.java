package world.ucode;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HitBox extends Pane {
    Rectangle rect;

    public HitBox(double h, double w, Color color) {
        rect = new Rectangle();
        rect.setFill(color);
        rect.setHeight(h);
        rect.setWidth(w);
        getChildren().add(rect);

        Main.rootGamePlay.getChildren().add(this);
    }

    void move(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
    }
}
