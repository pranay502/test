package com.jira.response;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@XmlRootElement
public class Fields {
	private IssueType issuetype;
	private String timespent;
	private String timeoriginalestimate;
	private String description;
	private Project project;
	private String aggregatetimespent;
	private Resolution resolution;
	private Date resolutiondate;
	private long workratio;
	private TimeTracking timetracking;
	private String customfield_11017;
	private Attachment[] attachment;
	private String aggregatetimeestimate;
	private Date resolutionDate;
	private long workRatio;
	private String summary;
	private Date lastViewed;
	private Watches watches;
	private Author creator;
	private Component[] components;
	private Issue[] subtasks;
	private String customfield_10083;
	private Date created;
	private String[] customfield_11010;
	private String customfield_11011;
	private Author reporter;
	private String customfield_11110;
	private String customfield_11012;
	private String customfield_11210;
	private String customfield_11020;
	private String customfield_10210;
	private String customfield_10211;
	private String customfield_10212;
	private String customfield_11016;
	private String customfield_10710;
	private String customfield_11018;
	private String customfield_11019;
	private String customfield_10076;
	private String customfield_10077;
	private String customfield_10078;
	private String customfield_10079;
	private CustomField customfield_10711;
	private Progress aggregateprogress;
	private Priority priority;
	private Label[] lables;
	private String customField_11016;
	private String environment;
	private String timeestimate;
	private String aggregatetimeoriginalestimate;
	private FixVersion[] versions;
	private Date duedate;
	private Progress progress;
	private Comments comment;
	private IssueLink[] issuelinks;
	private Votes votes;
	private WorkLog worklog;
	private Author assignee;
	private Date updated;
	private Status status;
	private FixVersion fixVersions[];
	private Issue parent;
	private CustomField customfield_10001;
	private String[] labels;
	private CustomField customfield_11013;
	public IssueType getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(IssueType issuetype) {
		this.issuetype = issuetype;
	}
	public String getTimespent() {
		return timespent;
	}
	public void setTimespent(String timeSpent) {
		this.timespent = timeSpent;
	}
	public String getTimeoriginalestimate() {
		return timeoriginalestimate;
	}
	public void setTimeoriginalestimate(String timeOriginalEstimate) {
		this.timeoriginalestimate = timeOriginalEstimate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getAggregatetimespent() {
		return aggregatetimespent;
	}
	public void setAggregatetimespent(String aggregateTimeSpent) {
		this.aggregatetimespent = aggregateTimeSpent;
	}
	public Resolution getResolution() {
		return resolution;
	}
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}
	public TimeTracking getTimetracking() {
		return timetracking;
	}
	public void setTimetracking(TimeTracking timeTracking) {
		this.timetracking = timeTracking;
	}
	public String getCustomfield_11017() {
		return customfield_11017;
	}
	public void setCustomfield_11017(String customfield_11017) {
		this.customfield_11017 = customfield_11017;
	}
	public Attachment[] getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment[] attachment) {
		this.attachment = attachment;
	}
	public String getAggregatetimeestimate() {
		return aggregatetimeestimate;
	}
	public void setAggregatetimeestimate(String aggregateTimeEstimate) {
		this.aggregatetimeestimate = aggregateTimeEstimate;
	}
	public Date getResolutionDate() {
		return resolutionDate;
	}
	public void setResolutionDate(Date resolutionDate) {
		this.resolutionDate = resolutionDate;
	}
	public long getWorkRatio() {
		return workRatio;
	}
	public void setWorkRatio(long workRatio) {
		this.workRatio = workRatio;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Date getLastViewed() {
		return lastViewed;
	}
	public void setLastViewed(Date lastViewed) {
		this.lastViewed = lastViewed;
	}
	public Watches getWatches() {
		return watches;
	}
	public void setWatches(Watches watches) {
		this.watches = watches;
	}
	public Author getCreator() {
		return creator;
	}
	public void setCreator(Author creator) {
		this.creator = creator;
	}
	public Issue[] getSubtasks() {
		return subtasks;
	}
	public void setSubtasks(Issue[] subtasks) {
		this.subtasks = subtasks;
	}
	public String getCustomfield_10083() {
		return customfield_10083;
	}
	public void setCustomfield_10083(String customfield_10083) {
		this.customfield_10083 = customfield_10083;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String[] getCustomfield_11010() {
		return customfield_11010;
	}
	public void setCustomfield_11010(String[] customfield_11010) {
		this.customfield_11010 = customfield_11010;
	}
	public String getCustomfield_11011() {
		return customfield_11011;
	}
	public void setCustomfield_11011(String customfield_11011) {
		this.customfield_11011 = customfield_11011;
	}
	public Author getReporter() {
		return reporter;
	}
	public void setReporter(Author reporter) {
		this.reporter = reporter;
	}
	public String getCustomfield_11110() {
		return customfield_11110;
	}
	public void setCustomfield_11110(String customfield_11110) {
		this.customfield_11110 = customfield_11110;
	}
	public String getCustomfield_11012() {
		return customfield_11012;
	}
	public void setCustomfield_11012(String customfield_11012) {
		this.customfield_11012 = customfield_11012;
	}
	public String getCustomfield_11210() {
		return customfield_11210;
	}
	public void setCustomfield_11210(String customfield_11210) {
		this.customfield_11210 = customfield_11210;
	}
	public Progress getAggregateprogress() {
		return aggregateprogress;
	}
	public void setAggregateprogress(Progress aggregateProgress) {
		this.aggregateprogress = aggregateProgress;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public Label[] getLables() {
		return lables;
	}
	public void setLables(Label[] lables) {
		this.lables = lables;
	}
	public String getCustomField_11016() {
		return customField_11016;
	}
	public void setCustomField_11016(String customField_11016) {
		this.customField_11016 = customField_11016;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getTimeestimate() {
		return timeestimate;
	}
	public void setTimeestimate(String timeEstimate) {
		this.timeestimate = timeEstimate;
	}
	public String getAggregatetimeoriginalestimate() {
		return aggregatetimeoriginalestimate;
	}
	public void setAggregatetimeoriginalestimate(String aggregateTimeoriginalEstimate) {
		this.aggregatetimeoriginalestimate = aggregateTimeoriginalEstimate;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date dueDate) {
		this.duedate = dueDate;
	}
	public Progress getProgress() {
		return progress;
	}
	public void setProgress(Progress progress) {
		this.progress = progress;
	}
	public Comments getComment() {
		return comment;
	}
	public void setComment(Comments comment) {
		this.comment = comment;
	}
	public IssueLink[] getIssuelinks() {
		return issuelinks;
	}
	public void setIssuelinks(IssueLink[] issueLinks) {
		this.issuelinks = issueLinks;
	}
	public Votes getVotes() {
		return votes;
	}
	public void setVotes(Votes votes) {
		this.votes = votes;
	}
	public WorkLog getWorklog() {
		return worklog;
	}
	public void setWorklog(WorkLog workLog) {
		this.worklog = workLog;
	}
	public Author getAssignee() {
		return assignee;
	}
	public void setAssignee(Author assignee) {
		this.assignee = assignee;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public FixVersion[] getFixVersions() {
		return fixVersions;
	}
	public void setFixVersions(FixVersion[] fixVersions) {
		this.fixVersions = fixVersions;
	}
	public Date getResolutiondate() {
		return resolutiondate;
	}
	public void setResolutiondate(Date resolutiondate) {
		this.resolutiondate = resolutiondate;
	}
	public long getWorkratio() {
		return workratio;
	}
	public void setWorkratio(long workratio) {
		this.workratio = workratio;
	}
	public FixVersion[] getVersions() {
		return versions;
	}
	public void setVersions(FixVersion[] versions) {
		this.versions = versions;
	}
	public Component[] getComponents() {
		return components;
	}
	public void setComponents(Component[] components) {
		this.components = components;
	}
	public String getCustomfield_11020() {
		return customfield_11020;
	}
	public void setCustomfield_11020(String customfield_11020) {
		this.customfield_11020 = customfield_11020;
	}
	public String getCustomfield_10210() {
		return customfield_10210;
	}
	public void setCustomfield_10210(String customfield_10210) {
		this.customfield_10210 = customfield_10210;
	}
	public String getCustomfield_10211() {
		return customfield_10211;
	}
	public void setCustomfield_10211(String customfield_10211) {
		this.customfield_10211 = customfield_10211;
	}
	public String getCustomfield_10212() {
		return customfield_10212;
	}
	public void setCustomfield_10212(String customfield_10212) {
		this.customfield_10212 = customfield_10212;
	}
	public String getCustomfield_11016() {
		return customfield_11016;
	}
	public void setCustomfield_11016(String customfield_11016) {
		this.customfield_11016 = customfield_11016;
	}
	public String getCustomfield_10710() {
		return customfield_10710;
	}
	public void setCustomfield_10710(String customfield_10710) {
		this.customfield_10710 = customfield_10710;
	}
	public CustomField getCustomfield_10711() {
		return customfield_10711;
	}
	public void setCustomfield_10711(CustomField customfield_10711) {
		this.customfield_10711 = customfield_10711;
	}
	public Issue getParent() {
		return parent;
	}
	public void setParent(Issue parent) {
		this.parent = parent;
	}
	public CustomField getCustomfield_10001() {
		return customfield_10001;
	}
	public void setCustomfield_10001(CustomField customfield_10001) {
		this.customfield_10001 = customfield_10001;
	}
	public String[] getLabels() {
		return labels;
	}
	public void setLabels(String[] labels) {
		this.labels = labels;
	}
	public String getCreatedDate(){
		return new SimpleDateFormat("d/MMM/YY hh:mm aaa").format(created);
	}
	public String getCustomfield_11018() {
		return customfield_11018;
	}
	public void setCustomfield_11018(String customfield_11018) {
		this.customfield_11018 = customfield_11018;
	}
	public String getCustomfield_11019() {
		return customfield_11019;
	}
	public void setCustomfield_11019(String customfield_11019) {
		this.customfield_11019 = customfield_11019;
	}
	public String getCustomfield_10076() {
		return customfield_10076;
	}
	public void setCustomfield_10076(String customfield_10076) {
		this.customfield_10076 = customfield_10076;
	}
	public String getCustomfield_10077() {
		return customfield_10077;
	}
	public void setCustomfield_10077(String customfield_10077) {
		this.customfield_10077 = customfield_10077;
	}
	public String getCustomfield_10078() {
		return customfield_10078;
	}
	public void setCustomfield_10078(String customfield_10078) {
		this.customfield_10078 = customfield_10078;
	}
	public String getCustomfield_10079() {
		return customfield_10079;
	}
	public void setCustomfield_10079(String customfield_10079) {
		this.customfield_10079 = customfield_10079;
	}
	public CustomField getCustomfield_11013() {
		return customfield_11013;
	}
	public void setCustomfield_11013(CustomField customfield_11013) {
		this.customfield_11013 = customfield_11013;
	}
}
