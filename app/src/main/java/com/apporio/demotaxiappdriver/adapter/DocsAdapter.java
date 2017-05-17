package com.apporio.demotaxiappdriver.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.R;
import com.apporio.demotaxiappdriver.models.document_type.DocumentType;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.others.ForImage;

public class DocsAdapter extends BaseAdapter implements ApiFetcher,ForImage {

    Context context;
    DocumentType documentType;

    public DocsAdapter(Context context, DocumentType documentType) {
        this.context = context;
        this.documentType = documentType;
    }

    @Override
    public int getCount() {
        return documentType.getMsg().size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.itemlayoutfordocs, parent, false);
            holder = new Holder();
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tv_docs_name = (TextView) convertView.findViewById(R.id.tv_docs_name);
        holder.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
        holder.tv_docs_name.setText(documentType.getMsg().get(position).getDocumentName().toString());
        holder.iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(context instanceof DocsActivity){
//                    ((DocsActivity)context).yourDesiredMethod();
//                }
                Intent i1 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i1.setType("image/*");
                ((Activity) context).startActivityForResult(i1, 101);
            }
        });

        return convertView;
    }

    @Override
    public void onAPIRunningState(int a) {

    }

    @Override
    public void onFetchProgress(int progress) {

    }

    @Override
    public void onFetchComplete(String response, String apiName) {

    }

    @Override
    public void onFetchFailed(ANError error) {

    }

    @Override
    public void WhichApi(String apiName) {

    }

    @Override
    public void onSelectedImage(int a) {

    }

    public static class Holder {
        TextView tv_docs_name;
        ImageView iv_image;
    }
}