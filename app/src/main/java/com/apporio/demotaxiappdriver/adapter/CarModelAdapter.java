package com.apporio.demotaxiappdriver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apporio.demotaxiappdriver.R;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.models.carmodels.CarModels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bhuvneshwar on 7/18/2016.
 */
public class CarModelAdapter extends BaseAdapter {

    Context context;
    CarModels carModels;

    LanguageManager languageManager;
    String language_id;

    public CarModelAdapter(Context context, CarModels carModels) {
        this.context = context;
        this.carModels = carModels;
        languageManager = new LanguageManager(context);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);
    }

    @Override
    public int getCount() {
        return carModels.getMsg().size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.itemlayoutforcarmodels, parent, false);
            myHolder = new MyHolder(convertView);
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }


        if (language_id.equals("1")) {
            myHolder.tv_car2.setText(carModels.getMsg().get(position).getCarModelName());
        } else if (language_id.equals("2")) {
            myHolder.tv_car2.setText(carModels.getMsg().get(position).getCarModelNameFrench());
        } else if (language_id.equals("3")) {
            myHolder.tv_car2.setText(carModels.getMsg().get(position).getCarModelNameArabic());
        }


        return convertView;
    }

    static class MyHolder {
        @Bind(R.id.tv_car2)
        TextView tv_car2;

        public MyHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
