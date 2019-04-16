package ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.main;

import java.util.List;

import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.model.News;

public interface MainView {
    void intializeViews();

    void showLodaing();

    void hideLodaing();

    void onGetResult(News news);

    void showErrorMessage(int imageview, String title, String message);

    void showToast(String actionName);


}
