package com.tktpl.helloworld.util;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tktpl.helloworld.model.WifiModel;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WifiListService {

    private static final String TAG = "WifiListService";

    private String ENDPOINT_URL = "https://8aa639cf38c94ba466f752c8ec092f6f.m.pipedream.net";

    private Retrofit retrofit;

    private ObjectMapper objectMapper;

    private EndpointService endpointService;

    public WifiListService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        objectMapper = new ObjectMapper();
        endpointService = retrofit.create(EndpointService.class);
    }

    public Call<BaseResponseJson> sendToEndPoint(BaseRequestJson baseRequestJson) {
        Log.w(TAG, "Status Wifi onService: " + baseRequestJson.getWifiModels().isEmpty());
        return endpointService.postMessage(baseRequestJson);
    }
}
