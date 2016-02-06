package com.bw.fit.fee.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.bw.common.actions.BaseAction;
import com.bw.common.model.LoginUser;
import com.bw.fit.fee.service.impl.FeeUseServiceImpl;
import com.bw.fit.spm.card.action.CardAdminAction;
import com.bw.fit.spm.card.service.impl.CardServiceImpl;
import com.bw.fit.spm.model.Common;

public class FeeUseAction   extends BaseAction {


	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpSession session = request.getSession(false);
	private static Log log = LogFactory.getLog(FeeUseAction.class);
	
	/**
	 * 创建机构的兑换类型
	 * @throws Exception 
	 * 
	 */
	public String createCompanyFeeUse() throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setFee_usetype_name((String) (((JSONObject) array.get(0)).get("param1")));
		c.setFormula((String) (((JSONObject) array.get(0)).get("param2")));
		c.setFee_usetype_company((String) (((JSONObject) array.get(0)).get("param3")));
		c.setCreator(((LoginUser) session.getAttribute("LoginUser"))
				.getUser_cd());
		JSONObject jsonObject= ((FeeUseServiceImpl) getBean("feeUseServiceImpl"))
				.createCompanyFeeUse(c);
			wr.write(jsonObject.toJSONString());
			wr.close();
			
		return null ;
	}
}
