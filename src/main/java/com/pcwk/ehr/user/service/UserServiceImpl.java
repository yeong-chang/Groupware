package com.pcwk.ehr.user.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.UserVO;

public class UserServiceImpl implements UserService {
	
	final Logger log = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDao userDao;
	
	public UserServiceImpl() {

	}

	@Override
	public int doDelete(UserVO inVO) throws SQLException {	
		return userDao.doDelete(inVO);
	}

	@Override
	public int doUpdate(UserVO inVO) throws SQLException {		
		return userDao.doUpdate(inVO);
	}

	@Override
	public List<UserVO> doRetrieve() {		
		return userDao.doRetrieve();
	}

	@Override
	public int doSave(UserVO inVO) throws SQLException {		
		return userDao.doSave(inVO);
	}

	@Override
	public UserVO doSelectOne(UserVO inVO) throws SQLException, EmptyResultDataAccessException, NullPointerException {		
		return userDao.doSelectOne(inVO);
	}
}
