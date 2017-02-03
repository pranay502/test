package com.jira.service;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jira.request.LoginRequest;
import com.jira.response.AuthenticateUser;
import com.jira.util.Constants;
import com.jira.util.URL;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Authenticator {
	private static Logger log = Logger.getLogger(Authenticator.class);
	private static String SESSION_ID;
	private static AuthenticateUser authenticate;
	private static String authenticateUser(){
		LoginRequest req = new LoginRequest();
		req.setUsername("saikrishna.rayapati");
		req.setPassword("Krishna123$");
		try{
			Client client = Client.create();
			WebResource ws = client.resource(URL.SESSION);
			ClientResponse res = ws.type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, req);
			if(res.getStatus() == Constants.statusCodeSuccess){
				ObjectMapper mapper = new ObjectMapper();
				/*byte[] in = new byte[10];
				while(res.getEntityInputStream().read(in) != -1){
					System.out.print(new String(in));
				}*/
				authenticate = mapper.readValue(res.getEntityInputStream(), AuthenticateUser.class);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		log.info("New session ID: " + authenticate.getSession().getValue());
		return authenticate.getSession().getName() + "=" + authenticate.getSession().getValue();
	}
	
	public static String getSessionID(){
		if(SESSION_ID == null || authenticate == null){
			SESSION_ID = authenticateUser();
		}
		return SESSION_ID;
	}
	
	public static void reAuthenticate(){
		SESSION_ID = authenticateUser();
	}
	
	public static void main(String[] args){
		System.out.println(getSessionID());
	}
}
