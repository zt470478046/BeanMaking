package com.myworld.android.beanmaking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myworld.android.beanmaking.R;

/**
 * Created by 小可爱 on 2018/5/14.
 */

public class GridAdapter extends BaseAdapter{
    private int[]image;
    private String[]str;
    private Context context;

    public GridAdapter(int[] image, String[] str, Context context) {
        this.image = image;
        this.str = str;
        this.context = context;
    }
    @Override
    public int getCount() {
        return str.length;
    }
    @Override
    public String getItem(int position) {
        return str[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.gridadapter,null);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        textView.setText(str[position]);
        imageView.setImageResource(image[position]);
        return view;
    }
}
