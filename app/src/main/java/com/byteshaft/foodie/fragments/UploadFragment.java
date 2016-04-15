package com.byteshaft.foodie.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
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

import java.util.ArrayList;

public class UploadFragment extends Fragment implements View.OnClickListener {

    private View mBaseView;
    private Button uploadButton;
    private static final int SELECT_PICTURE = 1;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_upload, container, false);
        uploadButton = (Button) mBaseView.findViewById(R.id.upload);
        uploadButton.setOnClickListener(this);
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
            case R.id.upload:
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                } else {
                    openPictures();
                }
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("TAG", "Permission granted");
                    openPictures();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Permission denied!"
                            , Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    private void openPictures() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Intent.ACTION_SEND_MULTIPLE.equals(data.getAction())
                && data.hasExtra(Intent.EXTRA_STREAM)) {
            // retrieve a collection of selected images
            ArrayList<Parcelable> list = data.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
            // iterate over these images
            if( list != null ) {
                for (Parcelable parcel : list) {
                    Uri uri = (Uri) parcel;
                    System.out.println(uri);
                }
            }
        }
    }
}
