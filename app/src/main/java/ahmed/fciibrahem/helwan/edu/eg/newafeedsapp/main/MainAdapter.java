package ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.main;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.Newsdetails.DetailsActivity;
import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.R;
import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.helpers.Utils;
import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.model.Article;
import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.model.News;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private News news;
    private Context context;

    public MainAdapter(News news, Context context) {
        this.news = news;
        this.context = context;

    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, viewGroup, false);
        MainViewHolder holder = new MainViewHolder(view);
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull final MainViewHolder mainViewHolder, int position) {
        List<Article> articles = new ArrayList<>();
        articles = news.getArticles();
        final Article article = articles.get(position);
        Glide.with(context).load(article.getUrlToImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        mainViewHolder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        mainViewHolder.progressBar.setVisibility(View.GONE);

                        return false;
                    }
                }).transition(DrawableTransitionOptions.withCrossFade()).into(mainViewHolder.imgNews);
        mainViewHolder.txtSource.setText(news.getSource());
        mainViewHolder.txtPublish_at.setText(Utils.DateFormat(article.getPublishedAt()));
        mainViewHolder.txtTitle.setText(article.getTitle());
        mainViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("article", article);
                intent.putExtra("source", news.getSource());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return news.getArticles().size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtPublish_at, txtSource;
        ImageView imgNews;
        ProgressBar progressBar;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtPublish_at = itemView.findViewById(R.id.txtPublishedAt);
            txtSource = itemView.findViewById(R.id.txtSource);
            imgNews = itemView.findViewById(R.id.newsImage);
            progressBar = itemView.findViewById(R.id.progressLoadPhoto);

        }


    }


}
