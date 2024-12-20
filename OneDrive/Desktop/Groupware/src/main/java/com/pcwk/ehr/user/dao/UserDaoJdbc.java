package com.pcwk.ehr.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.pcwk.ehr.user.domain.UserVO;


public class UserDaoJdbc implements UserDao {
	
	final Logger log = LogManager.getLogger(UserDaoJdbc.class);
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<UserVO> userMapper = new RowMapper<UserVO>() {

		@Override
		public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserVO outVO = new UserVO();
			
			outVO.setUserId(rs.getInt("user_id"));
			outVO.setDeptNo(rs.getInt("user_dept_no"));
			outVO.setSupUserId(rs.getInt("user_sup_id"));
			outVO.setName(rs.getString("user_name"));
			outVO.setPassword(rs.getString("user_password"));
			outVO.setPosition(rs.getString("user_position"));
			outVO.setBirthday(rs.getString("user_birthday"));
			outVO.setHiredate(rs.getString("user_hiredate"));
			outVO.setPhoneNo(rs.getString("user_phone_number"));
			outVO.setStatus(rs.getInt("user_status"));
			
			log.debug("outVO: " + outVO.toString());
			
			return outVO;
		}
	};
	
	public UserDaoJdbc() {
		
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;

		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int doDelete(UserVO inVO) throws SQLException {

		return 0;
	}

	@Override
	public int doUpdate(UserVO inVO) throws SQLException {

		return 0;
	}

	@Override
	public List<UserVO> doRetrieve() {

		return null;
	}

	@Override
	public int getCount() throws SQLException {
	
		return 0;
	}

	@Override
	public void deleteAll() throws SQLException {


	}

	@Override
	public int doSave(UserVO inVO) throws SQLException {
	
		return 0;
	}

	@Override
	public UserVO doSelectOne(UserVO inVO) throws SQLException,EmptyResultDataAccessException, NullPointerException {
		UserVO outVO = null;
		
		StringBuilder sb = new StringBuilder(200);
		
		sb.append("SELECT                \n");
		sb.append("    user_id,          \n");
		sb.append("    user_dept_no,     \n");
		sb.append("    user_sup_id,      \n");
		sb.append("    user_name,        \n");
		sb.append("    user_password,    \n");
		sb.append("    user_position,    \n");
		sb.append("    user_birthday,    \n");
		sb.append("    user_hiredate,    \n");
		sb.append("    user_phone_number,\n");
		sb.append("    user_status       \n");
		sb.append("  FROM                \n");
		sb.append("    gw_user           \n");
		sb.append(" WHERE user_id = ?    \n");
		
		//인자 받기
		Object [] args = {inVO.getUserId()};
		
		//인자로 받은 값 출력해서 체크
		for(Object obj : args) {
			log.debug(obj.toString());
		}
		
		//인자들 넣어서 원하는 데이터 조회하기
		outVO = this.jdbcTemplate.queryForObject(sb.toString(), userMapper, args);
		
		// 조회 데이터가 없을 경우
		if(outVO == null) {
			throw new NullPointerException(inVO.getUserId() + "(아이디)를 확인하세요.");
		}
		
 		return outVO;
	}

	@Override
	public List<UserVO> getAll() throws SQLException {
	
		return null;
	}

}
