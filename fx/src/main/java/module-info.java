module fx {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
//    requires javafx.base;

    opens fx;

    exports fx;
}