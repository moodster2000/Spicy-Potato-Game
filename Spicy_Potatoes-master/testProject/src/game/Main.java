package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Main extends Application {
    private static Stage stg;
    private Random rnd = new Random();
    private Image playerImage;
    private Image[] enemyArray = new Image[3];
    private Image enemyImage;
    private Pane roomLayer;
    private Pane playfieldLayer;
    private Pane scoreLayer;
    private List<Player> players = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private boolean enemiesCleared = false;
    private Text healthText = new Text();
    private Text bossHealth = new Text();
    private Text inventory = new Text();
    private Text challenge = new Text();
    private Text money = new Text();
    private boolean collision = false;
    private boolean challengeAccepted = false;
    private Scene scene;
    private Room currentRoom;
    private Input input;
    private boolean cheese = false;

    private AnimationTimer gameLoop = new AnimationTimer() {

        @Override
        public void handle(long now) {
            if (!players.get(0).isRemovable()) {
                // player input
                players.forEach(sprite -> sprite.processInput());

                enemiesCleared = true;
                for (SpriteBase enemy : enemies) {
                    if (!enemy.isRemovable()) {
                        enemiesCleared = false;
                    }
                }

                // add random enemies
                if (ConfigScreenController.getPV().getNumRooms() == 6) {
                    spawnBoss(currentRoom.getMonstersSpawned() < 1);
                    if (!enemies.isEmpty()) {
                            bossHealth.setText(enemies.get(0).getHealth() + "");
                    } else {
                        bossHealth.setText("0");
                    }
                }
                else if (ConfigScreenController.getPV().getNumRooms() == 1 || ConfigScreenController.getPV().getNumRooms() == 4) {
                    if (!challengeAccepted) {
                        challenge.setText("ENTER to accept challenge");
                    }
                    if (input.isChallenge() && !challengeAccepted) {
                        challenge.setText("");
                        for (int i = 0; i < 7; i++) {
                            spawnEnemies(true);
                        }
                        challengeAccepted = true;
                    }

                    if (challengeAccepted && enemies.isEmpty()) {
                        if(!cheese) {
                            ConfigScreenController.getPV().setMoney(ConfigScreenController.getPV().getMoney() + 300);
                            cheese = true;
                        }
                    }
                }
                else if (ConfigScreenController.getPV().getNumRooms() < 6) {
                    spawnEnemies(currentRoom.getMonstersSpawned() < 3);
                    spawnItems(currentRoom.getMonstersSpawned() < 1);
                }
                if (ConfigScreenController.getPV().getNumRooms() > 6) {
                    playfieldLayer = new Pane();
                }

                // movement
                players.forEach(sprite -> sprite.move());
                enemies.forEach(sprite -> sprite.move());
                players.get(0).firedBullets.forEach(sprite -> sprite.move());

                // check collisions
                checkCollisions();

                // update sprites in scene
                players.forEach(sprite -> sprite.updateUI());
                enemies.forEach(sprite -> sprite.updateUI());
                items.forEach(sprite -> sprite.updateUI());
                players.get(0).firedBullets.forEach(sprite -> sprite.updateUI());

                players.forEach(sprite -> sprite.checkRemovability());
                enemies.forEach(sprite -> sprite.checkRemovability());
                for (SpriteBase player : players) {
                    if (player.isRemovable()) {
                        Main m = new Main();
                        try {
                            m.changeScene("GameOver.fxml");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                int door;
                if (enemiesCleared) {
                    door = players.get(0).handleDoor();
                } else {
                    door = players.get(0).handleDoorOnlyBack();
                }
                if (door > -1 && ConfigScreenController.getPV().getNumRooms() < 7) {
                    cheese = false;
                    currentRoom = currentRoom.getRoom(door);
                    enemies = currentRoom.getEnemies();
                    items = currentRoom.getItems();
                    roomLayer = currentRoom.getPane();
                    challenge.setText("");
                    bossHealth.setText("");

                    Group root = new Group();
                    root.getChildren().add(roomLayer);
                    root.getChildren().add(playfieldLayer);
                    root.getChildren().add(scoreLayer);
                    stg.getScene().setRoot(root);
                }
                // remove removables from list, layer, etc
                removeSprites(enemies);
                removeSprites(items);
                removeSprites(players.get(0).firedBullets);

                // update score, health, etc
                updateScore();
            }
        }
    };

    public static void main(String[] args) {
        launch(args);
    }

    public Text getHealthText() {
        return healthText;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("Dungeon Crawler");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/fxml/" + fxml));
        Scene s = new Scene(pane);
        stg.setScene(s);
    }

    public void loadGame() throws IOException {
        String appMain = System.getProperty("user.dir");
        playerImage = new Image("file:testProject/src/SrcImage/piratespirit.png",
                50, 50, true, true);

        enemyArray[0] = new Image("file:testProject/src/SrcImage/pirat3.png",
                50, 50, true, true);
        enemyArray[1] = new Image("file:testProject/src/SrcImage/enemy2.jpeg",
                50, 50, true, true);
        enemyArray[2] = new Image("file:testProject/src/SrcImage/enemy3.jpeg",
                50, 50, true, true);
        Group root = new Group();

        // create layers
        playfieldLayer = new Pane();
        scoreLayer = new Pane();

        currentRoom = new Room();
        roomLayer = currentRoom.getPane();

        root.getChildren().add(roomLayer);
        root.getChildren().add(playfieldLayer);
        root.getChildren().add(scoreLayer);

        scene = new Scene(root, Settings.getSceneWidth(), Settings.getSceneHeight());
        createScoreLayer();
        createPlayers();

        gameLoop.start();

        stg.setScene(scene);
        stg.show();

        players.get(0).getItems().add(ConfigScreenController.getPV().getWeapon());
        players.get(0).setWeapon(ConfigScreenController.getPV().getWeapon());
    }

    private void createScoreLayer() {

        healthText.setFont(Font.font(null, FontWeight.NORMAL, 25));
        healthText.setFill(Color.WHITE);

        scoreLayer.getChildren().add(healthText);
        /*TODO quick-hack to ensure the text is centered; usually you don't have that;
         TODO instead have a health bar on top
         */
        double x = 39;
        double y = 55;
        healthText.relocate(x, y);
        healthText.setText("");

        healthText.setBoundsType(TextBoundsType.VISUAL);

        inventory.setFont(Font.font(null, FontWeight.BOLD, 24));
        inventory.setFill(Color.WHITE);
        scoreLayer.getChildren().add(inventory);
        inventory.relocate(500, 5);
        inventory.setText("");

        scoreLayer.getChildren().add(bossHealth);
        bossHealth.setFont(Font.font(null, FontWeight.BOLD, 32));
        bossHealth.setStroke(Color.RED);
        bossHealth.relocate(250, 20);
        bossHealth.setText("");

        scoreLayer.getChildren().add(challenge);
        challenge.setFont(Font.font(null, FontWeight.BOLD, 24));
        challenge.setFill(Color.WHITE);
        challenge.relocate(175, 20);
        challenge.setText("");

        scoreLayer.getChildren().add(money);
        money.setFont(Font.font(null, FontWeight.NORMAL, 25));
        money.setFill(Color.WHITE);
        money.relocate(125, 28);
        money.setText(ConfigScreenController.getPV().getMoney() + "");

    }

    private void createPlayers() {

        // player input
        input = new Input(scene);

        // register input listeners
        input.addListeners(); // TODO remove listeners on game over

        // center horizontally, position at 70% vertically
        double x = (Settings.getSceneWidth() - playerImage.getWidth()) / 2.0;
        double y = Settings.getSceneHeight() * 0.7;

        // create player
        Player player = new Player(playfieldLayer, playerImage, x, y, 0, 0, 0, 0,
                Settings.getPlayerShipHealth(), 0, Settings.getPlayerShipSpeed(), input, null);

        // register player
        players.add(player);

    }

    private void spawnEnemies(boolean random) {

        if (!random || rnd.nextInt(Settings.getEnemySpawnRandomness()) == 0) {
            return;
        }

        int ranIndex = (int) (Math.random() * 3);
        // image
        Image image = enemyArray[ranIndex];

        // random speed
        double speed = rnd.nextDouble() * 1.0 + 2.0;

        double x = rnd.nextDouble() * (Settings.getSceneWidth() - image.getWidth());
        double y = -image.getHeight() + 100;

        // create a sprite
        Enemy enemy = new Enemy(playfieldLayer, image, x, y, 0, 0, speed, 0, 10, 1);

        // manage sprite
        enemies.add(enemy);
        currentRoom.incrementMonster();

    }

    private void spawnBoss(boolean random) {

        if (!random) {
            return;
        }

        // image
        Image image = new Image("file:testProject/src/SrcImage/boss.png",
                150, 150, true, true);

        // random speed
        double speed = rnd.nextDouble() * 1.0 + 2.0;

        double x = rnd.nextDouble() * (Settings.getSceneWidth() - image.getWidth());
        double y = 50;

        // create a sprite
        Enemy enemy = new Enemy(playfieldLayer, image, x, y, 0, 0, speed, 0, 1000, 10);

        // manage sprite
        enemies.add(enemy);
        currentRoom.incrementMonster();

    }

    private void spawnItems(boolean random) {
        if (!random) {
            return;
        }
        String[] types = {"attack", "health", "Water", "Flame", "Laser", "speed"};
        String item = types[rnd.nextInt(types.length)];
        double x = rnd.nextDouble() * (Settings.getSceneWidth() - 10);
        double y = rnd.nextDouble() * (Settings.getSceneHeight() - 10);
        items.add(new Item(playfieldLayer, x, y, item));
        System.out.println(item);


    }

    private void removeSprites(List<? extends SpriteBase> spriteList) {
        Iterator<? extends SpriteBase> iter = spriteList.iterator();
        while (iter.hasNext()) {
            SpriteBase sprite = iter.next();

            if (sprite.isRemovable()) {

                // remove from layer
                sprite.removeFromLayer();

                // remove from list
                iter.remove();
            }
        }
    }

    private void checkCollisions() {

        collision = false;

        for (Bullet bullet : players.get(0).firedBullets) {
            for (Enemy enemy : enemies) {
                if (bullet.collidesWith(enemy)) {
                    enemy.getDamagedBy(bullet);
                    bullet.setRemovable(true);
                }
            }
        }
        for (Player player : players) {
            for (Enemy enemy : enemies) {
                if (player.collidesWith(enemy)) {
                    player.getDamagedBy(enemy);
                }
            }
        }
        for (Player player : players) {
            for (Item item : items) {
                if (player.collidesWith(item)) {
                    if (item.getType() == "health" || item.getType() == "attack"
                            || !player.getItems().contains(item.getType())) {
                        player.getItems().add(item.getType());
                    }
                    item.setRemovable(true);
                }
            }
        }
    }

    private void updateScore() {

        money.setText("" + ConfigScreenController.getPV().getMoney());
        healthText.setText("Health: " + players.get(0).getHealth());
        String invText = "";
        for (String str : players.get(0).getItems()) {
            invText += (str + "\n");
        }
        inventory.setText(invText);

    }
}