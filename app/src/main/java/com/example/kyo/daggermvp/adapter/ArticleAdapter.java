package com.example.kyo.daggermvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.kyo.daggermvp.R;
import com.example.kyo.daggermvp.data.model.Article;
import com.example.kyo.daggermvp.impl.OnMainClickedListener;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ItemViewHolder> {
    private Context context;
    private ArrayList<Article> articles;
    private OnMainClickedListener listener;

    public ArticleAdapter(Context context, ArrayList<Article> articles, OnMainClickedListener listener) {
        this.context = context;
        this.articles = articles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_article, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        final Article article = articles.get(position);

        itemViewHolder.tvTitle.setText(article.getTitle());
        itemViewHolder.tvDescription.setText(article.getDescription());
        Glide.with(context).load(article.getImage()).apply(new RequestOptions().circleCrop()).into(itemViewHolder.imgAvatar);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvatar;
        private TextView tvTitle;
        private TextView tvDescription;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);

            imgAvatar.setOnClickListener(v -> listener.onItemClicked(getAdapterPosition()));
        }
    }
}
