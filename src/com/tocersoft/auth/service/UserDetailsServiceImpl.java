package com.tocersoft.auth.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.auth.dao.IRightDao;
import com.tocersoft.auth.dao.IUserDao;
import com.tocersoft.auth.entity.Right;
import com.tocersoft.auth.entity.User;
import com.tocersoft.base.utils.Constant;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final long serialVersionUID = 2653636739190406891L;

	@Resource(name="userDaoImpl")
	private IUserDao userDao;
	
	@Resource(name="rightDaoImpl")
	private IRightDao rightDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		username = username.toLowerCase();
		User user = userDao.getUserByAccount(username);
		if (user == null) {
			throw new UsernameNotFoundException("账号[" + username + "]不存在!");
		}
		
		
		user.setAuthorities(getGrantedAuthorities(user));
		
		return user;
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_BASE"));
		List<Right> permissions = new ArrayList<Right>();
		if (user.getAccount().equals(Constant.ADMIN_USER)) {
			permissions = rightDao.getAllEnableRights();
		} else {
			permissions = rightDao.getAllEnableRightsByUserId(user.getId());
		}
		
		for(Right p : permissions){
			if(StringUtils.isEmpty(p.getPcode())){
				continue;
			}
			
			grantedAuthorities.add(new GrantedAuthorityImpl(p.getPcode()));
		}
		
		return grantedAuthorities;
	}

}