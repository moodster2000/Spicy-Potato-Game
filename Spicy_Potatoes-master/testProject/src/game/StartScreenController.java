package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class StartScreenController {

    @FXML
    private Button start;

    @FXML
    void goToNextScene(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("ConfigScreen.fxml");
    }
}