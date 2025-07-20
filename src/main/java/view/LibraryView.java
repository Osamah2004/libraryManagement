package view;

import java.time.LocalDate;

import controller.LibraryController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import model.Book;

public class LibraryView {
    private final LibraryController controller;

    public LibraryView(LibraryController controller) {
        this.controller = controller;
    }

    public Scene getScene() {
        TableView<Book> table = new TableView<>(controller.getBooks());

        TableColumn<Book, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getAuthor()));
        authorCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Book, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(data -> new javafx.beans.property.ReadOnlyObjectWrapper<>(data.getValue().getPrice()));
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<Book, LocalDate> dateCol = new TableColumn<>("Release Date");
        dateCol.setCellValueFactory(data -> new javafx.beans.property.ReadOnlyObjectWrapper<>(data.getValue().getReleaseDate()));
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));

        table.getColumns().addAll(nameCol, authorCol, priceCol, dateCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setEditable(true);

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField authorField = new TextField();
        authorField.setPromptText("Author");

        TextField priceField = new TextField();
        priceField.setPromptText("Price");

        DatePicker releaseDatePicker = new DatePicker();
        releaseDatePicker.setPromptText("Release Date");

        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> {
            try {
                String name = nameField.getText();
                String author = authorField.getText();
                String priceText = priceField.getText();
                LocalDate releaseDate = releaseDatePicker.getValue();

                if (name.isEmpty() || author.isEmpty() || priceText.isEmpty() || releaseDate == null) {
                    showAlert("All fields are required.");
                    return;
                }

                double price;
                try {
                    price = Double.parseDouble(priceText);
                } catch (NumberFormatException nfe) {
                    showAlert("Price must be a valid number.");
                    return;
                }

                Book book = new Book(name, author, price, releaseDate);
                controller.addBook(book);

                nameField.clear();
                authorField.clear();
                priceField.clear();
                releaseDatePicker.setValue(null);

            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert("Unexpected error occurred.");
            }
        });

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> {
            Book selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                controller.removeBook(selected);
            }
        });

        HBox inputs = new HBox(10, nameField, authorField, priceField, releaseDatePicker, addBtn, deleteBtn);
        VBox layout = new VBox(10, table, inputs);
        layout.setPadding(new javafx.geometry.Insets(10));

        return new Scene(layout, 800, 600);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}