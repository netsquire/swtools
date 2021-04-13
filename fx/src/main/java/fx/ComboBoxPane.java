package fx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class ComboBoxPane extends Application {

    enum Sources {
        FILE("FILE"),
        STORED("STORED"),
        EXTERNAL_URL("EXTERNAL_URL");

        String value;

        Sources(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    final Button button = new Button("Send");
    final Label notification = new Label();
    final TextField subject = new TextField("");
    final TextArea text = new TextArea("");

    String address = " ";

    String sourceType = "";

    @Override
    public void start(Stage stage) {
        stage.setTitle("ComboBoxSample");
        Scene scene = new Scene(new Group(), 750, 450);

        ComboBox<Sources> sourceType = new ComboBox<>();
        sourceType.getItems().addAll(Sources.FILE, Sources.STORED, Sources.EXTERNAL_URL);
        sourceType.setPromptText("Select input type: ");

        final ComboBox<String> emailComboBox = new ComboBox<>();
        emailComboBox.getItems().addAll(
                "jacob.smith@example.com",
                "isabella.johnson@example.com",
                "ethan.williams@example.com",
                "emma.jones@example.com",
                "michael.brown@example.com"
        );
        emailComboBox.setPromptText("Email address");
        emailComboBox.setEditable(true);
        emailComboBox.valueProperty().addListener((observable, oldValue, newValue) -> address = newValue);

        sourceType.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    this.sourceType = newValue.getValue();
                    emailComboBox.getItems().setAll(
                            switch (newValue) {
                                case FILE -> getFileSources();
                                case STORED -> getStoredSources();
                                case EXTERNAL_URL -> getExternalUrlSources();
                            });
                }
        );

        final ComboBox<String> priorityComboBox = new ComboBox<>();
        priorityComboBox.getItems().addAll(
                "Highest",
                "High",
                "Normal",
                "Low",
                "Lowest"
        );

        priorityComboBox.setValue("Normal");

        button.setOnAction(e -> {
            System.out.println("Selected source: " + this.sourceType);

            if (emailComboBox.getValue() != null
                    && !emailComboBox.getValue().isEmpty()) {
                notification.setText("Your message was successfully sent" + " to " + address);
                emailComboBox.setValue(null);
                if (priorityComboBox.getValue() != null
                        && !priorityComboBox.getValue().isEmpty()) { priorityComboBox.setValue(null); }
                subject.clear();
                text.clear();
            } else {
                notification.setText("You have not selected a recipient!");
            }
        });

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));

        //        grid.add(new Label("To: "), 0, 0);
        grid.add(sourceType, 0, 0);

        grid.add(emailComboBox, 1, 0);

        grid.add(new Label("Priority: "), 2, 0);
        grid.add(priorityComboBox, 3, 0);
        grid.add(new Label("Subject: "), 0, 1);
        grid.add(subject, 1, 1, 3, 1);
        grid.add(text, 0, 2, 4, 1);
        grid.add(button, 0, 3);
        grid.add(notification, 1, 3, 3, 1);

        Group root = (Group) scene.getRoot();
        root.getChildren().add(grid);
        stage.setScene(scene);
        stage.show();
    }

    private List<String> getFileSources() {
        return List.of("File_1.rdf", "File_2.rdf", "File_3.rdf", "File_4.rdf");
    }

    private List<String> getStoredSources() {
        return List.of("Stored_1.rdf", "Stored_2.rdf", "Stored_3.rdf", "Stored_4.rdf");
    }

    private List<String> getExternalUrlSources() {
        return List.of("ExternalUrl_1.rdf", "ExternalUrl_2.rdf", "ExternalUrl_3.rdf", "ExternalUrl_4.rdf");
    }

}
