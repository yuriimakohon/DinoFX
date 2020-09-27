package world.ucode;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Background extends Pane {
    private final ImageView land1;
    private final ImageView land2;
    private final ImageView cloud1;
    private final ImageView cloud2;
    private final ImageView cloud3;
    static boolean firstLand = true;

    public Background() {
        Image landSample = new Image(getClass().getResourceAsStream("images/Land.png"));
        Image cloudSample = new Image(getClass().getResourceAsStream("images/Cloud.png"));

        land1 = new ImageView(landSample);
        land2 = new ImageView(landSample);
        land1.setTranslateX(0);
        land1.setTranslateY(170);
        land2.setTranslateY(170);

        cloud1 = new ImageView(cloudSample);
        cloud2 = new ImageView(cloudSample);
        cloud3 = new ImageView(cloudSample);
        generateCloudPos(cloud1, 200);
        generateCloudPos(cloud2, (int)cloud1.getTranslateX());
        generateCloudPos(cloud3, (int)cloud2.getTranslateX());

        getChildren().addAll(land1, land2, cloud1, cloud2, cloud3);
    }

    private void generateCloudPos(ImageView cloud, int x) {
        cloud.setTranslateX(x + 200 + (int)(Math.random() * 200));
        cloud.setTranslateY(20 + (int)(Math.random() * 60));
    }

    void move() {
        moveLand();
        moveCloud();
    }

    private void moveCloud() {
        if (cloud1.getTranslateX() < -46)
            generateCloudPos(cloud1, (int)cloud3.getTranslateX());
        if (cloud2.getTranslateX() < -46)
            generateCloudPos(cloud2, (int)cloud1.getTranslateX());
        if (cloud3.getTranslateX() < -46)
            generateCloudPos(cloud3, (int)cloud2.getTranslateX());
        cloud1.setTranslateX(cloud1.getTranslateX() - 2);
        cloud2.setTranslateX(cloud2.getTranslateX() - 2);
        cloud3.setTranslateX(cloud3.getTranslateX() - 2);
    }

    private void moveLand() {
        if (firstLand && land1.getTranslateX() < 0) {
            land2.setTranslateX(1200 + land1.getTranslateX());
            firstLand = false;
        } else if (!firstLand && land2.getTranslateX() < 0) {
            land1.setTranslateX(1200 + land2.getTranslateX());
            firstLand = true;
        }
        land1.setTranslateX(land1.getTranslateX() - GamePlay.speed);
        land2.setTranslateX(land2.getTranslateX() - GamePlay.speed);
    }
}
