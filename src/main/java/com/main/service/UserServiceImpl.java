package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.DAO.UserDAO;
import com.main.entity.Account;
import com.main.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	@Override
	public User getUser(int id) {
		return userDAO.getUser(id);
	}

	@Transactional
	@Override
	public void saveUser(User user, Account account) {
		userDAO.saveUser(user, account);
		
	}

	@Transactional
	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Transactional
	@Override
	public User getUserFromUsername(String username) {
		
		return userDAO.getUserFromUsername(username);
	}
	
	@Transactional
	@Override
	public void saveUser(User tempUser) {
		userDAO.saveUser(tempUser);
		
	}

	@Transactional
	@Override
	public void updateUserWithAccount(User user, Account account) {
		userDAO.updateUserWithAccount(user, account);
		
	}

}
