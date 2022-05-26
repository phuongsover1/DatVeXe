package com.main.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.main.entity.Account;

@Repository
public class AccountDAOImpl implements AccountDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Account getAccountFromUsername(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		String sHQL = "FROM Account A WHERE A.username = :username";
		Query<Account> query = currentSession.createQuery(sHQL);
		query.setParameter("username", username);
		List<Account> accountList = query.getResultList();
		if (accountList.isEmpty())
			return null;
		return accountList.get(0);
	}

}
