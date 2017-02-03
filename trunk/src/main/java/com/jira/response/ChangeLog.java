package com.jira.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@XmlRootElement
public class ChangeLog {
	private long startAt;
	private long maxResults;
	private long total;
	private History[] histories;
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
	public History[] getHistories() {
		return histories;
	}
	public void setHistories(History[] histories) {
		this.histories = histories;
	}
}
