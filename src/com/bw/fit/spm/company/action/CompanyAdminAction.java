package com.bw.fit.spm.company.action;

import java.io.IOException;
import java.io.Writer;

import com.bw.fit.spm.company.service.impl.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.bw.common.actions.BaseAction;
import com.bw.common.model.CommonMedol;
import com.bw.common.model.LoginUser;
import com.bw.common.service.impl.AllSysAuthServiceImpl;
import com.bw.common.uitily.MD5;
import com.bw.common.uitily.PubFun;
import com.bw.fit.spm.card.action.CardAdminAction;
import com.bw.fit.spm.model.Common;

public class CompanyAdminAction extends BaseAction{
	
	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpSession session = request.getSession(false);
	private static Log log = LogFactory.getLog(CompanyAdminAction.class);
	/**
	 * 创建商业主体资料
	 * @throws Exception 
	 */
	public String createCompanyInfo() throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String((request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setCompany_name( (String) (((JSONObject) array.get(0)).get("param1")));
		c.setCompany_type_code((String) (((JSONObject) array.get(0)).get("param2")));
		c.setParent_company_id( (String) (((JSONObject) array.get(0)).get("param3")));
		c.setCompany_level( (String) (((JSONObject) array.get(0)).get("param4")));
		c.setArea_code((String) (((JSONObject) array.get(0)).get("param5")));
		c.setCompany_admin( (String) (((JSONObject) array.get(0)).get("param6")));
		c.setCompany_admin_phone( (String) (((JSONObject) array.get(0)).get("param7")));
		c.setCompany_address((String) (((JSONObject) array.get(0)).get("param8")));
		c.setCreate_time(PubFun.getSysDateM());
		c.setVersion_time(PubFun.getSysDateM());		
		c.setCreator(((LoginUser)session.getAttribute("LoginUser")).getUser_cd()); 
		JSONObject jsonObject= ((CompanyAdminServiceImpl) getBean("companyAdminServiceImpl"))
			.createCompanyInfo(c);
		wr.write(jsonObject.toJSONString());
		wr.close();
		return null ;
	}
	/**
	 * 新建社区资料
	 */
	public String createVillageInfo()  throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String((request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setVillage_name((String) (((JSONObject) array.get(0)).get("param1")));
		c.setVillage_address((String) (((JSONObject) array.get(0)).get("param2")));
		c.setCompany_id((String) (((JSONObject) array.get(0)).get("param3")));
		c.setCreator(((LoginUser)session.getAttribute("LoginUser")).getUser_cd()); 
		JSONObject jsonObject= ((CompanyAdminServiceImpl) getBean("companyAdminServiceImpl"))
			.createVillageInfo(c);
		wr.write(jsonObject.toJSONString());
		wr.close();
		
		return null ;
	}
	/**
	 * getVillageByCompany
	 * 根据商业主体查询住区
	 */
	public String getVillageByCompany()  throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String((request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setCompany_id((String) (((JSONObject) array.get(0)).get("param1")));
		c.setCreator(((LoginUser)session.getAttribute("LoginUser")).getUser_cd()); 
		JSONObject jsonObject= ((CompanyAdminServiceImpl) getBean("companyAdminServiceImpl"))
			.getVillageByCompany(c);
		wr.write(jsonObject.toJSONString());
		wr.close();
		
		return null ;
	}
	/**
	 * createHouseInfo
	 * 创建家庭
	 */
	public String createHouseInfo() throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String((request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setHouse_name((String) (((JSONObject) array.get(0)).get("param1")));
		c.setHouse_id(PubFun.getUUID());
		c.setVillage_id((String) (((JSONObject) array.get(0)).get("param2")));
		c.setCreator(((LoginUser)session.getAttribute("LoginUser")).getUser_cd()); 
		JSONObject jsonObject= ((CompanyAdminServiceImpl) getBean("companyAdminServiceImpl"))
			.createHouseInfo(c);
		wr.write(jsonObject.toJSONString());
		wr.close();
		return null ;
		
	}
	/**
	 * 制卡
	 * createCardInfo
	 */
	public String createCardInfo()  throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String((request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setCard_code((String) (((JSONObject) array.get(0)).get("param1")));
		c.setHouse_id((String) (((JSONObject) array.get(0)).get("param2")));
		c.setPerson_name((String) (((JSONObject) array.get(0)).get("param3")));
		c.setCard_phone((String) (((JSONObject) array.get(0)).get("param4")));
		c.setCard_sfz((String) (((JSONObject) array.get(0)).get("param5")));
		c.setCreator(((LoginUser)session.getAttribute("LoginUser")).getUser_cd()); 
		JSONObject jsonObject= ((CompanyAdminServiceImpl) getBean("companyAdminServiceImpl"))
			.createCardInfo(c);
		wr.write(jsonObject.toJSONString());
		wr.close();
		
		return null ;
	}
	/**
	 * 查询商业主体列表
	 */
	public String qryCompanyList()  throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String((request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setCompany_id((String) (((JSONObject) array.get(0)).get("param1")));
		int page_start = Integer.valueOf((String)(((JSONObject) array.get(0)).get("param2")))*Integer.valueOf((String)(((JSONObject) array.get(0)).get("param3")));
		int page_end = page_start + Integer.valueOf((String)(((JSONObject) array.get(0)).get("param3"))) ;
		c.setTemp_str1(String.valueOf(page_start));
		c.setTemp_str2(String.valueOf(page_end));
		c.setCreator(((LoginUser)session.getAttribute("LoginUser")).getUser_cd()); 
		JSONObject jsonObject= ((CompanyAdminServiceImpl) getBean("companyAdminServiceImpl"))
			.qryCompanyList(c);
		wr.write(jsonObject.toJSONString());
		wr.close();
		
		return null ;
	}
	/**
	 * 卡查询
	 */
	public String qryCardInfoList()   throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String((request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setPerson_name((String) (((JSONObject) array.get(0)).get("param1")));
		c.setCompany_id((String) (((JSONObject) array.get(0)).get("param2")));
		int page_start = Integer.valueOf((String)(((JSONObject) array.get(0)).get("param3")))*Integer.valueOf((String)(((JSONObject) array.get(0)).get("param4")));
		int page_end = page_start + Integer.valueOf((String)(((JSONObject) array.get(0)).get("param4"))) ;
		c.setTemp_str1(String.valueOf(page_start));
		c.setTemp_str2(String.valueOf(page_end));
		c.setCreator(((LoginUser)session.getAttribute("LoginUser")).getUser_cd()); 
		c.setCard_phone((String) (((JSONObject) array.get(0)).get("param5")));
		c.setCard_code((String) (((JSONObject) array.get(0)).get("param6")));
		JSONObject jsonObject= ((CompanyAdminServiceImpl) getBean("companyAdminServiceImpl"))
			.qryCardInfoList(c);
		wr.write(jsonObject.toJSONString());
		wr.close();
		
		return null ;
	}
	/**
	 * 删除卡记录
	 */
	public String deleteCardInfoList() throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String((request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setTemp_str1((String) (((JSONObject) array.get(0)).get("param1")));
		c.setOperator(((LoginUser)session.getAttribute("LoginUser")).getUser_cd()); 
		JSONObject jsonObject= ((CompanyAdminServiceImpl) getBean("companyAdminServiceImpl"))
			.deleteCardInfoList(c);
		wr.write(jsonObject.toJSONString());
		wr.close();
		
		return null ;
	}
	/**
	 * 修改密码
	 */
	public String changeUserPasswd()  throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String((request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		MD5 m = new MD5();
		String passwd = m.getMD5ofStr((String) (((JSONObject) array.get(0)).get("param1")));
		c.setTemp_str1(passwd); 
		
		c.setStaff_number(((LoginUser)session.getAttribute("LoginUser")).getUser_cd()); 
		JSONObject jsonObject= ((CompanyAdminServiceImpl) getBean("companyAdminServiceImpl"))
			.changeUserPasswd(c);
		wr.write(jsonObject.toJSONString());
		wr.close();
		
		return null ;
	}
	/**
	 * 根据地区查询商户列表
	 */
	public String getCompanyListByArea()  throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String((request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");		
		String areaCode =((String) (((JSONObject) array.get(0)).get("param1")));
		c.setArea_code(areaCode); 		
		JSONObject jsonObject= ((CompanyAdminServiceImpl) getBean("companyAdminServiceImpl"))
			.getCompanyListByArea(c);
		wr.write(jsonObject.toJSONString());
		wr.close();
		
		return null ;
	}
	/**
	 * 社区信息列表
	 */
	public String qryVillageList() throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String((request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");		
		String company_id =((String) (((JSONObject) array.get(0)).get("param1")));
		c.setTemp_str1((String) (((JSONObject) array.get(0)).get("param2")));
		c.setTemp_str2((String) (((JSONObject) array.get(0)).get("param3")));
		c.setCompany_id(company_id); 		
		JSONObject jsonObject= ((CompanyAdminServiceImpl) getBean("companyAdminServiceImpl"))
			.qryVillageList(c);
		wr.write(jsonObject.toJSONString());
		wr.close();
		
		return null ;
	}
	/**
	 * 检索房屋列表
	 */
	public String qryHouseInfoList()  throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String((request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");		
		c.setHouse_name((String) (((JSONObject) array.get(0)).get("param1")));
		c.setCompany_id((String) (((JSONObject) array.get(0)).get("param1")));
		c.setVillage_id((String) (((JSONObject) array.get(0)).get("param1")));
		c.setTemp_str1((String) (((JSONObject) array.get(0)).get("param4")));
		c.setTemp_str2((String) (((JSONObject) array.get(0)).get("param5"))); 
		c.setCreator(((LoginUser)session.getAttribute("LoginUser")).getUser_cd()); 
		JSONObject jsonObject= ((CompanyAdminServiceImpl) getBean("companyAdminServiceImpl"))
			.qryHouseInfoList(c);
		wr.write(jsonObject.toJSONString());
		wr.close();
		
		return null ;
	}
}
