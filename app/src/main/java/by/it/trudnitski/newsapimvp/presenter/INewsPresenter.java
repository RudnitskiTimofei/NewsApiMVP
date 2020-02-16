package by.it.trudnitski.newsapimvp.presenter;

public interface INewsPresenter {
    void loadNews();

    void getNewsResponse();

    void getMessage(String string);

    void openNewActivity(int position);
}