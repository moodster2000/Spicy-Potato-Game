package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Random;

public class ConfigScreenController {

    //instance in Controller
    private static PlayerValues pV = new PlayerValues();
    private static String[] roomArr = new String[]{"RandomRoom#1.fxml", "RandomRoom#2.fxml",
        "RandomRoom#3.fxml", "RandomRoom#4.fxml", "RandomRoom#5.fxml", "RandomRoom#6.fxml",
        "RandomRoom#7.fxml"};
    private Main m = new Main();
    @FXML
    private Button continues;
    @FXML
    private Button laser;
    @FXML
    private Button flameThrower;
    @FXML
    private TextField username;
    @FXML
    private Button easy;
    @FXML
    private Button medium;
    @FXML
    private Button hard;
    @FXML
    private Button waterGun;

    public static PlayerValues getPV() {
        return pV;
    }

    public void setPV(PlayerValues pV) {
        this.pV = pV;
    }

    public static String[] getRoom() {
        return roomArr;
    }

    static void shuffleArray(String[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    @FXML
    void goToNextScene(ActionEvent event) throws IOException {
        shuffleArray(roomArr);
        if (pV.isConfigured()) {
            m.loadGame();
        }
    }

    @FXML
    void setDiffEasy(ActionEvent event) {
        pV.setDifficulty(1);
        pV.setMoney(300);
    }

    @FXML
    void setDiffHard(ActionEvent event) {
        pV.setDifficulty(3);
        pV.setMoney(100);
    }

    @FXML
    void setDiffMedium(ActionEvent event) {
        pV.setDifficulty(2);
        pV.setMoney(200);
    }

    @FXML
    void setWeaponFlame(ActionEvent event) {
        pV.setWeapon("Flame");
    }

    @FXML
    void setWeaponLaser(ActionEvent event) {
        pV.setWeapon("Laser");
    }

    @FXML
    void setWeaponWater(ActionEvent event) {
        pV.setWeapon("Water");
    }

    @FXML
    void storeUser(ActionEvent event) {
        pV.setName(username.getText().toString());
    }

}

