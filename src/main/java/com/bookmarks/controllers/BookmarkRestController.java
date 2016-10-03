package com.bookmarks.controllers;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookmarks.dao.AccountRepository;
import com.bookmarks.dao.BookmarkRepository;
import com.bookmarks.entities.Bookmark;

@RestController
@RequestMapping("/{userId}/bookmarks")
public class BookmarkRestController {
	private  BookmarkRepository bookmarkRepository;
	private  AccountRepository accountRepository;
	
	@RequestMapping(method=org.springframework.web.bind.annotation.RequestMethod.POST)
	ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark input){
		this.validateUser(userId);
		return (ResponseEntity<?>) this.accountRepository.findByUsername(userId)
				.map(account -> {
					Bookmark result = bookmarkRepository.save(new Bookmark(account,
							input.uri, input.description));
					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.setLocation(ServletUriComponentsBuilder
							.fromCurrentRequest().path("/{id}")
							.buildAndExpand(result.getId()).toUri());
					return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
				}).get();

	}
	@Autowired
	BookmarkRestController(BookmarkRepository bookmarkRepository,
			AccountRepository accountRepository) {
		this.bookmarkRepository = bookmarkRepository;
		this.accountRepository = accountRepository;
	}
	@RequestMapping("/{bookmarkId}")
	Bookmark readBookmark(@PathVariable String userId, @PathVariable Long bookmarkId) {
		this.validateUser(userId);
		return this.bookmarkRepository.findOne(bookmarkId);
	}
	@RequestMapping(method = org.springframework.web.bind.annotation.RequestMethod.GET)
	Collection<Bookmark> readBookmarks(@PathVariable String userId) {
		this.validateUser(userId);
		return this.bookmarkRepository.findByAccountUsername(userId);
	}
	private void validateUser(String userId){
		this.accountRepository.findByUsername(userId).orElseThrow(()-> new UserNotFoundException(userId));
	}

}


