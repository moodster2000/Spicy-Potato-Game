package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class WinScreenController {

    @FXML
    private Button exit;

    @FXML
    void exitProgram(ActionEvent event) throws IOException {
        Main m = new Main();
        System.out.println("print");
        m.changeScene("sample.fxml");
    }

}
