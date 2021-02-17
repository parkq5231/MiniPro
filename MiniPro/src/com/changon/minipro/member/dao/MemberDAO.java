package com.changon.minipro.member.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.changon.minipro.common.DAO;
import com.changon.minipro.common.Dbinterface;
import com.changon.minipro.common.EmployeeVo;
import com.changon.minipro.member.service.MemberVO;

import oracle.jdbc.internal.OracleTypes;

public class MemberDAO extends DAO implements Dbinterface<MemberVO> {

	private PreparedStatement psmt;
	private ResultSet rs;

	private final String MEMBERLOGIN = "SELECT * FROM MEMBER WHERE MID = ?";

	@Override
	public ArrayList<MemberVO> selectList() {
		// 회원전체리스트를 올려준다.

		String sql = "SELECT * FROM MEMBER";

		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO vo;

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVO();
				vo.setmId(rs.getString("mid"));
				vo.setmName(rs.getString("mname"));
				vo.setmPassword(rs.getString("mpassword"));
				vo.setmAuth(rs.getString("mauth"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public MemberVO select(MemberVO vo) { // 지금은 로그인에서 사용한다.
		// 로그인 확인

		try {
			psmt = conn.prepareStatement(MEMBERLOGIN);
			psmt.setString(1, vo.getmId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setmAuth(rs.getString("mauth"));
				vo.setmName(rs.getString("mname"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public int insert(MemberVO vo) {
		// TODO Auto-generated method stub

		String sql = "INSERT INTO MEMBER(MID, MNAME, MPASSWORD) VALUES(?,?,?)";
		int n = 0;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getmId());
			psmt.setString(2, vo.getmName());
			psmt.setString(3, vo.getmPassword());
			n = psmt.executeUpdate();

			System.out.println(n + "건 업데이트 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}

	@Override
	public int update(MemberVO vo) {
		// 권한만 변경한다.
		String sql = null;
		if (vo.getmPassword() != null) {
			sql = "UPDATE MEMBER SET MPASSWORD = ? WHERE MID = ?"; // 패스워드 변경
		} else if (vo.getmAuth() != null) {
			sql = "UPDATE MEMBER SET MAUTH = ? WHERE MID = ?"; // 권한 변경
		}

		int n = 0;

		try {
			psmt = conn.prepareStatement(sql);
			if (vo.getmPassword() != null) {
				psmt.setString(1, vo.getmPassword()); // 패스워드 변경될때
			} else {
				psmt.setString(1, vo.getmAuth()); // 권한 변경될때
			}
			psmt.setString(2, vo.getmId());

			n = psmt.executeUpdate();

			System.out.println(n + "건 업데이트 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int delete(MemberVO vo) {
		// 회원 삭제

		String sql = "DELETE FROM MEMBER WHERE MID = ?";
		int n = 0;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getmId());
			n = psmt.executeUpdate();

			System.out.println(n + "건 삭제 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}

	// id 중복체크 메소드
	public boolean isIdCheck(String id) { // 관례상 boolean 타입 사용 메소드 이름은 is를 앞에 붙임
		boolean bool = true;
		String sql = "SELECT MID FROM MEMBER WHERE MID = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				bool = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return bool;
	}

	public MemberVO login(MemberVO vo) { // 로그인에서 사용한다.
		// 로그인 확인

		String sql = "SELECT * FROM MEMBER WHERE MID = ?, AND MPASSWORD = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getmId());
			psmt.setString(2, vo.getmPassword());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setmAuth(rs.getString("mauth"));
				vo.setmName(rs.getString("mname"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	// employeeList가져오기
	public ArrayList<EmployeeVo> EmployeeList() {
		ArrayList<EmployeeVo> list = new ArrayList<EmployeeVo>();
		EmployeeVo vo;
		String sql = "select employee_id,first_name,last_name,salary,hire_date,email from employees "
				+ "order by employee_id";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new EmployeeVo();
				vo.setEmployeeId(rs.getInt("employee_id"));
				vo.setFirstName(rs.getString("first_name"));
				vo.setLastName(rs.getString("last_name"));
				vo.setSalary(rs.getInt("salary"));
				vo.setHireDate(rs.getString("hire_date"));
				vo.setEmail(rs.getString("email"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

// 프로시저 활용
// 210217
	public EmployeeVo getSalaryInfo(int empId, int salary) {
		EmployeeVo resultVo = null;
		try {
			CallableStatement csmt = conn.prepareCall("{ call SAL_HISTORY_PROC(?,?,?) }");
			csmt.setInt(1, empId);
			csmt.setInt(2, salary);
			csmt.registerOutParameter(3, OracleTypes.CURSOR); // OUT
			csmt.execute();

			rs = (ResultSet) csmt.getObject(3);
			if (rs.next()) {
				resultVo = new EmployeeVo();
				resultVo.setEmail(rs.getString("email"));
				resultVo.setEmployeeId(rs.getInt("employee_id"));
				resultVo.setFirstName(rs.getString("first_name"));
				resultVo.setLastName(rs.getString("last_name"));
				resultVo.setHireDate(rs.getString("hire_date"));
				resultVo.setSalary(rs.getInt("salary"));

				System.out.println(rs.getInt(1)); // employee_id
				System.out.println(rs.getString("first_name"));
				System.out.println(rs.getString("salary"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return resultVo;
	}

// PagingList
	public List<EmployeeVo> getPagingList(int page) {
		List<EmployeeVo> list = new ArrayList<EmployeeVo>();
		String sql = "select b.rn, b.* " //
				+ "from (select rownum rn,a.* " //
				+ "from (select * from employees " //
				+ "order by employee_id) a ) b " //
				+ "where b.rn between ? and ?";
		EmployeeVo resultVo;

		try {
			psmt = conn.prepareStatement(sql);
			int startCnt = 1 + (page - 1) * 10;
			int endCnt = page * 10;
			psmt.setInt(1, startCnt);
			psmt.setInt(2, endCnt);
			rs = psmt.executeQuery();
			while (rs.next()) {
				resultVo = new EmployeeVo();
				resultVo.setEmail(rs.getString("email"));
				resultVo.setEmployeeId(rs.getInt("employee_id"));
				resultVo.setFirstName(rs.getString("first_name"));
				resultVo.setLastName(rs.getString("last_name"));
				resultVo.setHireDate(rs.getString("hire_date"));
				resultVo.setSalary(rs.getInt("salary"));
				list.add(resultVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

// Paging totalCnt
	public int getTotalCnt() {
		String sql = "select count(*) from employees";
		int totalCnt =0;
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				totalCnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return totalCnt;
	}
//close
	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
