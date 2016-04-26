package com.byteshaft.foodie.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.byteshaft.foodie.R;
import com.byteshaft.foodie.utils.Helpers;

public class RegisterActivity extends Activity implements View.OnClickListener {

    private static RegisterActivity instance;
    private EditText userNameEdittext;
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView registerButton;
    private TextView loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userNameEdittext = (EditText) findViewById(R.id.username);
        emailEditText = (EditText) findViewById(R.id.email);
        passwordEditText = (EditText) findViewById(R.id.password);
        registerButton = (TextView) findViewById(R.id.register);
        loginButton = (TextView) findViewById(R.id.login);
        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public static RegisterActivity getInstance() {
        return instance;
    }

    public void closeApplication() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        startActivity(startMain);
        RegisterActivity.this.finish();
    }


    //
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                RegisterActivity.this.finish();
                break;
        }

    }

    class RegisterTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }
}
