package com.tktpl.helloworld.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tktpl.helloworld.R;
import com.tktpl.helloworld.adapter.WifiListAdapter;
import com.tktpl.helloworld.model.WifiModel;
import com.tktpl.helloworld.util.BaseRequestJson;
import com.tktpl.helloworld.util.BaseResponseJson;
import com.tktpl.helloworld.util.WifiListService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WifiListerFragment extends Fragment {

    private static final String TAG = "WifiListerFragment";

    private WifiManager wifiManager;

    private Button mRefreshBtn, mSendToServiceBtn;

    private WifiListAdapter wifiListAdapter;

    private List<WifiModel> wifiModels = new ArrayList<>();

    private WifiListService wifiListService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_wifi_lister, container, false);

        wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiListService = new WifiListService();

        RecyclerView recyclerView = view.findViewById(R.id.wifi_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);
        mRefreshBtn = view.findViewById(R.id.btn_scan);
        mSendToServiceBtn = view.findViewById(R.id.btn_send_service);

        wifiListAdapter = new WifiListAdapter();
        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(getContext(), "WiFi is disabled ... We need to enable it", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }

        recyclerView.setAdapter(wifiListAdapter);

        scanWifi();
        wifiListAdapter.setWifiModels(wifiModels);
        Log.w(TAG, "Status Wifi after generated: " + wifiModels.isEmpty());

        mRefreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanWifi();
                wifiListAdapter.setWifiModels(wifiModels);
            }
        });

        mSendToServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceBtnOnClick();
            }
        });

        return view;
    }

    public void serviceBtnOnClick() {
        BaseRequestJson request = new BaseRequestJson();
        Log.w(TAG, "Status Wifi onClick: " + this.wifiModels.isEmpty());
        request.setSentTime(new Timestamp(System.currentTimeMillis()));
        request.setWifiModels(this.wifiModels);

        wifiListService.sendToEndPoint(request).enqueue(new Callback<BaseResponseJson>() {
            @Override
            public void onResponse(Call<BaseResponseJson> call, Response<BaseResponseJson> response) {
                if (response.body() != null) {
                    Toast.makeText(getContext(),"Response message: "+response.body().getSuccess(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponseJson> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void scanWifi() {
        wifiModels.clear();
        requireActivity().registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        Toast.makeText(getContext(), "Scanning WiFi ...", Toast.LENGTH_SHORT).show();
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<WifiModel> wifiModelList = new ArrayList<>();
            WifiModel wifiModel;
            for (ScanResult result : wifiManager.getScanResults()) {
                wifiModel = new WifiModel();
                wifiModel.setWifiName(result.SSID);
                wifiModel.setCapabilities(result.capabilities);
                wifiModelList.add(wifiModel);
//                wifiModel.setSignalLevel(resul);
            }
            getActivity().unregisterReceiver(this);
            wifiModels = wifiModelList;
            wifiListAdapter.notifyDataSetChanged();
        }
    };

    private void generateDummyWifi() {
        this.wifiModels.clear();
        WifiModel wifiModel;
        for (int i = 0; i < 2; i++) {
            wifiModel = new WifiModel();
            wifiModel.setWifiName("Wifi " + i);
            wifiModel.setCapabilities("WPA2");
            this.wifiModels.add(wifiModel);
        }
        wifiListAdapter.notifyDataSetChanged();
    }
}
