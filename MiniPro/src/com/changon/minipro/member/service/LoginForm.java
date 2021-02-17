package com.changon.minipro.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.changon.minipro.common.Service;

public class LoginForm implements Service {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		
		// 단순히 로그인 입력폼을 호출.
		
		return "views/member/loginForm.jsp";
	}

}
