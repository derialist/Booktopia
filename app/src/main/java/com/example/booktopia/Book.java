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

    public static Book[] books = {
            new Book("Outlander","Diana Gabaldon",2012),
            new Book("A Tale Of Two Cities","Charles Dickens",1859),
            new Book("Mr. Mercedes","Stephen King",2015),
            new Book("Outliers","Malcom Gladwell",2008),
            new Book("Harry Potter and the Sorcerer's Stone","J.K.Rowling",1997),
            new Book("The Hunger Games","Suzanne Collins",2008)
    };

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
