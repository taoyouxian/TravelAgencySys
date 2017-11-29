
package cn.edu.ruc.iir.ws.service.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.log4j.Logger;

/**
 * 
 * @Package cn.edu.ruc.iir.ws.service
 * @ClassName: FlightService
 * @Description: Flight 业务类
 * @author taoyouxian
 * @date 2017年11月29日 上午10:12:59
 */
@WebService
public class FlightServiceImpl {

	// sql template
	private String sqlT = "select * from flight where";
	
	private static Logger log = Logger.getLogger(FlightServiceImpl.class);

	@WebMethod(action = "getFlight")
	public String getAirline(String dayGo, String cityFrom, String cityTo, String prefE, double prefP) {
		log.info("request getFlight");
		String[] rStrings = new String[2];
		String rString;
		String sql = sqlT + " daygo = " + "'" + dayGo + "'" + " AND cityfrom = " + "'" + cityFrom + "'"
				+ " AND cityto = " + "'" + cityTo + "'";
		if (prefE != "" && prefE != null) {
			sql += " AND pref = " + "'" + prefE + "'";
		}
		if (prefP != Double.NaN && prefP != 0.0) {
			sql += " AND price <= " + prefP;
		}
		sql += " ORDER BY price LIMIT 2";

		return "";
	}
}
