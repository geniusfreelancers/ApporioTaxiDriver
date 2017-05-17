package com.apporio.demotaxiappdriver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apporio.demotaxiappdriver.R;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.models.cancelreasoncustomer.CancelReasonCustomer;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReasonAdapter extends BaseAdapter {
    Context context;
    CancelReasonCustomer cancelReasonCustomer;

    LanguageManager languageManager;
    String language_id;

    public ReasonAdapter(Context context, CancelReasonCustomer cancelReasonCustomer) {
        this.context = context;
        this.cancelReasonCustomer = cancelReasonCustomer;
        languageManager = new LanguageManager(context);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);
    }

    @Override
    public int getCount() {
        return cancelReasonCustomer.getMsg().size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.itemlayoutforreasons, parent, false);
            myHolder = new MyHolder(convertView);
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }

        if (language_id.equals("1")) {
            myHolder.tv_reason.setText(cancelReasonCustomer.getMsg().get(position).getReasonName());
        } else if (language_id.equals("2")) {
            myHolder.tv_reason.setText(cancelReasonCustomer.getMsg().get(position).getReasonNameFrench());
        } else if (language_id.equals("3")) {
            myHolder.tv_reason.setText(cancelReasonCustomer.getMsg().get(position).getReasonNameArabic());
        }

        return convertView;
    }

    static class MyHolder {
        @Bind(R.id.tv_reason)
        TextView tv_reason;

        public MyHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
