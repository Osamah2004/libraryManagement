package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;
import repository.BookRepository;

public class LibraryController {
    private final ObservableList<Book> books;
    private final BookRepository repo;

    public LibraryController() {
        repo = BookRepository.getInstance();
        books = FXCollections.observableArrayList(repo.loadBooks());
    }

    public ObservableList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
        repo.saveBooks(books);
    }

    public void removeBook(Book book) {
        books.remove(book);
        repo.saveBooks(books);
    }

    public void updateBook(Book oldBook, Book newBook) {
        int idx = books.indexOf(oldBook);
        if (idx != -1) {
            books.set(idx, newBook);
            repo.saveBooks(books);
        }
    }
}
