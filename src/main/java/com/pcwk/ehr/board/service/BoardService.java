package com.pcwk.ehr.board.service;

import com.pcwk.ehr.board.domain.BoardVO;

public interface BoardService {
    // 게시글 상세 조회
    BoardVO getBoardByArticleNo(int articleNo);
    // 게시글 수정
    int updateBoard(BoardVO boardVO);
    // 게시글 삭제
    int deleteBoard(int articleNo);
}
