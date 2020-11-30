package com.tktpl.helloworld.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tktpl.helloworld.R;
import com.tktpl.helloworld.model.WifiModel;

import java.util.ArrayList;
import java.util.List;

public class WifiListAdapter extends RecyclerView.Adapter<WifiListAdapter.WifiListHolder>{

    List<WifiModel> wifiModels = new ArrayList<>();

    @NonNull
    @Override
    public WifiListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wifi, parent, false);
        return new WifiListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WifiListHolder holder, int position) {
        WifiModel wifiModel = wifiModels.get(position);

        holder.mWifiName.setText(wifiModel.getWifiName());
        holder.mWifiSecurity.setText(wifiModel.getCapabilities());
    }

    @Override
    public int getItemCount() {
        return wifiModels.size();
    }

    public void setWifiModels(List<WifiModel> wifiModels) {
        this.wifiModels = wifiModels;
    }

    class WifiListHolder extends RecyclerView.ViewHolder {

        private TextView mWifiName, mWifiSecurity;
        private ImageView mWifiIcon;

        public WifiListHolder(@NonNull View itemView) {
            super(itemView);
            mWifiName = itemView.findViewById(R.id.wifi_name);
            mWifiSecurity = itemView.findViewById(R.id.wifi_security);
            mWifiIcon = itemView.findViewById(R.id.wifi_image_thumbnail);
        }
    }
}
