package ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.network;

import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.model.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("articles")
    Call<News> getNewsJson(
            @Query("source") String Country,
            @Query("apiKey") String apiKey
    );


}
