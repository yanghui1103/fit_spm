package com.bw.fit.spm.card.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bw.common.uitily.PubFun;
import com.bw.fit.common.DaoUtil.BusinessDaoUtil;
import com.bw.fit.spm.card.service.CardService; 
import com.bw.fit.spm.model.Common;
@Service
@Transactional 
@Scope("singleton")
public class CardServiceImpl implements CardService {

	@Autowired
	public  BusinessDaoUtil businessDaoUtil;
	private  Log log = LogFactory.getLog(CardServiceImpl.class); 

	@Override
	public JSONObject createSingleConsumeRecord(Common c) {
		/**
		 * 卡单笔消费录入
		 */
		JSONObject info = new JSONObject();
		try{
			c.setCreate_time(PubFun.getSysDateM());
			c.setVersion_time(PubFun.getSysDateM());		
			c.setSql("cardAdminDAO.createSingleConsumeRecord");
			c.setFdid(PubFun.getUUID());
			Document doc = businessDaoUtil.sysUpdateData(c.getSql(), c);
			info.put("res",doc.selectSingleNode("/root/res").getText());
			info.put("msg",doc.selectSingleNode("/root/msg").getText());
		}catch(Exception ex){
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
		return info ;
	}
	/**
	 * 单笔录入公用方法
	 * @return
	 */
	public boolean luruSingleConsumeRecord(){
		
		return true;
	}
	@Override
	public JSONObject getPersonInfoByCard(Common c) {
		/**
		 * 根据卡查询人员信息
		 */
		JSONObject info = new JSONObject();
		try{
			c.setCreate_time(PubFun.getSysDateM());
			c.setVersion_time(PubFun.getSysDateM());		
			c.setSql("cardAdminDAO.getPersonInfoByCard");
			c.setFdid(PubFun.getUUID());
			List<Common> list = businessDaoUtil.getListData(c.getSql(), c);
			if(list.size()>0){
				info.put("res","2");
				info.put("msg","执行成功");
			}else{
				info.put("res","1");
				info.put("msg","执行失败");
			}
			for(int i=0;i<list.size();i++){
				JSONArray array = new JSONArray();
				JSONObject jsonObjArr = new JSONObject();
				jsonObjArr.put("fdid", (list.get(i)).getFdid());
				jsonObjArr.put("person_name", (list.get(i)).getPerson_name());
				jsonObjArr.put("card_phone", (list.get(i)).getCard_phone());
				jsonObjArr.put("card_sfz", (list.get(i)).getCard_sfz());
				array.add( jsonObjArr);
				info.put("list", array);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
		return info ;
	}
	@Override
	public JSONObject qryCardConsumeRecords(Common c) {
		/**
		 * 卡消费查询
		 */
		JSONObject info = new JSONObject();
		try{	
			c.setSql("cardAdminDAO.qryCardConsumeRecords");
			c.setFdid(PubFun.getUUID());
			List<Common> list = businessDaoUtil.getListData(c.getSql(), c); 
			c.setTemp_str1("0");
			c.setTemp_str2("9999999");
			List<Common> pageTotalNoList = businessDaoUtil.getListData(c.getSql(), c);
			info.put("pageTotalNo", pageTotalNoList.size()/15 + (pageTotalNoList.size()/15>0?1:0));
			if(list.size()>0){
				info.put("res","2");
				info.put("msg","执行成功");
			}else{
				info.put("res","1");
				info.put("msg","执行失败");
			}
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject jsonObjArr = new JSONObject();
				jsonObjArr.put("person_name", (list.get(i)).getPerson_name());
				jsonObjArr.put("card_code", (list.get(i)).getCard_code());
				jsonObjArr.put("card_phone", (list.get(i)).getCard_phone());
				jsonObjArr.put("account_fee", (list.get(i)).getAccount_fee());
				jsonObjArr.put("area_name", (list.get(i)).getArea_name());
				jsonObjArr.put("state", (list.get(i)).getState());
				jsonObjArr.put("create_time", (list.get(i)).getCreate_time());
				jsonObjArr.put("creator", (list.get(i)).getCreator());
				jsonObjArr.put("company_name", (list.get(i)).getCompany_name());
				jsonObjArr.put("card_sfz", (list.get(i)).getCard_sfz());
				jsonObjArr.put("consume_id", (list.get(i)).getTemp_str1());  // 消费流水
				array.add( jsonObjArr);
			}
			info.put("list", array);
		}catch(Exception ex){
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
		return info ;
	}
	@Override
	public JSONObject changePersonCard(Common c) {
		/**
		 * 补换卡
		 */
		JSONObject info = new JSONObject();
		try{
			c.setFdid(PubFun.getUUID());
			c.setCreate_time(PubFun.getSysDateM());
			c.setVersion_time(PubFun.getSysDateM());		
			c.setSql("cardAdminDAO.getPersonCardCount");
			List list = businessDaoUtil.getListData(c.getSql(), c);
			if(list.size()>1){
				info.put("res","1");
				info.put("msg","原卡号发现多个属主，请联系系统管理员");
				return info ;
			}else if(list.size()<1){
				info.put("res","1");
				info.put("msg","原卡号无效");
				return info ;
			}
			c.setSql("cardAdminDAO.changePersonCard");
			Document doc = businessDaoUtil.sysUpdateData(c.getSql(), c);
			info.put("res",doc.selectSingleNode("/root/res").getText());
			info.put("msg",doc.selectSingleNode("/root/msg").getText());
		}catch(Exception ex){
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
		return info ;
	}
	@Override
	public JSONObject getFdIdByCardCode(Common c) {
		/**
		 * 根据卡号查询卡的fdid
		 */
		JSONObject info = new JSONObject();
		try{	
			c.setSql("cardAdminDAO.getFdIdByCardCode");
			List<Common> list = businessDaoUtil.getListData(c.getSql(), c);
			if(list.size()>0){
				info.put("fdid",list.get(0).getFdid()); 				
			}else{
				info.put("fdid","-9"); 		
			}
		}catch(Exception ex){
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
		return info ;
	}
	@Override
	public JSONObject qryConsumeList(Common c) {
		/**
		 * 持卡人查询消费
		 */
		JSONObject info = new JSONObject();
		try{	 
			c.setFdid((String)getFdIdByCardCode(c).get("fdid"));
			Common ct = new Common();
			ct.setFdid(c.getFdid());
			ct.setTemp_str3(c.getTemp_str3());
			ct.setSql("cardAdminDAO.qryConsumeList2");
			List<Common> list = businessDaoUtil.getListData(ct.getSql(), ct); 
			log.info(list.size());
			if(list.size()>0){
				info.put("res","2");
				info.put("msg","执行成功");
			}else{
				info.put("res","1");
				info.put("msg","执行失败");
			}
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject jsonObjArr = new JSONObject();
				jsonObjArr.put("company_name", (list.get(i)).getCompany_name());
				jsonObjArr.put("create_time", (list.get(i)).getCreate_time());
				jsonObjArr.put("account_fee", (list.get(i)).getAccount_fee());   
				array.add( jsonObjArr);
			}
			info.put("list", array);
		}catch(Exception ex){
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
		return info ;
	}
	@Override
	public JSONObject qryCardConsumeRecords2(Common c) {
		/**
		 * 商户查询消费记录，只能查本商户的
		 */
		JSONObject info = new JSONObject();
		try{	
			c.setSql("cardAdminDAO.qryCardConsumeRecords2");
			c.setFdid(PubFun.getUUID());
			List<Common> list = businessDaoUtil.getListData(c.getSql(), c); 
			c.setTemp_str1("0");
			c.setTemp_str2("9999999");
			List<Common> pageTotalNoList = businessDaoUtil.getListData(c.getSql(), c);
			info.put("pageTotalNo", pageTotalNoList.size()/15 + (pageTotalNoList.size()/15>0?1:0));
			if(list.size()>0){
				info.put("res","2");
				info.put("msg","执行成功");
			}else{
				info.put("res","1");
				info.put("msg","执行失败");
			}
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject jsonObjArr = new JSONObject();
				jsonObjArr.put("person_name", (list.get(i)).getPerson_name());
				jsonObjArr.put("card_code", (list.get(i)).getCard_code());
				jsonObjArr.put("card_phone", (list.get(i)).getCard_phone());
				jsonObjArr.put("account_fee", (list.get(i)).getAccount_fee());
				jsonObjArr.put("area_name", (list.get(i)).getArea_name());
				jsonObjArr.put("state", (list.get(i)).getState());
				jsonObjArr.put("create_time", (list.get(i)).getCreate_time());
				jsonObjArr.put("creator", (list.get(i)).getCreator());
				jsonObjArr.put("company_name", (list.get(i)).getCompany_name());
				jsonObjArr.put("card_sfz", (list.get(i)).getCard_sfz());
				jsonObjArr.put("consume_id", (list.get(i)).getTemp_str1());  // 消费流水
				array.add( jsonObjArr);
			}
			info.put("list", array);
		}catch(Exception ex){
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
		return info ;
	}
	@Override
	public JSONObject qryAdminConsumeRecords(Common c) {
		/**
		 * 待确认提成到帐的消费记录
		 */
		JSONObject info = new JSONObject();
		try{	
			c.setSql("cardAdminDAO.qryAdminConsumeRecords");
			c.setFdid(PubFun.getUUID());
			List<Common> list = businessDaoUtil.getListData(c.getSql(), c); 
			c.setTemp_str1("0");
			c.setTemp_str2("9999999");
			List<Common> pageTotalNoList = businessDaoUtil.getListData(c.getSql(), c);
			info.put("pageTotalNo", pageTotalNoList.size()/15 + (pageTotalNoList.size()/15>0?1:0));
			if(list.size()>0){
				info.put("res","2");
				info.put("msg","执行成功");
			}else{
				info.put("res","1");
				info.put("msg","执行失败");
			}
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject jsonObjArr = new JSONObject();
				jsonObjArr.put("person_name", (list.get(i)).getPerson_name());
				jsonObjArr.put("card_code", (list.get(i)).getCard_code());
				jsonObjArr.put("card_phone", (list.get(i)).getCard_phone());
				jsonObjArr.put("account_fee", (list.get(i)).getAccount_fee());
				jsonObjArr.put("area_name", (list.get(i)).getArea_name());
				jsonObjArr.put("state", (list.get(i)).getState());
				jsonObjArr.put("create_time", (list.get(i)).getCreate_time());
				jsonObjArr.put("creator", (list.get(i)).getCreator());
				jsonObjArr.put("company_name", (list.get(i)).getCompany_name());
				jsonObjArr.put("card_sfz", (list.get(i)).getCard_sfz());
				jsonObjArr.put("consume_id", (list.get(i)).getTemp_str1());  // 消费流水
				array.add( jsonObjArr);
			}
			info.put("list", array);
		}catch(Exception ex){
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
		return info ;
	}
}
