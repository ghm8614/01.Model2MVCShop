package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class LoginAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		UserVO userVO = new UserVO();
		userVO.setUserId(userId);
		userVO.setPassword(password);

		UserService service = new UserServiceImpl();
		// 로그인시 입력한 값을 담은 userVO를 통해 db에서 꺼내온 dbVO 
		UserVO dbVO = service.loginUser(userVO);

		HttpSession session = request.getSession();
		session.setAttribute("user", dbVO);

		return "redirect:/index.jsp";
	}
}