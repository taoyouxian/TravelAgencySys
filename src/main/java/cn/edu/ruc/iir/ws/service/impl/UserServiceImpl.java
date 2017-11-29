
package cn.edu.ruc.iir.ws.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.edu.ruc.iir.ws.dao.DBDao;
import cn.edu.ruc.iir.ws.service.UserServiceI;
import cn.edu.ruc.iir.ws.util.ConfigFactory;
import cn.edu.ruc.iir.ws.util.DBUtils;
import cn.edu.ruc.iir.ws.util.ReadSQLUtil;

/**
 * 
 * @Package cn.edu.ruc.iir.ws.service.impl
 * @ClassName: UserServiceImpl
 * @Description: UserService 实现类
 * @author taoyouxian
 * @date 2017年11月29日 上午10:46:06
 */
public class UserServiceImpl implements UserServiceI {

	ConfigFactory config = ConfigFactory.Instance();
	DBUtils dbUtils = DBUtils.Instance();
	DBDao dbDao = null;

	private static Logger log = Logger.getLogger(UserServiceImpl.class);

	/**
	 * (non Javadoc)
	 * <p>
	 * Title: getUser
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param uname
	 * @return
	 * @see cn.edu.ruc.iir.ws.service.impl.UserServiceI#getUser(java.lang.String)
	 */

	@Override
	public String getUser(String uname) {
		ReadSQLUtil.sqlSource = config.getProperty("SQLDirectory");
		Map<String, String> aPs = new HashMap<String, String>();
		aPs.put("u_name", uname);
		String fileName = "user/Users.txt";
		String aSql = ReadSQLUtil.ReadSqlFile(fileName);
		String sql = dbUtils.getSQL(aSql, aPs);
		JSONArray aJson = dbDao.getTable(sql);
		String res = JSON.toJSONString(aJson);
		return res;
	}

}
