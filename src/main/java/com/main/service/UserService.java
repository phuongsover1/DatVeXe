package com.main.service;

import com.main.entity.Account;
import com.main.entity.User;

public interface UserService {
	public User getUser(int id);
	
	public void saveUser(User user, Account account);

	public void update(User user);
	
	public User getUserFromUsername(String username);

	public void saveUser(User tempUser);
	
	public void updateUserWithAccount(User user, Account account);
}
