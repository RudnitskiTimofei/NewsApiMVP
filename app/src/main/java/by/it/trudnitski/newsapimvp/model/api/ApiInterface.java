package by.it.trudnitski.newsapimvp.model.api;

import java.util.List;

import by.it.trudnitski.newsapimvp.model.News;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/v2/everything")
    Single<News> getNews(
            @Query("q") String source,
            @Query("from") String from,
            @Query("sortBy") String sort,
            @Query("apiKey") String apiKey );
}
