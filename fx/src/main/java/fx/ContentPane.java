package fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class ContentPane extends Application {

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

    final Button button = new Button("View content");
    final Label notification = new Label();
    final TextArea text = new TextArea("");

    String content = "ZERO";
    String sourceType = "NOT DEFINED";

    @Override
    public void start(Stage stage) {
        stage.setScene(getScene());
        stage.setTitle("FileContentPane");
        stage.show();
    }

    public Scene getScene() {
        Scene scene = new Scene(new Group(), 750, 450);
        Group root = (Group) scene.getRoot();
        root.getChildren().add(getPane());
        return scene;
    }

    public Pane getPane() {
        ComboBox<Sources> sourceBox = new ComboBox<>();
        sourceBox.getItems().addAll(Sources.FILE, Sources.STORED, Sources.EXTERNAL_URL);
        sourceBox.setPromptText("Select input type: ");

        final ComboBox<String> contentBox = new ComboBox<>();
        // todo: restore from previous usages
//        contentBox.getItems().addAll(
//                "jacob.smith@example.com",
//                "isabella.johnson@example.com",
//                "ethan.williams@example.com",
//                "emma.jones@example.com",
//                "michael.brown@example.com"
//        );
        contentBox.setPromptText("Actual content");
        contentBox.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    content = newValue;
                    text.setText("Pointed: " + content);
                });

        sourceBox.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    this.sourceType = newValue.getValue();
                    contentBox.getItems().setAll(
                            switch (newValue) {
                                case FILE -> getFileSources();
                                case STORED -> getStoredSources();
                                case EXTERNAL_URL -> getExternalUrlSources();
                            });
                }
        );

        button.setOnAction(e -> {
            System.out.println("Selected source: " + this.sourceType);
            if (contentBox.getValue() != null && !contentBox.getValue().isEmpty()) {
                notification.setText("Your choice is displayed as: " + content);
                text.setText("Displayed: " + content);
            } else {
                notification.setText("You have not selected a source!");
            }
        });

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        hBox.getChildren().addAll(sourceBox, contentBox);

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(button, notification);

        BorderPane pane = new BorderPane();
        pane.setTop(hBox);
        pane.setCenter(text);
        pane.setBottom(vBox);

        return pane;
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
