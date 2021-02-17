package com.changon.minipro.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Service {

	public String run(HttpServletRequest request, HttpServletResponse response);
	
}
