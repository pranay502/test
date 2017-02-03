package com.jira.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@XmlRootElement
public class Field {
	private String summary;
	private Status status;
	private Priority priority;
	private IssueType issuetype;
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public IssueType getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(IssueType issueType) {
		this.issuetype = issueType;
	}
}
