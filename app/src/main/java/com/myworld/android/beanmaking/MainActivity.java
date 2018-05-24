package com.myworld.android.beanmaking;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.myworld.android.beanmaking.adapter.GridAdapter;
import com.myworld.android.beanmaking.adapter.MyPagerAdapter;
import com.myworld.android.beanmaking.adapter.PopuAdapter;
import com.myworld.android.beanmaking.fragment.TJFragment;

public class MainActivity extends AppCompatActivity {
    private String[]string = {"分享美文","收徒赚钱","排行榜","签到","商务合作","客服QQ","收入明细","提现"};
    private int[]image = {R.mipmap.share_message,R.mipmap.money,R.mipmap.rakings, R.mipmap.sign,
            R.mipmap.business,R.mipmap.serive_qq,R.mipmap.money_dails,R.mipmap.put_money};
    private String[]ss = {"推荐","健康","美文","美食","常识","娱乐","两性","社会",
            "搞笑","母婴","历史","励志","新闻","旅游","奇葩事"};
    private TabLayout layout;
    private ViewPager viewpager;
    private GridView grid;
    private MyPagerAdapter pagerAdapter;
    private GridAdapter adapter;
    private ImageView rightimage;
    private PopupWindow popupWindow;
    private PopuAdapter popuAdapter;
    private GridView popu_grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.windowColor));
        }

        findView();
    }
    private void findView() {
        grid = (GridView) findViewById(R.id.gridView);
        adapter = new GridAdapter(image,string,this);
        grid.setAdapter(adapter);
        layout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        for (int i=0;i<ss.length;i++){
            pagerAdapter.addFragment(new TJFragment(ss[i]),ss[i]);
        }
        viewpager.setAdapter(pagerAdapter);
        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        layout.setupWithViewPager(viewpager);
        rightimage = (ImageView) findViewById(R.id.right_icon);
        cumputerViewPageHeight();
    }
    private void cumputerViewPageHeight() {
        //获取屏幕的高度
        DisplayMetrics metrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int windowHeight = metrics.heightPixels;
        //获取tabLayout的高度
        int layoutHeight = layout.getLayoutParams().height;
        //获取状态栏的高度
        int statusBarHeight = (int) getStatusBarHeight(this);
        //设置高度
        ViewGroup.LayoutParams layoutParams = viewpager.getLayoutParams();
        layoutParams.height = windowHeight-layoutHeight-statusBarHeight;
        viewpager.setLayoutParams(layoutParams);
    }
    //获取状态栏的高度
    private double getStatusBarHeight(Context context){
        double statusBarHeight = Math.ceil(25*context.getResources().getDisplayMetrics().density);
        return statusBarHeight;

    }
    public void clickImage(View view) {
        popuImgData();
    }
    public void popuImgData(){
        View view = LayoutInflater.from(this).inflate(R.layout.popu_layout,null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popu_grid = (GridView) view.findViewById(R.id.popu_grid);
        popuAdapter = new PopuAdapter(ss,this);
        popu_grid.setAdapter(popuAdapter);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        //显示在tablayout下面
        popupWindow.showAsDropDown(layout);
        //grid的点击事件
        popu_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewpager.setCurrentItem(position);
                popupWindow.dismiss();
            }
        });
    }
}
