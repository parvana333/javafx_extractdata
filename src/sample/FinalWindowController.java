package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FinalWindowController {

    @FXML
    private Button button;

    @FXML
    private GridPane gridpane2;

    public void handleOkButtonClick(ActionEvent event) {
        Stage stage = (Stage) gridpane2.getScene().getWindow();
        stage.close();
    }

}