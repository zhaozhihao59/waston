package com.tocersoft.base.utils;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
public class CustomizePasswordEncoder implements PasswordEncoder{

	public String encodePassword(String rawPass, Object salt)
			throws DataAccessException {
		return DataUtil.encryptionPw(rawPass);
	}

	public boolean isPasswordValid(String encPass, String rawPass, Object salt) throws DataAccessException {
		return DataUtil.encryptionPw(rawPass).equals(encPass);
	}

}
