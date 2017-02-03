package com.jira.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
@XmlRootElement
public class Watches {
	private String self;
	private long watchCount;
	private boolean isWatching;
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public long getWatchCount() {
		return watchCount;
	}
	public void setWatchCount(long watchCount) {
		this.watchCount = watchCount;
	}
	@JsonProperty("isWatching")
	public boolean isWatching() {
		return isWatching;
	}
	public void setWatching(boolean isWatching) {
		this.isWatching = isWatching;
	}
}
