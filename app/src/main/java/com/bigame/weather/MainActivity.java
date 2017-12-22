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

import static android.content.ContentValues.TAG;


public class MainActivity extends Activity {

    private TextView cityText;
    private TextView temp;
    private  TextView weatherIcon;
    private TextView condDescr;

    Typeface weatherFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_row);
        String city = "Şanlıurfa";

        weatherFont = Typeface.createFromAsset(getAssets(),"fonts/weather.ttf");

        cityText = (TextView) findViewById(R.id.tvCity);
        condDescr = (TextView) findViewById(R.id.tvCondition);
        temp = (TextView) findViewById(R.id.tvDegree);
      
        weatherIcon = (TextView) findViewById(R.id.condIcon); 

        weatherIcon.setTypeface(weatherFont);

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ((new WeatherHttpClient()).getWeatherData(params[0]));
            Log.i(TAG, "doInBackground: "+data);
            try {
                weather = JSONWeatherParser.getWeather(data);       

            } catch (JSONException e) {
                e.printStackTrace();
                Log.i(TAG, "hataaaaa"+data);
            }
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            if(weather.currentCondition.getWeatherId() !=0)
                setWeatherIcon(weather.currentCondition.getWeatherId(),weather.location.getSunrise() * 1000,weather.location.getSunset() * 1000);
           
            cityText.setText(weather.location.getCity());
            condDescr.setText(weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescr() + ")");
            temp.setText("" + Math.round((weather.temperature.getTemp() - 273.15)) + " °C");
            
        }

        private void setWeatherIcon(int actualId , long sunrise,long sunset){
            int id = actualId / 100;
            String icon = "";
            if(actualId == 800)
            {
                long currentTime = new Date().getTime();
                if(currentTime>=sunrise && currentTime<sunset)
                {
                    icon = getString(R.string.weather_sunny);
                }
                else
                {
                    icon = getString(R.string.weather_clear_night);
                }
            }
            else
            {
                switch(id) {
                    case 2 : icon = getString(R.string.weather_thunder);
                        break;
                    case 3 : icon = getString(R.string.weather_drizzle);
                        break;
                    case 7 : icon = getString(R.string.weather_foggy);
                        break;
                    case 8 : icon = getString(R.string.weather_cloudy);
                        break;
                    case 6 : icon = getString(R.string.weather_snowy);
                        break;
                    case 5 : icon = getString(R.string.weather_rainy);
                        break;
                }
            }
            weatherIcon.setText(icon);
        }
    }
}
