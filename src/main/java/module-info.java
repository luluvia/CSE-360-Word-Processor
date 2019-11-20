module me.luyu.cse360 {
    requires javafx.controls;
    requires javafx.fxml;

    opens me.luyu.cse360 to javafx.fxml;
    exports me.luyu.cse360;
}