package com.example.booktopia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Book> mBooks;
    private RecyclerView mBooksListRecycler;
    private BookAdapter mBookAdapter;
    private BookOpenHelper mBookOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mBookOpenHelper = new BookOpenHelper(this);

        FloatingActionButton addFab = findViewById(R.id.fab);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddBookActivity.class));
            }
        });

        mBooksListRecycler = findViewById(R.id.book_list_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mBooksListRecycler.setLayoutManager(layoutManager);
        mBooks = Book.getBooks();
        mBookAdapter = new BookAdapter(this, mBooks);

    }

    @Override
    protected void onResume() {
        showBooks();
        super.onResume();
        mBookAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBookOpenHelper.close();
    }

    private void showBooks() {
        TextView tvEmptyList = findViewById(R.id.tv_books_empty);

        SQLiteDatabase db = mBookOpenHelper.getReadableDatabase();

        /* .size method returns the number of elements stored in an array list.
         Initially the data set is 0 because the array list is empty
         So, we show a placeholder view. Otherwise, show the recyclerview */

        if(mBooks.size() == 0) {
           tvEmptyList.setVisibility(View.VISIBLE);
        } else
            tvEmptyList.setVisibility(View.INVISIBLE);
            mBooksListRecycler.setAdapter(mBookAdapter);
    }

    /*TODO:Implement functionality to favorite a book*/
    public void showFavorite(View view) {
        Log.d("Hey","It works!");
    }
}
