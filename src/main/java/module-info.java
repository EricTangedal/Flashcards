module edu.miracosta.cs210.cs210finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens edu.miracosta.cs210.cs210finalproject to javafx.fxml;
    exports edu.miracosta.cs210.cs210finalproject;
}