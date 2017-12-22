package com.bigame.havadurumu;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bigame.havadurumu.iller.Akdeniz;

/**
 * Created by burakisik on 8/28/2017.
 */

public class NavigationMenuActivity extends Activity {

    private DrawerLayout mDrawerLayout;

 
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
   
    private String mTitle = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_navigation_menu);

       
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        FragmentHome fragmentHome = new FragmentHome();
        ft.add(R.id.content_frame, fragmentHome);
        ft.commit();

        mTitle = "Geleceği Yazanlar";
    
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerList = (ListView) findViewById(R.id.drawer_list);

        
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open,
                R.string.drawer_close) {

          
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
    }

           
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle("Navigation Drawer GY");
                invalidateOptionsMenu();
            }

        };


        mDrawerLayout.setDrawerListener(mDrawerToggle);

        String [] arr = {"araba","ev","şehir"};
       
        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(),
                R.layout.activity_navigation_menu_listrow, arr);

  
        mDrawerList.setAdapter(adapter);

      
        getActionBar().setHomeButtonEnabled(true);

        
        getActionBar().setDisplayHomeAsUpEnabled(true);

   
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {

         
            String[] menuItems ={"araba","ev","şehir"}; 

           
            mTitle = menuItems[position];

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
      
            if(position==0){
                FragmentHome fragmentHome = new FragmentHome();
                ft.replace(R.id.content_frame, fragmentHome);
                ft.commit();
            }
            
            mDrawerLayout.closeDrawer(mDrawerList);

        }
    });


}
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
      
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
