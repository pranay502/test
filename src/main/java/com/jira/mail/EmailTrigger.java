package com.jira.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.activation.DataSource;
import javax.activation.URLDataSource;

import org.apache.log4j.Logger;

import com.jira.mail.service.EMailService;
import com.jira.mail.templates.TemplatePicker;
import com.jira.response.History;
import com.jira.response.Issue;
import com.jira.response.Item;
import com.jira.response.RenderedComment;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class EmailTrigger {
	
	private static Logger log = Logger.getLogger(EmailTrigger.class);
	private static Map<String, String> issueTypeMap;
	private static Map<String, String> priorityMap;
	private static Map<String, String> usersMap;
	
	private Issue issue;
	private History history;
	private Item item1;
	private Item item2;
	private Item item3;
	private RenderedComment comment;
	private Template template;
	
	static{
		issueTypeMap = new HashMap<String, String>();
		issueTypeMap.put("Improvement", "Improvement.jpg");
		issueTypeMap.put("New Feature", "NewFeature.jpg");
		issueTypeMap.put("Task", "Task.jpg");
		issueTypeMap.put("Bug", "Bug.jpg");
		issueTypeMap.put("Sub-task", "Sub-task.jpg");
		issueTypeMap.put("Issue", "Issue.png");
		issueTypeMap.put("Change Request", "ChangeRequest.jpg");
		issueTypeMap.put("Pricing Request", "PricingRequest.jpg");
		issueTypeMap.put("Test Plan", "TestPlan.png");
		issueTypeMap.put("Test Case", "TestPlan.png");
		issueTypeMap.put("Epic", "Epic.jpg");
		issueTypeMap.put("Story", "Strory.jpg");
		issueTypeMap.put("Technical task", "TechnicalTask.png");
		issueTypeMap.put("New Receiver and/or Trader", "Trade.png");
		issueTypeMap.put("Reference Data Amendment", "ChangeRequest.jpg");
		issueTypeMap.put("Trade Amendment", "Trade.png");
		priorityMap = new HashMap<String, String>();
		priorityMap.put("1", "Critical.jpg");
		priorityMap.put("2", "High.jpg");
		priorityMap.put("3", "Medium.jpg");
		priorityMap.put("4", "Low.jpg");
		priorityMap.put("5", "NiceToHave.jpg");
		usersMap = new HashMap<String, String>();
		usersMap.put("aksana.golozhina", "aksana.golozhina.png");
		usersMap.put("padma.samineni", "padma.samineni.png");
		usersMap.put("rohith.bajjuri", "rohith.bajjuri.png");
		usersMap.put("burak.levent", "burak.levent.png");
		usersMap.put("elif.izbirak", "elif.izbirak.png");
		usersMap.put("gul.ozcalik", "gul.ozcalik.png");
		usersMap.put("ivan.tsumarau", "ivan.tsumarau.png");
		usersMap.put("omer.karatas", "omer.karatas.png");
		usersMap.put("ravi.gummadi", "ravi.gummadi.png");
		usersMap.put("unknown.user", "unknown.user.png");
		usersMap.put("kondalarao.suravarapu", "kondalarao.suravarapu.png");
		usersMap.put("hakan.guleryuz", "hakan.guleryuz.png");
		usersMap.put("saikrishna.rayapati", "saikrishna.rayapati.png");
	}
	
	public EmailTrigger(){
	}
	
	public EmailTrigger(Issue issue, Item item1, Item item2, Item item3, RenderedComment comment, History history) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException{
		this.issue = issue;
		this.history = history;
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
		this.comment = comment;
		if(comment != null){
			template = TemplatePicker.pickTemplate("comment");
		} else if(item1 == null){
			template = TemplatePicker.pickTemplate("create");
		} else if (item1 != null && item2 != null) {
			template = TemplatePicker.pickTemplate("update");
		} else if (item1 != null) {
			template = TemplatePicker.pickTemplate(item1.getField());
		}
	}
	
	public void triggerEmail() throws TemplateException, IOException{
		try{
			if(template == null){
				new EMailService("An unknown case found " + issue.getKey(), "<html><body> history items fields are not received as expected, for the issue " + issue.getKey() + ", which is not handled. <br/><br/>Please check this one. " + ((item1 != null) ? "Item type is :: " + item1.getField() : "") + "</body></html>", "Saikrishna").start();
				return;
			}
			Writer out = new StringWriter();
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("issue", issue);
			data.put("history", history);
			data.put("item1", item1);
			data.put("item2", item2);
			data.put("item3", item3);
			data.put("comment", comment);
			if(comment != null){
				if(comment.getCreated().equals(comment.getUpdated()))
					data.put("edited", null);
				else
					data.put("edited", "true");
			} else {
				data.put("edited", null);
			}
			template.process(data, out);
			String mailBody = out.toString();
			EMailService mail = new EMailService("", mailBody, history == null ? "Saikrihna Rayapati" : history.getAuthor().getDisplayName());
			String author = null;
			String authorName = "";
			if(history == null && comment == null){
				author = issue.getFields().getCreator().getKey();
				authorName = issue.getFields().getCreator().getDisplayName();
				log.info("Priority :: " + issue.getFields().getPriority().getId() + ", value = " +priorityMap.get(issue.getFields().getPriority().getId()));
				mail.embedImage("D:/JIRA/images/" + priorityMap.get(issue.getFields().getPriority().getId()), "priority", false);
			} else if(history != null){
				author = history.getAuthor().getKey();
				authorName = history.getAuthor().getDisplayName();
			} else if(comment != null){
				author = comment.getCreated().equals(comment.getUpdated()) ? comment.getAuthor().getKey() : comment.getUpdateAuthor().getKey();
				authorName = comment.getCreated().equals(comment.getUpdated()) ? comment.getAuthor().getDisplayName() : comment.getUpdateAuthor().getDisplayName();
			}
			log.info("Author key = " + author + ", author image name = " + usersMap.get(author));
			String subject = "[JIRA] (" + issue.getKey() + ") " + issue.getFields().getSummary();
			if(author != null){
				if(usersMap.containsKey(author))
					mail.embedImage("D:/JIRA/images/" + usersMap.get(author), "owner", false);
				else
					mail.embedImage("D:/JIRA/images/unknown.user.png", "owner", false);
				String status = "";
				if(comment == null && history == null){
					status = "Created";
				} else if(comment != null){
					status = "Commented";
				} else {
					status = "Updated";
					if(item1 != null){
						if("status".equalsIgnoreCase(item1.getField()))
							status = item1.getToString();
						else if("assignee".equalsIgnoreCase(item1.getField()))
							status = "Assigned";
						
					}
				}
				subject = "[JIRA] (" + issue.getKey() + ") " + status + ": " + authorName + ": " + issue.getFields().getSummary();
			}
			mail.setSubject(subject);
			mail.embedImage("D:/JIRA/images/comment.png", "comment", false);
			mail.embedImage("D:/JIRA/images/" + issueTypeMap.get(issue.getFields().getIssuetype().getName()), "issueType", false);
			mail.start();
		} catch(Exception e){
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public String readSvgFromUrl(String url){
		DataSource logo;
		try {
			logo = new URLDataSource(new URL(url));
		} catch (MalformedURLException e) {
			log.error(e.getMessage(), e);
			return "";
		}
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(logo.getInputStream());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return "";
		}
		BufferedReader br = new BufferedReader(isr);
		String line;
		String imageString = "";
		boolean firstLine = true;
		try {
			while((line=br.readLine()) != null){
				if(!firstLine){
					imageString += line + "\n";
				} else {
					firstLine = false;
				}
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return "";
		}
		return imageString;
	}
}
