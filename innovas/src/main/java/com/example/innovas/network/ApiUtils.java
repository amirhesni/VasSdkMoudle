package com.example.innovas.network;

/**
 * Created by amirhesni on 9/11/17.
 */

public class ApiUtils {

    public static final String BASE_URL = "http://mezons.ir/api/";


    public static RetrofitInterface GetCacheableRetrofit() {
        return RetrofitClient
                .getCacheableClient(BASE_URL)
                .create(RetrofitInterface.class);
    }

    public static RetrofitInterface GetRetrofit() {
        return RetrofitClient.getClient(BASE_URL).create(RetrofitInterface.class);
    }


}
