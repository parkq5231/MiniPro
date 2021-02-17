package com.changon.minipro.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.changon.minipro.common.Service;
import com.changon.minipro.member.dao.MemberDAO;

public class MemberIdCheck implements Service {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// id 중복체크
		MemberDAO dao = new MemberDAO();
		String id = request.getParameter("mid");
		boolean bool = dao.isIdCheck(id);
		
		String message = null;
		if(bool) {
			message = "사용할 수 있는 아이디"; // DAO에 정의된 함수에서 false 결과값(중복되지 않는 ID)
		}else {
			message = "이미 사용중인 아이디";
		}
		
		request.setAttribute("msg", message);
		request.setAttribute("check", bool);

		return "views/member/idCheck.jsp";
	}

}
