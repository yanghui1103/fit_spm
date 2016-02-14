package com.bw.common.uitily;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.activiti.engine.form.FormProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.bw.common.model.CommonMedol;
import com.bw.fit.spm.model.Common;

public class PubFun {
	private static final double PI = 3.1415926535898;
	private static double EARTH_RADIUS = 6378.137;// 地球半径

	public static String getSysDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static String getSysDateM() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(date);
	}

	public static String getTruncSysDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/*
	 * 校验session 是否已经过期
	 */
	public static boolean checkSessionValidate(HttpServletRequest request) {
		/*
		 * 获取当前session
		 */
		HttpSession session = request.getSession(false);
		if (session != null) {
			return true;
		}

		return false;
	}

	/*
	 * 返回长度为【strLength】的随机数，在前面补0
	 */
	public static String getFixLenthString(int strLength) throws Exception {

		Date d = new Date();

		return String.valueOf(d.getTime());
	}

	public static int compare_date(String DATE1, String DATE2) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/*
	 * convertXml2JsonByResMsg
	 */
	public static String convertXml2JsonByResMsg(Document doc) {
		String json = "{\"res\":\"";
		json = json + doc.selectSingleNode("/root/res").getText();
		json = json + "\",\"msg\":\"";
		json = json + doc.selectSingleNode("/root/msg").getText();
		json = json + "\"}";
		return json;

	}

	public static String getMutilLongIntId() {
		return String.valueOf(System.currentTimeMillis());
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double GetDistance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);

		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	/**
	 * 根据经纬度，和半径算出经纬度范围：
	 * 
	 * @param raidus
	 *            单位米 return minLat,minLng,maxLat,maxLng
	 */

	public static double[] getAround(double lat, double lon, int raidus) {

		Double latitude = lat;
		Double longitude = lon;

		Double degree = (24901 * 1609) / 360.0;
		double raidusMile = raidus;

		Double dpmLat = 1 / degree;
		Double radiusLat = dpmLat * raidusMile;
		Double minLat = latitude - radiusLat;
		Double maxLat = latitude + radiusLat;

		Double mpdLng = degree * Math.cos(latitude * (PI / 180));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng * raidusMile;
		Double minLng = longitude - radiusLng;
		Double maxLng = longitude + radiusLng;
		return new double[] { minLat, minLng, maxLat, maxLng };
	}

	/**
	 * 获取当前应用的
	 */
	public static ClassPathXmlApplicationContext getSysSpringCtx() {
		// 加载spring配置
		ClassPathXmlApplicationContext ctx = null;
		{
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext("springAppContext.xml");
			}
		}
		return ctx;
	}

	/**
	 * 将xml转为json（定制）
	 */
	public static String xml2json(Document doc) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("res", doc.selectSingleNode("/root/res").getText());

		jsonObj.put(
				"msg",
				(doc.selectSingleNode("/root/msg").getText() == null || ""
						.equals(doc.selectSingleNode("/root/msg").getText())) ? ""
						: doc.selectSingleNode("/root/msg").getText());
		return jsonObj.toJSONString();
	}

	public static String getFileTypeName(String s) {
		int index = 0;
		for (int i = s.length() - 1; i > 0; i--) {
			if ('.' == (s.charAt(i))) {
				index = i;
				break;
			}
		}
		return (s.substring(index, s.length()));
	}

	public static String getUUID(boolean isContainMLine) {
		String s = UUID.randomUUID().toString();
		if (isContainMLine) {
			return s;
		}
		// 去掉"-"符号
		return s.replace("-", "");
	}

	public static String getUUID() {
		return getUUID(false);
	}

	public static Map<String, FormProperty> createMap(List<FormProperty> props) {
		Map<String, FormProperty> re = new HashMap<String, FormProperty>();
		for (FormProperty p : props) {
			re.put(p.getId(), p);
		}
		return re;
	}

	/**
	 * 从request里取出数据
	 * 
	 * @throws Exception
	 */
	public static JSONArray getRequestParamData(HttpServletRequest request)
			throws Exception {
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		return array;
	}
}
