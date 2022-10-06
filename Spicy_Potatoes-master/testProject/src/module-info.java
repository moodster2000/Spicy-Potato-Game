module testProject {
    requires javafx.fxml;
    requires javafx.controls;
    requires junit;
    requires org.testfx.junit;
    requires org.testfx;
    requires javafx.graphics;
    exports test to junit;
    opens game;
}