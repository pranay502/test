package com.jira.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.jira.mail.EmailTrigger;
import com.jira.mail.service.EMailService;
import com.jira.response.Comment;
import com.jira.response.History;
import com.jira.response.Issue;
import com.jira.response.IssueList;
import com.jira.response.Item;
import com.jira.util.Constants;
import com.jira.util.PropertyLoader;
import com.jira.util.URL;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class NotificationService extends TimerTask {

	private static Logger log = Logger.getLogger(NotificationService.class);
	private static IssueNavigator navigator = new IssueNavigator();
	private int refreshMinutes = 5;
	@Override
	public void run() {
		try{
			Properties properties = PropertyLoader.getInstance().getExternalProperties();
			String minsString = properties.get("QUERY_REFRESH_MINUTES").toString();
			refreshMinutes = minsString != null && !"".equals(minsString.trim())? Integer.parseInt(minsString) : Constants.DEFAULT_REFRESH_MINUTES;
			String query = URL.SEARCH_QUERY_ALL_PARAM.replaceAll("#", ""+refreshMinutes);
			IssueList issueList = navigator.search(query, null);
			if(issueList == null || issueList.getTotal() <= 0){
				log.info("No issues found");
				return;
			}
			log.info("Total issues found :: " + issueList.getIssues().length);
			Issue[] issues = filterIssuesByWatcher(issueList);
			if(issues == null || issues.length <= 0)
				return;
			triggerIssueCreatedNotification(issues);
			for(Issue issue : issues){
				triggerCommentNotification(issue, issue.getFields().getComment().getComments());
				triggerUpdateNotification(issue, issue.getChangelog().getHistories());
			}
		} catch(Exception e){
			log.error(e.getMessage(), e);
		}
	}
	
	private Issue[] filterIssuesByWatcher(IssueList issueList){
		List<Issue> issues = new ArrayList<Issue>();
		for(Issue issue : issueList.getIssues()){
			if(!issue.getFields().getWatches().isWatching()){
				issues.add(navigator.getIssue(issue.getSelf(), null, "expand=changelog,renderedFields"));
			}
		}
		return issues.toArray(new Issue[issues.size()]);
	}
	
	public void triggerIssueCreatedNotification(Issue[] issueList){
		for(Issue issue : issueList){
			if(checkMinutesDiff(issue.getFields().getCreated())){
				try {
					log.info("Sending issue created notification for " + issue.getKey() + " with created date " + issue.getFields().getCreatedDate());
					new EmailTrigger(issue, null, null, null, null, null).triggerEmail();
				} catch (TemplateNotFoundException|MalformedTemplateNameException|ParseException|TemplateException e) {
					log.error(e.getMessage(), e);
					triggerErrorNotification(e, issue.getKey(), "Create");
				} catch(IOException e){
					log.error(e.getMessage(), e);
					triggerErrorNotification(e, issue.getKey(), "Create");
				}
				
			}
		}
	}
	
	public void triggerCommentNotification(Issue issue, Comment[] comments){
		if(comments == null || comments.length <= 0){
			return;
		}
		int i = 0;
		for(Comment comment : comments){
			if(checkMinutesDiff(comment.getCreated()) || checkMinutesDiff(comment.getUpdated())){
				try {
					log.info("Sending issue comment notification for " + issue.getKey() + " with updated date " + comment.getUpdatedDate());
					new EmailTrigger(issue, null, null, null, issue.getRenderedFields().getComment().getComments()[i], null).triggerEmail();
				} catch (TemplateNotFoundException|MalformedTemplateNameException|ParseException|TemplateException e) {
					log.error(e.getMessage(), e);
					triggerErrorNotification(e, issue.getKey(), "Comment");
				} catch(IOException e){
					log.error(e.getMessage(), e);
					triggerErrorNotification(e, issue.getKey(), "Comment");
				}
			}
			i++;
		}
	}
	
	public void triggerUpdateNotification(Issue issue, History[] histories){
		if(histories == null || histories.length <= 0)
			return;
		for(History history : histories){
			if(checkMinutesDiff(history.getCreated())){
				Item[] items = history.getItems();
				try{
					log.info("Sending issue update notification for " + issue.getKey() + " with updated date " + history.getCreatedDate());
					if(items == null || items.length <= 0){
						continue;
					} else if(items.length ==3){
						items = sortHistoryItems(items);
						new EmailTrigger(issue, items[0], items[1], items[2], null, history).triggerEmail();
					} else if(items.length == 1){
						new EmailTrigger(issue, items[0], null, null, null, history).triggerEmail();
					} else if(items.length > 1){
						if("Fix Version".equalsIgnoreCase(items[0].getField())){
							new EmailTrigger(issue, items[0], items[1], null, null, history).triggerEmail();
						} if("Component".equalsIgnoreCase(items[0].getField())){
							new EmailTrigger(issue, items[0], null, null, null, history).triggerEmail();
						} else {
							items = sortHistoryItems(items);
							if(items[0] == null || items[1] == null){
								sendUpdateNotificationIndividually(issue, history, items);
							}
							new EmailTrigger(issue, items[0], items[1], null, null, history).triggerEmail();
						}
					} else {
						sendUpdateNotificationIndividually(issue, history, items);
					}
				} catch (TemplateNotFoundException|MalformedTemplateNameException|ParseException|TemplateException e) {
					log.error(e.getMessage(), e);
					triggerErrorNotification(e, issue.getKey(), "Update");
				} catch(IOException e){
					log.error(e.getMessage(), e);
					triggerErrorNotification(e, issue.getKey(), "Update");
				}
			}
		}
	}
	
	private Item[] sortHistoryItems(Item[] items){
		Item[] sortedItems = new Item[3];
		for(Item item : items){
			if("assignee".equalsIgnoreCase(item.getField()))
				sortedItems[2] = item;
			else if("resolution".equalsIgnoreCase(item.getField())){
				sortedItems[1] = item;
			} else if("status".equalsIgnoreCase(item.getField())){
				sortedItems[0] = item;
			}
		}
		return sortedItems;
	}
	
	private boolean checkMinutesDiff(Date date){
		Date currDate = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("d/MM/YY");
		if(!dateFormat.format(currDate).equals(dateFormat.format(date)))
			return false;
		long diff = 0;
		if(currDate.getTime() > date.getTime())
			diff = (currDate.getTime() - date.getTime()) / (60*1000) % 60;
		else 
			diff = (date.getTime() - currDate.getTime()) / (60*1000) % 60;
		log.info("Diff is :: " + diff + " and date time is :: " + new SimpleDateFormat("d/MMM/YY hh:mm aaa").format(date));
		return diff <= refreshMinutes;
	}
	
	private void sendUpdateNotificationIndividually(Issue issue, History history, Item[] items){
		for(Item item : items){
			try {
				new EmailTrigger(issue, item, null, null, null, history).triggerEmail();
			} catch (TemplateException | IOException e) {
				log.error(e.getMessage(), e);
			}
		}
	}
	
	public static void main(String[] args){
		/*IssueList issueList = navigator.search("project IN (BH,BHR) AND updated>'-15m'", null);
		if(issueList == null || issueList.getTotal() <= 0)
			return;
		NotificationService service = new NotificationService();
		Issue[] issues = service.filterIssuesByWatcher(issueList);
		service.triggerIssueCreatedNotification(issues);
		for(Issue issue : issues){
			service.triggerCommentNotification(issue, issue.getFields().getComment().getComments());
			service.triggerUpdateNotification(issue, issue.getChangelog().getHistories());
		}*/
		
		Timer timer = new Timer();
		timer.schedule(new NotificationService(), 0, Constants.DEFAULT_REFRESH_MINUTES * 60 * 1000);
		
		/*NotificationService service = new NotificationService();
		Issue issue = navigator.getIssue(null, "BHR-1890", "expand=changelog,renderedFields");
		service.triggerIssueCreatedNotification(new Issue[]{issue});*/
	}
	
	public void triggerErrorNotification(Exception e, String issueId, String notificationType){
		String body = "<html><body>";
		body += "<p> Error Message: " + e.getMessage() + "</p>";
		body += "<br/><br/><p> StackTrace:<span style='font-color:red'>";
		for(Object trace : e.getStackTrace()){
			body += trace.toString();
		}
		body += "</span></p>";
		if(issueId != null){
			body += "<br/><br/> for issue: " + issueId;
			body += "<br/> while sending " + notificationType + " notification.";
		}
		try {
			new EMailService("Failed to send JIRA notification " + (issueId != null ? issueId : ""), body, "Saikrishna").start();;
		} catch (IOException e1) {
			log.error(e1.getMessage(), e1);
		}
	}
	
}
