package bd.org.bitm.mad.batch33.tourmate.fragment.Weather;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import bd.org.bitm.mad.batch33.tourmate.R;
import bd.org.bitm.mad.batch33.tourmate.Utils.CurrentWeatherApi;
import bd.org.bitm.mad.batch33.tourmate.model.CurrentWeatherResponses;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentWeatherFragment extends Fragment {
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private String units = "metric";

    private FusedLocationProviderClient client;
    private CurrentWeatherApi weatherApi;
    private double latitude, longitude;
    private TextView tempTV, dateTV, dayTV, locationTV,maxTempTV, minTempTV;
    private TextView sunriseTV, sunsetTV, humidityTV, pressureTV, cloudTV, weatherDescTV;
    ImageView weatherIconIV;


    View view;
    public CurrentWeatherFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_current_weather, container,false);
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        tempTV = view.findViewById(R.id.tempTV);

        getLocations();
        return view;
    }

    private void checkPermission(){
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            return;
        }

    }

    private void getLocations(){
        checkPermission();
        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location == null){
                    return;
                }

                latitude = location.getLatitude();
                longitude = location.getLongitude();

                Toast.makeText(getActivity(), latitude+"=="+ longitude+"", Toast.LENGTH_LONG).show();
                getData();

            }
        });
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApi = retrofit.create(CurrentWeatherApi.class);
        String urlString = String.format("weather?lat=%f&lon=%f&units=%s&appid=%s", latitude, longitude, units, getString(R.string.weather_api_key));
        Log.d("UrlString: " , urlString);
        Call<CurrentWeatherResponses> responsesCall = weatherApi.getCurrentWeatherData(urlString);
        responsesCall.enqueue(new Callback<CurrentWeatherResponses>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponses> call, Response<CurrentWeatherResponses> response) {
                CurrentWeatherResponses currentWeatherResponses = response.body();
                CurrentWeatherResponses.Main main = currentWeatherResponses.getMain();
               // CurrentWeatherResponses.Sys sys = currentWeatherResponses.getSys();

                double temp = main.getTemp();
                tempTV.setText(temp+"\u00B0C");
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponses> call, Throwable t) {
                Toast.makeText(getActivity(), "Cannot retrieve data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private double kelvinToDegree(double kelvin){
        Toast.makeText(getActivity(), (kelvin-273.15)+"", Toast.LENGTH_SHORT).show();
        return (kelvin-273.15);
    }



}
