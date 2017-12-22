package com.bigame.havadurumu;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.bigame.havadurumu.model.Weather;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;


public class ShowWhathwerInfo {
    ArrayList<String> iller;
    private Context context;

    public ShowWhathwerInfo(Context current) {
        this.context = current;
    }

    public List<Weather> getinfo(String fileName) {

        final List<Weather> weathers = new ArrayList<>();

        final ArrayList<String> iller = new ArrayList<String>();

        InputStream inputStream = null;
        if (fileName.equals("akdeniz"))
            inputStream = context.getResources().openRawResource(R.raw.akdeniz);
        else if (fileName.equals("doguanadolu"))
            inputStream = context.getResources().openRawResource(R.raw.doguanadolu);
        else if (fileName.equals("ege"))
            inputStream = context.getResources().openRawResource(R.raw.ege);
        else if (fileName.equals("guneydoguanadolu"))
            inputStream = context.getResources().openRawResource(R.raw.guneydoguanadolu);
        else if (fileName.equals("icanadolu"))
            inputStream = context.getResources().openRawResource(R.raw.icanadolu);
        else if (fileName.equals("karadeniz"))
            inputStream = context.getResources().openRawResource(R.raw.karadeniz);
        else if (fileName.equals("marmara"))
            inputStream = context.getResources().openRawResource(R.raw.marmara);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line = "";

        try {
            while ((line = buffreader.readLine()) != null) {
                iller.add(line);
            }
        } catch (Exception e) {
            Log.e("eror", String.valueOf(e));
        }

        for (int i = 0; i < iller.size(); i++) {
            Service service = new Service(iller.get(i));
            Weather weather = Service.weather;
            weathers.add(weather);
            Log.i(TAG, "getinfo: "+"hala burdaaaaaaa" );
        }

        return weathers;
    }
}


