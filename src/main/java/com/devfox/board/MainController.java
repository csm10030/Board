package com.devfox.board;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devfox.board.service.UserService;
import com.devfox.board.vo.UserVO;

@Controller
public class MainController {

	@Resource(name = "userService")
	private UserService userService;
	
	/* main page */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) throws Exception {
		try {
		    List<UserVO> userList = userService.selectUserList();
		    model.addAttribute("userList", userList);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return "forward:index";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model, HttpSession session) throws Exception {
		try {
		    List<UserVO> userList = userService.selectUserList();
		    model.addAttribute("userList", userList);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return "index";
	}
	
	/* login */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) throws Exception {
	    return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String loginForm(Model model, HttpSession session, UserVO userVO) throws Exception {
		int checkId = userService.checkUser(userVO.getUserId());
		
		if(checkId == 0) {
			return "idError";
		} else {
			userVO.setUserPw(SHA512(userVO.getUserPw()));
			UserVO loginUser = userService.loginUser(userVO);
			
			if(null == loginUser) {
				return "pwError";
			} else if(loginUser.getUserRl() == 0) {				
				return "verifyError";
			} else {
				return "success";
			}				
		}
	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signIn(Model model, UserVO userVO, HttpSession session) throws Exception {
		try {
			userVO.setUserPw(SHA512(userVO.getUserPw()));
			UserVO loginUser = userService.loginUser(userVO);
			session.setAttribute("loginUser", loginUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return this.index(model, null);
	}
	@RequestMapping(value = "/chkId", method = RequestMethod.POST)
	@ResponseBody
	public String checkId(UserVO userVO) throws Exception {
		String result = "";
		
		try {
			int checkId = userService.checkUser(userVO.getUserId());
			
			if(checkId != 0) {
				result = "idError";
			} else {
				result = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session) throws Exception {
		try {
			session.removeAttribute("loginUser");
			model.addAttribute("errorMessage", "ログアウトしました。");	
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return this.index(model, null);
	}
	
	/* join */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model, UserVO userVO) throws Exception {
		model.addAttribute("userVO", userVO);
	    return "join";
	}
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	@ResponseBody
	public String joinForm(Model model, HttpSession session, @Validated @ModelAttribute UserVO userVO, BindingResult result) throws Exception {
		int checkId = userService.checkUser(userVO.getUserId());
		
		if (result.hasErrors()) {
		    if(checkId != 0) {
				return "verifyError";
			}
			else if(userVO.getUserId() == "") {
				return "idError";
			}
			else if(userVO.getUserPw() == "") {
				return "pwError";
			}
		}else {
			userVO.setUserPw(SHA512(userVO.getUserPw()));
			userService.joinUser(userVO);
			return "success";
		}
		return null;
	}
	
//	@Autowired
//	private MessageSource messageSource;
//	
//	@ExceptionHandler({BindException.class})
//	public ResponseEntity<String> paramViolationError(BindException ex) {
//		System.out.println(ResponseEntity.badRequest().body(messageSource.getMessage(ex.getFieldError(), Locale.getDefault())));
//		return ResponseEntity.badRequest().body(messageSource.getMessage(ex.getFieldError(), Locale.getDefault()));
//	}
	
	@RequestMapping(value="/joinConfirm", method=RequestMethod.GET)
	public String emailConfirm(Model model, UserVO userVO) throws Exception {
		userVO.setUserRl(1);
		userService.updateUser(userVO);
		return this.joinForm(model, null, userVO, null);
	}
	public static String SHA512(String userPw) {
		String hex = null;
		try {
			MessageDigest msg = MessageDigest.getInstance("SHA-512");
			msg.update(userPw.getBytes());
			hex = String.format("%128x", new BigInteger(1, msg.digest()));
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hex; 
	}
}