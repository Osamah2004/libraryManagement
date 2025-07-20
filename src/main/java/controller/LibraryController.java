package controller;

import javafx.collections.ObservableList;
import model.Book;
import repository.BookRepository;
import service.BookService;

public class LibraryController {
    private final BookService bookService;

    public LibraryController() {
        this.bookService = new BookService(BookRepository.getInstance());
    }

    public ObservableList<Book> getBooks() {
        return bookService.getBooks();
    }

    public void addBook(Book book) {
        bookService.addBook(book);
    }

    public void removeBook(Book book) {
        bookService.removeBook(book);
    }

    public void updateBook(Book oldBook, Book newBook) {
        bookService.updateBook(oldBook, newBook);
    }
}