package repository;

import java.util.List;

import model.Book;

public interface IBookRepository {
    List<Book> loadBooks();
    void saveBooks(List<Book> books);
}