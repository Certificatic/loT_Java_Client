package org.certificatic.cui.client.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.certificatic.cui.door.api.Door;

public class DoorImpl implements Door {
	
	private static final Logger LOG = Logger.getLogger(DoorImpl.class.getName());

	public boolean open(String name) {
		
	LOG.log(Level.INFO, " :::::: Abriendo puerta :::::: {0}", name);
		client(Door.OPEN);
	LOG.log(Level.INFO, " :::::: Puerta abierta :::::: ", name);
	
	  return true;
	}

	public boolean close(String name) {		
        
        LOG.log(Level.INFO, " ::::: Cerrando puerta :::: {0}", name);
		   client(Door.CLOSE);
        LOG.log(Level.INFO, " ::::: Puerta cerrada  :::: ", name);
        
	  return true;
	}
	
	public boolean alarm(String name) {
            
	LOG.log(Level.INFO, " ::::: Poniendo Alarma :::: {0}", name);
		   client(Door.ALARM);
        LOG.log(Level.INFO, " ::::: Alarma iniciada :::: {0}", name);
	
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
		
            } catch (IOException e) {
		    LOG.warning(":::::::::: FALLO :::::::::::");
		    LOG.log(Level.SEVERE, "IOException", e);
	    } catch (UnsupportedOperationException e) {	
                    LOG.warning(":::::::::: FALLO :::::::::::");
                    LOG.log(Level.SEVERE, "UnsupportedOperationException", e);
            }	
	}
}