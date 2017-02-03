package com.jira.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@XmlRootElement
public class Item {
	private String field;
	private String fieldtype;
	private String from;
	private String fromString;
	private String to;
	private String toString;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getFieldtype() {
		return fieldtype;
	}
	public void setFieldtype(String fieldType) {
		this.fieldtype = fieldType;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getFromString() {
		return fromString;
	}
	public void setFromString(String fromString) {
		this.fromString = fromString;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getToString() {
		return toString;
	}
	public String getToStringName() {
		return toString;
	}
	public void setToString(String toString) {
		this.toString = toString;
	}
}
