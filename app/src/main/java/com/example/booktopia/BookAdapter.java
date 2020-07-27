package com.example.booktopia;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booktopia.BookDatabaseContract.BookEntry;

import java.util.List;

public class BookAdapter  extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{
    private Cursor mCursor;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private int mBookTitlePos;
    private int mBookAuthorPos;
    private int mBookPublishYearPos;
    private int mId;
    private int mIdPos;

    public BookAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
        mLayoutInflater = LayoutInflater.from(mContext);
        setColumnPositions();
    }

    private void setColumnPositions() {
        if(mCursor == null)
            return;
        //return column indexes from the cursor
        mBookTitlePos = mCursor.getColumnIndex(BookEntry.BOOK_TITLE);
        mBookAuthorPos = mCursor.getColumnIndex(BookEntry.BOOK_AUTHOR);
        mBookPublishYearPos = mCursor.getColumnIndex(BookEntry.BOOK_PUBLISH_YEAR);
      //  mIdPos = mCursor.getColumnIndex(BookEntry._ID);
    }

    public void changeCursor(Cursor cursor) {
        if(mCursor != null)
            mCursor.close();
        mCursor = cursor;
        setColumnPositions();
        notifyDataSetChanged();
    }


    public static class BookViewHolder extends RecyclerView.ViewHolder {

        public final TextView mBookTitle;
        public final TextView mBookAuthor;
        public final TextView mYearOfRelease;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            mBookTitle = itemView.findViewById(R.id.tv_title_book);
            mBookAuthor = itemView.findViewById(R.id.tv_author_book);
            mYearOfRelease = itemView.findViewById(R.id.tv_year_book);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Click","Recycler item clicked");
                }
            });
        }

    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.book_item_list, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        String bookTitle = mCursor.getString(mBookTitlePos);
        String bookAuthor = mCursor.getString(mBookAuthorPos);
        String bookPublishYear = mCursor.getString(mBookPublishYearPos);
       // int id = mCursor.getInt(mIdPos);
        //Book book = mBookList.get(position);
        holder.mBookTitle.setText(bookTitle);
        holder.mBookAuthor.setText(bookAuthor);
        holder.mYearOfRelease.setText(bookPublishYear);
    }

    @Override
    public int getItemCount() {
//        if(mBookList == null)
//            return 0;
//        else
//            return mBookList.size();
        return mCursor == null ? 0 : mCursor.getCount();
    }
}
