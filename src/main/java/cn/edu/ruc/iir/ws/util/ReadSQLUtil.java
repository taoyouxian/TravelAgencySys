package cn.edu.ruc.iir.ws.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * @Package utils
 * @ClassName: ReadSQLUtil
 * @Description: sql文本读取工具类
 * @author Tao
 * @date 2016年3月13日 上午11:08:21
 */
public class ReadSQLUtil {

	private static Logger log = Logger.getLogger(ReadSQLUtil.class);

	public static String sqlSource;

	public static String ReadSqlFromFile(File fileName, List<String> aErrors) {
		StringBuffer buffer = new StringBuffer();
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			// 获取磁盘的文件
			// File file = new File(fileName);
			// 开始读取磁盘的文件
			fileInputStream = new FileInputStream(fileName);
			// 创建一个字节流
			inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
			// 创建一个字节的缓冲流
			bufferedReader = new BufferedReader(inputStreamReader);
			String string = null;
			while ((string = bufferedReader.readLine()) != null) {
				buffer.append(string + "\n");
			}

		} catch (FileNotFoundException e) {
			aErrors.add("Sql file not Found!");
			log.error("Sql file not Found!\n");
			e.printStackTrace();
		} catch (IOException e) {
			aErrors.add("Read sql file IOException!");
			log.error("Read sql file IOException!");
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
				inputStreamReader.close();
				fileInputStream.close();
			} catch (IOException e) {
				aErrors.add(e.getMessage());
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}

	public static String ReadSqlFromFile(File fileName) {
		StringBuffer buffer = new StringBuffer();
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileInputStream = new FileInputStream(fileName);
			inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String string = null;
			while ((string = bufferedReader.readLine()) != null) {
				buffer.append(string + "\n");
			}
		} catch (FileNotFoundException e) {
			log.error("Sql file not Found!\n");
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Read sql file IOException!");
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
				inputStreamReader.close();
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log.error("fileName: " + fileName);
		return buffer.toString();
	}

	public static String ReadSqlFromFile(String fileName) {
		String sqlPath = sqlSource + fileName;
		return ReadSqlFromFile(new File(sqlPath));
	}

	public static String ReadSqlFromFile(String fileName, List<String> aErrors) {
		String sqlPath = sqlSource + fileName;
		return ReadSqlFromFile(new File(sqlPath), aErrors);
	}

	public static void writeFile(String content, String filename) {
		// 要写入的文件
		File file = new File(filename);
		// 写入流对象
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(file);
			printWriter.print(content);
			printWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null) {
				try {
					printWriter.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	public static String ReadSqlFile(String fileName) {
		String sql = "";
		String sqlPath = sqlSource + fileName;
		// String sqlPath = GlobalService.sqlPath + fileName;
		try {
			File file = new File(sqlPath);
			InputStream in = new FileInputStream(file);
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = in.read(buf)) != -1) {
				String tem = new String(buf, 0, size);
				sql += tem;
			}
			in.close();
		} catch (FileNotFoundException e) {
			log.error("Sql file not Found!\n" + sqlPath);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Read sql file IOException!");
			e.printStackTrace();
		}
		return sql;
	}

	public static String ReadFromUrl(String fileUrl) {
		String sql = "";
		try {
			URL url = new URL(fileUrl);
			InputStream in = url.openStream();
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = in.read(buf)) != -1) {
				String tem = new String(buf, 0, size);
				sql += tem;
			}
			in.close();
		} catch (IOException e) {
			log.error("Read file IOException!");
			e.printStackTrace();
		}
		return sql;
	}

	public static String Query(String fileName) {
		String sql = "";
		String sqlPath = "F:\\FFOutput\\Tomcat\\apache-tomcat-7.0.42-Devs\\wtpwebapps\\BT_Wx\\WEB-INF\\Sql\\";
		try {
			File file = new File(sqlPath + fileName);
			InputStream in = new FileInputStream(file);
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = in.read(buf)) != -1) {
				String tem = new String(buf, 0, size);
				sql += tem;
			}
			in.close();
		} catch (FileNotFoundException e) {
			log.error("Sql file not Found!\n" + sqlPath);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Read sql file IOException!");
			e.printStackTrace();
		}
		return sql;
	}

}
