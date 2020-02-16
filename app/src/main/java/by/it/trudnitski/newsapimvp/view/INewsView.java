package by.it.trudnitski.newsapimvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import by.it.trudnitski.newsapimvp.model.Article;
@StateStrategyType(AddToEndSingleStrategy.class)
public interface INewsView extends MvpView {

    void showNews(List<Article> list);

    void showDialog();

    void showTitle(String string);

    void hideDialog();

    void showErrorDialog();

    void pullToRefresh();
    @StateStrategyType(AddToEndSingleStrategy.class)
    void onClick(String title, String source, String description, String imageUrl);
}