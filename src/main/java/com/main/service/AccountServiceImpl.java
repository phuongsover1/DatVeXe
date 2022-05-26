package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.DAO.AccountDAO;
import com.main.entity.Account;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;
	
	@Transactional
	@Override
	public Account getAccountFromUsername(String username) {
		return accountDAO.getAccountFromUsername(username);
	}

}
