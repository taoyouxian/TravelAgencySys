/**
 * UserController.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.edu.ruc.iir.ws.controller;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import cn.edu.ruc.iir.ws.service.UserServiceI;
import cn.edu.ruc.iir.ws.service.impl.UserServiceImpl;

@WebService
public class UserController {

	private static Logger log = Logger.getLogger(UserController.class);

	@WebMethod(action = "getUser")
	public String getUser(String uname) {
		log.info("request getUser");
		UserServiceI userServiceI = new UserServiceImpl();
		String res = userServiceI.getUser(uname);
		return res;
	}
}