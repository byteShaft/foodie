package com.byteshaft.foodie.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

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

    private View baseView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_image, container, false);
        baseView = inflater.inflate(R.layout.fragment_image, container, false);
        gridView = (GridView) mBaseView.findViewById(R.id.images_grid);
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
                convertView = layoutInflater.inflate(R.layout.single_image_delegate, parent, false);
                holder.imageView = (ImageView) convertView.findViewById(R.id.single_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            System.out.println(holder.imageView == null);
            System.out.println(position);
            holder.imageView.setImageResource(images[position]);
            return convertView;
        }
    }

    public class ViewHolder {
        ImageView imageView;
    }


    class GetImagesTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
//            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
