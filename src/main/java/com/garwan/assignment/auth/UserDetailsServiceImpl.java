package com.garwan.assignment.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired private AccountRepository accountRepository;
	
    @Transactional(readOnly = true)
	public UserAccount loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUserName(username);
		
		if (account == null) {
			throw new UsernameNotFoundException("User doesn't exist!");
		}
		
		return new UserAccount(account);
	}

}
