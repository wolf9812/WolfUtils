package com.fangzheng.wolfutils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fangzheng.wolfutils.launch_mode.LaunchModeActivity;
import com.fangzheng.wolfutils.layout.FrameLayoutDemoActivity;
import com.fangzheng.wolfutils.layout.RelativeLayoutActivity;
import com.fangzheng.wolfutils.listview.ListViewActivity;
import com.fangzheng.wolfutils.media.AudioActivity;
import com.fangzheng.wolfutils.permission.PermissionApplyActivity;
import com.fangzheng.wolfutils.orientation.AActivity;
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
        String[] titles = {
                "Test activity","Toolbar activity","Relative layout activity",
                "Frame layout activity","Common widget","Media audio activity",
                "android 6.0 权限检测与动态申请","方向变化","activity四种启动模式",
                "list view"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,titles);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this,TestActivity.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, ToolBarActivity.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, RelativeLayoutActivity.class);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, FrameLayoutDemoActivity.class);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, AutoCompleteTextViewActivity.class);
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, AudioActivity.class);
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this,PermissionApplyActivity.class);
                        break;
                    case 7:
                        intent = new Intent(MainActivity.this, AActivity.class);
                        break;
                    case 8:
                        intent = new Intent(MainActivity.this, LaunchModeActivity.class);
                        break;
                    case 9:
                        intent = new Intent(MainActivity.this, ListViewActivity.class);
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
