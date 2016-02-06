package com.bw.fit.fee.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bw.common.uitily.PubFun;
import com.bw.fit.common.DaoUtil.BusinessDaoUtil;
import com.bw.fit.fee.service.FeeUseService;
import com.bw.fit.spm.card.service.impl.CardServiceImpl;
import com.bw.fit.spm.model.Common;
@Service
@Transactional 
@Scope("singleton")
@Component
public class FeeUseServiceImpl implements FeeUseService {

	@Autowired
	public  BusinessDaoUtil businessDaoUtil;
	private  Log log = LogFactory.getLog(FeeUseServiceImpl.class); 
	@Override
	public JSONObject createCompanyFeeUse(Common c) {
		JSONObject comp = new JSONObject();
		try{
			c.setFdid(PubFun.getUUID());
			c.setVersion_time(PubFun.getSysDateM());
			c.setCreate_time(PubFun.getSysDateM());
			c.setSql("compFeeUseAdminDAO.createCompanyFeeUse");
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

}
