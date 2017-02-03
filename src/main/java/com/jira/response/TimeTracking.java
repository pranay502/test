package com.jira.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@XmlRootElement
public class TimeTracking {
	private String originalEstimate;
	private String remainingEstimate;
	private long originalEstimateSeconds;
	private long remainingEstimateSeconds;
	public String getOriginalEstimate() {
		return originalEstimate;
	}
	public void setOriginalEstimate(String originalEstimate) {
		this.originalEstimate = originalEstimate;
	}
	public String getRemainingEstimate() {
		return remainingEstimate;
	}
	public void setRemainingEstimate(String remainingEstimate) {
		this.remainingEstimate = remainingEstimate;
	}
	public long getOriginalEstimateSeconds() {
		return originalEstimateSeconds;
	}
	public void setOriginalEstimateSeconds(long originalEstimateSeconds) {
		this.originalEstimateSeconds = originalEstimateSeconds;
	}
	public long getRemainingEstimateSeconds() {
		return remainingEstimateSeconds;
	}
	public void setRemainingEstimateSeconds(long remainingEstimateSeconds) {
		this.remainingEstimateSeconds = remainingEstimateSeconds;
	}
}
