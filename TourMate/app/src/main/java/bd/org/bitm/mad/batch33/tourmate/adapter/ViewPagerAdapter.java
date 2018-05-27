package bd.org.bitm.mad.batch33.tourmate.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import bd.org.bitm.mad.batch33.tourmate.fragment.Weather.CurrentWeatherFragment;
import bd.org.bitm.mad.batch33.tourmate.fragment.Weather.WeatherForecastFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private int numberOfTab;
    public ViewPagerAdapter(FragmentManager fm, int numberOfTab) {
        super(fm);
        this.numberOfTab = numberOfTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CurrentWeatherFragment();
            case 1:
                return new WeatherForecastFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberOfTab;
    }
}
