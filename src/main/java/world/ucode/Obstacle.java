package world.ucode;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Obstacle extends Pane {
    private static final Image imgCactus1 = new Image(Obstacle.class.getResourceAsStream("Cactus_1.png"));
    private static final Image imgCactus2 = new Image(Obstacle.class.getResourceAsStream("Cactus_2.png"));
    private static final Image imgCactus3 = new Image(Obstacle.class.getResourceAsStream("Cactus_3.png"));
    private static final Image imgCactus4 = new Image(Obstacle.class.getResourceAsStream("Cactus_4.png"));
    private static final Image imgCactus5 = new Image(Obstacle.class.getResourceAsStream("Cactus_5.png"));
    private static final Image imgCactus6 = new Image(Obstacle.class.getResourceAsStream("Cactus_6.png"));
    private ImageView imgv;
    HitBox hitBox;

    public Obstacle(int x) {
        int obstacleRand = (int)(Math.random() * 6);

        if (obstacleRand == 0)
            imgv = new ImageView(imgCactus1);
        else if (obstacleRand == 1)
            imgv = new ImageView(imgCactus2);
        else if (obstacleRand == 2)
            imgv = new ImageView(imgCactus3);
        else if (obstacleRand == 3)
            imgv = new ImageView(imgCactus4);
        else if (obstacleRand == 4)
            imgv = new ImageView(imgCactus5);
        else
            imgv = new ImageView(imgCactus6);
        if (obstacleRand < 3)
            setTranslateY(147);
        else
            setTranslateY(137);
        generateObstaclePos(x);

        hitBox = new HitBox(
                imgv.getImage().getHeight() - 3,
                imgv.getImage().getWidth() - 10,
                Color.rgb(255,0,0,0)  // Hit-box Color
        );

        getChildren().addAll(imgv);
        Main.root.getChildren().add(this);
    }

    private void generateObstaclePos(int x) {
        setTranslateX(x + 250 + (int)(Math.random() * 450));
    }

    public void move() {
        setTranslateX(getTranslateX() - Main.speed);
        hitBox.move(getTranslateX(), getTranslateY() + 3);
    }
}
