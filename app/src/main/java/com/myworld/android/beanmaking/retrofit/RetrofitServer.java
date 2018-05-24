package com.myworld.android.beanmaking.retrofit;

import com.myworld.android.beanmaking.bean.Beans;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by 小可爱 on 2018/5/15.
 */

public interface RetrofitServer {
    //http://dz.zhuan12.com/dz/minfo/call.action
    @POST("dz/minfo/call.action")
    @FormUrlEncoded
    Observable<Beans>getData(@Field("jdata")String jdata,@Field("opttype")String opttype);
}
