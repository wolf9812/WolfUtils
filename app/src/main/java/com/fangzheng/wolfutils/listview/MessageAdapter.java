package com.fangzheng.wolfutils.listview;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fangzheng.wolfutils.R;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Objects;

/**
 * @author fangzheng
 * @date 2016/11/21
 * @email fangzheng428@163.com
 */

public class MessageAdapter extends BaseAdapter{

    private List<Object> datas;
    private static final int ViewTypeCount = 2;

    private interface ViewType{
        int TEXT = 0;
        int IMAGE = 1;
    }

    public MessageAdapter(List<Object> datas){
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*ViewHolder viewHolder;

        if(convertView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.text_message_item,parent,false);
            TextView textView = (TextView) convertView.findViewById(R.id.message_text_view);
            textView.setText(datas.get(position).getText().toString());
            ImageView imageView = (ImageView) convertView.findViewById(R.id.message_image);
            viewHolder = new ViewHolder(textView,imageView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.getTextView().setText(datas.get(position).getText().toString());
        }*/

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolder viewHolder;
        if(getItemViewType(position)==ViewType.TEXT){

            if(convertView==null){

                convertView = layoutInflater.inflate(R.layout.text_message_item,parent,false);
                TextView textView = (TextView) convertView.findViewById(R.id.message_text_view);

                ImageView imageView = (ImageView) convertView.findViewById(R.id.message_image);
                viewHolder = new ViewHolder(textView,imageView,null);
                convertView.setTag(viewHolder);
            }
            viewHolder = (ViewHolder) convertView.getTag();
            TextMessage textMessage = (TextMessage) datas.get(position);
            viewHolder.getTextView().setText(textMessage.getText().toString());

        }else{

            if(convertView==null){

                convertView = layoutInflater.inflate(R.layout.image_message_item,parent,false);
                ImageView content = (ImageView) convertView.findViewById(R.id.image_content);
                ImageMessage imageMessage = (ImageMessage) datas.get(position);
                content.setImageResource(imageMessage.getImageResId());
                ImageView imageViewHead = (ImageView) convertView.findViewById(R.id.image_head);
                ImageView imageViewContent = (ImageView)convertView.findViewById(R.id.image_content);
                viewHolder = new ViewHolder(null,imageViewHead,imageViewContent);
                convertView.setTag(viewHolder);
            }else {
                ImageMessage imageMessage = (ImageMessage) datas.get(position);
                viewHolder = (ViewHolder) convertView.getTag();
                viewHolder.getImageViewContent().setImageResource(imageMessage.getImageResId());

            }

        }



        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return ViewTypeCount;
    }

    @Override
    public int getItemViewType(int position) {
        if(getItem(position) instanceof TextMessage){
            return ViewType.TEXT;
        }else{
            return ViewType.IMAGE;
        }
    }


     static class ViewHolder{
        private TextView textView;
        private ImageView imageViewHead;

        private ImageView imageViewContent;

        public ViewHolder(TextView textView,ImageView imageViewHead,ImageView imageViewContent){
            this.textView = textView;
            this.imageViewHead = imageViewHead;
            this.imageViewContent = imageViewContent;
        }

        public ImageView getImageViewHead() {
            return imageViewHead;
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getImageViewContent() {
            return imageViewContent;
        }

         public void refreshContent(String content){
             textView.setText(content);
         }
    }
}
