package by.it.trudnitski.newsapimvp.presenter;

import android.annotation.SuppressLint;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import java.util.List;
import by.it.trudnitski.newsapimvp.model.Article;
import by.it.trudnitski.newsapimvp.model.News;
import by.it.trudnitski.newsapimvp.model.api.ApiClient;
import by.it.trudnitski.newsapimvp.model.api.ApiInterface;
import by.it.trudnitski.newsapimvp.util.Util;
import by.it.trudnitski.newsapimvp.view.INewsView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class NewsPresenter extends MvpPresenter<INewsView> implements INewsPresenter {
    private static final String APY_KEY = "cdb1f31d30b140e0a5aecfd9ec58ec47";
    private static final String PUBLISHED_AT = "publishedAt";
    private String message;
    private List<Article> articles;

    public NewsPresenter() {
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadNews() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getNews(message, Util.getDate(), PUBLISHED_AT, APY_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<News>() {
                    @Override
                    public void onSuccess(News news) {
                        articles = news.getArticles();
                        getViewState().showNews(articles);
                        getViewState().hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showErrorDialog();
                    }
                });
    }

    @Override
    public void getNewsResponse() {
        getViewState().showTitle(message);
        getViewState().showDialog();
        loadNews();
    }

    @Override
    public void getMessage(String string) {
        message = string;
        getNewsResponse();
    }

    @Override
    public void openNewActivity(int position) {
        String title = articles.get(position).getTitle();
        String source = articles.get(position).getSource().getName();
        String description = articles.get(position).getDescription();
        String imageUrl = articles.get(position).getUrlToImage();
        getViewState().onClick(title, source, description, imageUrl);
    }
}