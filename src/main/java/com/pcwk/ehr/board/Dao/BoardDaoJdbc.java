package com.pcwk.ehr.board.Dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;         // JdbcTemplate
import org.springframework.jdbc.core.RowMapper;           // RowMapper
import java.sql.ResultSet;                              // ResultSet
import java.sql.SQLException;                            // SQLException
import java.util.List;                                   // List

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.board.domain.BoardVO;

@Repository
public class BoardDaoJdbc implements BoardDao {

    final Logger log = LogManager.getLogger(BoardDaoJdbc.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public int doWriteAnnouncementBoard(BoardVO inVO) throws SQLException {
		 StringBuilder sb = new StringBuilder(500);

		    
	        sb.append("INSERT INTO BOARD (article_no, article_user_id, article_title, article_contents, ")
	          .append("article_board_div, article_read_cnt, article_reg_date, article_mod_date) ")
	          .append("VALUES (board_article_no_seq.NEXTVAL, ?, ?, ?, 10, 0, SYSDATE, SYSDATE)"); 
	        
	        Object[] params = new Object[]{
	                inVO.getArticle_user_id(),  
	                inVO.getArticle_title(),
	                inVO.getArticle_contents()
	            };
	        
	        return jdbcTemplate.update(sb.toString(), params);
	}
    
    @Override
    public int doWriteFreeBoard(BoardVO inVO) throws SQLException {
        StringBuilder sb = new StringBuilder(500);

    
        sb.append("INSERT INTO BOARD (article_no, article_user_id, article_title, article_contents, ")
          .append("article_board_div, article_read_cnt, article_reg_date, article_mod_date) ")
          .append("VALUES (board_article_no_seq.NEXTVAL, ?, ?, ?, 20, 0, SYSDATE, SYSDATE)"); 

        Object[] params = new Object[]{
            inVO.getArticle_user_id(),  
            inVO.getArticle_title(),
            inVO.getArticle_contents()
        };

        return jdbcTemplate.update(sb.toString(), params);
    }

	@Override
	public int doWriteDepartmentBoard(BoardVO inVO) throws SQLException {
		StringBuilder sb = new StringBuilder(500);

	    
        sb.append("INSERT INTO BOARD (article_no, article_user_id, article_title, article_contents, ")
          .append("article_board_div, article_read_cnt, article_reg_date, article_mod_date) ")
          .append("VALUES (board_article_no_seq.NEXTVAL, ?, ?, ?, ?, 0, SYSDATE, SYSDATE)"); 

        Object[] params = new Object[]{
            inVO.getArticle_user_id(),  
            inVO.getArticle_title(),
            inVO.getArticle_contents(),
            inVO.getArticle_board_div()
        };

        return jdbcTemplate.update(sb.toString(), params);
	}

	@Override
	public List<BoardVO> selectMainBoardList() throws SQLException {
		 // SQL 쿼리 작성
	    StringBuilder sb = new StringBuilder(500);
	    sb.append("SELECT article_no, article_user_id, article_title, article_contents, ")
	      .append("article_board_div, article_read_cnt, article_reg_date, article_mod_date ")
	      .append("FROM BOARD ")
	      .append("WHERE article_board_div = 10")  
	      .append(" ORDER BY article_reg_date DESC"); 

	    // JDBC Template의 query 메서드를 사용하여 결과 조회
	    return jdbcTemplate.query(sb.toString(), new RowMapper<BoardVO>() {
	        @Override
	        public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	            // ResultSet에서 데이터를 추출하여 BoardVO 객체에 매핑
	            BoardVO boardVO = new BoardVO();
	            boardVO.setArticle_no(rs.getInt("article_no"));
	            boardVO.setArticle_user_id(rs.getString("article_user_id"));
	            boardVO.setArticle_title(rs.getString("article_title"));
	            boardVO.setArticle_contents(rs.getString("article_contents"));
	            boardVO.setArticle_board_div(rs.getInt("article_board_div"));
	            boardVO.setArticle_read_cnt(rs.getInt("article_read_cnt"));
	            // 날짜 필드를 getDate()로 설정
	            boardVO.setArticle_reg_date(rs.getString("article_reg_date"));
	            boardVO.setArticle_mod_date(rs.getString("article_mod_date"));
	            return boardVO;  
	        }
	    });
	}

	@Override
	public List<BoardVO> selectFreeBoardList() throws SQLException {
		 // SQL 쿼리 작성
	    StringBuilder sb = new StringBuilder(500);
	    sb.append("SELECT article_no, article_user_id, article_title, article_contents, ")
	      .append("article_board_div, article_read_cnt, article_reg_date, article_mod_date ")
	      .append("FROM BOARD ")
	      .append("WHERE article_board_div = 20")  
	      .append(" ORDER BY article_reg_date DESC"); 

	    // JDBC Template의 query 메서드를 사용하여 결과 조회
	    return jdbcTemplate.query(sb.toString(), new RowMapper<BoardVO>() {
	        @Override
	        public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	            // ResultSet에서 데이터를 추출하여 BoardVO 객체에 매핑
	            BoardVO boardVO = new BoardVO();
	            boardVO.setArticle_no(rs.getInt("article_no"));
	            boardVO.setArticle_user_id(rs.getString("article_user_id"));
	            boardVO.setArticle_title(rs.getString("article_title"));
	            boardVO.setArticle_contents(rs.getString("article_contents"));
	            boardVO.setArticle_board_div(rs.getInt("article_board_div"));
	            boardVO.setArticle_read_cnt(rs.getInt("article_read_cnt"));
	            // 날짜 필드를 getDate()로 설정
	            boardVO.setArticle_reg_date(rs.getString("article_reg_date"));
	            boardVO.setArticle_mod_date(rs.getString("article_mod_date"));
	            return boardVO;  
	        }
	    });
	}

	@Override
	public List<BoardVO> selectDevelopmentBoardList() throws SQLException {
		 // SQL 쿼리 작성
	    StringBuilder sb = new StringBuilder(500);
	    sb.append("SELECT article_no, article_user_id, article_title, article_contents, ")
	      .append("article_board_div, article_read_cnt, article_reg_date, article_mod_date ")
	      .append("FROM BOARD ")
	      .append("WHERE article_board_div = 30")  
	      .append(" ORDER BY article_reg_date DESC"); 

	    // JDBC Template의 query 메서드를 사용하여 결과 조회
	    return jdbcTemplate.query(sb.toString(), new RowMapper<BoardVO>() {
	        @Override
	        public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	            // ResultSet에서 데이터를 추출하여 BoardVO 객체에 매핑
	            BoardVO boardVO = new BoardVO();
	            boardVO.setArticle_no(rs.getInt("article_no"));
	            boardVO.setArticle_user_id(rs.getString("article_user_id"));
	            boardVO.setArticle_title(rs.getString("article_title"));
	            boardVO.setArticle_contents(rs.getString("article_contents"));
	            boardVO.setArticle_board_div(rs.getInt("article_board_div"));
	            boardVO.setArticle_read_cnt(rs.getInt("article_read_cnt"));
	            // 날짜 필드를 getDate()로 설정
	            boardVO.setArticle_reg_date(rs.getString("article_reg_date"));
	            boardVO.setArticle_mod_date(rs.getString("article_mod_date"));
	            return boardVO;  
	        }
	    });
	}

	@Override
	public List<BoardVO> selectHRBoardList() throws SQLException {
		 // SQL 쿼리 작성
	    StringBuilder sb = new StringBuilder(500);
	    sb.append("SELECT article_no, article_user_id, article_title, article_contents, ")
	      .append("article_board_div, article_read_cnt, article_reg_date, article_mod_date ")
	      .append("FROM BOARD ")
	      .append("WHERE article_board_div = 40")  
	      .append(" ORDER BY article_reg_date DESC"); 

	    // JDBC Template의 query 메서드를 사용하여 결과 조회
	    return jdbcTemplate.query(sb.toString(), new RowMapper<BoardVO>() {
	        @Override
	        public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	            // ResultSet에서 데이터를 추출하여 BoardVO 객체에 매핑
	            BoardVO boardVO = new BoardVO();
	            boardVO.setArticle_no(rs.getInt("article_no"));
	            boardVO.setArticle_user_id(rs.getString("article_user_id"));
	            boardVO.setArticle_title(rs.getString("article_title"));
	            boardVO.setArticle_contents(rs.getString("article_contents"));
	            boardVO.setArticle_board_div(rs.getInt("article_board_div"));
	            boardVO.setArticle_read_cnt(rs.getInt("article_read_cnt"));
	            // 날짜 필드를 getDate()로 설정
	            boardVO.setArticle_reg_date(rs.getString("article_reg_date"));
	            boardVO.setArticle_mod_date(rs.getString("article_mod_date"));
	            return boardVO;  
	        }
	    });
	}

	@Override
	public List<BoardVO> selectMarketingBoardList() throws SQLException {
		 // SQL 쿼리 작성
	    StringBuilder sb = new StringBuilder(500);
	    sb.append("SELECT article_no, article_user_id, article_title, article_contents, ")
	      .append("article_board_div, article_read_cnt, article_reg_date, article_mod_date ")
	      .append("FROM BOARD ")
	      .append("WHERE article_board_div = 50")  
	      .append(" ORDER BY article_reg_date DESC"); 

	    // JDBC Template의 query 메서드를 사용하여 결과 조회
	    return jdbcTemplate.query(sb.toString(), new RowMapper<BoardVO>() {
	        @Override
	        public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	            // ResultSet에서 데이터를 추출하여 BoardVO 객체에 매핑
	            BoardVO boardVO = new BoardVO();
	            boardVO.setArticle_no(rs.getInt("article_no"));
	            boardVO.setArticle_user_id(rs.getString("article_user_id"));
	            boardVO.setArticle_title(rs.getString("article_title"));
	            boardVO.setArticle_contents(rs.getString("article_contents"));
	            boardVO.setArticle_board_div(rs.getInt("article_board_div"));
	            boardVO.setArticle_read_cnt(rs.getInt("article_read_cnt"));
	            // 날짜 필드를 getDate()로 설정
	            boardVO.setArticle_reg_date(rs.getString("article_reg_date"));
	            boardVO.setArticle_mod_date(rs.getString("article_mod_date"));
	            return boardVO;  
	        }
	    });
	}

	




}
