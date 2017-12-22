
package com.bigame.havadurumu;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigame.havadurumu.model.Weather;

import org.json.JSONException;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

/**
 * Created by burakisik on 8/24/2017.
 */

public class Service {


    public static Weather weather;

    public Service(String city) {
        JSONWeatherTask task = new JSONWeatherTask();
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
               // Log.i(TAG, "dddddoooobccc" + weather.iconData);

            } catch (JSONException e) {
                e.printStackTrace();
                Log.i(TAG, "hataaaaa"+data);
            }
            // Log.i("sssssssssssss",weather.location.getCity());
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            Service.weather = weather;
        }

    }
}
