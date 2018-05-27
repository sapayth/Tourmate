package bd.org.bitm.mad.batch33.tourmate.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import bd.org.bitm.mad.batch33.tourmate.R;
import bd.org.bitm.mad.batch33.tourmate.adapter.ViewPagerAdapter;

public class WeatherActivity extends AppCompatActivity {
    private TabLayout weatherTL;
    private ViewPager weatherVP;
    private ViewPagerAdapter viewPagerAdapter;
    private FusedLocationProviderClient client;
    private double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherTL = findViewById(R.id.weatherTL);
        weatherVP = findViewById(R.id.weatherVP);

        weatherTL.addTab(weatherTL.newTab().setText("Current Weather"));
        weatherTL.addTab(weatherTL.newTab().setText("Weather Forecast"));
        weatherTL.setTabTextColors(Color.DKGRAY, Color.WHITE);
        weatherTL.setSelectedTabIndicatorColor(Color.WHITE);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), weatherTL.getTabCount());
        weatherVP.setAdapter(viewPagerAdapter);

        weatherTL.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                weatherVP.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        weatherVP.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(weatherTL));


/*        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            return;
        }
        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location == null){
                    return;

                }
                latitude = location.getLongitude();
                longitude = location.getLongitude();

                Toast.makeText(WeatherActivity.this, latitude+" "+longitude+"", Toast.LENGTH_SHORT).show();
            }


        });*/


    }
}
