package com.pro.emp.notification;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.pro.post.domain.CommentNotification;

public class DomainNotifyEncoder  implements Encoder.Text<DomainNotify> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(DomainNotify notify) throws EncodeException {
		// TODO Auto-generated method stub
		JsonObject jsonObject = Json.createObjectBuilder()
				.add("type", notify.getType())
				.add("json", notify.getJson().toString()).build();
		return jsonObject.toString();
	}

}
