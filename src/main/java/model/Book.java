package model;

import java.time.LocalDate;

public class Book {
    private String name;
    private String author;
    private double price;
    private LocalDate releaseDate;

    public Book(String name, String author, double price, LocalDate releaseDate) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
}
