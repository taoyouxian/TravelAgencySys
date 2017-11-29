package cn.edu.ruc.iir.ws.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.apache.log4j.Logger;

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

	public void close(PreparedStatement ps, Connection conn) {
		try {
			if (ps != null)
				ps.close();
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
			log.error("Select from sql server error! errmsg: ", e);
		}
		return rs;
	}

	public boolean Execute(String SQL) {
		boolean flag = false;
		Statement statement = null;
		try {
			statement = conn.createStatement();
			statement.execute(SQL);
			flag = true;
		} catch (Exception e) {
			log.error("Execute sql error! errmsg: ", e);
		} finally {
			close(statement, conn);
		}
		return flag;
	}

	public boolean execute(String sql, Object[] params) {
		boolean flag = false;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; ++i) {
					ps.setObject(i + 1, params[i]);
				}
			}
			ps.execute();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps, conn);
		}
		return flag;
	}

	public String getSQL(String aSql, Map<String, String> aPs) {
		try {
			for (String aKey : aPs.keySet()) {
				String aOld = "{" + aKey + "}";
				// String aNew = aPs.get(aKey);//可能是Int类型
				String aNew = String.valueOf(aPs.get(aKey));
				aSql = aSql.replace(aOld, aNew);
			}
		} catch (Exception er) {
			log.info(er.getMessage());
		}
		log.info("Execute sql msg: " + aSql);
		return aSql;
	}
}
