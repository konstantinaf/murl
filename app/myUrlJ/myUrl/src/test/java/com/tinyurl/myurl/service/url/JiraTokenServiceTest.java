/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tinyurl.myurl.service.url;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tinyurl.myurl.dto.url.TicketDTO;

/**
 *
 * @author ntinaki2f
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testContext.xml")
public class JiraTokenServiceTest {

	@Autowired
	JiraTokenService jiraTokenService;

	public JiraTokenServiceTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetTicketByJiraBaseUrl() {
		String JIRA_BASE_URL = "http://localhost:8100";

		TicketDTO dto = jiraTokenService.getTikenByJiraBaseUrl(JIRA_BASE_URL);

		assertFalse(dto.getToken().equals(null));
		assertFalse(dto.getTokenSecret().equals(null));
		assertFalse(dto.getRedirectUrl().equals(null));

	}

	@Test
	public void testGetAccessToken() {
		String JIRA_BASE_URL = "http://localhost:8100";
       //todo selenium
		TicketDTO dto = jiraTokenService.getTikenByJiraBaseUrl(JIRA_BASE_URL);

		assertFalse(dto.getToken().equals(null));
		assertFalse(dto.getTokenSecret().equals(null));
		assertFalse(dto.getRedirectUrl().equals(null));
		// Create a new instance of the html unit driver
		// Notice that the remainder of the code relies on the interface,
//		// not the implementation.
		//WebDriver driver = new WebDriver();
		// And now use this to visit redirect url
//		driver.get(dto.getRedirectUrl());
//		// Find the text input element by its name
//		WebElement element = driver.findElement(By.id("approve"));
//		element.click();
//
//		 // Check the title of the page
//        System.out.println("Page title is: " + driver.getTitle());
//
//        driver.quit();
	}

}
