package cn.edu.ruc.iir.ws.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class DBUtils {

	private Logger log = Logger.getLogger(DBUtils.class);
	private static DBUtils instance = null;

	private String DRIVER;
	private String URL;
	private String USERID;
	private String USERPASSWORD;
	private Connection conn = null;

	private DBUtils() {
		try {
			ConfigFactory config = ConfigFactory.Instance();
			DRIVER = config.getProperty("driver");
			URL = config.getProperty("url");
			USERID = config.getProperty("user");
			USERPASSWORD = config.getProperty("password");

			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DBUtils Instance() {
		if (instance == null) {
			instance = new DBUtils();
		}
		return instance;
	}

	public void close(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close(Statement st, Connection conn) {
		try {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet Select(String SQL) {
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(SQL);
		} catch (Exception e) {
			log.error("Select from sql server error! errmsg:{}", e);
		}
		return rs;
	}

	public void Execute(String SQL) {
		Statement statement = null;
		try {
			statement = conn.createStatement();
			statement.execute(SQL);
		} catch (Exception e) {
			log.error("Execute sql error! errmsg:{}", e);
		} finally {
			close(statement, conn);
		}
	}

	public String getTable(String aSql, Map<String, String> aPs, List<String> aErrors) {
		try {
			for (String aKey : aPs.keySet()) {
				String aOld = "{" + aKey + "}";
				// String aNew = aPs.get(aKey);//可能是Int类型
				String aNew = String.valueOf(aPs.get(aKey));
				aSql = aSql.replace(aOld, aNew);
			}
		} catch (Exception er) {
			aErrors.add(er.getMessage());
			log.info(er.getMessage());
		}
		log.info("sql: " + aSql);
		return aSql;
	}
}
