package fx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.QuadCurve;

public class DrawingQuadCurve extends Application {
    @Override
    public void start(Stage stage) {
        //Drawing a quadratic curve
        QuadCurve qudraticCurve = new QuadCurve();
        //Setting properties to cubic curve
        qudraticCurve.setStartX(75.0f);
        qudraticCurve.setStartY(75.0f);
        qudraticCurve.setControlX(250.0f);
        qudraticCurve.setControlY(250.0f);
        qudraticCurve.setEndX(500.0f);
        qudraticCurve.setEndY(260.0f);
        //Setting other properties
        qudraticCurve.setFill(Color.CHOCOLATE);
        qudraticCurve.setStrokeWidth(8.0);
        qudraticCurve.setStroke(Color.BROWN);
        //Setting the scene object
        Group root = new Group(qudraticCurve);
        Scene scene = new Scene(root, 595, 300);
        stage.setTitle("Drawing a quadratic curve");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
