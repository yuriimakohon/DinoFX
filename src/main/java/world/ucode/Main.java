package world.ucode;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    static Score score;
    private static ImageView imgGameOver;
    private static ImageView imgRetry;
    static double speed;
    static ArrayList<Obstacle> obstacles = new ArrayList<>(4);
    Background background;
    Character dino;

    static boolean gameOver = false;
    AnimationTimer timer;
    static Group root;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root = new Group();
        primaryStage.setTitle("DinoFX");
        Scene scene = new Scene(root, 620, 200);
        scene.setOnMouseClicked(e -> {
            if (gameOver)
                startGame();
            else
                dino.jump();
        });
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        initGame();
        startGame();
    }

    private void initGame() {
        imgGameOver = new ImageView(new Image(Main.class.getResourceAsStream("GameOver.png")));
        imgRetry = new ImageView(new Image(Main.class.getResourceAsStream("Retry.png")));
        background = new Background();
        dino = new Character();
        score = new Score();

        imgGameOver.setTranslateX(620 / 2 - imgGameOver.getImage().getWidth() / 2);
        imgRetry.setTranslateX(620 / 2 - imgRetry.getImage().getWidth() / 2);
        imgGameOver.setTranslateY(50);
        imgRetry.setTranslateY(100);

        root.getChildren().addAll(background, dino, score, imgGameOver, imgRetry);

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
    }

    private void startGame() {
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

