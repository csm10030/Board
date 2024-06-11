package com.devfox.board.dao;

import java.util.List;

import com.devfox.board.vo.BoardVO;

public interface BoardDAO {
	List<BoardVO> selectBoardList() throws Exception;
	void createBoard(BoardVO boardVO) throws Exception;
	BoardVO readBoard(BoardVO boardVO) throws Exception;
	void updateBoard(BoardVO boardVO) throws Exception;
	void deleteBoard(int boardNo) throws Exception;
}

