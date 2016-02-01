package com.bw.common.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.dom4j.Document;
@WebService
public interface UserInterInfos {
	@WebMethod
	public String getAlldriverinfos();
}
