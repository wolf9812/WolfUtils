package com.fangzheng.wolfutils;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.requestFocus();
        textView.setAllCaps(true);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"MONACO.TTF");
        textView.setTypeface(typeface);
    }
}
