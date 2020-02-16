package by.it.trudnitski.newsapimvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import java.util.List;
import by.it.trudnitski.newsapimvp.R;
import by.it.trudnitski.newsapimvp.model.Article;
import by.it.trudnitski.newsapimvp.presenter.NewsPresenter;
import by.it.trudnitski.newsapimvp.util.NewsAdapter;

public class NewsView extends MvpAppCompatActivity implements INewsView, NewsAdapter.OnNewsListener {

    @InjectPresenter
    NewsPresenter presenter;
    private final static String TAG = "NewsView";
    private static final String TITLE = "title";
    private static final String IMAGE_URL = "imageUrl";
    private static final String SOURCE_NAME = "source:name";
    private static final String DESCRIPTION = "description";
    private AlertDialog dialog;
    private List<Article> articles;
    private RecyclerView recyclerView;
    private Spinner spinner;
    private Toolbar toolbar;
    private NewsAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        spinner = findViewById(R.id.spinner);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        adapter = new NewsAdapter();
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        pullToRefresh();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choose = spinner.getSelectedItem().toString();
                presenter.getMessage(choose);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });
    }

    @Override
    public void pullToRefresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.getMessage(choose);
            swipeRefreshLayout.setRefreshing(false);
            Log.d("stack", "refresh");
        });
    }

    @Override
    public void showNews(List<Article> list) {
        articles = list;
        adapter.setData(articles, this::OnNewsClick);
        adapter.notifyDataSetChanged();
        Log.d("stack", "showNews");
    }

    @Override
    public void showDialog() {
        dialog = new AlertDialog.Builder(this).setTitle("Process").setMessage("content downloading").show();
        Log.d("stack", "showDialog");
    }

    @Override
    public void hideDialog() {
        dialog.dismiss();
        Log.d("stack", "hide dialog");
    }

    @Override
    public void showErrorDialog() {
        dialog.setMessage(" Something wrong! ");
        Log.d("stack", "show error");
    }

    @Override
    public void showTitle(String string) {
        toolbar.setTitle(string);
        Log.e(TAG, " setTitle = " + string);
    }

    @Override
    public void OnNewsClick(int position) {
        presenter.openNewActivity(position);
        Log.d("stack", "open activity presenter");
    }

    @Override
    public void onClick(String title, String source, String description, String imageUrl) {
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(SOURCE_NAME, source);
        intent.putExtra(DESCRIPTION, description);
        intent.putExtra(IMAGE_URL, imageUrl);
        startActivity(intent);
        Log.d("stack", "open new activity");
    }
}