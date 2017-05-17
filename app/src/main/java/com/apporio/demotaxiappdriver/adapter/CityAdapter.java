package com.apporio.demotaxiappdriver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apporio.demotaxiappdriver.R;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.models.viewcity.ViewCity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CityAdapter extends BaseAdapter {
    Context context;
    ViewCity viewCity;
    LanguageManager languageManager;
    String language_id;

    public CityAdapter(Context context, ViewCity viewCity) {
        this.context = context;
        this.viewCity = viewCity;
        languageManager = new LanguageManager(context);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);
    }

    @Override
    public int getCount() {
        return viewCity.getMsg().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.itemlayoutforcities, parent, false);
            myHolder = new MyHolder(convertView);
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }

        if (language_id.equals("1")) {
            myHolder.tv_city1.setText(viewCity.getMsg().get(position).getCityName());
        } else if (language_id.equals("2")) {
            myHolder.tv_city1.setText(viewCity.getMsg().get(position).getCityNameFrench());
        } else if (language_id.equals("3")) {
            myHolder.tv_city1.setText(viewCity.getMsg().get(position).getCityNameArabic());
        }
        return convertView;
    }

    static class MyHolder {
        @Bind(R.id.tv_city1)
        TextView tv_city1;

        public MyHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
