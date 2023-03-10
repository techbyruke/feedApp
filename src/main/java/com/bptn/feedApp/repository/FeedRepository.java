package com.bptn.feedApp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.bptn.feedApp.jpa.Feed;
import com.bptn.feedApp.jpa.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface FeedRepository extends JpaRepository<Feed,Integer>, PagingAndSortingRepository<Feed,Integer> {

	Page<Feed> findByUser(User user, Pageable pageable);
	
	Page<Feed> findByUserNot(User user, Pageable pageable);
	
}
