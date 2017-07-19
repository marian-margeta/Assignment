package com.garwan.assignment.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Extension of the {@link User} with an {@link Account} ID in addition
 *
 */
public class UserAccount extends User {
	static final long serialVersionUID = 6730587226782066359L;

	private Integer id;
	
	/**
	 * Creates account from {@code account} without any special authorities
	 */
	public UserAccount(Account account) {
		this(account.getUserName(), account.getPasswordHash(), new ArrayList<GrantedAuthority>());
		
		this.id = account.getId();
	}
	
	/**
	 * @see User#User(String, String, Collection)
	 */
	public UserAccount(String username, String password, 
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	/**
	 * @see User#User(String, String, boolean, boolean, boolean, boolean, Collection)
	 */
	public UserAccount(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
