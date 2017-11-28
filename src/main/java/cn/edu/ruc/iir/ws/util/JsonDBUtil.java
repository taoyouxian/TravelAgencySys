package cn.edu.ruc.iir.ws.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class JsonDBUtil {
	public static String rSetToJson(ResultSet rs) throws SQLException, JSONException {
		JSONArray array = new JSONArray();
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (rs.next()) {
			JSONObject jsonObj = new JSONObject();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				jsonObj.put(columnName, value);
			}
			array.add(jsonObj);
		}
		return array.toString();
	}

	public static JSONArray rSToJson(ResultSet rs) throws SQLException, JSONException {
		JSONArray array = new JSONArray();
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (rs.next()) {
			JSONObject jsonObj = new JSONObject();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				jsonObj.put(columnName, value);
			}
			array.add(jsonObj);
		}
		return array;
	}

	public static void main(String[] args) {
		DBUtils dbUtils = DBUtils.Instance();
		ResultSet rs = dbUtils.Select("select * from T_User");
		try {
			String aStr = JsonDBUtil.rSetToJson(rs);
			System.out.println(aStr);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
