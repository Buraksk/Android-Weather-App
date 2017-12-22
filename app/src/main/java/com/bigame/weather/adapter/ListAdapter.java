package com.bigame.havadurumu;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigame.havadurumu.model.Weather;

import java.util.List;

/**
 * Created by burakisik on 8/24/2017.
 */


//https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView

public class ListAdapter  extends ArrayAdapter<Weather> {

        private int layoutResource;


        public ListAdapter (Context context, int layoutResource, List<Weather> weather) {
            super(context, layoutResource, weather);
            this.layoutResource = layoutResource;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;

            if (view == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                view = layoutInflater.inflate(layoutResource, null);
            }

            Weather weather = getItem(position);

            if (weather != null) {
                TextView tvCityTextView = (TextView) view.findViewById(R.id.tvCity);
                TextView tvDegreeTextView = (TextView) view.findViewById(R.id.tvDegree);
                TextView tvConditionTextView = (TextView) view.findViewById(R.id.tvCondition);
                ImageView condIconImageView = (ImageView) view.findViewById(R.id.condIcon);



                if (tvCityTextView != null) {
                    tvCityTextView.setText(weather.location.getCity());
                }
                if (tvDegreeTextView != null) {
                    tvDegreeTextView .setText((int)weather.temperature.getTemp() +" °C");
                }
                if (tvConditionTextView != null) {
                    tvConditionTextView.setText(weather.currentCondition.getCondition());
                }

                if (condIconImageView != null) {

                    if (weather.iconData != null) {

                      //  Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);

                        condIconImageView.setImageBitmap(weather.iconData);
                    }
                }
            }

            return view;
        }
    }

