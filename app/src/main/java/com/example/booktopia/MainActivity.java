package com.example.booktopia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booktopia.BookDatabaseContract.BookEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
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

        initializeContent();
        updateBooksList();

    }

    private void initializeContent() {
        DataManager.getFromDatabase(mBookOpenHelper);

        RecyclerView booksListRecycler = findViewById(R.id.book_list_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        booksListRecycler.setLayoutManager(layoutManager);

        mBookAdapter = new BookAdapter(this, null);
        booksListRecycler.setAdapter(mBookAdapter);
    }

    @Override
    protected void onResume() {
        //  showBooks();
        super.onResume();
        //mBookAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateBooksList();
    }

    private void updateBooksList() {
        try {
            SQLiteDatabase db = mBookOpenHelper.getReadableDatabase();
            String[] bookColumns = new String[]{BookEntry.BOOK_TITLE, BookEntry.BOOK_AUTHOR, BookEntry.BOOK_PUBLISH_YEAR};
            Cursor bookCursor = db.query(
                    BookEntry.TABLE_NAME, bookColumns, null, null, null, null, BookEntry.BOOK_TITLE);
            int cursorStatus = bookCursor.getCount();
            if (cursorStatus == 0)
                showEmptyView();
            mBookAdapter.changeCursor(bookCursor);
        } catch (SQLException e) {
            Toast toast = Toast.makeText(this,"Database not found",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void showEmptyView() {
        TextView tvEmptyList = findViewById(R.id.tv_books_empty);
        tvEmptyList.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBookOpenHelper.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_all_entries, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_delete_entries) {
            deleteSavedEntries();
            updateBooksList();
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteSavedEntries() {
        SQLiteDatabase db = mBookOpenHelper.getWritableDatabase();
        db.delete(BookEntry.TABLE_NAME, null, null);
    }

    //    private void showBooks() {
//
//
//        /* .size method returns the number of elements stored in an array list.
//         Initially the data set is 0 because the array list is empty
//         So, we show a placeholder view. Otherwise, show the recyclerview */
//
//        if( mBookCursor.getCount() == 0) {
//           tvEmptyList.setVisibility(View.VISIBLE);
//        } else
//            tvEmptyList.setVisibility(View.INVISIBLE);
//            mBooksListRecycler.setAdapter(mBookAdapter);
//    }

    /*TODO:Implement functionality to favorite a book*/
    public void showFavorite(View view) {
        Log.d("Hey", "It works!");
    }
}
