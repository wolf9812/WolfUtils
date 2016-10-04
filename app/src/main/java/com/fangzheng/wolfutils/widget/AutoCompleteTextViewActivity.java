package com.fangzheng.wolfutils.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.fangzheng.wolfutils.R;
import com.fangzheng.wolfutils.view.ClearEditText;

public class AutoCompleteTextViewActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;

    private ClearEditText clearEditText;

    private Button loginButton;

    String[] arr = {"abc", "Paries,France", "PA,United States","Parana,Brazil", "Padua,Italy", "Pasadena,CA,United States"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);

        //设置提示内容
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,arr);
        autoCompleteTextView.setAdapter(adapter);

        //在弹出菜单的最底下有个提示
        autoCompleteTextView.setCompletionHint("按上下键选择");

        //这个是确定弹出框在哪个View 下显示，如果不设置就在本View即AutoCompleteTextView下
        //autoCompleteTextView.setDropDownAnchor(R.id.textView2);


        clearEditText = (ClearEditText) findViewById(R.id.clear_edit_view);
        clearEditText.setAdapter(adapter);
        //clearEditText.setClearIcon(getResources().getDrawable(R.drawable.settings));


        //login button
        loginButton = (Button)findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(clearEditText.getText())){
                    Toast.makeText(AutoCompleteTextViewActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                    clearEditText.setShakeAnimation(5);
                }

                if(TextUtils.isEmpty(clearEditText.getText())){
                    Toast.makeText(AutoCompleteTextViewActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                    clearEditText.setShakeAnimation(5);
                }
            }
        });
    }
}
