package com.changon.minipro.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.changon.minipro.member.dao.MemberDAO;

public class PagingTest implements Service {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO dao = new MemberDAO();

		String pageNo = request.getParameter("pageNo");
		pageNo = (pageNo == null) ? "1" : pageNo;
		int pg = Integer.parseInt(pageNo);
		Paging paging = new Paging();

		paging.setPageNo(pg); // 현재 페이지 지정
		paging.setPageSize(10);
		paging.setTotalCount(dao.getTotalCnt());
		System.out.println(paging);

		dao = new MemberDAO();// reason: close();
		List<EmployeeVo> list = dao.getPagingList(pg);
		request.setAttribute("list", list);
		request.setAttribute("params", paging);

		return "views/main/main.jsp";
	}
}
