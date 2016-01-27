package com.tinyurl.myurl.dto.url;

import java.util.HashMap;

import com.tinyurl.myurl.model.url.Project;

public class ProjectDTO {

	private String expand;
	private String self;
	private String id;
	private String key;
	private String name;
	private HashMap<String, String> avatarUrls;
	private String projectTypeKey;

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public HashMap<String, String> getAvatarUrls() {
		return avatarUrls;
	}

	public void setAvatarUrls(HashMap<String, String> avatarUrls) {
		this.avatarUrls = avatarUrls;
	}

	public String getProjectTypeKey() {
		return projectTypeKey;
	}

	public void setProjectTypeKey(String projectTypeKey) {
		this.projectTypeKey = projectTypeKey;
	}

	public static ProjectDTO map(Project project) {
		ProjectDTO dto = new ProjectDTO();

		if (project != null) {
			dto.setExpand(project.getExpand());
			dto.setSelf(project.getSelf());
			dto.setId(project.getId());
			dto.setKey(project.getKey());
			dto.setName(project.getName());
			dto.setAvatarUrls(project.getAvatarUrls());
			dto.setProjectTypeKey(project.getProjectTypeKey());
		}

		return dto;
	}

}
