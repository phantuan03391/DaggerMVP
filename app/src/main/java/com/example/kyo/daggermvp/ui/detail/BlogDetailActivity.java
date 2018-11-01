package com.example.kyo.daggermvp.ui.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.kyo.daggermvp.R;
import com.example.kyo.daggermvp.data.model.Article;

public class BlogDetailActivity extends AppCompatActivity implements BlogDetailContract.View {
    private static final String TAG = "kyo";
    private String articleId;
    private TextView tvDetailTitle;
    private BlogDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);
        addControls();
        addEvents();
    }

    private void addEvents() {
        tvDetailTitle.setText(articleId);

        presenter.loadBlogDetail(articleId);
    }

    private void addControls() {
        presenter = new BlogDetailPresenter(this);
        articleId = getIntent().getStringExtra("article_id");

        tvDetailTitle = findViewById(R.id.tvDetailTitle);
    }

    @Override
    public void showBlogDetail(Article article) {
        Log.e(TAG, "showBlogDetail: " + article.toString());
    }

    @Override
    public void showError(Throwable throwable) {
        Log.e(TAG, "showError: " + throwable.getMessage());
    }

    @Override
    public void setPresenter(BlogDetailContract.Presenter presenter) {
    }

    @Override
    public void showLoadingUi() {

    }

    @Override
    public void hideLoadingUi() {

    }
}
