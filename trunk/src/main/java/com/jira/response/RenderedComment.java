package com.jira.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@XmlRootElement
public class RenderedComment {
	private String self;
	private String id;
	private Author author;
	private String body;
	private Author updateAuthor;
	private String created;
	private String updated;
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
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getUpdated() {
		return updated;
	}
	public String getUpdatedDate() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
}
