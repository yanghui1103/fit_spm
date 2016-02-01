package com.bw.common.webservice.impl;

import javax.jws.WebService;

import org.dom4j.Document;

import com.bw.common.webservice.UserInterInfos;
@WebService(endpointInterface = "com.bw.common.webservice.UserInterInfos", serviceName = "t")
public class T implements UserInterInfos {

	@Override
	public String getAlldriverinfos() {
		// TODO Auto-generated method stub
		System.out.println("getAlldriverinfos .... ");
		return "hello getAlldriverinfos";
	}

}
