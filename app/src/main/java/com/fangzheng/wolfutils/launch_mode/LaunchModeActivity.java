package com.fangzheng.wolfutils.launch_mode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fangzheng.wolfutils.R;

public class LaunchModeActivity extends AppCompatActivity {

    private static final String TAG = "Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode);
    }

    /**
     * new_task
     * @param view
     */
    public void singleNewTaskWay(View view){
        Log.i(TAG,"singleNewTaskWay");
        //必须结合使用，在AndroidManifest.xml中配置
        // <activity android:name="" android:taskAffinity="" />
        Intent intent = new Intent(LaunchModeActivity.this,LaunchModeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * single_top
     * @param view
     */
    public void singleTopWay(View view){
        Log.i(TAG,"singleTopWay");
        Intent intent = new Intent(LaunchModeActivity.this,TempActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG,"onNewIntent");
    }
}
