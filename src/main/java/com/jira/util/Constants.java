package com.jira.util;

public interface Constants {
	public static int statusCodeSuccess = 200;
	public static int statusCodeBadRequest = 400;
	public static int statusCodeUnAuthorized = 401;
	public static int statusCodeNotFound = 404;
	public static int statusCodeInternalError = 403;
	
	public final String EXTERNAL_PROPERTIES_FILE = "ExternalProperties.properties";
	public final int DEFAULT_REFRESH_MINUTES = 5;
}
