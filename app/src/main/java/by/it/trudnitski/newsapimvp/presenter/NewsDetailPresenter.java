package by.it.trudnitski.newsapimvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import by.it.trudnitski.newsapimvp.view.INewsDetail;

@InjectViewState
public class NewsDetailPresenter extends MvpPresenter<INewsDetail> implements INewsDetailPresenter {

    public NewsDetailPresenter(){}

@Override
    public void sendMessage(String title, String source, String description, String imageUrl){
        getViewState().showContent(title, source, description, imageUrl);
    }
}