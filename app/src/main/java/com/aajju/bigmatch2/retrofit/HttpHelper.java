package com.aajju.bigmatch2.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by massivcode@gmail.com on 2017. 2. 24. 15:27
 */

public class HttpHelper {
    private static final String RELEASE_BASE_URL = "http://ec2-13-124-88-242.ap-northeast-2.compute.amazonaws.com:10001/api/";

    public static Api getAPI() {
        Retrofit retrofit = createRetrofit();
        return retrofit.create(Api.class);
    }

    private static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(RELEASE_BASE_URL)
                .client(getClient())
                .build();
    }

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }
}