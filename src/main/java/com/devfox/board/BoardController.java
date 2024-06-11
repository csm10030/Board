package com.devfox.board;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devfox.board.service.BoardService;
import com.devfox.board.service.CommentService;
import com.devfox.board.util.Paging;
import com.devfox.board.vo.BoardVO;
import com.devfox.board.vo.CommentVO;

@Controller
public class BoardController {
	@Resource(name = "boardService")
	private BoardService boardService;
	@Resource(name = "commentService")
	private CommentService commentService;
	
	/* board */
	@RequestMapping(value = "/listBoard", method = RequestMethod.GET)
	public String listBoard(Model model, HttpServletRequest request) throws Exception {
		try {
			List<BoardVO> boardList = boardService.selectBoardList();
			int pageNo = 0;
			if(null == request.getParameter("pageNo"))
				pageNo = 1;
			else
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			
			Paging paging = new Paging();
	        paging.setPageNo(pageNo);
	        paging.setPageSize(10);
	        paging.setTotalCount(boardList.size());

	        model.addAttribute("paging", paging);
			model.addAttribute("boardList", boardList);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return "/board/listBoard";
	}
	@RequestMapping(value = "/createBoard", method = RequestMethod.GET)
	public String createBoard(Model model) throws Exception {
	    return "/board/createBoard";
	}
	@RequestMapping(value = "/createBoard", method = RequestMethod.POST)
	public String createBoardForm(Model model, HttpServletRequest request, BoardVO boardVO) throws Exception {
		try {
			if(!request.getParameter("boardTitle").equals("") 
					&& !request.getParameter("boardWriter").equals("") 
					&& !request.getParameter("boardContent").equals("")) {
						boardVO.setBoardTitle(request.getParameter("boardTitle"));
						boardVO.setBoardWriter(request.getParameter("boardWriter"));
						boardVO.setBoardContent(request.getParameter("boardContent"));
						boardService.createBoard(boardVO);
						model.addAttribute("newTab", "y");
			}
			else{
				return this.createBoard(model);
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.listBoard(model, request);
	}
	@RequestMapping(value = "/readBoard", method = RequestMethod.GET)
	public String readBoard(Model model, HttpServletRequest request, BoardVO boardVO) throws Exception {
		try {
			int boardNo = boardVO.getBoardNo();
			if(null != request.getParameter("boardNo")) {
				boardNo = Integer.parseInt(request.getParameter("boardNo"));
			}
			boardVO.setBoardNo(boardNo);
			BoardVO readBoard = boardService.readBoard(boardVO);
			model.addAttribute("readBoard", readBoard);
			List<CommentVO> commentList = commentService.selectCommentList(boardNo);
			model.addAttribute("commentList", commentList);	
			
			int pageNo = 0;
			if(null == request.getParameter("pageNo"))
				pageNo = 1;
			else
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			
			Paging paging = new Paging();
	        paging.setPageNo(pageNo);
	        paging.setPageSize(3);
	        paging.setTotalCount(commentList.size());
	        
	        model.addAttribute("paging", paging);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return "/board/readBoard";
	}
	@RequestMapping(value = "/updateBoard", method = RequestMethod.GET)
	public String updateBoard(Model model, HttpServletRequest request, BoardVO boardVO) throws Exception {
		try {
			boardVO.setBoardNo(Integer.parseInt(request.getParameter("boardNo")));
			BoardVO updateBoard = boardService.readBoard(boardVO);
			model.addAttribute("updateBoard", updateBoard);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/board/updateBoard";
	}
	@RequestMapping(value = "/updateBoard", method = RequestMethod.POST)
	public String updateBoardForm(Model model, HttpServletRequest request, BoardVO boardVO) throws Exception {
		try {
			boardVO.setBoardNo(Integer.parseInt(request.getParameter("boardNo")));
			boardVO.setBoardTitle(request.getParameter("boardTitle"));
			boardVO.setBoardContent(request.getParameter("boardContent"));
			boardService.updateBoard(boardVO);
			model.addAttribute("errorMessage", "修正されました。");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.listBoard(model, request);
	}
	@RequestMapping(value = "/deleteBoard", method = RequestMethod.POST)
	public String deleteBoardForm(Model model, HttpServletRequest request) throws Exception {
		try {
			boardService.deleteBoard(Integer.parseInt(request.getParameter("boardNo")));
			model.addAttribute("errorMessage", "削除されました。");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.listBoard(model, request);
	}
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public String projects(Model model) throws Exception {
		return "/board/projects";
	}
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String history(Model model) throws Exception {
		return "/board/history";
	}
	@RequestMapping(value = "/etc", method = RequestMethod.GET)
	public String etc(Model model) throws Exception {
		return "/board/etc";
	}
	
	/* comment */
	@RequestMapping(value = "/createComment", method = RequestMethod.POST)
	public String createComment(Model model, HttpSession session, HttpServletRequest request, BoardVO boardVO, CommentVO commentVO) throws Exception {
		if(!commentVO.getCommentWriter().equals("") && !commentVO.getCommentContent().equals("")) {
			commentService.createComment(commentVO);
		}else if(!commentVO.getCommentWriter().equals("") || !commentVO.getCommentContent().equals("")) {
			return this.readBoard(model, request, boardVO);
		}
		else {
			MainController mainController = new MainController();
			model.addAttribute("errorMessage", "ログインしてください。");
			return mainController.login(model);
		}
	    return this.readBoard(model, request, boardVO);
	}
	@RequestMapping(value = "/deleteComment", method = RequestMethod.GET)
	public String deleteComment(Model model, HttpSession session, HttpServletRequest request, BoardVO boardVO, CommentVO commentVO) throws Exception {		
		if(!commentVO.getCommentWriter().equals(null) && commentVO.getBoardNo() > -1) {
			commentService.deleteComment(commentVO);
		}
		return this.readBoard(model, request, boardVO);
	}
}
