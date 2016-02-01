package com.bw.fit.spm.company.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Pointcut;
import org.dom4j.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bw.common.service.impl.AllSysAuthServiceImpl;
import com.bw.common.uitily.MybatisDaoUtil;
import com.bw.common.uitily.PubFun;
import com.bw.fit.common.DaoUtil.BusinessDaoUtil;
import com.bw.fit.spm.company.service.CompanyAdminService;
import com.bw.fit.spm.model.Common;
@Service
@Transactional 
@Scope("singleton")
public class CompanyAdminServiceImpl implements CompanyAdminService{

	@Autowired
	public  BusinessDaoUtil businessDaoUtil;
	private  Log log = LogFactory.getLog(CompanyAdminServiceImpl.class); 

	@Override
	public JSONObject createCompanyInfo(Common c) {
		/**
		 * 拿到前台数据，保存商业主体资料
		 */
		JSONObject comp = new JSONObject();
		try{
			c.setSql("companyAdminDAO.createCompanyInfo");
			c.setFdid(PubFun.getUUID());
			Document doc = businessDaoUtil.sysUpdateData(c.getSql(), c);
			comp.put("res",doc.selectSingleNode("/root/res").getText());
			comp.put("msg",doc.selectSingleNode("/root/msg").getText());
		}catch(Exception ex){
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
		return comp ;
	}

	@Override
	public JSONObject createVillageInfo(Common c) {
		/**
		 * 新建社区
		 */
		JSONObject village = new JSONObject();
		try{
			c.setSql("companyAdminDAO.createVillageInfo");
			c.setFdid(PubFun.getUUID());
			c.setCreate_time(PubFun.getSysDateM());
			c.setVersion_time(PubFun.getSysDateM());		
			Document doc = businessDaoUtil.sysUpdateData(c.getSql(), c);
			if("2".equalsIgnoreCase(doc.selectSingleNode("/root/res").getText())){
				c.setVillage_id(c.getFdid());				
				c.setSql("companyAdminDAO.createVillageCompanyRelation");
				doc = businessDaoUtil.sysUpdateData(c.getSql(), c);
				village.put("res",doc.selectSingleNode("/root/res").getText());
				village.put("msg",doc.selectSingleNode("/root/msg").getText());
			}	else{
				village.put("res","1");
				village.put("msg","执行失败");
			}		
		}catch(Exception ex){
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
		return village ;
	}

	@Override
	public JSONObject createHouseInfo(Common c) {
		/**
		 * 建家庭资料
		 */
		JSONObject info = new JSONObject(); 
		try{			
			c.setSql("companyAdminDAO.createHouseInfo");
			c.setFdid(PubFun.getUUID());
			c.setCreate_time(PubFun.getSysDateM());
			c.setVersion_time(PubFun.getSysDateM());		
			Document doc = businessDaoUtil.sysUpdateData(c.getSql(), c);
			if("2".equalsIgnoreCase(doc.selectSingleNode("/root/res").getText())){		
				c.setSql("companyAdminDAO.createVillageHouseRelation");
				doc = businessDaoUtil.sysUpdateData(c.getSql(), c);
				// 建立家庭与账户关系
				c.setHouse_id(c.getFdid());
				c.setHouse_account_id(PubFun.getUUID());
				c.setHouse_account_name("余额");
				c.setSql("companyAdminDAO.createHouseAccount");
				doc = businessDaoUtil.sysUpdateData(c.getSql(), c);
				c.setSql("companyAdminDAO.createHouseCardRelation");
				doc = businessDaoUtil.sysUpdateData(c.getSql(), c);
				info.put("res",doc.selectSingleNode("/root/res").getText());
				info.put("msg",doc.selectSingleNode("/root/msg").getText());
			}	else{
				info.put("res","1");
				info.put("msg","执行失败");
			}		
		}catch(Exception ex){
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
		return info ;
	}
	@Override
	public JSONObject createCardInfo(Common c) {
		/**
		 * 建卡
		 */
		JSONObject info = new JSONObject();
		try{
			c.setSql("companyAdminDAO.createCardInfo");
			c.setFdid(PubFun.getUUID());
			c.setCreate_time(PubFun.getSysDateM());
			c.setVersion_time(PubFun.getSysDateM());			
			Document doc = businessDaoUtil.sysUpdateData(c.getSql(), c);
			if("2".equalsIgnoreCase(doc.selectSingleNode("/root/res").getText())){
				c.setAccount_id(PubFun.getUUID());			
				c.setAccount_name("余额");
				c.setAccount_fee(0);
				c.setSql("companyAdminDAO.createCardAccount");
				doc = businessDaoUtil.sysUpdateData(c.getSql(), c);
				c.setSql("companyAdminDAO.createCardAccountRelation");
				doc = businessDaoUtil.sysUpdateData(c.getSql(), c);
				// 建立卡与家庭关系
				c.setSql("companyAdminDAO.createCard2house");
				doc = businessDaoUtil.sysUpdateData(c.getSql(), c);
				info.put("res",doc.selectSingleNode("/root/res").getText());
				info.put("msg",doc.selectSingleNode("/root/msg").getText());
			}	else{
				info.put("res","1");
				info.put("msg",doc.selectSingleNode("/root/msg").getText());
			}		
			

		}catch(Exception ex){
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
		return info ;
	}

	@Override
	public JSONObject createCardAccount(Common c) {
		/**
		 * 建卡账户
		 */
		JSONObject info = new JSONObject();
		try{
			c.setSql("companyAdminDAO.createCardInfo");
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

	@Override
	public JSONObject createCardAccountRelation(Common c) {
		/**
		 * 建卡与账户关联
		 */
		JSONObject info = new JSONObject();
		try{
			c.setSql("companyAdminDAO.createCardAccountRelation");
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

	@Override
	public JSONObject createHouseCardRelation(Common c) {
		/**
		 * 建立家庭与卡关系
		 */
		JSONObject info = new JSONObject();
		try{
			c.setSql("companyAdminDAO.createHouseCardRelation");
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

	@Override
	public JSONObject createVillageHouseRelation(Common c) {
		/**
		 * 建立社区与家庭关系
		 */
		JSONObject info = new JSONObject();
		try{
			c.setSql("companyAdminDAO.createVillageHouseRelation");
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
	public JSONObject createVillageCompanyRelation(Common c) {
		/**
		 * 建立社区与商业主体关系
		 */
		JSONObject info = new JSONObject();
		try{
			c.setSql("companyAdminDAO.createVillageCompanyRelation");
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
	public JSONObject getVillageByCompany(Common c) {
		/**
		 * 查询社区
		 */
		JSONObject info = new JSONObject();
		try{
			c.setSql("companyAdminDAO.getVillageByCompany");
			c.setFdid(PubFun.getUUID());
			List<Common> list =businessDaoUtil.getListData(c.getSql(), c);
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
				jsonObjArr.put("id", (list.get(i)).getVillage_id());
				jsonObjArr.put("name", (list.get(i)).getVillage_name());
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
	public JSONObject qryCompanyList(Common c) {
		/**
		 * 查询商业主体列表
		 */
		JSONObject info = new JSONObject();
		try{
			c.setSql("companyAdminDAO.qryCompanyList");
			c.setFdid(PubFun.getUUID());
			List<Common> list =businessDaoUtil.getListData(c.getSql(), c);
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
				jsonObjArr.put("fdid", (list.get(i)).getFdid());
				jsonObjArr.put("company_name", (list.get(i)).getCompany_name());
				jsonObjArr.put("address", (list.get(i)).getCompany_address());
				jsonObjArr.put("state", (list.get(i)).getState());
				jsonObjArr.put("create_time", (list.get(i)).getCreate_time());
				jsonObjArr.put("staff_name", (list.get(i)).getStaff_name());
				jsonObjArr.put("area_name", (list.get(i)).getArea_name());
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
	public JSONObject qryCardInfoList(Common c) {
		/**
		 * 卡查询
		 */
		JSONObject info = new JSONObject();
		try{
			c.setSql("companyAdminDAO.qryCardInfoList");
			c.setFdid(PubFun.getUUID());
			List<Common> list =businessDaoUtil.getListData(c.getSql(), c);
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
				jsonObjArr.put("fdid", (list.get(i)).getFdid());
				jsonObjArr.put("person_name", (list.get(i)).getPerson_name());
				jsonObjArr.put("card_code", (list.get(i)).getCard_code());
				jsonObjArr.put("village_name", (list.get(i)).getVillage_name());
				jsonObjArr.put("company_name", (list.get(i)).getCompany_name());
				jsonObjArr.put("area_name", (list.get(i)).getArea_name());
				jsonObjArr.put("create_time", (list.get(i)).getCreate_time());
				jsonObjArr.put("staff_name", (list.get(i)).getStaff_name());
				jsonObjArr.put("phone", (list.get(i)).getCard_phone());
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
	public JSONObject deleteCardInfoList(Common c) {
		/**
		 * 删除卡信息
		 */
		JSONObject info = new JSONObject();
		String[] arr = c.getTemp_str1().split(",");
		try{
			for (int i=0;i<arr.length;i++){
				c.setSql("companyAdminDAO.deleteCardInfoList");
				c.setCard_id(arr[i]);
				c.setVersion_time(PubFun.getSysDateM());	
				Document doc = businessDaoUtil.sysDeleteData(c.getSql(), c);
				if("1".equalsIgnoreCase(doc.selectSingleNode("/root/res").getText())){
					info.put("res",doc.selectSingleNode("/root/res").getText());
					info.put("msg",doc.selectSingleNode("/root/msg").getText());
					return info ;
				}
				info.put("res",doc.selectSingleNode("/root/res").getText());
				info.put("msg",doc.selectSingleNode("/root/msg").getText());
			}			
		}catch(Exception ex){
			ex.printStackTrace();
			log.info(ex.getMessage());
		}
		return info ;
	}

	@Override
	public JSONObject changeUserPasswd(Common c) {
		/**
		 *密码修改
		 */
		JSONObject info = new JSONObject();
		try{
			c.setSql("companyAdminDAO.changeUserPasswd");
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
	public JSONObject getCompanyListByArea(Common c) {
		/**
		 * 根据地区查询商户列表
		 */
		JSONObject info = new JSONObject();
		try{
			c.setSql("companyAdminDAO.getCompanyListByArea");
			List<Common> list =businessDaoUtil.getListData(c.getSql(), c);
			if(list.size()>0){
				info.put("res","2");
				info.put("msg","查询成功");
			}else{
				info.put("res","1");
				info.put("msg","查询失败");
			}
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject jsonObjArr = new JSONObject();
				jsonObjArr.put("company_name", (list.get(i)).getCompany_name());
				jsonObjArr.put("company_type_name", (list.get(i)).getCompany_type_name());
				jsonObjArr.put("company_admin_phone", (list.get(i)).getCompany_admin_phone());
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
	public JSONObject qryVillageList(Common c) {
		/**
		 * 社区列表
		 */
		JSONObject info = new JSONObject();
		try{
			c.setSql("companyAdminDAO.qryVillageList");
			c.setFdid(PubFun.getUUID());
			List<Common> list =businessDaoUtil.getListData(c.getSql(), c);
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
				jsonObjArr.put("fdid", (list.get(i)).getFdid());
				jsonObjArr.put("village_name", (list.get(i)).getVillage_name());
				jsonObjArr.put("village_address", (list.get(i)).getVillage_address());
				jsonObjArr.put("company_name", (list.get(i)).getCompany_name());
				jsonObjArr.put("create_time", (list.get(i)).getCreate_time());
				jsonObjArr.put("creator", (list.get(i)).getCreator());
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
	public JSONObject qryHouseInfoList(Common c) {
		/**
		 * 住户列表
		 */
		JSONObject info = new JSONObject();
		try{
			c.setSql("companyAdminDAO.qryHouseInfoList");
			c.setFdid(PubFun.getUUID());
			List<Common> list =businessDaoUtil.getListData(c.getSql(), c);
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
				jsonObjArr.put("fdid", (list.get(i)).getFdid());
				jsonObjArr.put("house_name", (list.get(i)).getHouse_name());
				jsonObjArr.put("village_name", (list.get(i)).getVillage_name());
				jsonObjArr.put("company_name", (list.get(i)).getCompany_name());
				jsonObjArr.put("area_name", (list.get(i)).getArea_name());
				jsonObjArr.put("create_time", (list.get(i)).getCreate_time());
				jsonObjArr.put("creator", (list.get(i)).getCreator());
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
