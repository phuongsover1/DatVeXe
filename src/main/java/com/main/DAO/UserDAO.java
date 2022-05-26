package com.main.DAO;



import java.util.List;

import com.main.entity.Account;
import com.main.entity.User;
import com.main.entity.Ve_Xe;


public interface UserDAO {
	public User getUser(int id);
	
	public void saveUser(User user, Account account);

	public void update(User user);
	
	public void updateUserWithAccount(User user, Account account);
	
	public User getUserFromUsername(String username);
	
	public List<Ve_Xe> getVeXeList(int id);

	public void saveUser(User tempUser);
	
}
