package ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.Newsdetails;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.R;
import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.helpers.Utils;
import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.main.MainActivity;
import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.model.Article;

public class DetailsActivity extends AppCompatActivity implements DetailsView {
    TextView txtTitle, txtDescription, txtPublish_at, txtSource;
    ImageView imgNewDetailed;
    Button btnOpenWebsite;
    DetailsPresenter presenter;
    Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        presenter = new DetailsPresenter(this);
        intializeViews();
        article = new Article();
        Intent intent = getIntent();
        article = intent.getParcelableExtra("article");
        String sourceText = intent.getStringExtra("source");
        presenter.LoadData(article, sourceText);
        btnOpenWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.openBrowser(getApplicationContext(), article.getUrl());
            }
        });
    }


    @Override
    public void intializeViews() {
        txtTitle = findViewById(R.id.txtDetailedTitle);
        txtDescription = findViewById(R.id.txtDetailedDescription);
        txtPublish_at = findViewById(R.id.txtDetailedPublishedAt);
        txtSource = findViewById(R.id.txtDetailedSource);
        imgNewDetailed = findViewById(R.id.newsDetailedImage);
        btnOpenWebsite = findViewById(R.id.btnViewWebPage);
        getSupportActionBar().setTitle("Link Development");


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void loadDataToView(Article article, String source) {
        Article detailedArticl = article;
        String newsSource = source;
        txtTitle.setText(detailedArticl.getTitle());
        txtDescription.setText(detailedArticl.getDescription());
        txtPublish_at.setText(Utils.DateFormat(detailedArticl.getPublishedAt()));
        txtSource.setText(newsSource);
        Glide.with(this).load(detailedArticl.getUrlToImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        return false;
                    }
                }).transition(DrawableTransitionOptions.withCrossFade()).into(imgNewDetailed);

    }
}
