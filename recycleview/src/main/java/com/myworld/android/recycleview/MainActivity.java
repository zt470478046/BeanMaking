package com.myworld.android.recycleview;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.myworld.android.recycleview.adapter.RecycleAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipe;
    private RecyclerView recycle;
    private ArrayList<String>list = new ArrayList<>();
    private RecycleAdapter adapter;
    private Handler handler;
    private int lastVisibleItem;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        for (int i = 0;i<20;i++){
            list.add("我是小成子"+i);
        }
        initView();
        initPullRefresh();
        initLoadMore();
    }
    private void initView() {
        adapter = new RecycleAdapter(list,this);
        manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recycle.setLayoutManager(manager);
        recycle.setAdapter(adapter);
        swipe.setColorSchemeColors(Color.BLUE,Color.RED);
    }
    private void initPullRefresh() {
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String>head = new ArrayList<String>();
                        for (int i = 0;i<10;i++){
                            head.add("header"+i);
                        }
                        adapter.AddHeaderItem(head);
                        swipe.setRefreshing(false);
                        Toast.makeText(MainActivity.this, "更新了条目", Toast.LENGTH_SHORT).show();
                    }
                },2000);
            }
        });
    }
    public void initLoadMore(){
        recycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem+1 == adapter.getItemCount()){
                    //设置加载更多
                    adapter.changeMore(adapter.LOADING_MORE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            List<String> footer = new ArrayList<String>();
                            for (int i = 0; i< 10; i++) {

                                footer.add("footer  item" + i);
                            }
                            adapter.AddFooterItem(footer);
                            //设置回到上拉加载更多
                            adapter.changeMore(adapter.PULLUP_LOAD_MORE);
                        }
                    },2000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }
}
