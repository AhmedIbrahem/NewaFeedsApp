package ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.Newsdetails;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.util.List;

import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.model.Article;
import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.model.News;

public class DetailsPresenter {
    DetailsView view;
    ;

    public DetailsPresenter(DetailsView view) {
        this.view = view;
    }

    public void LoadData(Article article, String source) {
        view.loadDataToView(article, source);

    }
}
