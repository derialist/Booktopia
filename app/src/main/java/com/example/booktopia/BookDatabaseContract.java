package com.example.booktopia;

import android.provider.BaseColumns;

public final class BookDatabaseContract {
    private BookDatabaseContract() {
    }

    public static final class BookEntry implements BaseColumns {
        public static final String TABLE_NAME = "book_info";
        public static final String  BOOK_TITLE = "book_title";
        public static final String BOOK_AUTHOR = "book_author";
        public static final String BOOK_PUBLISH_YEAR = "book_publish_year";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY, " +BOOK_TITLE + " TEXT NOT NULL, " + BOOK_AUTHOR
                + " TEXT NOT NULL, " + BOOK_PUBLISH_YEAR + " INTEGER)";
    }
}
