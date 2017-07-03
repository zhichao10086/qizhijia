package com.wuzhichao.test1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wuzhichao.test1.config.Config;
import com.wuzhichao.test1.ApiService.PostService;
import com.wuzhichao.test1.mvp.bean.BarInfoBean;
import com.wuzhichao.test1.mvp.bean.ResponseData;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //RetrofitFactory retrofitFactory = new RetrofitFactory();
        //request();


    }

    public void request(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        PostService postService = retrofit.create(PostService.class);

        Call<ResponseData<BarInfoBean>> call = postService.getHotPost();

        call.enqueue(new Callback<ResponseData<BarInfoBean>>() {
            @Override
            public void onResponse(Response<ResponseData<BarInfoBean>> response, Retrofit retrofit) {
                List<BarInfoBean> barInfoBeanList = new ArrayList<BarInfoBean>();
                barInfoBeanList = response.body().getData();

                System.out.println(barInfoBeanList.get(0).getName());

            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("吴志超1");
            }
        });

    }
}
