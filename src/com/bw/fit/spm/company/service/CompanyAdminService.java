package com.bw.fit.spm.company.service;

import org.json.simple.JSONObject;

import com.bw.fit.spm.model.Common;

public interface CompanyAdminService {

	/**
	 * 新建商业主体
	 */
	public JSONObject createCompanyInfo(Common c);
	/**
	 * 新建社区主体资料
	 * 
	 */
	public JSONObject createVillageInfo(Common c);
	public JSONObject createHouseInfo(Common c);
	public JSONObject createCardInfo(Common c);
	public JSONObject createCardAccount(Common c);
	public JSONObject createCardAccountRelation(Common c);
	public JSONObject createHouseCardRelation(Common c);
	public JSONObject createVillageHouseRelation(Common c);
	public JSONObject createVillageCompanyRelation(Common c);
	public JSONObject getVillageByCompany(Common c);
	public JSONObject qryCompanyList(Common c);
	public JSONObject qryCardInfoList(Common c);
	public JSONObject deleteCardInfoList(Common c);
	public JSONObject changeUserPasswd(Common c);
	public JSONObject getCompanyListByArea(Common c);
	public JSONObject qryVillageList(Common c);
	public JSONObject qryHouseInfoList(Common c);
}
