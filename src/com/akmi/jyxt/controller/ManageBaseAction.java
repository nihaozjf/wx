package com.akmi.jyxt.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.akmi.jyxt.model.Teacherinf;
public class ManageBaseAction {

	public Teacherinf getCurrentUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Teacherinf teacherinf=(Teacherinf) request.getSession().getAttribute("teacherinf");
		teacherinf=new Teacherinf();
		teacherinf.setTeacherid("201222");
		return teacherinf;
		}
}
