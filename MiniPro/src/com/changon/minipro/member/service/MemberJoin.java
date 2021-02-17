package com.changon.minipro.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.changon.minipro.common.Service;
import com.changon.minipro.member.dao.MemberDAO;

public class MemberJoin implements Service {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 회원가입시 DB에 저장하는 메소드
		
		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		
		vo.setmId(request.getParameter("mId"));
		vo.setmPassword(request.getParameter("mPassword"));
		vo.setmName(request.getParameter("mName"));
		
		int n = dao.insert(vo);
		String viewPage = null;
		request.setAttribute("vo", vo);
		if(n != 0) {
			viewPage = "views/member/memberJoinSuccess.jsp";
		}else {
			String message = "입력 내용을 DB에 저장하지 못했습니다.";
			request.setAttribute("msg", message);
			viewPage = "views/member/memberJoinFail.jsp";
		}
		
		return viewPage;
	}

}
