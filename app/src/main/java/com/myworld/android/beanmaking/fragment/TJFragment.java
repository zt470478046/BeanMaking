package com.myworld.android.beanmaking.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myworld.android.beanmaking.R;
import com.myworld.android.beanmaking.adapter.RecycleAdapter;
import com.myworld.android.beanmaking.bean.Beans;
import com.myworld.android.beanmaking.retrofit.RetrofitServer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class TJFragment extends Fragment {

    private String Opt = "ART_LIST";

    private String OneData = "{app_id:xzwl,app_token:xzwltoken070704,pars:{art_type:30,before_hour:0,openid:oH4k8vwFfaTIXHekuTSVR-5Ix2W8,orderby:asc,page:1,pagesize:10,start_id:0},version:1.0}";
    private String TwoData = "{app_id:xzwl,app_token:xzwltoken070704,pars:{art_type:7,before_hour:0,openid:oH4k8vwFfaTIXHekuTSVR-5Ix2W8,orderby:asc,page:1,pagesize:10,start_id:0},version:1.0}";
    private String ThreeData = "{app_id:xzwl,app_token:xzwltoken070704,pars:{art_type:6,before_hour:0,openid:oH4k8vwFfaTIXHekuTSVR-5Ix2W8,orderby:asc,page:1,pagesize:10,start_id:0},version:1.0}";
    private String FourData = "{app_id:xzwl,app_token:xzwltoken070704,pars:{art_type:8,before_hour:0,openid:ogIz8wjPi-ad12XLzQyBfrqQNZhk,orderby:asc,page:1,pagesize:10,start_id:0},version:1.0}";
    private String FiveData = "{app_id:xzwl,app_token:xzwltoken070704,pars:{art_type:9,before_hour:0,openid:oH4k8vwFfaTIXHekuTSVR-5Ix2W8,orderby:asc,page:1,pagesize:10,start_id:0},version:1.0}";
    private String SixData = "{app_id:xzwl,app_token:xzwltoken070704,pars:{art_type:32,before_hour:0,openid:oH4k8vwFfaTIXHekuTSVR-5Ix2W8,orderby:asc,page:1,pagesize:10,start_id:0},version:1.0}";
    private String SevenData = "{app_id:xzwl,app_token:xzwltoken070704,pars:{art_type:10,before_hour:0,openid:oH4k8vwFfaTIXHekuTSVR-5Ix2W8,orderby:asc,page:1,pagesize:10,start_id:0},version:1.0}";
    private String EightData = "{app_id:xzwl,app_token:xzwltoken070704,pars:{art_type:31,before_hour:0,openid:oH4k8vwFfaTIXHekuTSVR-5Ix2W8,orderby:asc,page:1,pagesize:10,start_id:0},version:1.0}";
    private String NineData = "{app_id:xzwl,app_token:xzwltoken070704,pars:{art_type:4,before_hour:0,openid:oH4k8vwFfaTIXHekuTSVR-5Ix2W8,orderby:asc,page:1,pagesize:10,start_id:0},version:1.0}";
    private String TenData = "{app_id:xzwl,app_token:xzwltoken070704,pars:{art_type:14,before_hour:0,openid:oH4k8vwFfaTIXHekuTSVR-5Ix2W8,orderby:asc,page:1,pagesize:10,start_id:0},version:1.0}";
    private String ElevenData = "{app_id:xzwl,app_token:xzwltoken070704,pars:{art_type:12,before_hour:0,openid:oH4k8vwFfaTIXHekuTSVR-5Ix2W8,orderby:asc,page:1,pagesize:10,start_id:0},version:1.0}";
    private String TwelveData = "{app_id:xzwl,app_token:xzwltoken070704,pars:{art_type:40,before_hour:0,openid:oH4k8vwFfaTIXHekuTSVR-5Ix2W8,orderby:asc,page:1,pagesize:10,start_id:0},version:1.0}";
    private String ThirteenData = "{app_id:xzwl,app_token:xzwltoken070704,pars:{art_type:37,before_hour:0,openid:oH4k8vwFfaTIXHekuTSVR-5Ix2W8,orderby:asc,page:1,pagesize:10,start_id:0},version:1.0}";
    private String FourttenData = "{app_id:xzwl,app_token:xzwltoken070704,pars:{art_type:33,before_hour:0,openid:oH4k8vwFfaTIXHekuTSVR-5Ix2W8,orderby:asc,page:1,pagesize:10,start_id:0},version:1.0}";
    private String FifteenData = "{app_id:xzwl,app_token:xzwltoken070704,pars:{art_type:42,before_hour:0,openid:oH4k8vwFfaTIXHekuTSVR-5Ix2W8,orderby:asc,page:1,pagesize:10,start_id:0},version:1.0}";
    private ArrayList<Beans.DatasBean> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapter adapter;
    private SwipeRefreshLayout swipelayout;
    private Handler handler = new Handler();
    private int lastVisibleItem;
    private LinearLayoutManager manager;
    private Retrofit retrofit;
    private String str;
    private View view;

    public TJFragment(String ss) {
        // Required empty public constructor
        Bundle bundle = new Bundle();
        bundle.putString("ss", ss);
        setArguments(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tj, null);
        //去除系统标题
        // getActivity().requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://dz.zhuan12.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        str = getArguments().getString("ss");
        inData();
        return view;
    }

    private void inData() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        swipelayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new RecycleAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);
        //设置刷新时动画的颜色,可以设置4个
        swipelayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swipelayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        //实现下拉加载
        swipelayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //添加数据
                        list.clear();
                        inBean();
                        swipelayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        //当滚动到底不是刷新数据
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //滚动底部时刷新数据
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    //设置加载更多
                    adapter.changeMore(adapter.LOADING_MORE);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            List<Beans.DatasBean> foot = new ArrayList<>();
                            inBean();
                            foot.addAll(list);
                            adapter.addFooter((ArrayList<Beans.DatasBean>) foot);
                            //设置加载更多
                            //adapter.changeMore(adapter.PULLUP_LOAD_MORE);
                            swipelayout.setRefreshing(false);
                        }
                    }, 2000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取滚动的最后位置
                lastVisibleItem = manager.findLastVisibleItemPosition();
            }
        });
        inBean();
    }

    private void inBean() {
        switch (str) {
            case "推荐":
                retrofitData(OneData, Opt);
                break;
            case "健康":
                retrofitData(TwoData, Opt);
                break;
            case "美文":
                retrofitData(ThreeData, Opt);
                break;
            case "美食":
                retrofitData(FourData, Opt);
                break;
            case "常识":
                retrofitData(FiveData, Opt);
                break;
            case "娱乐":
                retrofitData(SixData, Opt);
                break;
            case "两性":
                retrofitData(SevenData, Opt);
                break;
            case "社会":
                retrofitData(EightData, Opt);
                break;
            case "搞笑":
                retrofitData(NineData, Opt);
                break;
            case "母婴":
                retrofitData(TenData, Opt);
                break;
            case "历史":
                retrofitData(ElevenData, Opt);
                break;
            case "励志":
                retrofitData(TwelveData, Opt);
                break;
            case "新闻":
                retrofitData(ThirteenData, Opt);
                break;
            case "旅游":
                retrofitData(FourttenData, Opt);
                break;
            case "奇葩事":
                retrofitData(FifteenData, Opt);
                break;
        }
    }

    private void retrofitData(String jdata, String opt) {

        Observable<Beans> observable = retrofit.create(RetrofitServer.class).getData(jdata, opt);
        observable.map(new Func1<Beans, List<Beans.DatasBean>>() {
            @Override
            public List<Beans.DatasBean> call(Beans beans) {
                return beans.getDatas();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Beans.DatasBean>>() {
                    @Override
                    public void onCompleted() {
                        swipelayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        swipelayout.setRefreshing(false);
                    }

                    @Override
                    public void onNext(List<Beans.DatasBean> datasBeen) {
                        if (datasBeen != null) {
                            list.addAll(datasBeen);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
