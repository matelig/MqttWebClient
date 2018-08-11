/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mqttwebclient;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.json.JSONArray;
import java.util.Date;
import org.json.JSONObject;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author m_lig
 */
@ManagedBean(name = "dataChart", eager = true)
@ViewScoped
public class DataChart implements Serializable, MqttCallbackDelegate {
    private int newValue = 10;
    private JSONArray provider = new JSONArray();
    private LineChartModel lineChartModel;
    

    public JSONArray getProvider() {
        return provider;
    }

    public void setProvider(JSONArray provider) {
        this.provider = provider;
    }
    @PostConstruct
    public void init() {
        System.out.println("Hello World client!");
		try {
			MqttClient client = new MqttClient("tcp://test.mosquitto.org:1883", MqttClient.generateClientId());
			client.setCallback(new SimpleMqttCallback(this));
			client.connect();
			client.subscribe("tpdia_test");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public int getNewValue() {
        return newValue;
    }

    public void setNewValue(int newValue) {
        this.newValue = newValue;
    }

    public LineChartModel getLineChartModel() {
        return lineChartModel;
    }

    public void setLineChartModel(LineChartModel lineChartModel) {
        this.lineChartModel = lineChartModel;
    }

    @Override
    public void messageReceived(String message) {
        JSONObject object = new JSONObject();
        Date date = new Date();
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        object.put("date", sfd.format(date));
        object.put("value", "9");  
        provider.put(object);        
        System.out.println(message);
        newValue++;
    }
    
    public void prepareChartData() {
        RequestContext reqCtx = RequestContext.getCurrentInstance();        
        reqCtx.addCallbackParam("chartData", provider.toString());
    }
    
}
