package com.apporio.demotaxiappdriver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apporio.demotaxiappdriver.R;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.models.viewcartype.ViewCarType;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bhuvneshwar on 7/18/2016.
 */
public class CarAdapter extends BaseAdapter {

    Context context;
    ViewCarType viewCarType;

    LanguageManager languageManager;
    String language_id;

    public CarAdapter(Context context, ViewCarType viewCarType) {
        this.context = context;
        this.viewCarType = viewCarType;
        languageManager = new LanguageManager(context);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);
    }

    @Override
    public int getCount() {
        return viewCarType.getMsg().size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.itemlayoutforcars, parent, false);
            myHolder = new MyHolder(convertView);
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }

        if (language_id.equals("1")) {
            myHolder.tv_car1.setText(viewCarType.getMsg().get(position).getCarTypeName());
        } else if (language_id.equals("2")) {
            myHolder.tv_car1.setText(viewCarType.getMsg().get(position).getCarTypeNameFrench());
        } else if (language_id.equals("3")) {
            myHolder.tv_car1.setText(viewCarType.getMsg().get(position).getCarNameArabic());
        }

        return convertView;
    }

    static class MyHolder {
        @Bind(R.id.tv_car1)
        TextView tv_car1;

        public MyHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
