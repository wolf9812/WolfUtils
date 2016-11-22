package com.fangzheng.wolfutils.orientation;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fangzheng.wolfutils.R;

public class BActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Log.i("msg","create");
    }

    public void sureOK(View view){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void sureCancel(View view){
        Intent intent = new Intent();
        intent.putExtra("key","cancel");
        setResult(AActivity.RESULT_CANCEL,intent);

        finish();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.i("msg","change");
        super.onConfigurationChanged(newConfig);

    }


}
