package com.changon.minipro.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.changon.minipro.common.EmployeeVo;
import com.changon.minipro.common.Service;
import com.changon.minipro.member.dao.MemberDAO;

public class CursorType implements Service {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		MemberDAO dao = new MemberDAO();
		EmployeeVo resultVo = dao.getSalaryInfo(101, 15000);
		request.setAttribute("vo", resultVo);
		return "views/main/main.jsp";
	}

}
