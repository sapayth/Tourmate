package bd.org.bitm.mad.batch33.tourmate.Utils;

import bd.org.bitm.mad.batch33.tourmate.model.CurrentWeatherResponses;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CurrentWeatherApi {
    @GET
    Call<CurrentWeatherResponses> getCurrentWeatherData(@Url String urlString);

}
