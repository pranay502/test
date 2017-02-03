package com.jira.mail.templates;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.jira.util.PropertyLoader;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public class TemplatePicker {
	
	private static Map<String, String> templateFileNames = new HashMap<String, String>();
	private static Map<String, Template> templatePicker = new HashMap<String, Template>();
	
	private static Logger log = Logger.getLogger(TemplatePicker.class);
	private static Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
	
	private static Properties properties = null;
	
	static {
		try {
			properties = PropertyLoader.getInstance().getExternalProperties();
			templateFileNames.put("assignee", "Assignee.ftl");
			templateFileNames.put("create", "CreateIssue.ftl");
			templateFileNames.put("update", "UpdateIssue.ftl");
			templateFileNames.put("status", "UpdateIssue.ftl");
			templateFileNames.put("comment", "Comment.ftl");
			templateFileNames.put("description", "Description.ftl");
			templateFileNames.put("attachment", "Attachment.ftl");
			templateFileNames.put("sprint", "Attachment.ftl");
			templateFileNames.put("rank", "Attachment.ftl");
			templateFileNames.put("link", "Link.ftl");
			templateFileNames.put("fix version", "FixVersion.ftl");
			templateFileNames.put("issuetype", "IssueType.ftl");
			templateFileNames.put("summary", "Summary.ftl");
			templateFileNames.put("component", "Component.ftl");
			cfg.setDirectoryForTemplateLoading(new File(properties.getProperty("TEMPLATE_DIRECTORY")));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setLogTemplateExceptions(false);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	public static Template pickTemplate(String field) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException{
		if(templateFileNames.containsKey(field.toLowerCase())){
			Template template = null;
			if(templatePicker.containsKey(templateFileNames.get(field.toLowerCase()))
					&& (template = templatePicker.get(templateFileNames.get(field.toLowerCase()))) != null){
				return template;
			} else {
				template = cfg.getTemplate(templateFileNames.get(field.toLowerCase()));
				templatePicker.put(field.toLowerCase(), template);
				return template;
			}
		}
		return null;
	}
}
