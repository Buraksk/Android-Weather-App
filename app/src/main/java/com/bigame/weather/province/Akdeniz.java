package com.bigame.havadurumu.iller;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bigame.havadurumu.ListAdapter;
import com.bigame.havadurumu.MainActivity;
import com.bigame.havadurumu.R;
import com.bigame.havadurumu.Service;
import com.bigame.havadurumu.ShowWhathwerInfo;
import com.bigame.havadurumu.info;
import com.bigame.havadurumu.model.Weather;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by burakisik on 8/27/2017.
 */
//https://www.codeproject.com/Articles/630248/WPF-OpenWeather cokkk güzel inçele kesinnnnn!!!!!!!!!!!!!

    //https://alty.co/blog/weather-app-development-tricks-tips/ esfane design lar var!!!!!

public class Akdeniz extends Activity implements AdapterView.OnItemClickListener {

    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        //ShowWhathwerInfo showWhathwerInfo = new ShowWhathwerInfo(this); //callbacktask a alındı


       // List<Weather> weathers = showWhathwerInfo.getinfo("akdeniz"); //callbacktask a alındı



        //Log.i("onItemClick",":buraya geldimi");

            ListView listView = (ListView) findViewById(R.id.listview);

            listView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
       // setContentView(R.layout.info);

        Weather weather = (Weather) adapterView.getItemAtPosition(position);
        Toast.makeText(this, "this is on click event", Toast.LENGTH_SHORT).show();
        Intent appInfo = new Intent(Akdeniz.this, info.class);

        appInfo.putExtra("city",weather.location.getCity());


        appInfo.putExtra("minTemp",weather.temperature.getMinTemp());
        appInfo.putExtra("maxTemp",weather.temperature.getMaxTemp());

        appInfo.putExtra("windSpeed",weather.wind.getSpeed());
        appInfo.putExtra("humidity",weather.currentCondition.getHumidity());
        appInfo.putExtra("pressure",weather.currentCondition.getPressure());

        appInfo.putExtra("sunSet",weather.location.getSunset());
        appInfo.putExtra("sunRise",weather.location.getSunrise());

        appInfo.putExtra("temp",weather.temperature.getTemp());
        appInfo.putExtra("weatherIcon",weather.iconData);

        appInfo.putExtra("conditions",weather.currentCondition.getCondition());


        startActivity(appInfo);
    }

    public class CallBackTask extends AsyncTask<String,Void,List<Weather>>{

        @Override
        protected List<Weather> doInBackground(String... params) {

            ShowWhathwerInfo showWhathwerInfo = new ShowWhathwerInfo(Akdeniz.this);
            List<Weather> weathers = showWhathwerInfo.getinfo("akdeniz");


            return weathers;
        }

        @Override
        protected void onPostExecute(List<Weather> weathers) {

            ListAdapter Adapter = new ListAdapter(Akdeniz.this, R.layout.list_row, weathers);
            listView.setAdapter(Adapter);
        }
    }


/*
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "this is on click event", Toast.LENGTH_SHORT).show();
        Intent appInfo = new Intent(Akdeniz.this, info.class);
        startActivity(appInfo);

    }
*/
}
