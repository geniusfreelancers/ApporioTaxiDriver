package com.apporio.demotaxiappdriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.adapter.DocsAdapter;
import com.apporio.demotaxiappdriver.logger.Logger;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.models.ResultCheck;
import com.apporio.demotaxiappdriver.models.document_type.DocumentType;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DocsActivity extends AppCompatActivity implements ApiFetcher {

    ListView lv_documents;
    ProgressDialog pd;

    String docsCheck = "";

    DocumentType documentType;

    LanguageManager languageManager;
    String language_id;

    Uri selectedImage;
    Bitmap bitmap1;
    String imagePath="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docs);
        getSupportActionBar().hide();

        lv_documents = (ListView) findViewById(R.id.lv_documents);
        pd = new ProgressDialog(this);
        pd.setMessage(DocsActivity.this.getResources().getString(R.string.loading));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

//        ViewModule viewModule = new ViewModule(this);
//        viewModule.viewDocsApi();


    }

    @Override
    public void onAPIRunningState(int a) {

        if (a == ApiFetcher.KEY_API_IS_RUNNING) {
            pd.show();
        }
        if (a == ApiFetcher.KEY_API_IS_STOPPED) {
            pd.dismiss();
        }
    }

    @Override
    public void onFetchProgress(int progress) {

    }

    @Override
    public void onFetchComplete(String response, String apiName) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        if (apiName.equals("View Document")) {
            ResultCheck resultCheck;
            resultCheck = gson.fromJson(response, ResultCheck.class);
            if (resultCheck.result.toString().equals("1")) {
                docsCheck = "1";
                documentType = gson.fromJson(response, DocumentType.class);
                lv_documents.setAdapter(new DocsAdapter(this, documentType));
            } else {
                docsCheck = "2";
            }
        }
    }

    @Override
    public void onFetchFailed(ANError error) {

    }

    @Override
    public void WhichApi(String apiName) {

    }

    public void yourDesiredMethod() {
        Intent i1 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i1.setType("image/*");
        startActivityForResult(i1, 101);
    }

    public void onActivityResult(int req, int res, Intent data) {
        super.onActivityResult(req, res, data);
        if (res == Activity.RESULT_OK) {
            try {
                if (req == 101) {
                    selectedImage = data.getData();
                    imagePath = getPath(selectedImage);

                    Logger.e("imagepath         "+imagePath);

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    // Set the Image in ImageView after decoding the String
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(filePath, options);
                    final int REQUIRED_SIZE = 300;
                    int scale = 1;
                    while (options.outWidth / scale / 2 >= REQUIRED_SIZE && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                        scale *= 2;
                    options.inSampleSize = scale;
                    options.inJustDecodeBounds = false;
                    bitmap1 = BitmapFactory.decodeFile(filePath, options);

                }
            } catch (Exception e) {
                Logger.e("res         " + e.toString());
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
