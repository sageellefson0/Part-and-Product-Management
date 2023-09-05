/** Module info file for the project. */
module main.sellefson {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.sellefson to javafx.fxml;
    exports main.sellefson;
    opens controller_controllerfiles to javafx.fxml;
    exports controller_controllerfiles;
    opens model_classfiles to javafx.base;
    exports model_classfiles;
}