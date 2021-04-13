package fx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class ComboBoxExample extends Application {
    public void start(Stage stage) {
        //Setting the label
        Label label = new Label("Select Desired Course:");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        label.setFont(font);
        //Creating a combo box
        ComboBox<String> combo = new ComboBox<String>();
        //Setting the prompt text of the combo box
        combo.setPromptText("Select Course");
        //Getting the observable list of the combo box
        ObservableList<String> list = combo.getItems();
        //Adding items to the combo box
        list.add("Java");
        list.add("C++");
        list.add("Python");
        list.add("Big Data");
        list.add("Machine Learning");
        HBox hbox = new HBox(15);
        //Setting the space between the nodes of a HBox pane
        hbox.setPadding(new Insets(75, 150, 50, 60));
        hbox.getChildren().addAll(label, combo);
        //Creating a scene object
        Scene scene = new Scene(new Group(hbox), 595, 280, Color.BEIGE);
        stage.setTitle("Combo Box");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}