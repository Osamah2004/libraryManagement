package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;
import repository.IBookRepository;

public class BookService {
    private final IBookRepository repo;
    private final ObservableList<Book> books;

    public BookService(IBookRepository repo) {
        this.repo = repo;
        this.books = FXCollections.observableArrayList(repo.loadBooks());
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