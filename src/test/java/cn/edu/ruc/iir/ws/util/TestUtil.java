
package cn.edu.ruc.iir.ws.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestUtil {

	@Test
	public void testDBUtil() {
		ConfigFactory config = ConfigFactory.Instance();
		ReadSQLUtil.sqlSource = config.getProperty("SQLDirectory");
		DBUtils dbUtils = DBUtils.Instance();
		Map<String, String> aPs = new HashMap<String, String>();
		aPs.put("OpenID", "123");
		String fileName = "login/BindCheck.txt";
		String aSql = ReadSQLUtil.ReadSqlFile(fileName);
		dbUtils.getSQL(aSql, aPs);
	}
}
