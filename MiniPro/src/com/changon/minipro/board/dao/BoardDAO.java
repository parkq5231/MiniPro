package com.changon.minipro.board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.changon.minipro.board.service.BoardVO;
import com.changon.minipro.common.DAO;
import com.changon.minipro.common.Dbinterface;

public class BoardDAO extends DAO implements Dbinterface<BoardVO> {
	
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public ArrayList<BoardVO> selectList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardVO select(BoardVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
