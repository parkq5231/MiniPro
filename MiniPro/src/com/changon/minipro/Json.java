package com.changon.minipro;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.changon.minipro.common.EmployeeVo;
import com.changon.minipro.member.dao.MemberDAO;

@WebServlet("/Json")
public class Json extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Json() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		MemberDAO dao = new MemberDAO();
		ArrayList<EmployeeVo> list = new ArrayList<EmployeeVo>();
		list = dao.EmployeeList();
		String json = "[";
		for (EmployeeVo obj : list) {

			json += "{\"employeeId\":\"" + obj.getEmployeeId() + "\", ";
			json += "\"firstName\":\"" + obj.getFirstName() + "\", ";
			json += "\"lastName\":\"" + obj.getLastName() + "\", ";
			json += "\"salary\":\"" + obj.getSalary() + "\", ";
			json += "\"hireDate\":\"" + obj.getHireDate() + "\", ";
			json += "\"email\":\"" + obj.getEmail() + "\"}";
			json += "]";
//			if (json == list.size()) {
//				json += ",";
//			}
		}
		response.getWriter().append(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
