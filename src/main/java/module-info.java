module main.tennyakihary_pa1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.tennyakihary_pa1 to javafx.fxml, javafx.base;
    opens classes to javafx.base;
    exports main.tennyakihary_pa1;
}