package com.example.innovas.network;

import com.example.innovas.model.CharkhoonePurchasePostModel;
import com.example.innovas.model.OtpPostModel;
import com.example.innovas.model.VerifyPostModel;
import com.example.innovas.model.IsUserPostModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Android on 9/4/2017.
 */

public interface RetrofitInterface {
    @POST("clients/is-user")
    Call<ResponseBody> isUser(@Header("Vw-Application-ID") Integer id , @Header("Vw-Checksum") String checksum
            , @Header("Content-Type") String Content_Type , @Header("Accept") String Accept , @Body IsUserPostModel postModel);

    @POST("clients/pak/otp/request")
    Call<ResponseBody> PakOtpRequest(@Header("Vw-Application-ID") Integer id , @Header("Vw-Checksum") String checksum
            , @Header("Content-Type") String Content_Type , @Header("Accept") String Accept , @Body OtpPostModel postModel);

    @POST("clients/pak/otp/request")
    Call<ResponseBody> PakVerifyRequest(@Header("Vw-Application-ID") Integer id , @Header("Vw-Checksum") String checksum
            , @Header("Content-Type") String Content_Type , @Header("Accept") String Accept , @Body VerifyPostModel postModel);

    @POST("clients/pak/otp/request")
    Call<ResponseBody> CharkhoonePurchase(@Header("Vw-Application-ID") Integer id , @Header("Vw-Checksum") String checksum
            , @Header("Content-Type") String Content_Type , @Header("Accept") String Accept , @Body CharkhoonePurchasePostModel postModel);
}