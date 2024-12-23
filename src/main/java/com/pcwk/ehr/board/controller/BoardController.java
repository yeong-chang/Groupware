package com.pcwk.ehr.board.controller;

import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.board.Dao.BoardDaoJdbc;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    private BoardDaoJdbc boardDaoJdbc;

    @Autowired
    private BoardService boardService;
    
    @GetMapping("/announcement.do")    
    public String announcementBoardPage(Model model) {
    	BoardVO boardVO = new BoardVO();
        String view = "board/announcement/announcementBoardList";
        try {
        	 List<BoardVO> boardList = boardDaoJdbc.selectMainBoardList();
             model.addAttribute("boardList", boardList);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
        	
		model.addAttribute("vo", boardVO);
        return view;
    }


    @RequestMapping("/boardDetail.do")
    public String getAnnouncementDetail(@RequestParam("article_no") int articleNo, Model model) {
        // DB에서 해당 article_no에 해당하는 게시글을 조회
        BoardVO board = boardService.getBoardByArticleNo(articleNo);
        // 모델에 게시글 정보 전달
        model.addAttribute("board", board);

        return "board/boardDetail";  // JSP 페이지로 이동
    }

    @GetMapping("/announcementwrite.do")
    public String announcementWritePage() {
        return "board/announcement/announcementBoardWrite";
    }

    @PostMapping("/announcementboard.do")
    public String announcementBoard(@RequestParam("title") String title,
                              @RequestParam("content") String content,
                              @RequestParam("author") String author) {
        BoardVO boardVO = new BoardVO();
        boardVO.setArticle_user_id(author);
        boardVO.setArticle_title(title);
        boardVO.setArticle_contents(content);

        try {
            int result = boardDaoJdbc.doWriteAnnouncementBoard(boardVO);

            if (result > 0) {
                return "redirect:/board/announcement.do"; 
            } else {
                return "board/announcementBoardWrite";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "게시글 작성에 실패 했습니다"; 
        }
    }
    
    @GetMapping("/free.do")
    public String FreeBoardPage(Model model) {
    	BoardVO boardVO = new BoardVO();
        String view = "board/free/FreeBoardList";
        try {
       	 List<BoardVO> boardList = boardDaoJdbc.selectFreeBoardList(); 
            model.addAttribute("boardList", boardList); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
       	
		model.addAttribute("vo", boardVO);
        return view;
    }

    @GetMapping("/freewrite.do")
    public String showWritePage() {
        return "board/free/FreeBoardWrite";
    }

    @PostMapping("/freeboard.do")
    public String freeBoard(@RequestParam("title") String title,
                              @RequestParam("content") String content,
                              @RequestParam("author") String author) {
        BoardVO boardVO = new BoardVO();
        boardVO.setArticle_user_id(author);
        boardVO.setArticle_title(title);
        boardVO.setArticle_contents(content);

        try {
            int result = boardDaoJdbc.doWriteFreeBoard(boardVO);

            if (result > 0) {
                return "redirect:/board/announcement.do";
            } else {
                return "board/announcementBoardWrite";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "게시글 작성에 실패 했습니다"; 
        }
    }

    @GetMapping("/departmentwrite.do")
    public String showDepartmentWritePage() {
    	return "board/department/DepartmentBoardWrite";
    }
    
    @PostMapping("/departmentboard.do")
    public String submitBoard(@RequestParam("title") String title,
                              @RequestParam("content") String content,
                              @RequestParam("author") String author,
                              @RequestParam("department") int department) {
        BoardVO boardVO = new BoardVO();
        boardVO.setArticle_user_id(author);
        boardVO.setArticle_title(title);
        boardVO.setArticle_contents(content);
        boardVO.setArticle_board_div(department);

        try {
            int result = boardDaoJdbc.doWriteDepartmentBoard(boardVO);

            if (result > 0) {
                return "redirect:/board/announcement.do"; 
            } else {
                return "board/DepartmentBoardWrite";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "게시글 작성에 실패 했습니다"; 
        }
    }

    @GetMapping("/development.do")
    public String DevelopmentBoardPage(Model model) {
    	BoardVO boardVO = new BoardVO();
    	String view = "board/department/DevelopmentBoardList";
    	 try {
        	 List<BoardVO> boardList = boardDaoJdbc.selectDevelopmentBoardList();
             model.addAttribute("boardList", boardList);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
        	
		model.addAttribute("vo", boardVO);
    	return view;
    }
    
    @GetMapping("/hr.do")
    public String HRBoardPage(Model model) {
    	BoardVO boardVO = new BoardVO();
    	String view = "board/department/HRBoardList";
    	 try {
        	 List<BoardVO> boardList = boardDaoJdbc.selectHRBoardList();
             model.addAttribute("boardList", boardList);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
        	
		model.addAttribute("vo", boardVO);
    	return view;
    }

    @GetMapping("/marketing.do")
    public String DepartmentBoardPage(Model model) {
    	BoardVO boardVO = new BoardVO();
    	String view = "board/department/MarketingBoardList";
    	 try {
        	 List<BoardVO> boardList = boardDaoJdbc.selectMarketingBoardList();
             model.addAttribute("boardList", boardList);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
        	
		model.addAttribute("vo", boardVO);
    	return view;
    }
    
    
}
