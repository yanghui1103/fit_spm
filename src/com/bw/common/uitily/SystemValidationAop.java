package com.bw.common.uitily;

import org.aspectj.lang.annotation.Aspect;
import org.dom4j.Document;
import org.springframework.core.Ordered;

import com.bw.fit.spm.model.Common;
 
public class SystemValidationAop implements SystemValidationInterface, Ordered{
	
	public Document  validationCheck(Common cm){
		System.out.println("check....");
		System.out.println("check....222");
		return null ;
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}
}
