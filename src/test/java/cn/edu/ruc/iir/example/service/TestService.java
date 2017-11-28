
package cn.edu.ruc.iir.example.service;

import org.junit.Test;

import cn.edu.ruc.iir.example.add.AddService;
import cn.edu.ruc.iir.example.sub.SubService;

public class TestService {

	@Test
	public void testExample() {
		double a1 = 1.55;
		double a2 = 1.4;
		AddService aService = new AddService();
		aService.add(a1, a2);

		SubService sService = new SubService();
		sService.sub(a1, a2);
	}
}
