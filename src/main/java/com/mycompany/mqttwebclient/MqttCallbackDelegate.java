/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mqttwebclient;

/**
 *
 * @author m_lig
 */
public interface MqttCallbackDelegate {
    public void messageReceived(String message);
}
