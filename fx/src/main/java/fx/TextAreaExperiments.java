package fx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TextAreaExperiments extends Application  {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Select working stream");

        TextArea textArea = new TextArea();

        Button button = new Button("Click to get text");
        button.setMinWidth(50);
        button.setOnAction(action -> {
            System.out.println(textArea.getText());
            textArea.setText("Clicked!");
        });

        ChoiceBox<String> cb = new ChoiceBox<>(FXCollections.observableArrayList("File", "Stored", "URL"));
        cb.setValue("Source");
        VBox vbox = new VBox(textArea, cb, button);

        Scene scene = new Scene(vbox, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}