package by.it.trudnitski.newsapimvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import by.it.trudnitski.newsapimvp.model.Article;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface INewsView extends MvpView {

    void showNews(List<Article> list);

    void showDialog();

    @StateStrategyType(SingleStateStrategy.class)
    void chooseTheme();

    void showTitle(String string);

    void hideDialog();

    void showErrorDialog();

    @StateStrategyType(SingleStateStrategy.class)
    void pullToRefresh();

    @StateStrategyType(SkipStrategy.class)
    void onClick(String title, String source, String description, String imageUrl);
}