package com.tinyurl.myurl.model.url;

import com.atlassian.jira.rest.client.api.domain.Version;

public class IssueFields {

    protected ProjectKey project;
    // TODO: limit size
    protected String summary;
    // TODO: limit size
    protected String description;
    protected IssueTypeName issuetype;
    protected String timespent;
    protected String timeoriginalestimate;
    protected Version[] fixVersions;

    public ProjectKey getProject() {
        return project;
    }

    public void setProject(ProjectKey project) {
        this.project = project;
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

    public IssueTypeName getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(IssueTypeName issuetype) {
        this.issuetype = issuetype;
    }
}
