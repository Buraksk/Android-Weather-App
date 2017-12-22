package com.bigame.havadurumu;

import com.bigame.havadurumu.iller.*;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by burakisik on 8/27/2017.
 */

public class TabMenuActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabmenu);

        TabHost tabh = (TabHost) findViewById(android.R.id.tabhost);

        TabHost.TabSpec IcAnadolu = tabh.newTabSpec("İç Anadolu");
        TabHost.TabSpec Marmara = tabh.newTabSpec("Marmara");
        TabHost.TabSpec Ege = tabh.newTabSpec("Ege");
        TabHost.TabSpec Akdeniz = tabh.newTabSpec("Akdeniz");
        TabHost.TabSpec Karadeniz = tabh.newTabSpec("Karadeniz");
        TabHost.TabSpec GuneyDoguAnadolu = tabh.newTabSpec("Güney Doğu Anadolu");
        TabHost.TabSpec DoguAnadolu = tabh.newTabSpec("Doğu Anadolu");


        IcAnadolu.setIndicator("İç Anadolu");
        IcAnadolu.setContent(new Intent(this,IcAnadolu.class)); 

        Marmara.setIndicator("Marmara");
        Marmara.setContent(new Intent(this,Marmara.class));

        Ege.setIndicator("Ege");
        Ege.setContent(new Intent(this,Ege.class));

        Akdeniz.setIndicator("Akdeniz");
        Akdeniz.setContent(new Intent(this,Akdeniz.class));

        Karadeniz.setIndicator("Karadeniz");
        Karadeniz.setContent(new Intent(this,Karadeniz.class));

        GuneyDoguAnadolu.setIndicator("Güney Doğu Anadolu");
        GuneyDoguAnadolu.setContent(new Intent(this,GuneyDoguAnadolu.class));

        DoguAnadolu.setIndicator("Doğu Anadolu");
        DoguAnadolu.setContent(new Intent(this,DoguAnadolu.class));


        tabh.addTab(IcAnadolu);
        tabh.addTab(Marmara);
        tabh.addTab(Ege);
        tabh.addTab(Akdeniz);
        tabh.addTab(Karadeniz);
        tabh.addTab(GuneyDoguAnadolu);
        tabh.addTab(DoguAnadolu);
    }
}
