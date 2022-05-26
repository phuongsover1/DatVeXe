package com.main.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.main.entity.Account;
import com.main.entity.Role;
import com.main.entity.User;
import com.main.entity.Ve_Xe;

@Repository
public class UserDAOImpl implements UserDAO {
	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User getUser(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		User tempUser = currentSession.get(User.class, id);
		return tempUser;
	}

	@Override
	public void saveUser(User user, Account account) {
		Session currentSession = sessionFactory.getCurrentSession();
//		User newUser = user;
//			newUser.setPassword("{noop}" + user.getPassword());
//			Role role = new Role("ROLE_USER");
//			newUser.addRole(role);
//		currentSession.save(newUser);
		
		Role role = new Role("ROLE_USER");
		account.setPassword("{noop}" + account.getPassword());
		account.setAccountState(1);
		currentSession.save(account);
		account.addRole(role);
		user.setPhoneNumber(account.getUsername());
		user.addAccount(account);
		currentSession.save(user);
		
	}

	@Override
	public void update(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.update(user);
	}
	
	

	@Override
	public User getUserFromUsername(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		String sHQL = "FROM User U WHERE U.phoneNumber = :username";
		Query<User> query = currentSession.createQuery(sHQL);
		query.setParameter("username", username);
		List<User> userList = query.getResultList();
		if (userList.isEmpty())
			return null;
		return userList.get(0);
	}

	@Override
	public List<Ve_Xe> getVeXeList(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public void saveUser(User tempUser) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(tempUser);
		
	}

	@Override
	public void updateUserWithAccount(User user, Account account) {
		Session currentSession = sessionFactory.getCurrentSession();
		Role role = new Role("ROLE_USER");
		account.setPassword("{noop}" + account.getPassword());
		account.setAccountState(1);
		currentSession.save(account);
		account.addRole(role);
		user.addAccount(account);
		currentSession.update(user);
	}

}
