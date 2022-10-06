package game;

//import javafx.beans.property.StringProperty;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;

//import java.util.Random;
//import java.util.Set;

public class Bullet extends SpriteBase {
    //    static String appMain = System.getProperty("user.dir");
    static Image  image =  new Image("file:testProject/src/SrcImage/red.png",
                10, 10, true, true);;
    private static double speed = 3;

    public Bullet(Pane layer, double x, double y, String weapon) {

        super(layer, image, x, y, 0, 0, 0, 0, 1, 1000);
        String color;
        if (weapon.equals("Flame")) {
            color = "red";
        } else if (weapon.equals("Water")) {
            color = "blue";
        } else {
            color = "green";
        }
        this.setImage(new Image("file:testProject/src/SrcImage/" + color + ".png",
                10, 10, true, true));
    }

    public void launchBullet(String direction) {
        if (direction == "left") {
            setDx(-speed);
        } else if (direction == "right") {
            setDx(speed);
        } else if (direction == "back") {
            setDy(speed);
        } else if (direction == "front") {
            setDy(-speed);
        }
    }

    @Override
    public void checkRemovability() {

        if (getY() < 0 || getX() < 0 || getY() > Settings.getSceneHeight()
                || getX() > Settings.getSceneWidth()) {
            setRemovable(true);
        }
    }

}
