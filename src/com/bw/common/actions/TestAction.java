package com.bw.common.actions;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.task.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.bw.common.model.CommonMedol;

public class TestAction extends BaseAction {

	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpSession session = request.getSession(true);

	private static Log log = LogFactory.getLog(TestAction.class);

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

	public static void main(String[] args) {
		String s = UUID.randomUUID().toString();
	}
}
