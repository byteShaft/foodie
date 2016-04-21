package com.byteshaft.foodie.fragments;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.byteshaft.foodie.R;
import com.byteshaft.foodie.activities.MainActivity;
import com.byteshaft.foodie.utils.AppGlobals;
import com.byteshaft.foodie.utils.Helpers;
import com.byteshaft.foodie.utils.MultiPartUtility;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UploadFragment extends Fragment implements View.OnClickListener {

    private String imageEncoded;
    private List<String> imagesEncodedList;

    private View mBaseView;
    private Button selectImage;
    private static final int PICK_IMAGE_MULTIPLE = 1;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;
    private Button upload;
    private ArrayList<Uri> mArrayUri;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_upload, container, false);
        selectImage = (Button) mBaseView.findViewById(R.id.select_image);
        upload = (Button) mBaseView.findViewById(R.id.upload);
        upload.setOnClickListener(this);
        selectImage.setOnClickListener(this);
        return mBaseView;
    }

    private void showPictures(ArrayList<String> imagesUrls) {
        int value = 0;
        for (String url : imagesUrls) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setPadding(2, 2, 2, 2);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            value++;
            imageView.setTag(value);
//            Picasso.with(SelectedAdDetail.this)
//                    .load(url)
//                    .resize(150, 150)
//                    .into(imageView);
//            layout.addView(imageView);
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    System.out.println(v.getTag());
//                    Intent intent = new Intent(getApplicationContext(), productImageView.getClass());
//                    intent.putExtra("url", imagesUrls.get((Integer) v.getTag() - 1));
//                    startActivity(intent);
                }
//            });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_image:
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                } else {
                    openPictures();
                }
                break;
            case R.id.upload:
                new UploadTask().execute();
                break;

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("TAG", "Permission granted");
                    openPictures();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Permission denied!"
                            , Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void openPictures() {
        if (Build.VERSION.SDK_INT <19){
            Intent intent = new Intent();
            intent.setType("image/jpeg");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "pictures"),PICK_IMAGE_MULTIPLE);
        } else {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == MainActivity.RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                imagesEncodedList = new ArrayList<>();
                if(data.getData()!= null){

                    Uri mImageUri = data.getData();

                    // Get the cursor
                    Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded  = cursor.getString(columnIndex);
                    cursor.close();

                }else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        mArrayUri = new ArrayList<>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
//                            final int takeFlags = data.getFlags()
//                                    & (Intent.FLAG_GRANT_READ_URI_PERMISSION
//                                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                            // Check for the freshest data.
//                            getActivity().getApplicationContext().getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            System.out.println(getRealPathFromURI(getActivity().getApplicationContext(), uri));
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded  = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri);
                    }
                }
            } else {
                Toast.makeText(getActivity(), "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == SELECT_PICTURE ) {
//            System.out.println(data);
//        }
//        if (Intent.ACTION_SEND_MULTIPLE.equals(data.getAction())
//                && data.hasExtra(Intent.EXTRA_STREAM)) {
//            // retrieve a collection of selected images
//            ArrayList<Parcelable> list = data.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
//            // iterate over these images
//            if( list != null ) {
//                for (Parcelable parcel : list) {
//                    Uri uri = (Uri) parcel;
//                    System.out.println(uri);
//                    System.out.println(data + "selected images");
//                }
//            }
//        }
    }

    class UploadTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            if (Helpers.isNetworkAvailable() && Helpers.isInternetWorking()) {
                try {
                    MultiPartUtility multiPartUtility = new MultiPartUtility(new URL(AppGlobals.SEND_IMAGES_URL));
                    multiPartUtility.addFilePart("file", new File("sdcard/img1.png"));
                    String string = multiPartUtility.finish();
                    System.out.println(string);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
