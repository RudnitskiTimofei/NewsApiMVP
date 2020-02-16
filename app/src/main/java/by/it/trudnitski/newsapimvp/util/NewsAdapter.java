package by.it.trudnitski.newsapimvp.util;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import by.it.trudnitski.newsapimvp.R;
import by.it.trudnitski.newsapimvp.model.Article;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<Article> articles;
    private OnNewsListener mOnNewsListener;

    public NewsAdapter() {
        articles = new ArrayList<>();
        notifyDataSetChanged();
    }

    public void setData(List<Article> list, OnNewsListener onNewsListener) {
        articles = list;
        mOnNewsListener = onNewsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new NewsAdapter.ViewHolder(view, mOnNewsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Article model = articles.get(position);
        if (!TextUtils.isEmpty(model.getAuthor())) {
            holder.author.setText(model.getAuthor());
        }
        if (!TextUtils.isEmpty(model.getTitle())) {
            holder.title.setText(model.getTitle());
        }
        if (!TextUtils.isEmpty(model.getPublishedAt())) {
            holder.publishedAt.setText(model.getPublishedAt());
        }
        if (model.getUrlToImage()!= null) {
            Picasso.get().load(model.getUrlToImage()).into(holder.imageView);
        } else {
            Picasso.get().load(android.R.mipmap.sym_def_app_icon).into(holder.imageView);
            Log.d("stack", "download picture");
        }
    }

    @Override
    public int getItemCount() {
        return articles != null ? articles.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView author;
        private TextView title;
        private TextView publishedAt;
        private ImageView imageView;
        OnNewsListener mOnNewsListener;

        ViewHolder(@NonNull View itemView, OnNewsListener onNewsListener) {
            super(itemView);
            author = itemView.findViewById(R.id.author_item);
            title = itemView.findViewById(R.id.title_item);
            publishedAt = itemView.findViewById(R.id.publishedAt_item);
            imageView = itemView.findViewById(R.id.image_item);
            mOnNewsListener = onNewsListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnNewsListener.OnNewsClick(getAdapterPosition());
        }
    }

    public interface OnNewsListener {
        void OnNewsClick(int position);
    }
}