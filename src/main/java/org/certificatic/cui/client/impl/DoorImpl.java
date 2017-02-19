package org.certificatic.cui.client.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.certificatic.cui.door.api.Door;

public class DoorImpl implements Door {
	
	private static final Logger LOG = Logger.getLogger(DoorImpl.class.getName());

	public boolean open(String name) {
		
	LOG.info(" :::::: Abriendo puerta :::::: " + name);
		client(Door.OPEN);
	LOG.info(" :::::: Puerta abierta :::::: " + name);
	
	  return true;
	}

	public boolean close(String name) {		
		LOG.info(" ::::: Cerrando puerta :::: " + name);
		   client(Door.CLOSE);
		   LOG.info(" ::::: Puerta cerrada  :::: " + name);
		
		return true;
	}
	
	public boolean alarm(String name) {	
			LOG.info(" ::::: Poniendo Alarma :::: " + name);
			client(Door.ALARM);
			LOG.info(" ::::: Alarma iniciada :::: " + name);
		return true;
	}
	
        
        
	private void client(String args) {
	
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(Door.URI);

		List nameValuePairs = new ArrayList(1);

		nameValuePairs.add(new BasicNameValuePair(Door.ACCESS_TOKE_NAME, Door.ACCESS_TOKEN_VALUE));
		nameValuePairs.add(new BasicNameValuePair(Door.OPERATION, args));

		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		
		HttpResponse response = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";

		while ((line = rd.readLine()) != null) {
			System.out.println(line);
		}
		
		} catch (Exception e) {
			LOG.warning(":::::::::: FALLO :::::::::::");
			LOG.log(Level.SEVERE, "Exception occur", e);
		}	
	}
}