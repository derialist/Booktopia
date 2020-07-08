package com.example.booktopia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView booksListRecycler = findViewById(R.id.book_list_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        booksListRecycler.setLayoutManager(layoutManager);

        List<Book> books = Arrays.asList(Book.books);
        BookAdapter bookAdapter = new BookAdapter(this, books);
        booksListRecycler.setAdapter(bookAdapter);

    }
}
