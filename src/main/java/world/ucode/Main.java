package world.ucode;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {
    static Stage window;
    static HBox rootGameMenu;
    static Group rootGamePlay;
    static GameMenu GameMenuScene;
    static GamePlay GamePlayScene;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        rootGameMenu = new HBox();
        rootGamePlay = new Group();

        primaryStage.setTitle("DinoFX");
        primaryStage.getIcons().add(new Image(GamePlay.class.getResourceAsStream("images/DinoFX_icon.png")));
        GameMenuScene = new GameMenu(rootGameMenu, 620, 200);
        GamePlayScene = new GamePlay(rootGamePlay, 620, 200);

        primaryStage.setScene(GameMenuScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

