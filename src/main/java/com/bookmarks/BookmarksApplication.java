package com.bookmarks;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bookmarks.dao.AccountRepository;
import com.bookmarks.dao.BookmarkRepository;
import com.bookmarks.entities.Account;
import com.bookmarks.entities.Bookmark;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class BookmarksApplication {
	
	@Bean
	CommandLineRunner init(AccountRepository accountRepository, BookmarkRepository bookmarkRepository){
		return(evt)->Arrays.asList("jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
				.forEach(a->{
					Account account=accountRepository.save(new Account(a,"password"));
					bookmarkRepository.save(new Bookmark(account,"http://bookmark.com/1/"+a,"A Description"));
					bookmarkRepository.save(new Bookmark(account,"http://bookmark.com/2/"+a,"A Description"));
					
					
				});
	}

	public static void main(String[] args) {
		SpringApplication.run(BookmarksApplication.class, args);
	}
}
