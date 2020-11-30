package com.tktpl.helloworld.util;

import com.tktpl.helloworld.model.WifiModel;

import java.sql.Timestamp;
import java.util.List;

public class BaseRequestJson {

    private Timestamp sentTime;

    private List<WifiModel> wifiModels;

    /**
     *
     * @return
     * The email
     */
    public Timestamp getSentTime() {
        return sentTime;
    }

    /**
     *
     * @param sentTime
     * The sentTime
     */
    public void setSentTime(Timestamp sentTime) {
        this.sentTime = sentTime;
    }

    /**
     *
     * @return
     * The Wifi
     */
    public List<WifiModel> getWifiModels() {
        return wifiModels;
    }

    /**
     *
     * @param wifiModels
     * The password
     */
    public void setWifiModels(List<WifiModel> wifiModels) {
        this.wifiModels = wifiModels;
    }
}
