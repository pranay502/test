package com.jira.response;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@XmlRootElement
@JsonAutoDetect
public class LoginInfo {
	private long failedLoginCount;
	private long loginCount;
	private Date lastFailedLoginTime;
	private Date previousLoginTime;
	public long getFailedLoginCount() {
		return failedLoginCount;
	}
	public void setFailedLoginCount(long failedLoginCount) {
		this.failedLoginCount = failedLoginCount;
	}
	public long getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(long loginCount) {
		this.loginCount = loginCount;
	}
	public Date getLastFailedLoginTime() {
		return lastFailedLoginTime;
	}
	public void setLastFailedLoginTime(Date lastFailedLoginTime) {
		this.lastFailedLoginTime = lastFailedLoginTime;
	}
	public Date getPreviousLoginTime() {
		return previousLoginTime;
	}
	public void setPreviousLoginTime(Date previousLoginTime) {
		this.previousLoginTime = previousLoginTime;
	}
}
