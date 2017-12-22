package com.bigame.havadurumu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.bigame.havadurumu.model.Location;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by burakisik on 8/21/2017.
 */

public class WeatherHttpClient {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String IMG_URL = "http://openweathermap.org/img/w/";


    public String getWeatherData(String location){
        HttpURLConnection con = null ;
        InputStream is = null;

        try{
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+ location+"&APPID=ea574594b9d36ab688642d5fbeab847e"); //7a150e3afad6aca85901b0f7212a000d

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuffer json = new StringBuffer(1024);
        String tmp = "";

        while((tmp = reader.readLine()) != null)
            json.append(tmp).append("\n");
        reader.close();

        JSONObject data = new JSONObject(json.toString());

        if(data.getInt("cod") != 200) {
            System.out.println("Cancelled");
            return null;
        }
            if(data!=null){
                Log.d("my weather received",data.toString());
            }
            return  data.toString();

    }
    catch (Exception e) {

        System.out.println("Exception "+ e.getMessage());
        return null;
    }
  }

    public Bitmap getImage(String code) {  //byte[]
        HttpURLConnection con = null ;
        InputStream is = null;
        try {

            /*
            con = (HttpURLConnection) ( new URL(IMG_URL + code +".png")).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            is = con.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while ( is.read(buffer) != -1)
                baos.write(buffer);
          */

            Bitmap bitmap;

            URL url = new URL(IMG_URL + code +".png");  //http://openweathermap.org/img/w/13d.png
            URLConnection conn = url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());


            return bitmap;//baos.toByteArray();
        }
        catch(Throwable t) {
            t.printStackTrace();
            Log.i("my weather received","resim" );
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }
}

