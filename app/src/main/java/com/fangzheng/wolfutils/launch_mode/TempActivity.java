package com.fangzheng.wolfutils.launch_mode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fangzheng.wolfutils.R;

public class TempActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
    }

    /**
     * single_top
     * @param view
     */
    public void singleTopWay(View view){
        Log.i("Activity","singleTopWay");
        Intent intent = new Intent(TempActivity.this,LaunchModeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
