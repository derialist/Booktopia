package com.example.booktopia;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.widget.Toast;

import com.example.booktopia.BookDatabaseContract.BookEntry;

import java.util.ArrayList;

public class DataManager {
    private static Object MainActivity;
    private ArrayList<Book> mBookArrayList = new ArrayList<>();
    private DataManager(){}


    public static void getFromDatabase(BookOpenHelper dbHelper) {
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor bookCursor = db.query(BookEntry.TABLE_NAME,
                    new String[]{BookEntry.BOOK_TITLE, BookEntry.BOOK_AUTHOR, BookEntry.BOOK_PUBLISH_YEAR},
                    null, null, null, null, null);
            cursorNavigator(bookCursor);
        } catch (SQLException error) {
            Toast toast = Toast.makeText((Context) MainActivity,"Unable to reach the database",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private static void cursorNavigator(Cursor cursor) {
        int bookTitleIndex = cursor.getColumnIndex(BookEntry.BOOK_TITLE);
        int bookAuthorIndex = cursor.getColumnIndex(BookEntry.BOOK_AUTHOR);
        int bookPublishYearIndex = cursor.getColumnIndex(BookEntry.BOOK_PUBLISH_YEAR);

      //  DataManager dm = getInstance();
       // dm.mBookArrayList.clear();

        while(cursor.moveToNext()) {
            cursor.getString(bookTitleIndex);
            cursor.getString(bookAuthorIndex);
            cursor.getInt(bookPublishYearIndex);

//            Book book = new Book(bookTitle,bookAuthor,bookPublishYear);
//            dm.mBookArrayList.add(book);
        }
        cursor.close();
    }
}
