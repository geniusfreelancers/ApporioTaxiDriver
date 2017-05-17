package com.apporio.demotaxiappdriver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apporio.demotaxiappdriver.R;
import com.apporio.demotaxiappdriver.models.viewrides.ViewRides;

public class RidesAdapter extends BaseAdapter {

    Context context;
    ViewRides viewRides;

    public RidesAdapter(Context context, ViewRides viewRides) {
        this.context = context;
        this.viewRides = viewRides;
    }

    @Override
    public int getCount() {
        return viewRides.getMsg().size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.rides_item_new, parent, false);
            holder = new Holder();
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.tv_pickup_addresss = (TextView) convertView.findViewById(R.id.tv_pickup_addresss);
        holder.tv_drop_addresss = (TextView) convertView.findViewById(R.id.tv_drop_addresss);
        holder.tv_date_time = (TextView) convertView.findViewById(R.id.tv_date_time);
        holder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
        holder.customer_name_txt = (TextView) convertView.findViewById(R.id.customer_name_txt);
        holder.customer_phone_txt = (TextView) convertView.findViewById(R.id.customer_phone_txt);


        holder.tv_date_time.setText(viewRides.getMsg().get(position).getRideDate().toString() + ", " + viewRides.getMsg().get(position).getRideTime().toString());
        holder.customer_name_txt.setText(""+viewRides.getMsg().get(position).getUser_name());
        holder.customer_phone_txt.setText(""+viewRides.getMsg().get(position).getUser_phone());

        if (viewRides.getMsg().get(position).getRideStatus().toString().equals("2")) {
            holder.tv_status.setText("");
            holder.tv_pickup_addresss.setText(viewRides.getMsg().get(position).getPickupLocation().trim());
            holder.tv_drop_addresss.setText(viewRides.getMsg().get(position).getDropLocation().trim());

        } else if (viewRides.getMsg().get(position).getRideStatus().toString().equals("1")) {
            holder.tv_status.setText(""+context.getResources().getString(R.string.new_ride));
            holder.tv_pickup_addresss.setText(viewRides.getMsg().get(position).getPickupLocation().trim());
            holder.tv_drop_addresss.setText(viewRides.getMsg().get(position).getDropLocation().trim());

        } else if (viewRides.getMsg().get(position).getRideStatus().toString().equals("3")) {
            holder.tv_status.setText(""+context.getResources().getString(R.string.accpted));
            holder.tv_pickup_addresss.setText(viewRides.getMsg().get(position).getPickupLocation().trim());
            holder.tv_drop_addresss.setText(viewRides.getMsg().get(position).getDropLocation().trim());

        } else if (viewRides.getMsg().get(position).getRideStatus().toString().equals("4")) {
            holder.tv_status.setText(""+context.getResources().getString(R.string.rejected));
            holder.tv_pickup_addresss.setText(viewRides.getMsg().get(position).getPickupLocation().trim());
            holder.tv_drop_addresss.setText(viewRides.getMsg().get(position).getDropLocation().trim());

        } else if (viewRides.getMsg().get(position).getRideStatus().toString().equals("5")) {
            holder.tv_status.setText(""+context.getResources().getString(R.string.reached));
            holder.tv_pickup_addresss.setText(viewRides.getMsg().get(position).getPickupLocation().trim());
            holder.tv_drop_addresss.setText(viewRides.getMsg().get(position).getDropLocation().trim());

        } else if (viewRides.getMsg().get(position).getRideStatus().toString().equals("6")) {
            holder.tv_status.setText(""+context.getResources().getString(R.string.ongoing));
            holder.tv_pickup_addresss.setText(viewRides.getMsg().get(position).getBeginLocation().trim());
            holder.tv_drop_addresss.setText(viewRides.getMsg().get(position).getDropLocation().trim());

        } else if (viewRides.getMsg().get(position).getRideStatus().toString().equals("7")) {
            holder.tv_status.setText("" + viewRides.getMsg().get(position).getAmount()+" "+context.getResources().getString(R.string.currency_symbol));
            holder.tv_pickup_addresss.setText(viewRides.getMsg().get(position).getBeginLocation().trim());
            holder.tv_drop_addresss.setText(viewRides.getMsg().get(position).getEndLocation().trim());

        } else if (viewRides.getMsg().get(position).getRideStatus().toString().equals("8")) {
            holder.tv_status.setText(""+context.getResources().getString(R.string.new_ride));
            holder.tv_pickup_addresss.setText(viewRides.getMsg().get(position).getPickupLocation().trim());
            holder.tv_drop_addresss.setText(viewRides.getMsg().get(position).getDropLocation().trim());

        } else if (viewRides.getMsg().get(position).getRideStatus().toString().equals("9")) {
            holder.tv_status.setText(""+context.getResources().getString(R.string.cancel_by_driver));
            holder.tv_pickup_addresss.setText(viewRides.getMsg().get(position).getPickupLocation().trim());
            holder.tv_drop_addresss.setText(viewRides.getMsg().get(position).getDropLocation().trim());

        }
        return convertView;
    }

    public static class Holder {
        public static TextView tv_date_time, tv_status, tv_pickup_addresss, tv_drop_addresss,  customer_name_txt,  customer_phone_txt;
    }
}