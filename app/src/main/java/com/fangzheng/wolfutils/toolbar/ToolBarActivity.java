package com.fangzheng.wolfutils.toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.fangzheng.wolfutils.R;

import java.lang.reflect.Method;

public class ToolBarActivity extends AppCompatActivity {

    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        initActionBar();
    }

    /**
     * 初始化自定义的标题栏
     */
    public void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_second_toolbar);

        //设置标题
        toolbar.setTitle("回退");
        //设置logo
        toolbar.setLogo(R.drawable.toolbar_logo);

        setSupportActionBar(toolbar);

        //设置回退按钮
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    /**
     * 设置标题栏的菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setIconEnable(menu, true);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        //获取Action Provider:share
        MenuItem shareItem = menu.findItem(R.id.menu_share);
        //mShareActionProvider = (ShareActionProvider) shareItem.getActionProvider();
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        Intent intent = new Intent();
        intent.setType("image/*");
        mShareActionProvider.setShareIntent(intent);

        //获取Action View
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(ToolBarActivity.this, "提交文本：" + s, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Toast.makeText(ToolBarActivity.this, "当前文本：" + s, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Toast.makeText(this, "onOptionsItemSelected", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * enable为true时，菜单添加图标有效，enable为false时无效。4.0系统默认无效
     *
     * @param menu
     * @param enable
     */
    private void setIconEnable(Menu menu, boolean enable) {
        try {
            Class<?> clazz = Class.forName("android.support.v7.view.menu.MenuBuilder");
            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);

            //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
            m.invoke(menu, enable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
