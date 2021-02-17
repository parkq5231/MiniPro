package com.changon.minipro;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.changon.minipro.common.Service;
import com.changon.minipro.member.service.CursorType;
import com.changon.minipro.member.service.Login;
import com.changon.minipro.member.service.LoginForm;
import com.changon.minipro.member.service.Logout;
import com.changon.minipro.member.service.MemberIdCheck;
import com.changon.minipro.member.service.MemberJoin;
import com.changon.minipro.member.service.MemberJoinForm;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Service> map = new HashMap<String, Service>(); // String = 요청명, Service = 실행할 Command
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// 초기화 구현
		map.put("/main.do", new MainService()); // 메인화면 호출
		map.put("/loginForm.do", new LoginForm()); // 로그인폼 호출
		map.put("/login.do", new Login()); // 로그인
		map.put("/logout.do", new Logout()); // 로그아웃
		map.put("/memberJoinForm.do", new MemberJoinForm()); // 회원가입 Form으로 이동
		map.put("/memberJoin.do", new MemberJoin()); // 회원가입 버튼 클릭 시 진행
		map.put("/idCheck.do", new MemberIdCheck()); // id 중복체크
		//map.put("/memberJoin.do", new MemberJoin());
		
		map.put("/cursor.do", new CursorType());
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// 서비스 구현
		
		request.setCharacterEncoding("utf-8");
		
		// 요청 분석
		String contextPath = request.getContextPath(); // 최상위 경로
		String uri = request.getRequestURI(); 
		String path = uri.substring(contextPath.length()); // 실제 요청한 것(최상위 경로를 추출한 순수 uri값)
		
		Service service = map.get(path); // 적절한 command를 찾는 부분
		String viewPage = service.run(request, response); // 실행해서 결과를 돌려주는 페이지
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
