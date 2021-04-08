package fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label label;

    public void initialize() {
        System.out.println("FX Controller initialized");
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        label.setText("Hello JavaFX World!");
        System.out.println("You clicked me!");
    }
}
