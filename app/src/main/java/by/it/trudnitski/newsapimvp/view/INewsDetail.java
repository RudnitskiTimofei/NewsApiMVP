package by.it.trudnitski.newsapimvp.view;

import com.arellomobile.mvp.MvpView;

public interface INewsDetail extends MvpView {
    void showContent(String title, String source, String description, String imageUrl);
}