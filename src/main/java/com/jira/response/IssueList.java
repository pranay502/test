package com.jira.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@XmlRootElement
public class IssueList {
	private String expand;
	private long startAt;
	private long maxResults;
	private long total;
	private Issue[] issues;
	public String getExpand() {
		return expand;
	}
	public void setExpand(String expand) {
		this.expand = expand;
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
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public Issue[] getIssues() {
		return issues;
	}
	public void setIssues(Issue[] issues) {
		this.issues = issues;
	}
}
