package world.ucode;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Character extends Pane {
    HitBox hitBox;
    Point2D velocity;
    int groundY = 137;
    ImageView imgv;

    public Character() {
        imgv = new ImageView(new Image(getClass().getResourceAsStream("Dino.png")));
        hitBox = new HitBox(37,29, Color.rgb(0, 255, 0, 0));    // Hit-box Color

        imgv.setViewport(new Rectangle2D(0, 0, 44, 47));

        velocity = new Point2D(0, 0);
        setTranslateX(10);
        setTranslateY(groundY);

        getChildren().addAll(imgv);
    }

    void move(double value) {
        boolean moveDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Obstacle o : Main.obstacles) {
                if (o.hitBox.getBoundsInParent().intersects(hitBox.getBoundsInParent()))
                    Main.gameOver = true;
            }
            if (i < Math.abs(value) * 0.5) {
                if (moveDown && getTranslateY() < groundY)
                    setTranslateY(getTranslateY() + 0.1);
                else if (!moveDown)
                    setTranslateY(getTranslateY() - 0.1);
            } else {
                if (moveDown && getTranslateY() < groundY)
                    setTranslateY(getTranslateY() + 1);
                else if (!moveDown)
                    setTranslateY(getTranslateY() - 1);
            }
            changeSprite();
            hitBox.move(getTranslateX() + 5, getTranslateY());
        }
    }

    private int previous = 0;
    private int offset;
    private void changeSprite() {
        if (Main.gameOver) {
            offset = 44 * 3;
        } else if (getTranslateY() < groundY) {
            offset = 44 * 2;
        } else if (Main.score.get() != previous ) {
            if (offset >= 44 * 2)
                offset = 0;
            previous = Main.score.get();
            if (imgv.getViewport().getMinX() == 44)
                offset = 0;
            else
                offset = 44;
        }
        imgv.setViewport(new Rectangle2D(offset, 0, 44, 47));
    }

    void jump() {
        if (getTranslateY() >= groundY)
            velocity = new Point2D(0, -20);
    }
}
