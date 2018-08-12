package com.mycompany.mqttwebclient;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;


public class SimpleMqttCallback implements MqttCallback {
    
         public MqttCallbackDelegate delegate;
         
         public SimpleMqttCallback(MqttCallbackDelegate delegate) {
             this.delegate = delegate;
         }

	  public void connectionLost(Throwable throwable) {
	    System.out.println("Connection to MQTT broker lost!");
	  }

	  public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {            
            String jsonString = new String(mqttMessage.getPayload());
            JSONObject json = new JSONObject(jsonString);
            System.out.println(json);
            delegate.messageReceived(json);
	    System.out.println("Message received:\n\t"+ new String(mqttMessage.getPayload()) );
            
	  }

	  public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
	
	  }
	}