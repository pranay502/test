package com.jira.request;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@XmlRootElement
public class SearchRequest {
	private String jql;
	private long startAt;
	private long maxResults;
	private String[] fields;
	public String getJql() {
		return jql;
	}
	public void setJql(String jql) {
		this.jql = jql;
	}
	public long getStartAt() {
		return startAt;
	}
	public void setStartAt(long startAt) {
		this.startAt = startAt;
	}
	public long getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(long maxResults) {
		this.maxResults = maxResults;
	}
	public String[] getFields() {
		return fields;
	}
	public void setFields(String[] fields) {
		this.fields = fields;
	}
}
