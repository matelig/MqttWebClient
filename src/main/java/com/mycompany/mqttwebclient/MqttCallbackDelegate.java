/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mqttwebclient;

import org.json.JSONObject;

/**
 *
 * @author m_lig
 */
public interface MqttCallbackDelegate {
    public void messageReceived(JSONObject json);
}
