package com.example.innovas;

import com.example.innovas.model.CharkhoonePurchasePostModel;
import com.example.innovas.model.IsUserPostModel;
import com.example.innovas.model.OtpPostModel;
import com.example.innovas.model.VerifyPostModel;
import com.example.innovas.network.ApiUtils;
import com.example.innovas.network.RetrofitInterface;

import java.net.ConnectException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amirhesni on 9/9/2018.
 */

public class VasRequests {
    private static final String CONTENT_TYPE = "application/json";
    private static final String ACCEPT = "application/json";
    private static Integer applicationId;
    private static String uuid;
    private static RetrofitInterface retrofitInterface;
    private static EventListener listener;

    public interface EventListener {
        void isSubscribed();

        void unAuthorization();

        void serverError();

        void badRequest();

        void connectionError();

        void unExpectedError();
    }

    public static void isUser( String msisdn ,String checkSum, EventListener eventListener) {
        listener = eventListener;
        IsUserPostModel isUserPostModel = new IsUserPostModel(uuid, formatPhone(msisdn), checkOperator(msisdn));
        retrofitInterface.isUser(applicationId, checkSum, CONTENT_TYPE, ACCEPT, isUserPostModel).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    listener.isSubscribed();
                } else if (response.code() == 400) {
                    listener.badRequest();
                } else if (response.code() == 500) {
                    listener.serverError();
                } else if (response.code() == 401) {
                    listener.unAuthorization();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (throwable instanceof ConnectException || throwable instanceof UnknownHostException)
                    listener.connectionError();
                else {
                    listener.unExpectedError();
                }
            }
        });
    }

    private static String formatPhone(String msisdn) {
        if (msisdn.startsWith("0")) {
            return "98"  + msisdn.substring(1);
        }
        return msisdn;
    }

    public static void otpRequest( String msisdn, String checksum, EventListener eventListener) {
        listener = eventListener;
        OtpPostModel otpPostModel = new OtpPostModel(uuid, formatPhone(msisdn));
        retrofitInterface.PakOtpRequest(2, checksum, CONTENT_TYPE, ACCEPT, otpPostModel).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    listener.isSubscribed();
                } else if (response.code() == 400) {
                    listener.badRequest();
                } else if (response.code() == 500) {
                    listener.serverError();
                } else if (response.code() == 401) {
                    listener.unAuthorization();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (throwable instanceof ConnectException || throwable instanceof UnknownHostException)
                    listener.connectionError();
                else {
                    listener.unExpectedError();
                }
            }
        });
    }

    public static void verifyRequest(String msisdn, int otpId, String pin, String checkSum, EventListener eventListener) {
        listener = eventListener;
        VerifyPostModel verifyPostModel = new VerifyPostModel(uuid, formatPhone(msisdn), otpId, pin);
        retrofitInterface.PakVerifyRequest(2, checkSum, CONTENT_TYPE, ACCEPT, verifyPostModel).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    listener.isSubscribed();
                } else if (response.code() == 400) {
                    listener.badRequest();
                } else if (response.code() == 500) {
                    listener.serverError();
                } else if (response.code() == 401) {
                    listener.unAuthorization();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (throwable instanceof ConnectException || throwable instanceof UnknownHostException)
                    listener.connectionError();
                else {
                    listener.unExpectedError();
                }
            }
        });
    }

    public static void charkhoonePurchase(String msisdn, String purchaseToken, String checkSum, EventListener eventListener) {
        listener = eventListener;
        CharkhoonePurchasePostModel postModel = new CharkhoonePurchasePostModel(uuid, formatPhone(msisdn), purchaseToken);
        retrofitInterface.CharkhoonePurchase(2, checkSum, CONTENT_TYPE, ACCEPT, postModel).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    listener.isSubscribed();
                } else if (response.code() == 400) {
                    listener.badRequest();
                } else if (response.code() == 500) {
                    listener.serverError();
                } else if (response.code() == 401) {
                    listener.unAuthorization();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (throwable instanceof ConnectException || throwable instanceof UnknownHostException)
                    listener.connectionError();
                else {
                    listener.unExpectedError();
                }
            }
        });
    }

    public static void init(int Application_ID, String uuidApplication) {
        retrofitInterface = ApiUtils.GetRetrofit();
        applicationId = Application_ID;
        uuid = uuidApplication;
    }

    public static String checkOperator(String phone) {
        String[] mci_array = {"0910", "0911", "0912", "0913", "0914", "0915", "0916", "0917", "0918", "0919", "0990"};
        String[] mtn_array = {"0901", "0902", "0903", "0904", "0905", "0906", "0907", "0908", "0909", "0930", "0931", "0932", "0933", "0934", "0935", "0936", "0937", "0938", "0939"};
        for (int i = 0; i < mci_array.length - 1; i++) {
            if (phone.startsWith(mci_array[i]))
                return "mci";
        }
        for (int i = 0; i < mtn_array.length - 1; i++) {
            if (phone.startsWith(mtn_array[i]))
                return "mtn";
        }
        return "";
    }

}
