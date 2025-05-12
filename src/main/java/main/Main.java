package main;

import controller.LibraryController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.LibraryView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        LibraryController controller = new LibraryController();
        LibraryView view = new LibraryView(controller);

        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(view.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
