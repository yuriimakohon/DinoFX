package world.ucode;

import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class GamePlay extends Scene {
    static Score score;
    private static ImageView imgGameOver;
    private static ImageView imgRetry;
    static double speed;
    static ArrayList<Obstacle> obstacles = new ArrayList<>(4);
    Background background;
    Character dino;

    static boolean gameOver = false;
    AnimationTimer timer;

    public GamePlay(Parent root, double width, double height) {
        super(root, width, height);

        setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W, SPACE, UP -> dino.jump();
                case S, CONTROL, DOWN -> dino.down();
                case ENTER, R -> { if (gameOver) startGame(); }
            }
        });
        setOnMouseClicked(e -> { if (gameOver) startGame(); });

        initGame();
    }

    private void initGame() {
        imgGameOver = new ImageView(new Image(GamePlay.class.getResourceAsStream("images/GameOver.png")));
        imgRetry = new ImageView(new Image(GamePlay.class.getResourceAsStream("images/Retry.png")));
        background = new Background();
        dino = new Character();
        score = new Score();

        imgGameOver.setTranslateX(620 / 2 - imgGameOver.getImage().getWidth() / 2);
        imgRetry.setTranslateX(620 / 2 - imgRetry.getImage().getWidth() / 2);
        imgGameOver.setTranslateY(50);
        imgRetry.setTranslateY(100);

        Main.rootGamePlay.getChildren().addAll(background, dino, score, imgGameOver, imgRetry);

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
    }

    void startGame() {
        if (!obstacles.isEmpty()) {
            for (Obstacle o : obstacles) {
                o.setTranslateX(-75);
            }
            obstacles.clear();
        }

        obstacles.add(new Obstacle(500));
        for (int i = 0; i < 3; i++)
            obstacles.add(new Obstacle((int)obstacles.get(obstacles.size() - 1).getTranslateX()));

        speed = 4.5;
        score.reset();

        imgGameOver.setVisible(false);
        imgRetry.setVisible(false);
        gameOver = false;
        timer.start();
    }

    private void update() {
        if (gameOver) {
            gameOverScreen();
        } else {
            if (dino.velocity.getY() < 15) {
                dino.velocity = dino.velocity.add(0, 1);
            }
            obstaclesMove(obstacles);
            dino.move((int)dino.velocity.getY());
            background.move();
            score.update(.12);

            speed += 0.001;
        }
    }

    private void obstaclesMove(ArrayList<Obstacle> obstacles) {
        if (obstacles.get(0).getTranslateX() < -100) {
            obstacles.remove(0);
            obstacles.add(new Obstacle((int)obstacles.get(obstacles.size() - 1).getTranslateX()));
        }
        for (Obstacle o : obstacles) {
            o.move();
        }
    }

    private void gameOverScreen() {
        timer.stop();
        imgGameOver.setVisible(true);
        imgRetry.setVisible(true);
    }
}
