package com.jira.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@XmlRootElement
@JsonAutoDetect
public class AuthenticateUser {
	private Session session;
	private LoginInfo loginInfo;
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
	public void setLogininfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
}
