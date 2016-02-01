package com.bw.common.service.impl;

import java.io.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bw.common.model.CommonMedol;
import com.bw.common.uitily.MybatisDaoUtil;

public class SD {
	public String getThisUserAuthLists(CommonMedol c){
		
		return "111";
	}
	//从request中构建Document
	private Document buildDocument(HttpServletRequest request)
			throws Exception {
		InputStream in = (InputStream) request.getInputStream();
		SAXReader xmlReader = new SAXReader();
		org.dom4j.Document document = xmlReader.read(in);
		return document;
	}
}
