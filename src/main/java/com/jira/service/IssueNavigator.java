package com.jira.service;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jira.mail.service.EMailService;
import com.jira.request.SearchRequest;
import com.jira.response.Issue;
import com.jira.response.IssueList;
import com.jira.util.Constants;
import com.jira.util.URL;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class IssueNavigator {
	
	private static Logger log = Logger.getLogger(IssueNavigator.class);
	
	public Issue getIssue(String link, String issueId, String queryParams){
		Issue issue = null;
		if(link == null && (issueId == null || "".equals(issueId.trim()))){
			log.error("Issue ID is missing");
			return null;
		}
		String uri = null;
		if(link != null){
			uri = link;
		} else {
			uri = URL.ISSUE + issueId;
		}
		if(queryParams != null && !"".equals(queryParams.trim())){
			uri += "?" + queryParams;
		}
		log.info("JIRA Query String : " + uri);
		try{
			Client client = Client.create();
			WebResource ws = client.resource(uri);
			String session = Authenticator.getSessionID();
			ClientResponse res = ws.type(MediaType.APPLICATION_JSON_TYPE).header("cookie", session).get(ClientResponse.class);
			log.info("Received response status : " + res.getStatus());
			if(res.getStatus() == Constants.statusCodeSuccess){
				ObjectMapper mapper = new ObjectMapper();
				/*InputStreamReader in = new InputStreamReader(res.getEntityInputStream());
				BufferedReader br = new BufferedReader(in);
				String rawText = "";
				String temp = "";
				while((temp = br.readLine()) != null) rawText += temp;
				System.out.println(rawText);*/
				issue = mapper.readValue(res.getEntityInputStream(), Issue.class);
			} else if(res.getStatus() == Constants.statusCodeUnAuthorized){
				byte[] in = new byte[10];
				String output = "";
				while(res.getEntityInputStream().read(in) != -1){
					output += new String(in);
				}
				log.error("Authentication failed - " + output);
				Authenticator.reAuthenticate();
			} else {
				byte[] in = new byte[10];
				String output = "";
				while(res.getEntityInputStream().read(in) != -1){
					output += new String(in);
				}
				log.error(output);
			}
		} catch(Exception e){
			log.error(e.getMessage(), e);
			EMailService.triggerErrorNotification(e, issueId, "REST api", "Failed read issue into java object");
		}
		return issue;
	}
	
	public IssueList search(String jql, SearchRequest req){
		if(req == null){
			req = new SearchRequest();
			if(jql == null || "".equals(jql.trim()))
				req.setJql(URL.SEARCH_QUERY_BH);
			else
				req.setJql(jql);
			req.setStartAt(0);
			req.setMaxResults(100);
			req.setFields(new String[]{"summary","status","assignee", "watches"});
		}
		IssueList issuesList = null;
		try{
			Client client = Client.create();
			WebResource ws = client.resource(URL.SEARCH);
			String session = Authenticator.getSessionID();
			ObjectMapper mapper = new ObjectMapper();
			ClientResponse res = ws.type(MediaType.APPLICATION_JSON_TYPE).header("cookie", session).post(ClientResponse.class, mapper.writeValueAsString(req));
			log.info("Received response status : " + res.getStatus());
			if(res.getStatus() == Constants.statusCodeSuccess){
				/*InputStreamReader in = new InputStreamReader(res.getEntityInputStream());
				BufferedReader br = new BufferedReader(in);
				String rawText = "";
				String temp = "";
				while((temp = br.readLine()) != null) rawText += temp;
				System.out.println(rawText);*/
				issuesList = mapper.readValue(res.getEntityInputStream(), IssueList.class);
			} else if(res.getStatus() == Constants.statusCodeUnAuthorized){
				byte[] in = new byte[10];
				String output = "";
				while(res.getEntityInputStream().read(in) != -1){
					output += new String(in);
				}
				log.error("Authentication failed - " + output);
				Authenticator.reAuthenticate();
			} else {
				byte[] in = new byte[10];
				String output = "";
				while(res.getEntityInputStream().read(in) != -1){
					output += new String(in);
				}
				log.error(output);
			}
		} catch(Exception e){
			log.error(e.getMessage(), e);
			EMailService.triggerErrorNotification(e, req.getJql(), "REST api", "Failed read issue list into java object");
		}
		return issuesList;
	}
	
	public static void main(String[] args){
		IssueNavigator issue = new IssueNavigator();
		issue.getIssue(null, "BHR-2104", "expand=changelog,renderedFields");
		//issue.search(null, null);
	}
}
