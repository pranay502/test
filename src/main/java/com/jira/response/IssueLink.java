package com.jira.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@XmlRootElement
public class IssueLink {
	private String id;
	private String self;
	private Type type;
	private OutwardIssue outwardIssue;
	private InwardIssue inwardIssue;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public OutwardIssue getOutwardIssue() {
		return outwardIssue;
	}
	public void setOutwardIssue(OutwardIssue outwardIssue) {
		this.outwardIssue = outwardIssue;
	}
	public InwardIssue getInwardIssue() {
		return inwardIssue;
	}
	public void setInwardIssue(InwardIssue inwardIssue) {
		this.inwardIssue = inwardIssue;
	}
}
