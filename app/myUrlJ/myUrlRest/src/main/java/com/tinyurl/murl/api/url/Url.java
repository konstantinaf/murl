package com.tinyurl.murl.api.url;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinyurl.murl.api.BaseService;
import com.tinyurl.myurl.dto.ServiceResponseDTO;
import com.tinyurl.myurl.dto.url.TicketDTO;
import com.tinyurl.myurl.dto.url.UrlDTO;
import com.tinyurl.myurl.service.url.JiraTokenService;

@Component
@Path("/api/url")
public class Url extends BaseService {

	private static final Logger LOGGER = Logger.getLogger(Url.class.getName());
	protected static final String ERROR_GET_URL = "CANNOT_READ_URL";
	protected static final String ERROR_CODES_JSON_PARSE_ERROR = "ERROR_CODES_JSON_PARSE_ERROR";
	protected static final String ERROR_CODES_IO_ERROR = "ERROR_CODES_IO_ERROR";

	@Autowired
	JiraTokenService jiraTokenService;

	@POST
	@Path("/requestToken/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponseDTO requestToken(InputStream inputStream)
			throws MalformedURLException {
		String json = getJSON(inputStream);
		ObjectMapper mapper = new ObjectMapper();
		ServiceResponseDTO result = new ServiceResponseDTO();
		try {
			mapper.setSerializationInclusion(Include.NON_NULL);
			UrlDTO dto = mapper.readValue(json, UrlDTO.class);

			result.setError(false);
			result.setError_code("");
			TicketDTO ticketDTO = jiraTokenService.getTikenByJiraBaseUrl(dto
					.getUrl());
			try {
				result.setData(ticketDTO);
			} catch (Exception e) {
				LOGGER.log(Level.WARNING, SERVICE_EXCEPTION, e);
				result.setError(true);
				result.setError_code(e.toString());
			}
		} catch (IOException e) {
			result.setError(true);
			result.setError_code(e.toString());
			LOGGER.log(Level.WARNING, SERVICE_EXCEPTION, e);
		} catch (NumberFormatException e) {
			result.setError(true);
			result.setError_code(e.toString());
			LOGGER.log(Level.WARNING, SERVICE_EXCEPTION, e);
		}

		return result;

	}
	
	@POST
	@Path("/accessToken/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponseDTO accessToken(HttpServletRequest request, @RequestParam(value = "url") String url, @RequestParam(value = "token") String token, @RequestParam(value = "tokenSecret") String tokenSecret, @RequestParam(value = "verificationCode") String verificationCode) throws MalformedURLException {
		
		ServiceResponseDTO result = new ServiceResponseDTO();
		try {
			
			HttpSession session = request.getSession();
			String accessToken = jiraTokenService.getAccessToken(url, token, tokenSecret, verificationCode);
			
			if (accessToken == null || accessToken == "") {
				result.setError(true);
				result.setError_code("No access Token provided. Something went wrong while trying to obtain access token.");
			} else {
				result.setError(false);
				result.setError_code("");
				result.setData(jiraTokenService.getProjectsFromJiraByUser(accessToken, url));
				session.setAttribute("accessToken", accessToken);
			}
			
		} catch (NumberFormatException e) {
			result.setError(true);
			result.setError_code(e.toString());
			LOGGER.log(Level.WARNING, SERVICE_EXCEPTION, e);
		}

		return result;

	}
	
	@POST
	@Path("/getIssues/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponseDTO getIssues(HttpServletRequest request, @RequestParam(value = "key") String projectKey, @RequestParam(value="url") String url) throws MalformedURLException {
		
		ServiceResponseDTO result = new ServiceResponseDTO();
		try {
			
			HttpSession session = request.getSession();
			String accessToken = session.getAttribute("accessToken").toString();
			
			if (accessToken == null || accessToken == "") {
				result.setError(true);
				result.setError_code("No access Token provided. Something went wrong while trying to obtain access token.");
			} else {
				result.setError(false);
				result.setError_code("");
				result.setData(jiraTokenService.getIssuesFromJiraByProjectKey(url, accessToken, projectKey));
			}
			
		} catch (NumberFormatException e) {
			result.setError(true);
			result.setError_code(e.toString());
			LOGGER.log(Level.WARNING, SERVICE_EXCEPTION, e);
		}

		return result;

	}


}