package com.changon.minipro.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.changon.minipro.common.Service;

public class Logout implements Service {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		 
		HttpSession session = request.getSession();
		//String mid = (String)session.getAttribute("mid");
		//request.setAttribute("mid", mid);
		session.invalidate();
		//return "views/member/logout.jsp";
		return "main.do";
	}

}
