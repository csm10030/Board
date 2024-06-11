package com.devfox.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.devfox.board.dao.CommentDAO;
import com.devfox.board.service.CommentService;
import com.devfox.board.vo.CommentVO;

@Service("commentService")
public class CommentServiceImpl implements CommentService{
	@Resource
	private CommentDAO commentMapper;
	
	@Override
	public List<CommentVO> selectCommentList(int boardNo) throws Exception {
		return commentMapper.selectCommentList(boardNo);
	}

	@Override
	public void createComment(CommentVO commentVO) throws Exception {
		commentMapper.createComment(commentVO);
	}

	@Override
	public void deleteComment(CommentVO commentVO) throws Exception {
		commentMapper.deleteComment(commentVO);
		
	}
}
