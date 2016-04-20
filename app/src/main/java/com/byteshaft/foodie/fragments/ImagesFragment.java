package com.byteshaft.foodie.fragments;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.byteshaft.foodie.R;

public class ImagesFragment extends Fragment {

    private View mBaseView;
    private GridView gridView;
    public int[] images = {
//            R.drawable.pape,
//            R.drawable.chat_50,
//            R.drawable.checkmark_52,
//            R.drawable.info_52
    };

    private  View baseView;
    private  ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        mBaseView = inflater.inflate(R.layout.fragment_image_delegate, container, false);
        baseView = inflater.inflate(R.layout.fragment_image, container, false);
        progressBar = (ProgressBar) baseView.findViewById(R.id.progress_bar);
//        gridView = (GridView) mBaseView.findViewById(R.id.gridView);
        ImageAdapter imageAdapter = new ImageAdapter();
//        gridView.setAdapter(imageAdapter);
        return mBaseView;
    }

    class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return images[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
//                convertView = layoutInflater.inflate(R.layout.image_fragment, parent, false);
                holder.imageView = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            System.out.println( holder.imageView ==null);
            System.out.println(position);
            holder.imageView.setImageResource(images[position]);
            return convertView;
        }
    }

    public class ViewHolder {
        ImageView imageView;
    }


    class GetJsonData extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
