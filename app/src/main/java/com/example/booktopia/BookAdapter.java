package com.example.booktopia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter  extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{
    private List<Book> mBookList;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;

    public BookAdapter(Context context, List<Book> bookList) {
        mContext = context;
        mBookList = bookList;
        mLayoutInflater = LayoutInflater.from(mContext);
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
        Book book = mBookList.get(position);
        holder.mBookTitle.setText(book.getTitle());
        holder.mBookAuthor.setText(book.getAuthor());
        holder.mYearOfRelease.setText(String.valueOf(book.getReleaseYear()));
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }
}
