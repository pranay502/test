package com.jira.mail.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;
import javax.activation.MimetypesFileTypeMap;

public class InputStreamDataSource implements DataSource {

	private InputStream inputStream;
	private String contentType;
	public InputStreamDataSource(InputStream inputStream, String contentType) {
		this.inputStream = inputStream;
		this.contentType = contentType;
	}

	@Override
	public String getContentType() {
		return new MimetypesFileTypeMap().getContentType(contentType);
	}

	@Override
	public InputStream getInputStream() {
		return inputStream;
	}

	@Override
	public String getName() {
		return contentType;
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return null;
	}

}
