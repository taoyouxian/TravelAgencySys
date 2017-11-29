
package cn.edu.ruc.iir.ws.cli;

import javax.xml.ws.Endpoint;

import cn.edu.ruc.iir.ws.controller.UserController;

public class Main {

	/**
	 * http://localhost:8080/Tutorial-on-BPEL/getUser 发布WebService 简单
	 */
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/Tutorial-on-BPEL/getUser", new UserController());
	}
}
