package com.bw.fit.spm.card.action;

import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.task.Task;

import com.bw.fit.spm.card.service.impl.*;

import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.bw.common.actions.AllSysItemsAction;
import com.bw.common.actions.BaseAction;
import com.bw.common.model.LoginUser;
import com.bw.common.uitily.PubFun;
import com.bw.fit.spm.company.service.impl.CompanyAdminServiceImpl;
import com.bw.fit.spm.model.Common;

//卡务
public class CardAdminAction extends BaseAction {
	private HttpServletResponse response = ServletActionContext.getResponse();
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpSession session = request.getSession(false);
	private static Log log = LogFactory.getLog(CardAdminAction.class);

	/**
	 * 卡消费，单笔
	 */
	public String createSingleConsumeRecord() throws Exception {
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setCard_code((String) (((JSONObject) array.get(0)).get("param1")));
		c.setTemp_str2((String) (((JSONObject) array.get(0)).get("param2")));
		c.setTemp_str1((String) (((JSONObject) array.get(0)).get("param3")));
		c.setCreate_time(PubFun.getSysDateM());
		c.setVersion_time(PubFun.getSysDateM());
		c.setCreator(((LoginUser) session.getAttribute("LoginUser"))
				.getUser_cd());
		JSONObject jsonObject = ((CardServiceImpl) getBean("cardServiceImpl"))
				.getPersonInfoByCard(c);
		JSONObject jsonCardFdid = ((CardServiceImpl) getBean("cardServiceImpl"))
				.getFdIdByCardCode(c);
		if(!"-9".equals(jsonCardFdid.get("fdid"))){
			c.setCard_code((String)jsonCardFdid.get("fdid"));
		}else{
			JSONObject errorJson =  new JSONObject();
			errorJson.put("res", "1");
			errorJson.put("msg", "卡无效"); 
			wr.write(errorJson.toJSONString());
			wr.close();
			return null;
		}
		if ("1".equals(jsonObject.get("res"))) {
			// 说明卡号本身出错了
			wr.write(jsonObject.toJSONString());
			wr.close();
			return null;
		}
		if (luruSingleConsumeRecord(c)) {
			jsonObject.put("res", "2");
			jsonObject.put("msg", "执行成功");
		} else {
			jsonObject.put("res", "1");
			jsonObject.put("msg", "执行异常");
		}
		wr.write(jsonObject.toJSONString());
		wr.close();

		return null;
	}

	/**
	 * 单笔录入公用方法
	 * 
	 * @return
	 */
	public boolean luruSingleConsumeRecord(Common c) {
		// 发布流程
		try {
			ProcessEngine processEngine = (ProcessEngine) getBean("processEngine");
			processEngine.getRepositoryService().createDeployment()
					.addClasspathResource("consume_process.bpmn").deploy();
			TaskService taskService = (TaskService) getBean("taskService");
			FormService formService = (FormService) getBean("formService");
			RuntimeService runtimeService = (RuntimeService) getBean("runtimeService");
			// 流程起始参数，会保存在流程实例的变量中
			Map<String, Object> map = new HashMap<String, Object>();
			String time = PubFun.getSysDate();
			map.put("start_time", time);
			// 开始流程
			runtimeService.startProcessInstanceByKey("process", map);
			List<Task> tasks = taskService.createTaskQuery()
					.taskAssignee("merchant").list();
			log.info("fillsize" + tasks.size());
			for (Task task : tasks) {
				if ("in".equals(task.getTaskDefinitionKey())) {
					// 设置节点的填报账号，这个值应该是要从前台获得的
					taskService.setVariable(task.getId(), "creator",
							c.getCreator());
					taskService.setVariable(task.getId(), "create_time",
							c.getCreate_time());
					taskService.setVariable(task.getId(), "card_code",
							c.getCard_code());
					taskService.setVariable(task.getId(), "card_points",
							c.getTemp_str2());
					taskService.setVariable(task.getId(), "source_code",
							c.getTemp_str1());
					taskService.setVariable(task.getId(), "state", "single_in");
					// 节点任务结束
					taskService.complete(task.getId());
				}
			}
			audit(formService, taskService);
			over(formService, taskService);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.info(ex.getLocalizedMessage());
			return false;
		}

	}

	/**
	 * 根据卡查询其关联人员信息
	 */
	public String getPersonInfoByCard() throws Exception {
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String(
				(request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setCard_code((String) (((JSONObject) array.get(0)).get("param1")));
		c.setCreator(((LoginUser) session.getAttribute("LoginUser"))
				.getUser_cd());
		JSONObject jsonObject = ((CardServiceImpl) getBean("cardServiceImpl"))
				.getPersonInfoByCard(c);
		wr.write(jsonObject.toJSONString());
		wr.close();

		return null;
	}

	public boolean audit(FormService formService, TaskService taskService) {
		// query fozzie's tasks;
		List<Task> tasks = taskService.createTaskQuery()
				.taskAssignee("sysAdmin").processDefinitionKey("process")
				.list();
		log.info("auditSize" + tasks.size());
		for (Task task : tasks) {
			if ("audit".equals(task.getTaskDefinitionKey())) {
				taskService.setVariable(task.getId(), "state", "audit_yes"); // 审核自动通过
				// 节点任务结束
				taskService.complete(task.getId());
			}
		}
		return true;
	}

	public boolean over(FormService formService, TaskService taskService) {
		// query fozzie's tasks;
		List<Task> tasks = taskService.createTaskQuery()
				.taskAssignee("inStorge").processDefinitionKey("process")
				.list();
		log.info("storgeInSize" + tasks.size());
		for (Task task : tasks) {
			if ("over".equals(task.getTaskDefinitionKey())) {
				// 获取节点报表打开类型
				taskService.setVariable(task.getId(), "state", "in_storge"); // 自动入库
				// 节点任务结束
				taskService.complete(task.getId());
			}
		}
		return true;
	}
	
	/**
	 * 消费记录查询
	 */
	public String qryCardConsumeRecords()   throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String((request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setPerson_name((String) (((JSONObject) array.get(0)).get("param1")));
		c.setCreate_time((String) (((JSONObject) array.get(0)).get("param2")));
		c.setCompany_name((String) (((JSONObject) array.get(0)).get("param3")));
		c.setTemp_str1(String.valueOf((((JSONObject) array.get(0)).get("param4"))));
		c.setTemp_str2(String.valueOf (((JSONObject) array.get(0)).get("param5")));
		c.setCreator(((LoginUser)session.getAttribute("LoginUser")).getUser_cd()); 
		JSONObject jsonObject= ((CardServiceImpl) getBean("cardServiceImpl"))
			.qryCardConsumeRecords(c);
		wr.write(jsonObject.toJSONString());
		wr.close();
		
		return null ;
	}
	/**
	 * 补换卡
	 */
	public String changePersonCard()  throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String((request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setTemp_str1((String) (((JSONObject) array.get(0)).get("param1")));
		c.setTemp_str2((String) (((JSONObject) array.get(0)).get("param2")));
		c.setCreator(((LoginUser)session.getAttribute("LoginUser")).getUser_cd()); 
		JSONObject jsonObject= ((CardServiceImpl) getBean("cardServiceImpl"))
			.changePersonCard(c);
		wr.write(jsonObject.toJSONString());
		wr.close();
		
		return null ;
	}
	/**
	 * 持卡人查询消费记录
	 */
	public String qryConsumeList() throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String((request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setCard_code((String) (((JSONObject) array.get(0)).get("param1")));
		c.setTemp_str1((String) (((JSONObject) array.get(0)).get("param2"))); 
		String date = String.valueOf((((JSONObject) array.get(0)).get("param3"))) +"-"+String.valueOf(((JSONObject) array.get(0)).get("param4"));
		c.setTemp_str3(date);
		JSONObject jsonObject= ((CardServiceImpl) getBean("cardServiceImpl"))
			.qryConsumeList(c);
		log.info((jsonObject.toJSONString()));
		wr.write(jsonObject.toJSONString());
		wr.close();
		
		return null ;
	}
	/**
	 * qryCardConsumeRecords2
	 * 商户查询，其门店内消费记录
	 */
	public String qryCardConsumeRecords2()  throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		Writer wr = response.getWriter();
		String str = new String((request.getParameter("context")).getBytes("ISO-8859-1"), "GBK");
		Common c = new Common();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		JSONArray array = (JSONArray) obj.get("content");
		c.setPerson_name((String) (((JSONObject) array.get(0)).get("param1")));
		c.setCard_phone((String) (((JSONObject) array.get(0)).get("param2")));
		c.setCreate_time((String) (((JSONObject) array.get(0)).get("param3")));
		c.setTemp_str1(String.valueOf((((JSONObject) array.get(0)).get("param4"))));
		c.setTemp_str2(String.valueOf (((JSONObject) array.get(0)).get("param5")));
		c.setCreator(((LoginUser)session.getAttribute("LoginUser")).getUser_cd()); 
		c.setCompany_id(((LoginUser)session.getAttribute("LoginUser")).getOrg_cd());
		JSONObject jsonObject= ((CardServiceImpl) getBean("cardServiceImpl"))
			.qryCardConsumeRecords2(c);
		wr.write(jsonObject.toJSONString());
		wr.close();
		
		return null ;
	}
}
