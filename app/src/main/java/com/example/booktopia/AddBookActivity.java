package com.example.booktopia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booktopia.BookDatabaseContract.BookEntry;
import com.google.android.material.snackbar.Snackbar;

public class AddBookActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mAuthor;
    private TextView mYear;
    private Button mAddButton;
    private String mBookTitle;
    private String mBookAuthor;
    private String mBookYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        mTitle = findViewById(R.id.tv_title);
        mAuthor = findViewById(R.id.tv_author);
        mYear = findViewById(R.id.tv_year);
        mAddButton = findViewById(R.id.button_add_book);

        mTitle.addTextChangedListener(tvWatcher);
        mAuthor.addTextChangedListener(tvWatcher);
        mYear.addTextChangedListener(tvWatcher);



    }

    // creates a new book entry
    public void addBook(View view) {
        BookOpenHelper bookDbHelper = new BookOpenHelper(this);
        SQLiteDatabase db = bookDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BookEntry.BOOK_TITLE,mBookTitle);
        values.put(BookEntry.BOOK_AUTHOR,mBookAuthor);
        values.put(BookEntry.BOOK_PUBLISH_YEAR,Integer.parseInt(mBookYear));

        long newRow = db.insert(BookEntry.TABLE_NAME,null,values);

       // Book.setBook(new Book(mBookTitle, mBookAuthor, Integer.parseInt(mBookYear)));
        Snackbar.make(view, "Book added", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        Log.d("Alert", "Book added");

        resetView(); //reset the views after the user adds the book item


    }

    private void resetView() {
        mTitle.setText("");
        mAuthor.setText("");
        mYear.setText("");
    }

    TextWatcher tvWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mBookTitle = mTitle.getText().toString();
            mBookAuthor = mAuthor.getText().toString();
            mBookYear = mYear.getText().toString();

            mAddButton.setEnabled(!mBookTitle.isEmpty() &&
                    !mBookAuthor.isEmpty() &&
                    !mBookYear.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

    };
}
