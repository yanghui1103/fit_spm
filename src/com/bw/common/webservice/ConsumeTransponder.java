package com.bw.common.webservice;

import org.json.simple.JSONObject;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ConsumeTransponder {
	@WebMethod
	// 接受外部商户传过来的会员消费记录
	public JSONObject acceptConsumeRecords(String merchant_id,String merchant_passwd,String card_code,String card_phone, double   fee,double points ,String create_time)  ;
}
