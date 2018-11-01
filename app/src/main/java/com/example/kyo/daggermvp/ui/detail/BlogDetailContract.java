package com.example.kyo.daggermvp.ui.detail;

import com.example.kyo.daggermvp.data.model.Article;
import com.example.kyo.daggermvp.ui.base.BasePresenter;
import com.example.kyo.daggermvp.ui.base.BaseView;

public interface BlogDetailContract {
    interface Presenter extends BasePresenter {
        void loadBlogDetail(String id);
    }

    interface View extends BaseView<Presenter> {
        void showBlogDetail(Article article);

        void showError(Throwable throwable);

        void setPresenter(Presenter presenter);
    }
}
