package com.devfox.board.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devfox.board.dao.BoardDAO;
import com.devfox.board.vo.BoardVO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BoardVO> selectBoardList() throws Exception {
		return sqlSession.selectList("selectBoardList");
	}

	@Override
	public void createBoard(BoardVO boardVO) throws Exception {
		sqlSession.insert("createBoard", boardVO);
	}

	@Override
	public BoardVO readBoard(BoardVO boardVO) throws Exception {
		return sqlSession.selectOne("readBoard", boardVO);
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		sqlSession.update("updateBoard", boardVO);
	}

	@Override
	public void deleteBoard(int boardNo) throws Exception {
		sqlSession.delete("deleteBoard", boardNo);
		
	}
}

