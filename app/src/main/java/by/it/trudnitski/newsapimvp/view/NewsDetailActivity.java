package by.it.trudnitski.newsapimvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;
import by.it.trudnitski.newsapimvp.R;
import by.it.trudnitski.newsapimvp.presenter.NewsDetailPresenter;

public class NewsDetailActivity extends MvpAppCompatActivity implements INewsDetail {

    @InjectPresenter
    NewsDetailPresenter presenter;

    private static final String TITLE = "title";
    private static final String IMAGE_URL = "imageUrl";
    private static final String SOURCE_NAME = "source:name";
    private static final String DESCRIPTION = "description";
    private TextView titleView;
    private TextView descriptionView;
    private TextView sourceNameView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        init();
    }

    private void init() {
        titleView = findViewById(R.id.title_detail);
        sourceNameView = findViewById(R.id.source_name_detail);
        descriptionView = findViewById(R.id.description_detail);
        imageView = findViewById(R.id.picasso_image);
        getMessage();
    }

    @Override
    public void showContent(String title, String source, String description, String imageUrl) {
        titleView.setText(title);
        sourceNameView.setText(source);
        descriptionView.setText(description);
        Picasso.get().load(imageUrl).into(imageView);
    }

    public void getMessage(){
        Intent intent = getIntent();
        String title = intent.getExtras().getString(TITLE);
        String source = intent.getExtras().getString(SOURCE_NAME);
        String description = intent.getExtras().getString(DESCRIPTION);
        String imageUrl = intent.getExtras().getString(IMAGE_URL);
        presenter.sendMessage(title, source, description, imageUrl);
    }
}