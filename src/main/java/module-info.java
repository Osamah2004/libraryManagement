module java {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    

    exports model;
    exports controller;
    exports view;
    exports repository;
    exports main; // if your main.Main class is in a 'main' package
}
