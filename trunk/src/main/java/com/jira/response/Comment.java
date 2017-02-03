package com.jira.response;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@XmlRootElement
public class Comment {
	private String self;
	private String id;
	private Author author;
	private String body;
	private Author updateAuthor;
	private Date created;
	private Date updated;
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Author getUpdateAuthor() {
		return updateAuthor;
	}
	public void setUpdateAuthor(Author updateAuthor) {
		this.updateAuthor = updateAuthor;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public String getUpdatedDate() {
		return new SimpleDateFormat("d/MMM/YY hh:mm aaa").format(updated);
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
