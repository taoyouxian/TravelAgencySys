package cn.edu.ruc.iir.ws.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;

import cn.edu.ruc.iir.ws.util.DBUtils;
import cn.edu.ruc.iir.ws.util.JsonDBUtil;

public class DBDao {
	private static Logger log = Logger.getLogger(DBDao.class.getName());

	static DBDao _tDB = null;
	static DBUtils dbUtils = null;

	public static DBDao geTiDB() {
		if (_tDB == null) {
			_tDB = new DBDao();
			dbUtils = DBUtils.Instance();
		}
		return _tDB;
	}

	// 执行SQL操作
	public boolean executeSql(String aSql) {
		boolean aFlag = false;
		try {
			aFlag = dbUtils.Execute(aSql);
		} catch (Exception er) {
			log.info(er.getMessage());
		}
		return aFlag;
	}

	// 执行SQL文件及参数表的SQL操作
	public boolean executeSql(String aSql, Map<String, String> aPs) {
		String sql = dbUtils.getSQL(aSql, aPs);
		return executeSql(sql);
	}

	public JSONArray getTable(String aSql) {
		JSONArray aDBJson = null;
		try {
			ResultSet rs = dbUtils.Select(aSql);
			aDBJson = JsonDBUtil.rSToJson(rs);
		} catch (Exception er) {
			log.info(er.getMessage());
		}
		return aDBJson;
	}

	// 根据文件名与参数表返回查询结果
	public JSONArray getTable(String aSql, Map<String, String> aPs, List<String> aErrors) {
		String sql = dbUtils.getSQL(aSql, aPs);
		return getTable(sql);
	}

}
