package game;

//import javafx.beans.property.StringProperty;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;

//import java.util.Random;
//import java.util.Set;

public class Item extends SpriteBase {
    //    static String appMain = System.getProperty("user.dir");
    static Image  image =  new Image("file:testProject/src/SrcImage/attack.png",
                30, 30, true, true);
    private static double speed = 3;
    private String type;

    public Item(Pane layer, double x, double y, String type) {

        super(layer, image, x, y, 0, 0, 0, 0, 1, 1);
        String color;
        this.type = type;
        this.setImage(new Image("file:testProject/src/SrcImage/" + type + ".png",
                30, 30, true, true));
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

    public String getType() {
        return type;
    }

    @Override
    public void checkRemovability() {

        if (getY() < 0 || getX() < 0 || getY() > Settings.getSceneHeight()
                || getX() > Settings.getSceneWidth()) {
            setRemovable(true);
        }
    }

}
