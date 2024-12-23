package com.pcwk.ehr.board.service;

import com.pcwk.ehr.board.domain.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardServiceImpl implements BoardService {

    private final JdbcTemplate jdbcTemplate;

    public BoardServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BoardVO getBoardByArticleNo(int articleNo) {
        String sql = "SELECT * FROM board WHERE article_no = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{articleNo}, (rs, rowNum) -> {
            BoardVO boardVO = new BoardVO();
            boardVO.setArticle_no(rs.getInt("article_no"));
            boardVO.setArticle_title(rs.getString("article_title"));
            boardVO.setArticle_contents(rs.getString("article_contents"));
            boardVO.setArticle_user_id(rs.getString("article_user_id"));
            boardVO.setArticle_reg_date(rs.getString("article_reg_date")); // article_reg_date는 String으로 처리
            boardVO.setArticle_mod_date(rs.getString("article_mod_date")); // 추가된 부분: 수정일
            return boardVO;
        });
    }

    @Override
    public int updateBoard(BoardVO boardVO) {
        String sql = "UPDATE board SET article_title = ?, article_contents = ? WHERE article_no = ?";
        return jdbcTemplate.update(sql, boardVO.getArticle_title(), boardVO.getArticle_contents(), boardVO.getArticle_no());
    }

    @Override
    public int deleteBoard(int articleNo) {
        String sql = "DELETE FROM board WHERE article_no = ?";
        return jdbcTemplate.update(sql, articleNo);
    }
}
