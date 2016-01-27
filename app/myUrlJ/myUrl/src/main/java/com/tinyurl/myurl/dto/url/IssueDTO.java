package com.tinyurl.myurl.dto.url;

import java.io.Serializable;
import java.net.URI;
import java.util.Collection;
import java.util.Set;

import org.joda.time.DateTime;

import com.atlassian.jira.rest.client.api.domain.Attachment;
import com.atlassian.jira.rest.client.api.domain.BasicComponent;
import com.atlassian.jira.rest.client.api.domain.BasicPriority;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.BasicVotes;
import com.atlassian.jira.rest.client.api.domain.BasicWatchers;
import com.atlassian.jira.rest.client.api.domain.ChangelogGroup;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueField;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.IssueType;
import com.atlassian.jira.rest.client.api.domain.Operations;
import com.atlassian.jira.rest.client.api.domain.Resolution;
import com.atlassian.jira.rest.client.api.domain.Status;
import com.atlassian.jira.rest.client.api.domain.Subtask;
import com.atlassian.jira.rest.client.api.domain.TimeTracking;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.api.domain.Version;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import com.atlassian.util.concurrent.Nullable;

public class IssueDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2388422638724473196L;
	private Status status;
	private IssueType issueType;
	private BasicProject project;
	private URI transitionsUri;
	private Iterable<String> expandos;
	private Collection<BasicComponent> components;
	private String summary;
	@Nullable
	private String description;
	@Nullable
	private User reporter;
	private User assignee;
	@Nullable
	private Resolution resolution;
	private Collection<IssueField> issueFields;
	private DateTime creationDate;
	private DateTime updateDate;
	private DateTime dueDate;
	private BasicPriority priority;
	private BasicVotes votes;
	@Nullable
	private Collection<Version> fixVersions;
	@Nullable
	private Collection<Version> affectedVersions;

	private Collection<Comment> comments;

	@Nullable
	private Collection<IssueLink> issueLinks;

	private Collection<Attachment> attachments;

	private Collection<Worklog> worklogs;
	private BasicWatchers watchers;

	@Nullable
	private TimeTracking timeTracking;
	@Nullable
	private Collection<Subtask> subtasks;
	@Nullable
	private Collection<ChangelogGroup> changelog;
	@Nullable
	private Operations operations;
	private Set<String> labels;
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public IssueType getIssueType() {
		return issueType;
	}
	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}
	public BasicProject getProject() {
		return project;
	}
	public void setProject(BasicProject project) {
		this.project = project;
	}
	public URI getTransitionsUri() {
		return transitionsUri;
	}
	public void setTransitionsUri(URI transitionsUri) {
		this.transitionsUri = transitionsUri;
	}
	public Iterable<String> getExpandos() {
		return expandos;
	}
	public void setExpandos(Iterable<String> expandos) {
		this.expandos = expandos;
	}
	public Collection<BasicComponent> getComponents() {
		return components;
	}
	public void setComponents(Collection<BasicComponent> components) {
		this.components = components;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getReporter() {
		return reporter;
	}
	public void setReporter(User reporter) {
		this.reporter = reporter;
	}
	public User getAssignee() {
		return assignee;
	}
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}
	public Resolution getResolution() {
		return resolution;
	}
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}
	public Collection<IssueField> getIssueFields() {
		return issueFields;
	}
	public void setIssueFields(Collection<IssueField> issueFields) {
		this.issueFields = issueFields;
	}
	public DateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}
	public DateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(DateTime updateDate) {
		this.updateDate = updateDate;
	}
	public DateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(DateTime dueDate) {
		this.dueDate = dueDate;
	}
	public BasicPriority getPriority() {
		return priority;
	}
	public void setPriority(BasicPriority priority) {
		this.priority = priority;
	}
	public BasicVotes getVotes() {
		return votes;
	}
	public void setVotes(BasicVotes votes) {
		this.votes = votes;
	}
	public Collection<Version> getFixVersions() {
		return fixVersions;
	}
	public void setFixVersions(Collection<Version> fixVersions) {
		this.fixVersions = fixVersions;
	}
	public Collection<Version> getAffectedVersions() {
		return affectedVersions;
	}
	public void setAffectedVersions(Collection<Version> affectedVersions) {
		this.affectedVersions = affectedVersions;
	}
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	public Collection<IssueLink> getIssueLinks() {
		return issueLinks;
	}
	public void setIssueLinks(Collection<IssueLink> issueLinks) {
		this.issueLinks = issueLinks;
	}
	public Collection<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(Collection<Attachment> attachments) {
		this.attachments = attachments;
	}
	public Collection<Worklog> getWorklogs() {
		return worklogs;
	}
	public void setWorklogs(Collection<Worklog> worklogs) {
		this.worklogs = worklogs;
	}
	public BasicWatchers getWatchers() {
		return watchers;
	}
	public void setWatchers(BasicWatchers watchers) {
		this.watchers = watchers;
	}
	public TimeTracking getTimeTracking() {
		return timeTracking;
	}
	public void setTimeTracking(TimeTracking timeTracking) {
		this.timeTracking = timeTracking;
	}
	public Collection<Subtask> getSubtasks() {
		return subtasks;
	}
	public void setSubtasks(Collection<Subtask> subtasks) {
		this.subtasks = subtasks;
	}
	public Collection<ChangelogGroup> getChangelog() {
		return changelog;
	}
	public void setChangelog(Collection<ChangelogGroup> changelog) {
		this.changelog = changelog;
	}
	public Operations getOperations() {
		return operations;
	}
	public void setOperations(Operations operations) {
		this.operations = operations;
	}
	public Set<String> getLabels() {
		return labels;
	}
	public void setLabels(Set<String> labels) {
		this.labels = labels;
	}
	

	public static IssueDTO map(Issue issue) {
		IssueDTO dto = new IssueDTO();

		if (issue != null) {
			//to do map issue
		}

		return dto;
	}

	
}
