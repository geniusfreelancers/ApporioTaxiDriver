package com.apporio.demotaxiappdriver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apporio.demotaxiappdriver.R;
import com.apporio.demotaxiappdriver.models.earnings.Earnings;

public class EarningAdapter extends BaseAdapter {

    Context context;
    Earnings earnings;

    public EarningAdapter(Context context, Earnings earnings) {
        this.context = context;
        this.earnings = earnings;
    }

    @Override
    public int getCount() {
        return earnings.getMsg().size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.itemlayoutforearnings, parent, false);
            holder = new Holder();
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.tv_rides = (TextView) convertView.findViewById(R.id.tv_rides);
        holder.tv_earning = (TextView) convertView.findViewById(R.id.tv_earnings);
        holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);

        holder.tv_date.setText(earnings.getMsg().get(position).getRideDate().toString());
        holder.tv_rides.setText(earnings.getMsg().get(position).getRides().toString());
        holder.tv_earning.setText(  earnings.getMsg().get(position).getAmount().toString()+" "+context.getResources().getString(R.string.currency_symbol));

        return convertView;
    }

    public static class Holder {
        TextView tv_rides, tv_date, tv_earning;
    }
}
