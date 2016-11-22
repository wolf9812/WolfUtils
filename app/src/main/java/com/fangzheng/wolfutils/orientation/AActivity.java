package com.fangzheng.wolfutils.orientation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fangzheng.wolfutils.R;


public class AActivity extends AppCompatActivity {

    public static final int REQURST_CODE = 1;
    public static final int RESULT_OK = 2;
    public static final int RESULT_CANCEL = 3;

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        textView = (TextView) findViewById(R.id.text_view);
    }

    public void click(View view){
        Intent intent = new Intent(AActivity.this,BActivity.class);
        intent.putExtra("key","value");
        startActivityForResult(intent,REQURST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQURST_CODE){
            switch (resultCode){
                case RESULT_OK:
                    textView.setText(data.getStringExtra("key"));
                    break;
                case RESULT_CANCEL:
                    textView.setText(data.getStringExtra("key"));
                    break;
            }
        }
    }
}
