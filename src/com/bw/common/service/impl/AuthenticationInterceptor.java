package com.bw.common.service.impl;

import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bw.common.model.*;
import com.bw.fit.common.DaoUtil.BusinessDaoUtil;
import com.bw.fit.spm.card.service.impl.CardServiceImpl;
import com.bw.fit.spm.model.Common;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthenticationInterceptor extends AbstractInterceptor {

	public BusinessDaoUtil businessDaoUtil;
	private Log log = LogFactory.getLog(AuthenticationInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		/**
		 * 是否登录，是否有权限拦截器
		 */
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletResponse response = (HttpServletResponse) actionContext
				.get(StrutsStatics.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);
		JSONObject info = new JSONObject();
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();

		Map map = invocation.getInvocationContext().getSession();
		if (map == null || "".equals(map.get("LoginUser"))) {
			info.put("res", "1");
			info.put("msg", "请重新登录");
			wr.write(info.toJSONString());
			wr.close();
			return "NOLOGIN";
		} else {
			String user_cd = ((LoginUser) map.get("LoginUser")).getUser_cd();
			// 拦截的action的名字
			String action_name = invocation.getInvocationContext().getName();
			Common c = new Common();
			c.setStaff_number(user_cd);
			c.setAction_name(action_name);
			c.setSql("sysAuthenticationDAO.getHasPowerByThisUser");
			// 判断用户是否有权限进行操作
			// List ls = businessDaoUtil.getListData(c.getSql(), c);
			if (true) {
				return invocation.invoke();
			}
			info.put("res", "1");
			info.put("msg", "无权限");
			wr.write(info.toJSONString());
			wr.close();
			return "NOPOWER";
		}

	}

}
