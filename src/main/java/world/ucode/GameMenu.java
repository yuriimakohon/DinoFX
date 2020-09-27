package world.ucode;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


class GameMenu extends Scene {
    public GameMenu(HBox root, double width, double height) {
        super(root, width, height);

        Button btnStart = new Button("Start");
        Button btnExit = new Button("Exit");
        btnStart.setOnAction(e -> {Main.window.setScene(Main.GamePlayScene); Main.GamePlayScene.startGame();});
        btnExit.setOnAction(e -> Platform.exit());

        btnStart.setStyle(
                "-fx-text-fill: rgba(80,80,80,1); -fx-font-size: 32;" +
                        "-fx-font-family: Consolas; -fx-font-weight: Bold;" +
                        "-fx-cursor: hand; -fx-min-width: 150"
        );
        btnExit.setStyle(
                "-fx-text-fill: rgba(80,80,80,1); -fx-font-size: 32;" +
                "-fx-font-family: Consolas; -fx-font-weight: Bold;" +
                "-fx-cursor: hand; -fx-min-width: 150"
        );

        Main.rootGameMenu.setAlignment(Pos.CENTER);
        Main.rootGameMenu.setSpacing(50);

        Main.rootGameMenu.getChildren().addAll(btnExit, btnStart);
    }
}
