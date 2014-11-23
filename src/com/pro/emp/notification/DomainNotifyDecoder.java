package com.pro.emp.notification;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class DomainNotifyDecoder implements Decoder.Text<DomainNotify>{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DomainNotify decode(String jsonMessage) throws DecodeException {

		JsonObject jsonObject = Json
				.createReader(new StringReader(jsonMessage)).readObject();
		DomainNotify message = new DomainNotify();
		message.setType(jsonObject.getString("type"));
		message.setJson(jsonObject.getString("json"));
		return message;
	}

	@Override
	public boolean willDecode(String jsonMessage) {
		try {
			// Check if incoming message is valid JSON
			Json.createReader(new StringReader(jsonMessage)).readObject();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
