package com.pcwk.ehr.board.Dao;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.user.domain.UserVO;

public interface BoardDao {

	int doWriteFreeBoard(BoardVO inVO) throws SQLException;
	int doWriteDepartmentBoard(BoardVO inVO) throws SQLException;
	int doWriteAnnouncementBoard(BoardVO inVO) throws SQLException;
	List<BoardVO> selectMainBoardList()throws SQLException;
	List<BoardVO> selectFreeBoardList()throws SQLException;
	List<BoardVO> selectDevelopmentBoardList()throws SQLException;
	List<BoardVO> selectHRBoardList()throws SQLException;
	List<BoardVO> selectMarketingBoardList()throws SQLException;
}
