package com.myworld.android.beanmaking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myworld.android.beanmaking.R;

/**
 * Created by 小可爱 on 2018/5/16.
 */

public class PopuAdapter extends BaseAdapter{

    private String[]popu;
    private Context context;

    public PopuAdapter(String[] popu, Context context) {
        this.popu = popu;
        this.context = context;
    }

    @Override
    public int getCount() {
        return popu.length;
    }

    @Override
    public String getItem(int position) {
        return popu[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.popu_adapter,null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        vh.type_name.setText(getItem(position));
        return convertView;
    }
    class ViewHolder{

            TextView type_name;

        public ViewHolder(View view) {
            type_name = (TextView) view.findViewById(R.id.type_name);
        }
    }
}
