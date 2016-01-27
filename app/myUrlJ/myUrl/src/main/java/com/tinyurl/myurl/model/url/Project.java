package com.tinyurl.myurl.model.url;

import java.util.HashMap;


public class Project {

	protected String expand;
    protected String self;
    protected String id;
    protected String key;
    protected String name;
    protected HashMap<String, String> avatarUrls;
    protected String projectTypeKey;
     
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

   
}
