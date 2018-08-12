/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mqttwebclient;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.json.JSONArray;
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
    public void messageReceived(JSONObject json) {
        provider.put(json);        
    }
    
    public void prepareChartData() {
        RequestContext reqCtx = RequestContext.getCurrentInstance();        
        reqCtx.addCallbackParam("chartData", provider.toString());
    }
    
}
