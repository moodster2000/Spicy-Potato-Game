package game;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

//import java.util.Set;

public class Player extends SpriteBase {

    List<Bullet> firedBullets;
    String currentFacing = "front";
    private double playerShipMinX;
    private double playerShipMaxX;
    private double playerShipMinY;
    private double playerShipMaxY;
    private Input input;
    private ArrayList<String> items = new ArrayList<String>();

    private String weapon;

    private double speed;

    private Pane layer;

    public Player(Pane layer, Image image, double x, double y, double r, double dx,
                  double dy, double dr, double health, double damage, double speed,
                  Input input, List<Bullet> firedBullets) {

        super(layer, image, x, y, r, dx, dy, dr, health, damage);

        this.speed = speed;
        this.input = input;
        this.layer = layer;

        init();
    }


    private void init() {

        // calculate movement bounds of the player ship
        // allow half of the ship to be outside of the screen
        playerShipMinX = 0 - getImage().getWidth() / 2.0;
        playerShipMaxX = Settings.getSceneWidth() - getImage().getWidth() / 2.0;
        playerShipMinY = 0 - getImage().getHeight() / 2.0;
        playerShipMaxY = Settings.getSceneHeight() - getImage().getHeight() / 2.0;
        firedBullets = new ArrayList<>();

    }

    public void processInput() {

        // ------------------------------------
        // movement
        // ------------------------------------

        // vertical direction
        if (input.isMoveUp()) {
            setDy(-speed);
            currentFacing = "front";
        } else if (input.isMoveDown()) {
            setDy(speed);
            currentFacing = "back";
        } else {
            setDy(0d);
        }

        // horizontal direction
        if (input.isMoveLeft()) {
            setDx(-speed);
            currentFacing = "left";
        } else if (input.isMoveRight()) {
            setDx(speed);
            currentFacing = "right";
        } else {
            setDx(0d);
        }
        //weapon
        if (input.isFirePrimaryWeapon()) {
            fireWeapon();
        }

        if (input.isInventory()) {
            if (getItems().size() != 0) {
                String usedItem = this.getItems().remove(getItems().size() - 1);
                if (usedItem.equals("health")) {
                    setHealth(getHealth() + 10);
                } else if (usedItem.equals("attack")) {
                    setDamage(getDamage() + 10);
                } else if (usedItem.equals("Water")) {
                    setWeapon("Water");
                } else if (usedItem.equals("Flame")) {
                    setWeapon("Flame");
                } else if (usedItem.equals("Laser")) {
                    setWeapon("Laser");
                } else if (usedItem.equals("speed")) {
                    incrementSpeed(10);
                }
            }
        }
    }

    void fireWeapon() {
        Bullet bullet = new Bullet(layer, getX(), getY(), weapon);
        this.firedBullets.add(bullet);
        bullet.launchBullet(currentFacing);
    }

    @Override
    public void move() {

        super.move();

        // ensure the ship can't move outside of the screen
        checkBounds();


    }

    private void checkBounds() {

        // vertical
        if (Double.compare(getY(), playerShipMinY) < 0) {
            setY(playerShipMinY);
        } else if (Double.compare(getY(), playerShipMaxY) > 0) {
            setY(playerShipMaxY);
        }

        // horizontal
        if (Double.compare(getX(), playerShipMinX) < 0) {
            setX(playerShipMinX);
        } else if (Double.compare(getX(), playerShipMaxX) > 0) {
            setX(playerShipMaxX);
        }

    }

    public int handleDoor() {
        Double xMid = (playerShipMaxX + playerShipMinX) / 2;
        // Double yMid = (playerShipMaxY + playerShipMinY) / 2;
        // Double xMax = Settings.getSceneWidth();
        Double yMax = Settings.getSceneHeight();
        if (getY() < 0 && getX() > xMid - 75 && getX() < xMid + 75) {
            setY(playerShipMaxY - 50);
            return 1;
        } else if (getY() > yMax - 30 && getX() > xMid - 75 && getX() < xMid + 75) {
            setY(playerShipMinY + 50);
            return 0;
            /*
//        } else if (getX() < 0 && getY() > yMid - 75 && getY() < yMid + 75) {
//            setX(playerShipMinX - 50);
//            return 1;
//        } else if (getX() > xMax - 30 && getY() > yMid - 75 && getY() < yMid + 75) {
//            setX(playerShipMinX + 50);
//            return 3;

             */
        } else {
            return -1;
        }

    }

    public int handleDoorOnlyBack() {
        Double xMid = (playerShipMaxX + playerShipMinX) / 2;
        // Double yMid = (playerShipMaxY + playerShipMinY) / 2;
        // Double xMax = Settings.getSceneWidth();
        Double yMax = Settings.getSceneHeight();
        if (getY() > yMax - 30 && getX() > xMid - 75 && getX() < xMid + 75) {
            setY(playerShipMinY + 50);
            return 0;
            /*
//        } else if (getX() < 0 && getY() > yMid - 75 && getY() < yMid + 75) {
//            setX(playerShipMinX - 50);
//            return 1;
//        } else if (getX() > xMax - 30 && getY() > yMid - 75 && getY() < yMid + 75) {
//            setX(playerShipMinX + 50);
//            return 3;

             */
        } else {
            return -1;
        }
    }


    @Override
    public void checkRemovability() {
        if (getHealth() < 0) {
            setRemovable(true);
        }
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public void incrementSpeed(int x) {
        this.speed += x;
    }
}