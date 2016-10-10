package com.movie.liam.movieapp.rest;

import com.movie.liam.movieapp.BuildConfig;
import com.movie.liam.movieapp.api.Api;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lduf0001 on 06/10/2016.
 * Class for building rest adapter and Api object
 */

public final class RestService {

    private RestService() {
    }

    static <T> T createRetrofitService(Class<T> clazz) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(chain -> {
            Request request = getRequestWithApiKey(chain);
            return chain.proceed(request);
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit.create(clazz);
    }

    private static Request getRequestWithApiKey(Interceptor.Chain chain) {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build();
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);
        return requestBuilder.build();
    }

    public static Api getApi() {
        return createRetrofitService(Api.class);
    }
}