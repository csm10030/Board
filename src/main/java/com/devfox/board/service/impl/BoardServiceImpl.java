package com.devfox.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.devfox.board.dao.BoardDAO;
import com.devfox.board.service.BoardService;
import com.devfox.board.vo.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	@Resource
	private BoardDAO boardMapper;
	
	@Override
	public List<BoardVO> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}

	@Override
	public void createBoard(BoardVO boardVO) throws Exception {
		boardMapper.createBoard(boardVO);
	}

	@Override
	public BoardVO readBoard(BoardVO boardVO) throws Exception {
		return boardMapper.readBoard(boardVO);
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		boardMapper.updateBoard(boardVO);
	}

	@Override
	public void deleteBoard(int boardNo) throws Exception {
		boardMapper.deleteBoard(boardNo);
	}
}
