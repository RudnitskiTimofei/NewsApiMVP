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
        String title = intent.getExtras().getString("title");
        String source = intent.getExtras().getString("source:name");
        String description = intent.getExtras().getString("description");
        String imageUrl = intent.getExtras().getString("imageUrl");
        presenter.sendMessage(title, source, description, imageUrl);
    }
}