package world.ucode;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

class Score extends Pane {
    private final Label lblCurrScore;
    private static Label lblHiScore;
    private double counter;

    public Score() {
        setTranslateX(450);
        setTranslateY(15);

        ImageView imgvHi = new ImageView(new Image(getClass().getResourceAsStream("HI.png")));
        counter = 0;
        lblHiScore = new Label("" + (int) counter);
        lblCurrScore = new Label("" + (int) counter);
        lblHiScore.setStyle(
                "-fx-font-family: Unispace; -fx-font-size: 14;"
                        + "-fx-translate-y: -5; -fx-translate-x: 35;"
                        + "-fx-text-fill: #696969"
        );
        lblCurrScore.setStyle(
                "-fx-font-family: Unispace; -fx-font-size: 14;"
                        + "-fx-translate-y: -5; -fx-translate-x: 100;"
                        + "-fx-text-fill: #323232"
        );

        getChildren().addAll(lblCurrScore, lblHiScore, imgvHi);
    }

    public void update(double i) {
        counter += i;
        lblCurrScore.setText("" + (int) counter);
    }

    public int get() {
        return (int) counter;
    }

    public void reset() {
        if (counter > Integer.parseInt(lblHiScore.getText()))
            lblHiScore.setText("" + (int) counter);
        counter = 0;
    }
}
