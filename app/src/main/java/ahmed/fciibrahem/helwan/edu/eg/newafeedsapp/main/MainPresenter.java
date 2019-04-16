package ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.main;

import android.content.Context;
import android.provider.ContactsContract;

import java.util.List;

import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.R;
import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.model.Article;
import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.model.News;
import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.network.APIService;
import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.network.RetrofitClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainPresenter {
    Context context;

    MainView view;

    public MainPresenter(MainView mainview, Context context) {
        this.view = mainview;
        this.context=context;

    }

    //get data from Api and pass to View
    public void getNews(String source, String apikey) {
        view.showLodaing();
        APIService apiService = RetrofitClass.getApiclient().create(APIService.class);
        Call<News> call = apiService.getNewsJson(source, apikey);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.hideLodaing();
                    view.onGetResult(response.body());
                } else {
                    String error_code;
                    switch (response.code()) {
                        case 404:
                            error_code = context.getResources().getString(R.string.notfound);
                            break;
                        case 500:
                            error_code = context.getResources().getString(R.string.server_broken);
                            break;
                        default:
                            error_code = context.getResources().getString(R.string.unknown);

                    }
                    view.showErrorMessage(R.drawable.no_result, context.getResources().getString(R.string.no_result), context.getResources().getString(R.string.plz_again) + error_code);

                }

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                view.hideLodaing();
                view.showErrorMessage(R.drawable.no_result, context.getResources().getString(R.string.network_fail), context.getResources().getString(R.string.plz_again) + t.toString());

            }
        });


    }


}
