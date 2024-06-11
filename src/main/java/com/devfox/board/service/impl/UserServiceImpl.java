package com.devfox.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.devfox.board.dao.UserDAO;
import com.devfox.board.service.UserService;
import com.devfox.board.util.MailUtils;
import com.devfox.board.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Resource
	private UserDAO userMapper;
	
	@Override
	public List<UserVO> selectUserList() throws Exception {
		return userMapper.selectUserList();
	}
	
	@Override
	public void joinUser(UserVO userVO) throws Exception {
		userMapper.joinUser(userVO);

		// mail 작성 관련 
		MailUtils sendMail = new MailUtils(mailSender);

		sendMail.setSubject("Spring Board 会員登録の認証メール");
		sendMail.setText(new StringBuffer().append("<h1>[メールアドレスを認証]</h1>")
				.append("<p>下記のリンクをクリックするとメール認証が完了されます。</p>")
				.append("<a href='http://localhost:8090/board/joinConfirm")
				.append("?userId=")
				.append(userVO.getUserId())
				.append("&userMc=")
				.append(userVO.getUserMc())
				.append("' target='_blenk'>メール認証確認</a>")
				.toString());
		sendMail.setFrom("roqkf!2121", "dev.souryuu1003@gmail.com");
		sendMail.setTo(userVO.getUserId());
		sendMail.send();
	}

	@Override
	public void updateUser(UserVO userVO) throws Exception {
		userMapper.updateUser(userVO);
	}
	
	@Override
	public UserVO loginUser(UserVO userVO) throws Exception {
		return userMapper.loginUser(userVO);
	}

	@Override
	public int checkUser(String userId) throws Exception {
		return userMapper.checkUser(userId);
	}
	
	
}
