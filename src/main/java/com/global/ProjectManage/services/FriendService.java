package com.global.ProjectManage.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.global.ProjectManage.entity.Friend;
import com.global.ProjectManage.error.AlreadyFriendsException;
import com.global.ProjectManage.repository.FriendRepo;
import com.global.UserManage.entity.AppUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class FriendService {

	private final FriendRepo repo;

	@Transactional(readOnly = true)
	public List<Friend> findAll() {
		return repo.findAll();
	}

	public Friend findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	public void addFriend(AppUser user, AppUser friend) {
		// Check if the friend relationship already exists
		if (!areFriends(user, friend)) {
			// Create a new friend relationship
			if (user.getId() != friend.getId()) {
				Friend friendRelation = new Friend();

				friendRelation.setUser(user);
				friendRelation.setFriend(friend);

				// Save the friend relationship
				repo.save(friendRelation);
			} else {
				log.info("at the same id");
			}

//            // You may also want to update the inverse relationship (friend's friends2 list)
//            Friend inverseFriendRelation = new Friend();
//            inverseFriendRelation.setUser(friend);
//            inverseFriendRelation.setFriend(user);
//            repo.save(inverseFriendRelation);
		} else {
			throw new AlreadyFriendsException("Already friends");
		}
	}

	public boolean areFriends(AppUser user, AppUser friend) {
		// Check if the friend relationship already exists
		return repo.existsByUserAndFriend(user, friend);
	}
}
