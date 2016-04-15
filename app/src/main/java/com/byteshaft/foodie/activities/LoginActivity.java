package com.byteshaft.foodie.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import com.byteshaft.foodie.R;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    class LoginTask extends AsyncTask<String, String , String> {

        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }
}
