package com.myworld.android.beanmaking.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myworld.android.beanmaking.R;
import com.myworld.android.beanmaking.bean.Beans;

import java.util.ArrayList;

/**
 * Created by 小可爱 on 2018/5/15.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Beans.DatasBean>data;
    private Context context;
    private static final int TYPE_ITEM   = 0;
    private static final int TYPE_FOOTER = 1;
    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE     = 1;
    //没有加载更多 隐藏
    public static final int NO_LOAD_MORE     = 2;
    //上拉加载更多状态-默认为0
    private int mLoadMoreStatus = 0;
    public RecycleAdapter(ArrayList<Beans.DatasBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM){
            View view = LayoutInflater.from(context).inflate(R.layout.recycle_adapter,parent,false);
            return new ReHolder(view);
        }else if (viewType == TYPE_FOOTER){
            View foot = LayoutInflater.from(context).inflate(R.layout.foot_item,parent,false);
            return new FooterViewHolder(foot);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ReHolder){
            ReHolder reHolder = (ReHolder) holder;
            Beans.DatasBean datasBean = data.get(position);
            reHolder.readprice.setText(datasBean.getReadprice());
            reHolder.Vistts.setText(datasBean.getVistts()+"阅读");
            reHolder.getArt_title.setText(datasBean.getArt_title());
            Glide.with(context).load(datasBean.getArt_pic()).into(reHolder.Art_pic);
            //item的点击事件
            reHolder.list_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(data.get(position).getArt_url());
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    context.startActivity(intent);
                }
            });
        }else if (holder instanceof FooterViewHolder){
            FooterViewHolder footer = (FooterViewHolder) holder;
            switch (mLoadMoreStatus) {
                case PULLUP_LOAD_MORE:
                    footer.footer_text.setText("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    footer.footer_text.setText("正在加载...");
                    break;
                case NO_LOAD_MORE:
                    //隐藏加载更多
                    footer.footer_linear.setVisibility(View.GONE);
                    break;
            }
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()){
            //最后一个item设置为footerView
            return TYPE_FOOTER;
        }else{
            return TYPE_ITEM;
        }
    }
    public void addHeader(ArrayList<Beans.DatasBean>mList){
        data.addAll(0,mList);
        notifyDataSetChanged();
    }
    public void addFooter(ArrayList<Beans.DatasBean>addItem){
        data.addAll(addItem);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return data.size()+1;
    }

    class ReHolder extends RecyclerView.ViewHolder{
        ImageView Art_pic;
        TextView getArt_title,Vistts,readprice;
        LinearLayout list_item;
        public ReHolder(View itemView) {
            super(itemView);
            Art_pic = (ImageView) itemView.findViewById(R.id.Art_pic);
            getArt_title = (TextView) itemView.findViewById(R.id.getArt_title);
            Vistts = (TextView) itemView.findViewById(R.id.Vistts);
            readprice = (TextView) itemView.findViewById(R.id.readprice);
            list_item = (LinearLayout) itemView.findViewById(R.id.list_item1);
           // fenxiang = (TextView) itemView.findViewById(R.id.fenxiang);
        }
    }
    //底部footerview布局
    class FooterViewHolder extends RecyclerView.ViewHolder{
        TextView footer_text;
        LinearLayout footer_linear;
        public FooterViewHolder(View itemView) {
            super(itemView);
            footer_linear= (LinearLayout) itemView.findViewById(R.id.layout);
            footer_text= (TextView) itemView.findViewById(R.id.tvLoadText);
        }
    }
    public void changeMore(int status){
        mLoadMoreStatus = status;
        notifyDataSetChanged();
    }
}
