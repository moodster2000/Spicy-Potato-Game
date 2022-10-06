package game;

//import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class EndOfGameController {

    @FXML
    private Button exit;

    @FXML
    void exitProgram(ActionEvent event) throws IOException {
        Main m = new Main();
        System.out.println("print");
        ConfigScreenController.getPV().setNumRooms(0);
        m.changeScene("sample.fxml");
    }

}

