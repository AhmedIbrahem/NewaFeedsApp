package ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.Newsdetails;

import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.model.Article;

public interface DetailsView {
    void intializeViews();

    void loadDataToView(Article article, String source);


}
