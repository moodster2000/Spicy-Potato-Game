package game;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Enemy extends SpriteBase {

    public Enemy(Pane layer, Image image, double x, double y, double r, double dx,
                 double dy, double dr, double health, double damage) {

        super(layer, image, x, y, r, dx, dy, dr, health, damage);
    }

    @Override
    public void move() {

        setX(getX() + getDx());
        setY(getY() + getDy());

        if (getY() > Settings.getSceneHeight() || getY() < 0) {
            setDy(-getDy());
        }
    }

    @Override
    public void checkRemovability() {

        if (getHealth() < 0) {
            setRemovable(true);
        }

    }
}
