package com.tinyurl.myurl.service.url.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.tinyurl.myurl.dto.url.IssueDTO;
import com.tinyurl.myurl.dto.url.ProjectDTO;
import com.tinyurl.myurl.dto.url.TicketDTO;
import com.tinyurl.myurl.model.url.Project;
import com.tinyurl.myurl.model.url.Ticket;
import com.tinyurl.myurl.service.url.JiraTokenService;

@Service("jiraTokenService")
public class JiraTokenServiceImpl implements JiraTokenService {

	private BufferedReader prepareBufferedReader(String command) {
		String workingDirectory = System.getProperty("user.dir");
		ProcessBuilder builder = new ProcessBuilder(
				"cmd.exe",
				"/c",
				"cd \""
						+ workingDirectory
						+ "\\lib\" && java -jar rest-oauth-client-1.0.one-jar.jar "+ command) ;
		builder.redirectErrorStream(true);
		Process p = null;
		try {
			p = builder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		
		return r;
	}
	@Override
	public TicketDTO getTikenByJiraBaseUrl(String JIRA_BASE_URL) {
		TicketDTO dto = new TicketDTO();
		Ticket requestTicket = null;
		String callbackUrl = "http://localhost:8080/myUrlUI/";
		String command = "requestToken " + JIRA_BASE_URL + " " + callbackUrl;
		BufferedReader r = prepareBufferedReader(command);
		String token = null;
		String tokenSecret = null;
		String redirectUrl = null;
		while (true) {
			try {
				token = r.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (token != null) {
				token = token.split("Token is ")[1].trim();
			}
			else { break; }
			
			try {
				tokenSecret = r.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (tokenSecret != null) { 
				tokenSecret = tokenSecret.split("Token secret is ")[1].trim();
			}
			else { break; }
			try {
				redirectUrl = r.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (redirectUrl != null) {
				redirectUrl = redirectUrl.split("Retrieved request token. go to ")[1].trim();
			}
			else { break; }
			requestTicket = new Ticket();
			requestTicket.setToken(token);
			requestTicket.setTokenSecret(tokenSecret);
			requestTicket.setRedirectUrl(redirectUrl);
			
			
		}
		dto = TicketDTO.map(requestTicket);
	
		return dto;
	}

	@Override
	public String getAccessToken(String JIRA_BASE_URL, String token, String tokenSecret, String verificationCode) {
		String accessToken = "";
		String command = "accessToken "+ JIRA_BASE_URL+ " " + token + " " + tokenSecret +" " + verificationCode;
		BufferedReader r = prepareBufferedReader(command);
		String line = null;
		try {
			while ((line = r.readLine()) != null) {
				accessToken = line;
				if (accessToken != null && accessToken != "") {
					accessToken = accessToken.split("Access token is : ")[1].trim();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accessToken;
	}
	@Override
	public List<ProjectDTO> getProjectsFromJiraByUser(String accessToken, String url) {
		String command = "request "+ accessToken + " " + url + "rest/api/2/project";
		BufferedReader r = prepareBufferedReader(command);
		List<ProjectDTO> dtoList = new ArrayList<ProjectDTO>();
		List<Project> projects = null;
		String response = "";
		String line = null;
		    try {
				while ((line = r.readLine()) != null) {
					response = line;
					if(response != null) {
						response = response.replace("RESPONSE IS", "").trim();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    ObjectMapper mapper = new ObjectMapper();
		    mapper.configure(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		    try {
		    	projects = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, Project.class));
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    for (Project p : projects) {
		    	dtoList.add(ProjectDTO.map(p));
		    }
		return dtoList;
	}
	@Override
	public List<IssueDTO> getIssuesFromJiraByProjectKey(String url, String accessToken,
			String projectKey) {
		
		String command = "request "+ accessToken + " " + url + "rest/api/2/search?jql=project=" + projectKey +" and type=bug";
		BufferedReader r = prepareBufferedReader(command);
		List<IssueDTO> dtoList = new ArrayList<IssueDTO>();
		List<Issue> issues = null;
		String response = "";
		String line = null;
		    try {
				while ((line = r.readLine()) != null) {
					response = line;
					if(response != null) {
						response = response.replace("RESPONSE IS", "").trim();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    ObjectMapper mapper = new ObjectMapper();
		    mapper.configure(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		    try {
				issues = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, Issue.class));
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    for (Issue i : issues) {
		    	dtoList.add(IssueDTO.map(i));
		    }
		return null;
	}

}
