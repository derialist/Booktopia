package com.example.booktopia;


import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private int releaseYear;
    private static List<Book> sBookArrayList = new ArrayList<>();;

    public Book(String title, String author, int releaseYear) {
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
    }


    public static void setBook(Book book) {
            sBookArrayList.add(book);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public static List<Book> getBooks() {
        return sBookArrayList;
    }
}
