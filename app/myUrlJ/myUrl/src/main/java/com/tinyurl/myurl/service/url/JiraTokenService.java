package com.tinyurl.myurl.service.url;

import java.util.List;

import com.tinyurl.myurl.dto.url.IssueDTO;
import com.tinyurl.myurl.dto.url.ProjectDTO;
import com.tinyurl.myurl.dto.url.TicketDTO;

public interface JiraTokenService {
	
	TicketDTO getTikenByJiraBaseUrl(String JIRA_BASE_URL);

	String getAccessToken(String JIRA_BASE_URL, String token, String tokenSecret, String verificationCode);

	List<ProjectDTO> getProjectsFromJiraByUser(String accessToken, String url);

	List<IssueDTO> getIssuesFromJiraByProjectKey(String url, String accessToken, String projectKey);
}
