package repository;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import model.Book;

public class BookRepository implements IBookRepository {
    private static final String FILE_PATH = "books.json";
    private static BookRepository instance;

    private BookRepository() {}

    public static BookRepository getInstance() {
        if (instance == null)
            instance = new BookRepository();
        return instance;
    }

    @Override
    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (InputStream is = new FileInputStream(FILE_PATH)) {
            JSONArray arr = new JSONArray(new JSONTokener(is));
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                books.add(new Book(
                        obj.getString("name"),
                        obj.getString("author"),
                        obj.getDouble("price"),
                        LocalDate.parse(obj.getString("releaseDate"))
                ));
            }
        } catch (Exception e) {
            // Ignore file errors
        }
        return books;
    }

    @Override
    public void saveBooks(List<Book> books) {
        JSONArray arr = new JSONArray();
        for (Book b : books) {
            JSONObject obj = new JSONObject();
            obj.put("name", b.getName());
            obj.put("author", b.getAuthor());
            obj.put("price", b.getPrice());
            obj.put("releaseDate", b.getReleaseDate().toString());
            arr.put(obj);
        }
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(arr.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}