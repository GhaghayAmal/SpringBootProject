package com.bookmarks.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bookmark {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	public String uri;
	public String description;
	@JsonIgnore
    @ManyToOne
	private Account account;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Bookmark(Account account, String uri, String description) {
		
		this.uri = uri;
		this.description = description;
		this.account = account;
	}
	public Bookmark(){}
	
	

}
