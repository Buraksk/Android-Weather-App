package com.bigame.havadurumu;

import android.os.AsyncTask;
import android.util.Log;

import com.bigame.havadurumu.model.Weather;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

/**
 * Created by burakisik on 8/27/2017.
 */

public class TabMenuService {

    public static Weather weather;

    public TabMenuService(String city) {
        TabMenuService.JSONWeatherTask task = new TabMenuService.JSONWeatherTask();
        try {
            weather = task.execute(new String[]{city}).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ((new WeatherHttpClient()).getWeatherData(params[0]));
            Log.i(TAG, "doInBackground: "+ data);
            try {
                weather = JSONWeatherParser.getWeather(data);

                //Let's retrieve the icon
                weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));
                Log.i(TAG, "dddddoooobccckkkggrrnnd" + weather.iconData);

            } catch (JSONException e) {
                e.printStackTrace();
                Log.i(TAG, "hataaaaa"+data);
            }
            Log.i("sssssssssssss",weather.location.getCity());
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            Service.weather = weather;
        }

    }
}
