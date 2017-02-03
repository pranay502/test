package com.jira.response;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@XmlRootElement
public class History {
	private String id;
	private Author author;
	private Date created;
	private Item[] items;
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
	public Date getCreated() {
		return created;
	}
	public String getCreatedDate() {
		return new SimpleDateFormat("d/MMM/YY hh:mm aaa").format(created);
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Item[] getItems() {
		return items;
	}
	public void setItems(Item[] items) {
		this.items = items;
	}
}
