package com.fangzheng.wolfutils.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.fangzheng.wolfutils.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private List<Object> datas;
    private MessageAdapter messageAdapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        listView = (ListView) findViewById(R.id.list_view);

        datas = new ArrayList<>();
        int count = 0;
        for(int i=0;i<100;i++){
            /*if(i%2==0){
                datas.add(new TextMessage("消息:"+count+"  kkk"));
                count++;

                Log.i("tag",count+"");
            }else{
                datas.add(new ImageMessage(R.drawable.shishi));
            }*/

            datas.add(new TextMessage("消息:"+i));
        }

        messageAdapter = new MessageAdapter(datas);

        listView.setAdapter(messageAdapter);
    }

    public void click(View view){
        String operationHint = "";
        EditText modifyText = (EditText) findViewById(R.id.modify_text);
        int index;
        try {
            index = Integer.parseInt(modifyText.getText().toString());

        }catch (Exception e){
            e.printStackTrace();
            operationHint = "数据不合法";
            Toast.makeText(this,operationHint,Toast.LENGTH_SHORT).show();
            return;
        }
        if(index<0||index>=datas.size()){
            operationHint = "数据不合法";
            Toast.makeText(this,operationHint,Toast.LENGTH_SHORT).show();
            return;
        }


        switch (view.getId()){
            case R.id.btn_modify:
                TextMessage textMessage = (TextMessage) datas.get(index);
                textMessage.setText("修改过的信息");
                //messageAdapter.notifyDataSetChanged();
                partialRefresh(listView,index);
                operationHint = "修改成功！";
                break;
            case R.id.btn_add:
                TextMessage newMessage = new TextMessage("新消息");
                datas.add(index,newMessage);
                messageAdapter.notifyDataSetChanged();
                operationHint = "添加成功！";
                break;
            case R.id.btn_delete:
                datas.remove(index);
                messageAdapter.notifyDataSetChanged();
                operationHint = "删除成功！";
                break;
        }
        Toast.makeText(this,operationHint,Toast.LENGTH_SHORT).show();

    }

    //ListView 局部刷新的方法
    public void partialRefresh(ListView listView,int position){
        if(position>=listView.getFirstVisiblePosition()
                &&position<=listView.getLastVisiblePosition()){
            int childIndex = position-listView.getFirstVisiblePosition();
            View view = listView.getChildAt(childIndex);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ListViewActivity.this,"吐司",Toast.LENGTH_SHORT).show();
                }
            });

            if(view.getTag() instanceof  MessageAdapter.ViewHolder){
                MessageAdapter.ViewHolder  viewHolder = (MessageAdapter.ViewHolder) view.getTag();
                viewHolder.refreshContent("局部修改的消息");
            }

        }
    }
}
