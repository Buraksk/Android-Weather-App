package com.bigame.havadurumu.iller;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;

import com.bigame.havadurumu.ListAdapter;
import com.bigame.havadurumu.R;
import com.bigame.havadurumu.Service;
import com.bigame.havadurumu.ShowWhathwerInfo;
import com.bigame.havadurumu.model.Weather;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by burakisik on 8/27/2017.
 */

public class Marmara extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        ShowWhathwerInfo showWhathwerInfo = new ShowWhathwerInfo(this);

        List<Weather> weathers = showWhathwerInfo.getinfo("marmara");

        ListView listView = (ListView)findViewById(R.id.listview);
        ListAdapter Adapter = new ListAdapter(this, R.layout.list_row, weathers );
        listView.setAdapter(Adapter);

    }
}
