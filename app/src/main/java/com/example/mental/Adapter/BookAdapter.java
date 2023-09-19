package com.example.mental.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Definition.BookItem;
import com.example.mental.FunctionUI.BookDetailsActivity;
import com.example.mental.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context context;
    private List<BookItem> bookItems;
    private BookItem bookItem;

    public BookAdapter(Context context, List<BookItem> bookItems) {
        this.context = context;
        this.bookItems = bookItems;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        final BookItem bookItem = bookItems.get(position);
        holder.bookBackgroundImageView.setImageResource(bookItem.getBookBackgroundResId());
        holder.bookTitleTextView.setText(bookItem.getBookTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookDetailsActivity.class);
                // 传递所需字段的值
                intent.putExtra("bookBackgroundResId", bookItem.getBookBackgroundResId());
                intent.putExtra("bookTitle", bookItem.getBookTitle());
                intent.putExtra("bookContent", bookItem.getBookContent());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return bookItems.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView bookBackgroundImageView;
        TextView bookTitleTextView;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookBackgroundImageView = itemView.findViewById(R.id.bookItemBackground);
            bookTitleTextView = itemView.findViewById(R.id.bookItemName);
        }
    }
}
