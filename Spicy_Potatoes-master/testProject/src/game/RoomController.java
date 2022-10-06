package game;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RoomController {

    @FXML
    private Label moneyAmount;

    @FXML
    public void initialize() {
        int money = ConfigScreenController.getPV().getMoney();
//        moneyAmount.setText(money + " " + ConfigScreenController.getPV().getNumRooms());
    }

}

