package com.bw.common.actions;

import com.bw.common.service.impl.*;

import java.io.*;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.xml.XMLSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.simple.*;

import com.bw.common.model.CommonMedol;
import com.bw.common.model.LoginUser;
import com.bw.common.uitily.MD5;
import com.bw.common.uitily.MybatisDaoUtil;
import com.bw.common.uitily.PubFun;
import com.bw.fit.spm.model.Common;

public class AllSysItemsAction extends BaseAction {
	public String j_username;
	public String j_password;

	public String getJ_username() {
		return j_username;
	}

	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}

	public String getJ_password() {
		return j_password;
	}

	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}

	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpSession session = request.getSession(true);

	private static Log log = LogFactory.getLog(AllSysItemsAction.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 根据机构查询，其下的工种
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getWtItemsByOrg() throws Exception {
		response.setContentType("text/plain;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		log.info("str" + str);
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		String param1 = (String) (((JSONObject) array.get(0)).get("param1"));
		CommonMedol c = new CommonMedol();
		c.setParam1(param1);
		if ("-9".equals(param1)) {
			c.setParam1(((LoginUser) session.getAttribute("LoginUser"))
					.getOrg_cd());
		}
		List lsResault = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getWtItemsByOrg(c);

		JSONObject objItem = new JSONObject();
		JSONArray arrayItem = new JSONArray();
		for (int i = 0; i < lsResault.size(); i++) {
			objItem = new JSONObject();
			objItem.put("id", ((CommonMedol) lsResault.get(i)).getParam1());
			objItem.put("name", ((CommonMedol) lsResault.get(i)).getParam2());
			arrayItem.add(objItem);
		}
		wr.write(arrayItem.toJSONString());
		wr.close();
		return null;
	}

	public String getSysItems() throws Exception {
		response.setContentType("text/plain;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		log.info("str" + str);
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		String param1 = (String) (((JSONObject) array.get(0)).get("param1"));
		CommonMedol c = new CommonMedol();
		c.setParam1(param1);
		List lsResault = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getDragWindowList(c);

		JSONObject objItem = new JSONObject();
		JSONArray arrayItem = new JSONArray();
		for (int i = 0; i < lsResault.size(); i++) {
			objItem = new JSONObject();
			objItem.put("id", ((CommonMedol) lsResault.get(i)).getParam1());
			objItem.put("name", ((CommonMedol) lsResault.get(i)).getParam2());
			arrayItem.add(objItem);
		}
		System.out.println(arrayItem.toJSONString());
		wr.write(arrayItem.toJSONString());
		wr.close();
		return null;
	}

	public String getUserAuthListV2() throws Exception {
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		String param1 = (String) (((JSONObject) array.get(0)).get("param1"));
		CommonMedol c = new CommonMedol();
		c.setParam1(((LoginUser)session.getAttribute("LoginUser")).getUser_cd());
		Document doc= ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getThisUserAuthListsV2(c);
		log.info(doc.asXML());

		wr.write(doc.asXML());
		wr.close();

		return null;
	}
	public String getUserAuthListV3() throws Exception {
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		String param1 = (String) (((JSONObject) array.get(0)).get("param1"));
		CommonMedol c = new CommonMedol();
		c.setParam1(((LoginUser)session.getAttribute("LoginUser")).getUser_cd());
		String ss= ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getThisUserAuthListsV3(c);
		log.info(ss);

		wr.write(ss);
		wr.close();

		return null;
	}
	/**
	 * getUserAuthList
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public String getUserAuthList() throws Exception {
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		String param1 = (String) (((JSONObject) array.get(0)).get("param1"));
		CommonMedol c = new CommonMedol();
		c.setParam1(((LoginUser)session.getAttribute("LoginUser")).getUser_cd());
		List<CommonMedol> ls = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getThisUserAuthLists(c);
		StringBuffer sb = new StringBuffer();
		StringBuffer sb3j = new StringBuffer();
		String id = null; // 游标所指的记录的功能ID
		for (int i = 0; i < ls.size(); i++) {
			if ("1".equals(ls.get(i).getParam3())) {
				// 第一级
				sb.append("<div class='accordionHeader'><h2><span>Folder</span>"
						+ ls.get(i).getParam1() + "</h2></div>");
				id = ls.get(i).getParam5();
			}
			if (id.equalsIgnoreCase(ls.get(i).getParam2())
					&& "2".equals(ls.get(i).getParam3())) {
				sb.append("<div class='accordionContent'><ul class='tree treeFolder'><li><a>"
						+ ls.get(i).getParam1() + "</a><ul>");
				id = ls.get(i).getParam5();
			}
			if (id.equalsIgnoreCase(ls.get(i).getParam2())
					&& "3".equals(ls.get(i).getParam3())) {
				sb.append("<li><a href='" + ls.get(i).getParam4()
						+ "' target='" + ls.get(i).getParam6() + "' rel='"
						+ ls.get(i).getParam7() + "' fresh=false title=\""
						+ ls.get(i).getParam1() + "\">" + ls.get(i).getParam1()
						+ "</a></li>");
			}
			if (i == (ls.size() - 1) || !"3".equals(ls.get(i + 1).getParam3())) {
				sb.append("</ul></li></ul></div> ");
			}
		}

		log.info(sb.toString());
		wr.write(sb.toString());
		wr.close();
		return null;
	}

	/**
	 * getRoleExistAndNoexitAuthList
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public String getRoleExistAndNoexitAuthList() throws Exception {

		return null;
	}

	/**
	 * getUserListUnderTheStation 获取人员
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getUserListUnderTheStation() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		log.info("str" + str);
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		log.info(array.size());
		String param1 = (String) (((JSONObject) array.get(0)).get("param1"));
		String param2 = (String) (((JSONObject) array.get(0)).get("param2"));
		String param3 = (String) (((JSONObject) array.get(0)).get("param3"));
		String param4 = (String) (((JSONObject) array.get(0)).get("param4"));
		String param5 = (String) (((JSONObject) array.get(0)).get("param5"));
		CommonMedol c = new CommonMedol();
		c.setCompany_number(param5);
		c.setParam4(param4);
		c.setParam5(param5);
		c.setParam39(Integer.valueOf(param2));
		c.setParam40(Integer.valueOf(param3));
		Document document = DocumentHelper.createDocument();
		Element rootR = document.addElement("root");
		List<CommonMedol> ls = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getUserListUnderTheStation(c);
		for (int i = 0; i < ls.size(); i++) {
			Element Node = rootR.addElement("user");
			Node.addElement("user_cd").addText(ls.get(i).getParam1());
			Node.addElement("user_name").addText(ls.get(i).getParam2());
			Node.addElement("phone").addText(ls.get(i).getParam3());
			Node.addElement("fixed_phone").addText(ls.get(i).getParam4());
			Node.addElement("address").addText(ls.get(i).getParam5());
			Node.addElement("peculiarity_name").addText(ls.get(i).getParam8());
			Node.addElement("company_name").addText(ls.get(i).getParam7());
		}
		log.info(document.asXML());
		wr.write(document.asXML());
		wr.close();
		return null;
	}

	/*
	 * createNewRole
	 */
	public String createNewRole() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/plain;charset=UTF-8");
		Writer wr = response.getWriter();
		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setParam1((String) (((JSONObject) array.get(0)).get("param1")));
		c.setParam2((String) (((JSONObject) array.get(0)).get("param2")));
		c.setParam3(String.valueOf(System.currentTimeMillis()));
		c.setStaff_number(((LoginUser) session.getAttribute("LoginUser"))
				.getUser_cd());
		Document document = DocumentHelper.createDocument();
		Element rootR = document.addElement("root");
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.createNewRoleService(c);
		wr.write((PubFun.xml2json(doc)));
		wr.close();
		return null;
	}

	public String createNewPosition() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/plain;charset=UTF-8");
		Writer wr = response.getWriter();
		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setParam1((String) (((JSONObject) array.get(0)).get("param1")));
		c.setParam2((String) (((JSONObject) array.get(0)).get("param2")));
		c.setParam3(String.valueOf(System.currentTimeMillis()));
		c.setStaff_number(((LoginUser) session.getAttribute("LoginUser"))
				.getUser_cd());
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.createNewPositionService(c);
		wr.write((PubFun.xml2json(doc)));
		wr.close();
		return null;
	}

	/**
	 * getAllOrgs获取组织架构
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getAllOrgs() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setParam1((String) (((JSONObject) array.get(0)).get("param1")));
		c.setParam2((String) (((JSONObject) array.get(0)).get("param2")));
		c.setParam3(String.valueOf(System.currentTimeMillis()));
		Document document = DocumentHelper.createDocument();
		Element rootR = document.addElement("root");
		List<CommonMedol> ls = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getAllOrgsService(c);
		for (int i = 0; i < ls.size(); i++) {
			Element Node = rootR.addElement("org");
			Node.addElement("company_cd").addText(ls.get(i).getParam1());
			Node.addElement("company_name").addText(ls.get(i).getParam2());
			Node.addElement("level").addText(ls.get(i).getParam3());
			Node.addElement("up_company_name").addText(ls.get(i).getParam4());
			Node.addElement("state").addText(ls.get(i).getParam5());
		}
		log.info(document.asXML());
		wr.write(document.asXML());
		wr.close();

		return null;
	}

	/**
	 * getAllOrgs获取组织架构 当前机构以下
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getAllOrgsUnderMyOrg() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setParam1((String) (((JSONObject) array.get(0)).get("param1")));
		c.setParam2((String) (((JSONObject) array.get(0)).get("param2")));
		c.setParam3(String.valueOf(System.currentTimeMillis()));
		Document document = DocumentHelper.createDocument();
		Element rootR = document.addElement("root");
		List<CommonMedol> ls = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getAllOrgsUnderMyOrgService(c);
		for (int i = 0; i < ls.size(); i++) {
			Element Node = rootR.addElement("org");
			Node.addElement("company_cd").addText(ls.get(i).getParam1());
			Node.addElement("company_name").addText(ls.get(i).getParam2());
			Node.addElement("level").addText(ls.get(i).getParam3());
			Node.addElement("up_company_name").addText(ls.get(i).getParam4());
			Node.addElement("state").addText(ls.get(i).getParam5());
		}
		wr.write(document.asXML());
		wr.close();

		return null;
	}

	/**
	 * getOrgInfoTree 获取组织架构树
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getOrgInfoTree() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/plain;charset=UTF-8");
		Writer wr = response.getWriter();
		documentB = buildDocument(request);
		Element rootB = (Element) documentB.selectSingleNode("/root");
		CommonMedol c = new CommonMedol();
		c.setParam1(rootB.selectSingleNode("./param1").getText());
		Document document = DocumentHelper.createDocument();
		Element rootR = document.addElement("root");

		List<CommonMedol> ls = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getOrgInfoTreeService(c);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ls.size(); i++) {
			sb.append("{ id:" + ls.get(i).getParam1() + ", pId:"
					+ ls.get(i).getParam6() + ", name:\""
					+ ls.get(i).getParam2() + "\",open:true},");
		}
		wr.write((sb.toString()).substring(0, (sb.toString().length() - 1)));
		wr.close();
		return null;
	}

	/**
	 * getThisRoleAuthTreeJson 获取角色功能树json
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getThisRoleAuthTreeJson() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/plain;charset=UTF-8");
		Writer wr = response.getWriter();

		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setParam1((String) (((JSONObject) array.get(0)).get("param1")));
		Document document = DocumentHelper.createDocument();
		Element rootR = document.addElement("root");

		List<CommonMedol> ls = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getThisRoleAuthTreeJsonService(c);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ls.size(); i++) {
			sb.append("{");
			sb.append(" id:" + ls.get(i).getParam1() + ",");
			sb.append(" pId:" + ls.get(i).getParam2() + ",");
			sb.append("  name:" + "\"" + ls.get(i).getParam3() + "\"" + ", ");
			sb.append(" open:" + ls.get(i).getParam1() + " ");
			if ("1".equals(ls.get(i).getParam4())) {
				sb.append(",checked:true ");
			} else {
				sb.append(",nochecked:true ");
			}
			sb.append("},");
		}
		wr.write((sb.toString()).substring(0, (sb.toString().length() - 1)));
		wr.close();

		return null;
	}

	/**
	 * giveAuthorityToRole 赋权于角色
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String giveAuthorityToRole() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/plain;charset=UTF-8");
		Writer wr = response.getWriter();
		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		String param1 = (String) (((JSONObject) array.get(0)).get("param1"));
		String param2 = (String) (((JSONObject) array.get(0)).get("param2"));
		c.setParam1(param1);
		c.setParam2(param2);
		c.setParam3(String.valueOf(System.currentTimeMillis()));
		c.setStaff_number(((LoginUser) session.getAttribute("LoginUser"))
				.getUser_cd());
		Document document = DocumentHelper.createDocument();
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.giveAuthorityToRoleService(c);
		log.info(doc.asXML());
		wr.write((PubFun.xml2json(doc)));
		wr.close();

		return null;
	}

	/**
	 * getAuthorityBtnsByThisUser
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getAuthorityBtnsByThisUser() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();

		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		String param1 = (String) (((JSONObject) array.get(0)).get("param1"));
		String param2 = (String) (((JSONObject) array.get(0)).get("param2"));
		CommonMedol c = new CommonMedol();
		c.setParam1(param1); // 当前页面
		c.setParam36(param2); // 按钮

		c.setParam2(((LoginUser) session.getAttribute("LoginUser"))
				.getUser_cd()); // 当前用户

		String txt = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getAuthorityBtnsByThisUserService(c);
		log.info(txt);
		wr.write(txt);
		wr.close();

		return null;
	}

	/**
	 * createNewSysUser
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String createNewSysUser() throws Exception {
		response.setContentType("text/xml;charset=UTF-8");
	Writer wr = response.getWriter();
	String str = new String(
			(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK"); 
	JSONObject obj = (JSONObject) JSONValue.parse(str);
	JSONArray array = (JSONArray) obj.get("content");
		CommonMedol c = new CommonMedol();
		c.setParam1((String) ((JSONObject) array.get(0)).get("param1"));
		c.setParam2((String) ((JSONObject) array.get(0)).get("param2"));
		c.setParam3((String) ((JSONObject) array.get(0)).get("param3"));
		c.setParam4((String) ((JSONObject) array.get(0)).get("param4"));
		c.setParam5((String) ((JSONObject) array.get(0)).get("param5"));
		c.setParam6((String) ((JSONObject) array.get(0)).get("param6"));
		c.setParam7((String) ((JSONObject) array.get(0)).get("param7"));
		c.setParam8((String) ((JSONObject) array.get(0)).get("param8"));
		c.setParam9((String) ((JSONObject) array.get(0)).get("param9"));
		c.setParam10((String) ((JSONObject) array.get(0)).get("param10"));
		c.setStaff_number(((LoginUser) session.getAttribute("LoginUser"))
				.getUser_cd()); // 当前用户
		c.setParam20(String.valueOf(System.currentTimeMillis()));
		c.setParam21(PubFun.getSysDate());
		c.setAction_name("createNewSysUser");
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.createNewSysUserService(c);
		log.info(doc.asXML());

		wr.write((PubFun.xml2json(doc)));
		wr.close();

		return null;
	}

	/**
	 * getUserInfosByUserId
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getUserInfosByUserId() throws Exception {
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setParam1((String) ((JSONObject) array.get(0)).get("param1"));
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getUserInfosByUserIdService(c);
		log.info(doc.asXML());
		wr.write(doc.asXML());
		wr.close();

		return null;
	}

	/**
	 * updateUserInfosByUserId
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String updateUserInfosByUserId() throws Exception {
		response.setContentType("text/plain;charset=UTF-8");
		Writer wr = response.getWriter();
		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setParam1((String) ((JSONObject) array.get(0)).get("param1"));
		c.setParam2((String) ((JSONObject) array.get(0)).get("param2"));
		c.setParam3((String) ((JSONObject) array.get(0)).get("param3"));
		c.setParam4((String) ((JSONObject) array.get(0)).get("param4"));
		c.setParam5((String) ((JSONObject) array.get(0)).get("param5"));
		c.setParam6((String) ((JSONObject) array.get(0)).get("param6"));
		c.setParam7((String) ((JSONObject) array.get(0)).get("param7"));
		c.setParam8((String) ((JSONObject) array.get(0)).get("param8"));
		c.setParam9((String) ((JSONObject) array.get(0)).get("param9"));
		c.setParam10((String) ((JSONObject) array.get(0)).get("param10"));
		c.setParam11((String) ((JSONObject) array.get(0)).get("param11"));
		c.setParam12((String) ((JSONObject) array.get(0)).get("param12"));
		c.setStaff_number(((LoginUser) session.getAttribute("LoginUser"))
				.getUser_cd()); // 当前用户
		c.setParam20(String.valueOf(System.currentTimeMillis()));
		c.setParam21(PubFun.getSysDate());
		c.setAction_name("updateUserInfosByUserId");
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.updateUserInfosByUserIdService(c);
		log.info(doc.asXML());
		wr.write((PubFun.xml2json(doc)));
		wr.close();

		return null;
	}

	/**
	 * createNewSysOrg
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String createNewSysOrg() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/json;charset=UTF-8");
		Writer wr = response.getWriter();
		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		log.info(str);
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setParam1((String) ((JSONObject) array.get(0)).get("param1"));
		c.setParam2((String) ((JSONObject) array.get(0)).get("param2"));
		c.setParam3((String) ((JSONObject) array.get(0)).get("param3"));
		c.setParam4(String.valueOf(System.currentTimeMillis()));
		c.setStaff_number(((LoginUser) session.getAttribute("LoginUser"))
				.getUser_cd());
		c.setParam21(PubFun.getSysDate());
		c.setAction_name("createNewSysOrg");
		Document document = DocumentHelper.createDocument();
		Element rootR = document.addElement("root");
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.createNewSysOrgService(c);
		wr.write((PubFun.xml2json(doc)));
		wr.close();
		return null;
	}

	/**
	 * getOrgInfosById
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getOrgInfosById() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setParam1((String) ((JSONObject) array.get(0)).get("param1"));

		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getOrgInfosByIdService(c);
		log.info(doc.asXML());
		wr.write(doc.asXML());
		wr.close();

		return null;
	}

	/**
	 * updateOrgInfosByOrgId 更新机构资料
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String updateOrgInfosByOrgId() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/plain;charset=UTF-8");
		Writer wr = response.getWriter();

		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");// 将tbyte转换为ISO-8859-1编码形式

		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setParam1((String) ((JSONObject) array.get(0)).get("param1"));
		c.setParam2((String) ((JSONObject) array.get(0)).get("param2"));
		c.setParam3((String) ((JSONObject) array.get(0)).get("param3"));
		c.setParam4((String) ((JSONObject) array.get(0)).get("param4"));
		c.setStaff_number(((LoginUser) session.getAttribute("LoginUser"))
				.getUser_cd());
		c.setParam20(PubFun.getSysDate());
		c.setAction_name("updateOrgInfosByOrgId");
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.updateOrgInfosByOrgIdService(c);
		log.info(doc.asXML());
		wr.write((PubFun.xml2json(doc)));
		wr.close();

		return null;
	}

	/**
	 * getAllOrgListInfoJson
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getAllOrgListInfoJson() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		documentB = buildDocument(request);
		Element rootB = (Element) documentB.selectSingleNode("/root");
		CommonMedol c = new CommonMedol();
		c.setParam1("-9");
		c.setParam2("-9");
		c.setParam3("-9");
		Document document = DocumentHelper.createDocument();
		Element rootR = document.addElement("root");
		List<CommonMedol> ls = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getAllOrgsService(c);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ls.size(); i++) {
			sb.append("{");
			sb.append(" id:" + ls.get(i).getParam1() + ",");
			sb.append(" pId:" + ls.get(i).getParam6() + ",");
			sb.append("  name:" + "\"" + ls.get(i).getParam2() + "\"" + ", ");
			sb.append(" open:true ,");
			sb.append(" t:\"空\" ,");
			sb.append(" right:false , down:false, up:false ");
			sb.append("},");
		}
		log.info((sb.toString()).substring(0, (sb.toString().length() - 1)));
		wr.write((sb.toString()).substring(0, (sb.toString().length() - 1)));
		wr.close();
		return null;

	}

	/**
	 * qrySearchOrgUserTopList
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String qrySearchOrgUserTopListAction() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		documentB = buildDocument(request);
		Element rootB = (Element) documentB.selectSingleNode("/root");
		CommonMedol c = new CommonMedol();
		c.setParam1(rootB.selectSingleNode("./param1").getText());
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.qrySearchOrgUserTopListService(c);
		log.info(doc.asXML());
		wr.write(doc.asXML());
		wr.close();

		return null;
	}

	/**
	 * qrySearchOrgUserTopListByKeyWords
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String qrySearchOrgUserTopListByKeyWords() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		documentB = buildDocument(request);
		Element rootB = (Element) documentB.selectSingleNode("/root");
		CommonMedol c = new CommonMedol();
		c.setParam1(rootB.selectSingleNode("./param1").getText());
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.qrySearchOrgUserTopListByKeyWordsService(c);
		log.info(doc.asXML());
		wr.write(doc.asXML());
		wr.close();

		return null;
	}

	/**
	 * qrySearchStaffInfoByOrgTopTree
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String qrySearchStaffInfoByOrgTopTree() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		documentB = buildDocument(request);
		Element rootB = (Element) documentB.selectSingleNode("/root");
		CommonMedol c = new CommonMedol();
		c.setParam1(rootB.selectSingleNode("./param1").getText());
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.qrySearchStaffInfoByOrgTopTreeService(c);
		log.info(doc.asXML());
		wr.write(doc.asXML());
		wr.close();

		return null;
	}

	/**
	 * userLogin
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String userLogin() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		documentB = buildDocument(request);
		Element rootB = (Element) documentB.selectSingleNode("/root");
		CommonMedol c = new CommonMedol();
		c.setParam1(rootB.selectSingleNode("./param1").getText());
		MD5 m = new MD5();
		c.setParam2(m.getMD5ofStr(rootB.selectSingleNode("./param2").getText()));
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.userLoginService(c, session);
		log.info(doc.asXML());
		session.setAttribute("user_cd", "sd1");
		wr.write(doc.asXML());
		wr.close();

		return null;
	}

	public String userLoginStyleStaff() throws Exception {
		response.setContentType("text/plain;charset=UTF-8");
		Writer wr = response.getWriter();
		CommonMedol c = new CommonMedol();
		c.setParam1(this.j_username);
		MD5 m = new MD5();
		c.setParam2(m.getMD5ofStr(this.j_password));
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.userLoginService(c, session);
		if ("2".equals(doc.selectSingleNode("/root/res").getText())) {
			return "SUCCESS";
		}

		return "FAIL";
	}

	/**
	 * getPositionList
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getPositionList() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		log.info(str);
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setParam1((String) ((JSONObject) array.get(0)).get("param1"));
		c.setParam2((String) ((JSONObject) array.get(0)).get("param2"));
		c.setParam3((String) ((JSONObject) array.get(0)).get("param3"));
		c.setParam4((String) ((JSONObject) array.get(0)).get("param4"));
		c.setParam5((String) ((JSONObject) array.get(0)).get("param5"));
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getPositionListService(c);
		wr.write(doc.asXML());
		wr.close();

		return null;
	}

	/**
	 * getFileListByForeginId 根据外键查询出关联的所有文件
	 */
	public String getFileListByForeginId() throws Exception {
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setParam1(String.valueOf(((JSONObject) array.get(0)).get("param1")));
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getFileListByForeginIdService(c);
		wr.write(doc.asXML());
		wr.close();

		return null;

	}

	/**
	 * getPositionInfosById
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getPositionInfosById() throws Exception {
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setParam1((String) ((JSONObject) array.get(0)).get("param1"));
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getPositionInfosByIdService(c);
		wr.write(doc.asXML());
		wr.close();

		return null;
	}

	/**
	 * updatePositionInfosByPositionId 更新岗位说明
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String updatePositionInfosByPositionId() throws Exception {
		response.setContentType("text/plain;charset=UTF-8");
		Writer wr = response.getWriter();

		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");// 将tbyte转换为ISO-8859-1编码形式

		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setParam1((String) ((JSONObject) array.get(0)).get("param1"));
		c.setParam2((String) ((JSONObject) array.get(0)).get("param2"));
		c.setParam3((String) ((JSONObject) array.get(0)).get("param3"));
		c.setStaff_number(((LoginUser) session.getAttribute("LoginUser"))
				.getUser_cd());
		c.setParam20(PubFun.getSysDate());
		c.setAction_name("updatePositionInfosByPositionId");
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.updatePositionInfosService(c);
		wr.write((PubFun.xml2json(doc)));
		wr.close();

		return null;
	}

	/*
	 * excelDownload
	 */
	public String excelDownload() throws Exception {
		String path = ServletActionContext.getServletContext().getRealPath(
				"/uploadfile");
		log.info(path);
		Writer wr = response.getWriter();
		wr.write(path + "/" + request.getParameter("find"));
		log.info("find:" + request.getParameter("find"));
		wr.close();
		return null;
	}

	/*
	 * createForeignAndAttachmentRelation 创建外键与附件关联
	 */
	public String createForeignAndAttachmentRelation() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		documentB = buildDocument(request);
		Element rootB = (Element) documentB.selectSingleNode("/root");
		CommonMedol c = new CommonMedol();
		c.setParam1(rootB.selectSingleNode("./param1").getText());
		c.setParam2(rootB.selectSingleNode("./param2").getText());
		c.setParam3(rootB.selectSingleNode("./param3").getText());
		c.setParam18(PubFun.getFixLenthString(11));
		c.setParam20(PubFun.getSysDateM());
		c.setStaff_number(((LoginUser) session.getAttribute("LoginUser"))
				.getUser_cd());
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.createForeignAndAttachmentRelationService(c);
		log.info(doc.asXML());
		wr.write(doc.asXML());
		wr.close();

		return null;
	}

	/**
	 * getTeachersItemsByOrg 根据机构查询其下的教师
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getTeachersItemsByOrg() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setParam1(((LoginUser) session.getAttribute("LoginUser")).getOrg_cd());
		c.setStaff_number(((LoginUser) session.getAttribute("LoginUser"))
				.getUser_cd());
		String strResult = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.getTeachersItemsByOrg(c);
		log.info(strResult);
		wr.write(strResult);
		wr.close();

		return null;
	}

	/**
	 * 根据机构id查询其下的班级
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String qrySearchClassInfoByOrgTopTree() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		documentB = buildDocument(request);
		Element rootB = (Element) documentB.selectSingleNode("/root");
		CommonMedol c = new CommonMedol();
		c.setParam1(rootB.selectSingleNode("./param1").getText());
		c.setParam18(PubFun.getFixLenthString(11));
		c.setParam20(PubFun.getSysDateM());
		c.setStaff_number(((LoginUser) session.getAttribute("LoginUser"))
				.getUser_cd());
		Document doc = ((AllSysAuthServiceImpl) getBean("allSysAuthServiceImpl"))
				.qrySearchClassInfoByOrgTopTree(c);
		log.info(doc.asXML());
		wr.write(doc.asXML());
		wr.close();

		return null;
	}

	public String test() throws Exception {
		org.dom4j.Document documentB = null;
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		CommonMedol c = new CommonMedol();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK"); 
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		log.info(array.size());

		String param1 = (String) (((JSONObject) array.get(0)).get("param1"));
		log.info(param1); 

		JSONObject jsonObj = new JSONObject();// 创建json格式的数据
		JSONArray jsonArr = new JSONArray();// json格式的数组
		JSONObject jsonObjArr = new JSONObject();
		jsonObjArr.put("value1", "value1");
		jsonObjArr.put("value2", "value2");
		jsonArr.add(jsonObjArr);// 将json格式的数据放到json格式的数组里
		jsonObj.put("rows", jsonArr);// 再将这个json格式的的数组放到最终的json对象中。
		jsonObj.put("res", "2");
		jsonObj.put("msg", "成功的");
		System.out.println(jsonObj.toString());

		wr.write(jsonObj.toString());
		wr.close();
		return null;
	}
	
	public void testss(){
		System.out.println("testssssss");
	}
	// 从request中构建Document
	private Document buildDocument(HttpServletRequest request) throws Exception {
		InputStream in = (InputStream) request.getInputStream();
		SAXReader xmlReader = new SAXReader();
		org.dom4j.Document document = xmlReader.read(in);
		return document;
	}
}
