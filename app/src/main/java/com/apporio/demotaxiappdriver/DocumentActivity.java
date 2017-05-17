package com.apporio.demotaxiappdriver;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.register.Register;
import com.apporio.demotaxiappdriver.others.ApiFetcher;

import com.apporio.demotaxiappdriver.logger.Logger;
import com.apporio.demotaxiappdriver.others.FirebaseUtils;
import com.apporio.demotaxiappdriver.others.ImageCompressMode;
import com.apporio.demotaxiappdriver.parsing.AccountModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class DocumentActivity extends AppCompatActivity implements ApiFetcher {
    LinearLayout ll_ok_docs, ll_back_docs;

    public static Activity documents;

    ImageView iv_insurance, iv_rc, iv_license;

    LanguageManager languageManager;
    String language_id;

    Uri selectedImage;
    Bitmap bitmap1;
    String insurance = "", licence = "", rc = "", other = "", imagePath = "", docFilePath = "", imagePathCompressed = "";

    String check = "1";
    String check1 = "0";
    int i = 0;

    String exten = "";
    SessionManager sessionManager;
    ProgressDialog pd;

    String driver_token;


    FirebaseUtils firebaseUtils ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseUtils = new FirebaseUtils(this);
        setContentView(R.layout.activity_document);
        getSupportActionBar().hide();
//        setStatusBarColor();
        documents = this;

        ll_ok_docs = (LinearLayout) findViewById(R.id.ll_ok_docs);
        ll_back_docs = (LinearLayout) findViewById(R.id.ll_back_docs);
        iv_insurance = (ImageView) findViewById(R.id.iv_insurance);
        iv_license = (ImageView) findViewById(R.id.iv_licence);
        iv_rc = (ImageView) findViewById(R.id.iv_rc);

        sessionManager = new SessionManager(this);

        driver_token = sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken);
        languageManager = new LanguageManager(this);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);


        pd = new ProgressDialog(this);
        pd.setMessage(DocumentActivity.this.getResources().getString(R.string.loading));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        ll_back_docs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i = 0;
                Logger.e("value of i        " + i + "");
                Intent i1 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i1.setType("image/*");
                startActivityForResult(i1, 101);
//                showDialog();
            }
        });

        iv_license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i = 1;
                Logger.e("value of i        " + i + "");
                Intent i1 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i1.setType("image/*");
                startActivityForResult(i1, 101);
//                showDialog();

            }
        });

        iv_rc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i = 2;
                Logger.e("value of i        " + i + "");
                Intent i1 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i1.setType("image/*");
                startActivityForResult(i1, 101);
//                showDialog();

            }
        });

//        ll_other_docs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                i = 3;
//                Logger.e("value of i        " + i + "");
//                showDialog();
//            }
//        });

        ll_ok_docs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (insurance.equals("")) {
                    Toast.makeText(DocumentActivity.this,DocumentActivity.this.getResources().getString(R.string.please_upload_insurance_copy), Toast.LENGTH_SHORT).show();
                } else if (licence.equals("")) {
                    Toast.makeText(DocumentActivity.this, DocumentActivity.this.getResources().getString(R.string.please_upload_copy), Toast.LENGTH_SHORT).show();
                } else if (rc.equals("")) {
                    Toast.makeText(DocumentActivity.this, DocumentActivity.this.getResources().getString(R.string.please_rc_copy), Toast.LENGTH_SHORT).show();
                } else {

                    AccountModule accountModule = new AccountModule(DocumentActivity.this);
                    accountModule.registerDocsApi(sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID), insurance, licence, rc, driver_token, language_id);


//                    Intent intent = new Intent();
//                    intent.putExtra("insurance", insurance);
//                    intent.putExtra("licence", licence);
//                    intent.putExtra("rc", rc);
//                    intent.putExtra("other", other);
//                    setResult(Activity.RESULT_OK, intent);
//                    finish();
                }
            }
        });
    }

    public void showDialog() {

        final Dialog dialog = new Dialog(DocumentActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_for_documents);

        LinearLayout yes = (LinearLayout) dialog.findViewById(R.id.ll_yes);
        LinearLayout no = (LinearLayout) dialog.findViewById(R.id.ll_cancel);
        final LinearLayout ll_image_selected = (LinearLayout) dialog.findViewById(R.id.ll_image_selected);
        final LinearLayout ll_image_unselected = (LinearLayout) dialog.findViewById(R.id.ll_image_unselected);
        final LinearLayout ll_pdf_unselected = (LinearLayout) dialog.findViewById(R.id.ll_pdf_unselected);
        final LinearLayout ll_pdf_selected = (LinearLayout) dialog.findViewById(R.id.ll_pdf_selected);

        ll_image_unselected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check = "1";
                check1 = "0";
                ll_pdf_unselected.setVisibility(View.VISIBLE);
                ll_pdf_selected.setVisibility(View.GONE);
                ll_image_selected.setVisibility(View.VISIBLE);
                ll_image_unselected.setVisibility(View.GONE);
            }
        });

        ll_pdf_unselected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check = "0";
                check1 = "1";
                ll_pdf_unselected.setVisibility(View.GONE);
                ll_pdf_selected.setVisibility(View.VISIBLE);
                ll_image_selected.setVisibility(View.GONE);
                ll_image_unselected.setVisibility(View.VISIBLE);
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.equals("1") && check1.equals("0")) {
                    dialog.dismiss();
                    Intent i1 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    i1.setType("image/*");
                    startActivityForResult(i1, 101);
                } else if (check.equals("0") && check1.equals("1")) {
                    dialog.dismiss();
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("application/msword,application/pdf");
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(intent, 102);
                }
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void onActivityResult(int req, int res, Intent data) {
        super.onActivityResult(req, res, data);
        if (res == Activity.RESULT_OK) {
            try {
                if (req == 101) {
                    selectedImage = data.getData();
                    imagePath = getPath(selectedImage);

                    ImageCompressMode imageCompressMode = new ImageCompressMode(this);
                    imagePathCompressed = imageCompressMode.compressImage(imagePath);

                    Logger.e("image Compreed        " + imagePathCompressed);

                    if (i == 0) {
                        insurance = imagePathCompressed;
                        Toast.makeText(DocumentActivity.this, DocumentActivity.this.getResources().getString(R.string.insurance_copy_saved), Toast.LENGTH_SHORT).show();
                    } else if (i == 1) {
                        licence = imagePathCompressed;
                        Toast.makeText(DocumentActivity.this,DocumentActivity.this.getResources().getString(R.string.lisence_copy_saved), Toast.LENGTH_SHORT).show();
                        Logger.e("licence         " + licence);
                    } else if (i == 2) {
                        rc = imagePathCompressed;
                        Toast.makeText(DocumentActivity.this, DocumentActivity.this.getResources().getString(R.string.RC_copy_saved), Toast.LENGTH_SHORT).show();
                        Logger.e("rc         " + rc);
                    }
//                    else if (i == 3) {
//                        other = imagePath;
//                        Toast.makeText(DocumentActivity.this, "Documents Copy Saved", Toast.LENGTH_SHORT).show();
//                        Logger.e("other         " + other);
//                    }

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

                    if (i == 0) {
                        iv_insurance.setImageBitmap(bitmap1);
                    } else if (i == 1) {
                        iv_license.setImageBitmap(bitmap1);
                    } else if (i == 2) {
                        iv_rc.setImageBitmap(bitmap1);
                    }
                }

                if (req == 102) {
                    Uri fileuri = data.getData();
                    docFilePath = getFileNameByUri(this, fileuri);
                    Logger.e("pdf path" + docFilePath);

//                    int i = docFilePath.lastIndexOf('.');
//                    // If the index position is greater than zero then get the substring.
//                    if (i > 0) {
//                        exten = docFilePath.substring(i + 1);
//                    }
//                    String mimeType = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(exten);
//                    mimeType = (mimeType == null) ? "*/*" : mimeType;
//                    Log.e("ex", exten);
//                    Log.e("mimetype", mimeType);

                    if (i == 0) {
                        insurance = docFilePath;
                        Toast.makeText(DocumentActivity.this, DocumentActivity.this.getResources().getString(R.string.insurance_copy_saved), Toast.LENGTH_SHORT).show();
                        Logger.e("insurance         " + insurance);
                    } else if (i == 1) {
                        licence = docFilePath;
                        Toast.makeText(DocumentActivity.this,DocumentActivity.this.getResources().getString(R.string.lisence_copy_saved), Toast.LENGTH_SHORT).show();
                        Logger.e("licence         " + licence);
                    } else if (i == 2) {
                        rc = docFilePath;
                        Toast.makeText(DocumentActivity.this, DocumentActivity.this.getResources().getString(R.string.RC_copy_saved), Toast.LENGTH_SHORT).show();
                        Logger.e("rc         " + rc);
                    } else if (i == 3) {
                        other = docFilePath;
                        Toast.makeText(DocumentActivity.this, DocumentActivity.this.getResources().getString(R.string.document_copy_saved), Toast.LENGTH_SHORT).show();
                        Logger.e("other         " + other);
                    }

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

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (resultCode == RESULT_OK) {
//
//            if (requestCode == SELECT_PDF) {
//                System.out.println("SELECT_PDF");
//                Uri selectedImageUri = data.getData();
//                selectedPath = getPath(selectedImageUri);
//                System.out.println("SELECT_AUDIO Path : " + selectedPath);
////                doFileUpload();
//            }
//        }
//    }
//
//    public String getPath(Uri uri) {
//        String[] projection = {MediaStore.Images.Media.DATA};
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        return cursor.getString(column_index);
//    }

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
        if (apiName.equals("Register Docs")) {
            Register register = new Register();
            register = gson.fromJson(response + "", Register.class);

            Log.d("****** REGISTER_DOC_RESPONSE " , ""+response);
            if (register.getResult().toString().equals("1")) {
                new SessionManager(this).createLoginSession(register.getDetails().getDriverId(),register.getDetails().getDriverName(),register.getDetails().getDriverPhone(),register.getDetails().getDriverEmail(),register.getDetails().getDriverImage(),register.getDetails().getDriverPassword(),register.getDetails().getDriverToken(),register.getDetails().getDeviceId(),Config.Devicetype,register.getDetails().getRating(),register.getDetails().getCarTypeId(),register.getDetails().getCarModelId(),
                        register.getDetails().getCarNumber(),register.getDetails().getCityId(),register.getDetails().getRegisterDate(),register.getDetails().getLicense(),register.getDetails().getRc(),register.getDetails().getInsurance(),"other_doc","getlast update","last update date ",register.getDetails().getCompletedRides(), register.getDetails().getRejectRides(),register.getDetails().getCancelledRides(),
                        register.getDetails().getLoginLogout(),register.getDetails().getBusy(),register.getDetails().getOnlineOffline(),register.getDetails().getDetailStatus(),register.getDetails().getStatus(),register.getDetails().getCarTypeName(),register.getDetails().getCarModelName());

//                firebaseUtils.setUpDriver(register);
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
            } else if (register.getResult().toString().equals("419")) {
                sessionManager.logoutUser();
                Intent intent = new Intent(this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
                Logger.e("lat long update       " + register.getMsg());
            } else {
                Toast.makeText(this, "" + register.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFetchFailed(ANError error) {

    }

    @Override
    public void WhichApi(String apiName) {

    }

    private String getFileNameByUri(Context context, Uri uri) {
        String filepath = "";//default fileName
        //Uri filePathUri = uri;
        File file;
        if (uri.getScheme().toString().compareTo("content") == 0) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{android.provider.MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.ORIENTATION}, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            cursor.moveToFirst();

            String mImagePath = cursor.getString(column_index);
            cursor.close();
            filepath = mImagePath;

        } else if (uri.getScheme().compareTo("file") == 0) {
            try {
                file = new File(new URI(uri.toString()));
                if (file.exists())
                    filepath = file.getAbsolutePath();

            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            filepath = uri.getPath();
        }
        return filepath;
    }
}
