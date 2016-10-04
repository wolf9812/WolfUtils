package com.fangzheng.wolfutils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fangzheng.wolfutils.layout.FrameLayoutDemoActivity;
import com.fangzheng.wolfutils.layout.RelativeLayoutActivity;
import com.fangzheng.wolfutils.toolbar.ToolBarActivity;
import com.fangzheng.wolfutils.widget.AutoCompleteTextViewActivity;


public class MainActivity extends AppCompatActivity {

    private ListView mListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar();

        mListView = (ListView) findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
        adapter.add("Toolbar activity");
        adapter.add("Relative layout activity");
        adapter.add("Frame layout activity");

        adapter.add("Common widget");

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, ToolBarActivity.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, RelativeLayoutActivity.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, FrameLayoutDemoActivity.class);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, AutoCompleteTextViewActivity.class);
                        break;
                    default:
                        break;
                }
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化标题栏
     */
    public void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        toolbar.setTitle("首页");

        setSupportActionBar(toolbar);
    }

}
