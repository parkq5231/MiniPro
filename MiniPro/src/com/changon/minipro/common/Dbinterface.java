package com.changon.minipro.common;

import java.util.ArrayList;

public interface Dbinterface<T> { // generic type T 부여

	public ArrayList<T> selectList();
	public T select(T vo);
	public int insert(T vo);
	public int update(T vo);
	public int delete(T vo);
}
