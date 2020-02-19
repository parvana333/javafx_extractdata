package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.List;
public class Controller {
    @FXML
    public Button chooseButton;
    @FXML
    public Button implementButton;
    @FXML
    public TextField textfield;
    @FXML
    private GridPane gridpane;
    @FXML
    private Text errormessage;
    @FXML
    private Text chosenFile;

    public String mainFilePath;
    public File file;

    @FXML
    public void handleChooseButtonClick(ActionEvent event) throws IOException {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File Dialog");
            Stage stage = (Stage) gridpane.getScene().getWindow();
            file = fileChooser.showOpenDialog(stage);
            String filePath2 = file.getParent();
            mainFilePath = filePath2 + "/";
            chosenFile.setText("File is chosen");
        } catch (RuntimeException e) {

        }

    }

    @FXML
    public void handleImplementButtonClick(ActionEvent event) throws IOException {
        // taking text from textfield
        String RNN = textfield.getText();
        //allow processing if user enter everything correctly
        if (mainFilePath != null && RNN.length() >= 12) {
            errormessage.setText(" ");
            //Method class instantiated
            Methods methods = new Methods();
            String transid = methods.findRNN(file, RNN);
            if (transid.isEmpty()) {
                errormessage.setText("There is not id for this RNN");
            } else if (transid.equals("no file")) {
                errormessage.setText("Check your file");
            }
            //getting valid transaction id and allowing to process remainings
            else {
                List<String> id = methods.findId(file, transid);
                String notification = methods.createDoc(id, mainFilePath, file.getName().substring(0, file.getName().length() - 4) + ".docx", RNN);
                if (notification.equals("ok")) {
                    //for second window
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FinalWindow.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Final Window");
                    stage.setScene(new Scene(root1));
                    stage.show();
                } else {
                    errormessage.setText("Please, check your docx file being closed");

                }
            }

        } else if (mainFilePath == null) {
            errormessage.setText("Please,choose proper file");
        } else {
            errormessage.setText("Check your RNN");
        }
    }
    // end of controller
}
