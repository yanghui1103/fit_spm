package com.bw.common.webservice.impl;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import com.bw.common.uitily.PubFun;
import com.bw.common.webservice.ConsumeTransponder;
import com.bw.fit.common.DaoUtil.BusinessDaoUtil; 
import com.bw.fit.spm.card.action.CardAdminAction;
import com.bw.fit.spm.card.service.impl.CardServiceImpl;
import com.bw.fit.spm.model.Common;
@WebService(endpointInterface = "com.bw.common.webservice.ConsumeTransponder", serviceName = "consumeTransponder")
@Transactional 
@Scope("singleton")
public class ConsumeTransponderImpl implements ConsumeTransponder {
	@Autowired
	public  BusinessDaoUtil businessDaoUtil;
	@Autowired
	public  CardServiceImpl cardServiceImpl;
	private  Log log = LogFactory.getLog(ConsumeTransponderImpl.class); 
	@Override
	public JSONObject acceptConsumeRecords(String merchant_id,String merchant_passwd,String card_code,String card_phone, double   fee,double points ,String create_time) {
		/**
		 * 接受外部电商传输过来的会员消费记录
		 */
		Common c  = new Common();
		c.setCompany_id(merchant_id);
		c.setTemp_str3(merchant_passwd);
		c.setTemp_str1("SINGLE");
		c.setCard_code(card_code);
		c.setCard_phone(card_phone);
		c.setTemp_str2(String.valueOf(points));
		c.setVersion_time(PubFun.getSysDateM());		
		c.setCreate_time(create_time);
		c.setFdid(PubFun.getUUID()); 
		JSONObject jsonObject = cardServiceImpl.getPersonInfoByCard(c);
		JSONObject jsonCardFdid =cardServiceImpl.getFdIdByCardCode(c);
		if(!"-9".equals(jsonCardFdid.get("fdid"))){
			c.setCard_code((String)jsonCardFdid.get("fdid"));
		}else{
			JSONObject errorJson =  new JSONObject();
			errorJson.put("res", "1");
			errorJson.put("msg", "卡无效");  
			return errorJson;
		}
		if ("1".equals(jsonObject.get("res"))) {
			// 说明卡号本身出错了 
			return jsonObject;
		}
		CardAdminAction m = new CardAdminAction();
		if (m.luruSingleConsumeRecord(c)) {
			jsonObject.put("res", "2");
			jsonObject.put("msg", "执行成功");
		} else {
			jsonObject.put("res", "1");
			jsonObject.put("msg", "执行异常");
		} 
		return jsonObject ;
	} 
}
