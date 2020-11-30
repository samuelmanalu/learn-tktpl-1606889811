package com.tktpl.helloworld.util;

import java.util.HashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface EndpointService {

    @POST("post_message")
    Call<BaseResponseJson> postMessage(@Body BaseRequestJson params);
}
