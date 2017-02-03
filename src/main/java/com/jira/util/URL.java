package com.jira.util;

public class URL {
	public static final String JIRA_URL = "https://jira.aspone.co.uk";
	public static final String SESSION = JIRA_URL + "/rest/auth/1/session";
	public static final String ISSUE = JIRA_URL + "/rest/api/2/issue/";
	public static final String SEARCH = JIRA_URL + "/rest/api/2/search";
	public static final String SEARCH_QUERY_BH = "project=BH AND updated>'-5m'";
	public static final String SEARCH_QUERY_BHR = "project=BHR AND updated>'-5m'";
	public static final String SEARCH_QUERY_ALL = "project IN (BH,BHR) AND updated>'-5m'";
	public static final String SEARCH_QUERY_ALL_PARAM = "project IN (BH,BHR) AND updated>'-#m'";
}
