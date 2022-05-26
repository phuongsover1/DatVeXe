package com.main.DAO;

import com.main.entity.Account;

public interface AccountDAO {
	public Account getAccountFromUsername(String username);
}
