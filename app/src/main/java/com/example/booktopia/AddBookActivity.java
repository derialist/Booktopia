package com.example.booktopia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class AddBookActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mAuthor;
    private TextView mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        mTitle = findViewById(R.id.tv_title);
        mAuthor = findViewById(R.id.tv_author);
        mYear = findViewById(R.id.tv_year);

    }
// creates a new book entry
    public void addBook(View view) {
        //TextView emptyFields = findViewById(R.id.tv_warning);
        String bookTitle = mTitle.getText().toString();
        String bookAuthor = mAuthor.getText().toString();
        String bookYear = mYear.getText().toString();

            Book.setBook(new Book(bookTitle, bookAuthor, Integer.parseInt(bookYear)));
            Snackbar.make(view, "Book added", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            Log.d("Alert", "Book added");

            resetView(); //reset the views after the user adds the book item
        }



    private void resetView() {
        mTitle.setText("");
        mAuthor.setText("");
        mYear.setText("");
    }
}
