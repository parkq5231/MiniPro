package com.changon.minipro.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.changon.minipro.common.Service;
import com.changon.minipro.member.dao.MemberDAO;

public class Login implements Service {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 로그인 과정을 처리
		
		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		
		vo.setmId(request.getParameter("mId"));
		vo.setmPassword(request.getParameter("mPassword"));
		vo = dao.login(vo); // MemberDAO의 select 메소드에 vo 담기
		
		String viewPage = null;
		
		if(vo.getmAuth() != null) {
			HttpSession session = request.getSession(); // 세션객체 호출
			session.setAttribute("mid", vo.getmId()); // 세션 아이디
			session.setAttribute("mauth", vo.getmAuth()); // 세션 권한
			request.setAttribute("vo", vo);
			viewPage = "views/member/loginSuccess.jsp";
		}else {
			viewPage = "views/member/loginFail.jsp";
		}
		
		return viewPage;
	}

}
