package com.jira.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@XmlRootElement
public class Issue {
	private String expand;
	private String id;
	private String self;
	private String key;
	private Fields fields;
	private ChangeLog changelog;
	private RenderedFields renderedFields;
	public String getExpand() {
		return expand;
	}
	public void setExpand(String expand) {
		this.expand = expand;
	}
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Fields getFields() {
		return fields;
	}
	public void setFields(Fields fields) {
		this.fields = fields;
	}
	public ChangeLog getChangelog() {
		return changelog;
	}
	public void setChangelog(ChangeLog changeLog) {
		this.changelog = changeLog;
	}
	public RenderedFields getRenderedFields() {
		return renderedFields;
	}
	public void setRenderedFields(RenderedFields renderedFields) {
		this.renderedFields = renderedFields;
	}
}
