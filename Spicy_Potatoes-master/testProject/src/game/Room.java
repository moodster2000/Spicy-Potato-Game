package game;

//import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private Pane pane;
    // clockwise starting with bottom door
    private Room[] rooms = new Room[2];
    //private String[] randomizedRooms = ConfigScreenController.getRoom();
    private int monstersSpawned = 0;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    public Room(Room room, int doornum) throws IOException {
        ConfigScreenController.getPV().incrementRoomNum();
        if (ConfigScreenController.getPV().getNumRooms() > 6) {
            Main m = new Main();
            m.changeScene("WinScreen.fxml");
            ConfigScreenController.getPV().setNumRooms(7);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/WinScreenController"));
            pane = loader.load();
        } else {
            Main m = new Main();
            String fxmlNextRoom =
                    ConfigScreenController.getRoom()[ConfigScreenController.getPV().getNumRooms()];
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlNextRoom));
            this.pane = loader.load();
            this.rooms[doornum] = room;
        }
    }

    public Room() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Room.fxml"));
        pane = loader.load();
    }

    public void incrementMonster() {
        monstersSpawned++;
    }

    public int getMonstersSpawned() {
        return monstersSpawned;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Pane getPane() {
        return pane;
    }

    public Room getRoom(int num) {
        if (rooms[num] == null) {
            try {
                if (num == 1) {
                    rooms[num] = new Room(this, 0);
                } else if (num == 0) {
                    rooms[num] = new Room(this, 1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rooms[num];
    }
}
