package com.bookmarks.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmarks.entities.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>{
	Collection<Bookmark> findByAccountUsername(String username);

}
