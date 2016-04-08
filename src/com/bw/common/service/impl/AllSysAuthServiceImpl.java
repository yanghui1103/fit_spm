package com.bw.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.bw.common.actions.BaseAction;
import com.bw.common.model.CommonMedol;
import com.bw.common.model.LoginUser;
import com.bw.common.service.*;
import com.bw.common.uitily.MybatisDaoUtil;
import com.bw.common.uitily.PubFun;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bw.common.model.CommonMedol;
import com.bw.common.uitily.MybatisDaoUtil;
import com.bw.common.uitily.PubFun; 
import com.bw.fit.spm.model.Common;
@Service
@Transactional 
@Scope("singleton")
public class AllSysAuthServiceImpl implements AllSysAuthService{

	@Autowired
	public  MybatisDaoUtil mybatisDaoUtil;

	private  Log log = LogFactory.getLog(AllSysAuthServiceImpl.class); 

	public List getThisUserAuthLists(CommonMedol c){
		
		List<CommonMedol> ls = new ArrayList<CommonMedol>();
		try {
			c.setSql("sysAuthenticationDAO.getThisUserAuthLists");
			ls = mybatisDaoUtil.getListData(c.getSql(), c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.setParam31("getThisUserAuthLists");
		c.setParam32("获取左侧功能栏");
		mybatisDaoUtil.userOperateTrail(c);
			return ls ;
	}
 
	@Override
	public List getDragWindowList(CommonMedol c) {
		List ls = new ArrayList<CommonMedol>();
		try {
			c.setSql("sysAuthenticationDAO.getDragWindowList");
			ls = mybatisDaoUtil.getListData(c.getSql(), c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return ls ;
	}

	@Override
	public String getRoleExistAndNoexitAuthList(CommonMedol c) {
		StringBuffer sb = new StringBuffer();
		try {
			c.setSql("sysAuthenticationDAO.getRoleExistAuthList");
			List existLs = mybatisDaoUtil.getListData(c.getSql(), c); // 已经选中的功能
			c.setSql("sysAuthenticationDAO.getAllAuthList");
			List allLs = mybatisDaoUtil.getListData(c.getSql(), c);// 全部功能
			c.setSql("sysAuthenticationDAO.getCheckFuncBtnList");
			List chkBtnLs = mybatisDaoUtil.getListData(c.getSql(), c);//以选中的按钮
			c.setSql("sysAuthenticationDAO.getFuncBtnList");
			List btnLs = mybatisDaoUtil.getListData(c.getSql(), c);//全部按钮
			
			sb.append("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			return sb.toString() ;
	}

	@Override
	public List getUserListUnderTheStation(CommonMedol c) {
		List ls = new ArrayList();
		try {
			c.setSql("sysAuthenticationDAO.getUserListUnderTheStation");
			ls = mybatisDaoUtil.getListData(c.getSql(), c);  
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
			return ls ;
	}

	@Override
	public Document createNewRoleService(CommonMedol c) {
		// TODO Auto-generated method stub

 		Document doc = DocumentHelper.createDocument(); 	
		try {
			c.setParam4(PubFun.getUUID());
			c.setSql("sysAuthenticationDAO.createNewRoleService");
			doc = mybatisDaoUtil.sysUpdateData(c.getSql(), c);  			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return doc;
	}

	@Override
	public List getAllOrgsService(CommonMedol c) {
		// TODO Auto-generated method stub
		List ls = new ArrayList();
		try {
			c.setSql("sysAuthenticationDAO.getAllOrgsService");
			ls = mybatisDaoUtil.getListData(c.getSql(), c);  
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		c.setParam28(PubFun.getSysDateM());
		c.setParam31("getAllOrgsService");
		c.setParam32("获取组织架构"); 
		mybatisDaoUtil.userOperateTrail(c);
			return ls ;
	}

	@Override
	public List getOrgInfoTreeService(CommonMedol c) {
		// TODO Auto-generated method stub
		List ls = new ArrayList();
		try {
			c.setSql("sysAuthenticationDAO.getAllOrgsService");
			ls = mybatisDaoUtil.getListData(c.getSql(), c);  
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
			return ls ;
	}

	@Override
	public List getThisRoleAuthTreeJsonService(CommonMedol c) {
		List ls = new ArrayList();
		try {
			c.setSql("sysAuthenticationDAO.getThisRoleAuthTreeJsonService");
			ls = mybatisDaoUtil.getListData(c.getSql(), c);  
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
			return ls ;
	}

	@Override
	public Document giveAuthorityToRoleService(CommonMedol c) {

 		Document doc = DocumentHelper.createDocument(); 	
		try {
			c.setSql("sysAuthenticationDAO.qryAuthorityRoleRelation");
			if( mybatisDaoUtil.getListData(c.getSql(), c).size()>0){
				c.setSql("sysAuthenticationDAO.deleteAuthorityToRole");
				doc = mybatisDaoUtil.sysDeleteData(c.getSql(), c);  	
				if(!"2".equals(doc.selectSingleNode("/root/res").getText())){
					return doc ;
				}
			}
			
			String role_cd = c.getParam1();
	 		String[] nodeArray = c.getParam2().split(",");
	 		for(int i=0;i<nodeArray.length;i++){	 			
	 			c.setSql("sysAuthenticationDAO.checkAuthorityExiste");
	 			c.setParam2(nodeArray[i]);
	 			CommonMedol cc = (CommonMedol)mybatisDaoUtil.getOneData(c.getSql(), c);  
				if(Integer.valueOf(cc.getParam1())<1){
					// 说明functions表中不存在这个ID的功能,肯定是在btn表里
					c.setSql("sysAuthenticationDAO.qryAuthBtnExiste");
					List<CommonMedol> ls = mybatisDaoUtil.getListData(c.getSql(), c);  	
					c.setSql("sysAuthenticationDAO.giveAuthorityToRoleService");
					c.setParam3(ls.get(0).getParam2()); //btn_cd
					c.setParam2(ls.get(0).getParam4()); // 把这个btn的function_cd找到并赋进去
					doc = mybatisDaoUtil.sysUpdateData(c.getSql(), c);  		
				}else{// 这样就把该功能的展示权限赋上
					c.setSql("sysAuthenticationDAO.giveAuthorityToRoleService");
					c.setParam3("view");//展示权限
					doc = mybatisDaoUtil.sysUpdateData(c.getSql(), c);  		
				}
	 		}	 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
log.info(doc.asXML());
		c.setParam28(PubFun.getSysDateM());
		c.setParam31("giveAuthorityToRoleService");
		c.setParam32("角色权限");
		c.setParam29(doc.selectSingleNode("/root/res").getText());
		c.setParam30(doc.selectSingleNode("/root/msg").getText());
		mybatisDaoUtil.userOperateTrail(c);
		return doc; 
	}

	@Override
	public String getAuthorityBtnsByThisUserService(CommonMedol c) {
		StringBuffer sb = new StringBuffer();
		try {			
			c.setSql("sysAuthenticationDAO.getAuthorityBtnsByThisUserService");
			List<CommonMedol> btnLs = mybatisDaoUtil.getListData(c.getSql(), c);//全部按钮
			sb.append("<ul>");
				for(int i=0;i<btnLs.size();i++){
					if("a_url".equalsIgnoreCase(btnLs.get(i).getParam5())){
						sb.append("<li><a class=buttonActive href=\""+btnLs.get(i).getParam4()+"\" target=navTab><span id="+btnLs.get(i).getParam3()+">"+btnLs.get(i).getParam2()+"</span></a></li>");
					}else if("common".equalsIgnoreCase(btnLs.get(i).getParam5())){
						sb.append("<li><div class=buttonActive><div class=buttonContent><button id="+btnLs.get(i).getParam3()+" type=button>"+btnLs.get(i).getParam2()+"</button></div></div>");
				}
				
			} // end for
			sb.append("</ul>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return sb.toString() ;
	}

	@Override
	public Document createNewSysUserService(CommonMedol c) {
		// TODO Auto-generated 		method stub
		
		 		Document doc = DocumentHelper.createDocument(); 	
				try {
					c.setParam12(PubFun.getUUID());
					c.setParam13(PubFun.getUUID());
					
					c.setSql("sysAuthenticationDAO.createNewSysUserService");
					doc = mybatisDaoUtil.sysUpdateData(c.getSql(), c);  	
//					if("2".equals(doc.selectSingleNode("/root/res").getText())){
//						CommonMedol cm = c ;
						c.setSql("sysAuthenticationDAO.createUserToRoleRelation");
						mybatisDaoUtil.sysUpdateData(c.getSql(), c);  		
						c.setSql("sysAuthenticationDAO.createUserToOrgRelationService");
						log.info(c.getParam12());
						log.info(c.getParam4());
						log.info(c.getParam8());
						doc = mybatisDaoUtil.sysUpdateData(c.getSql(), c);  	
//					}else {
//						Document doc2 = DocumentHelper.createDocument(); 	
//						Element rootR = doc2.addElement("root");
//						rootR.addElement("res").addText("1");
//						rootR.addElement("msg").addText("创建用户失败");
//						doc = doc2 ;
//					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
//				c.setParam28(PubFun.getSysDateM());
//				c.setParam31("createNewSysUserService");
//				c.setParam32("新建用户");
//				c.setParam29(doc.selectSingleNode("/root/res").getText());
//				c.setParam30(doc.selectSingleNode("/root/msg").getText());
//				mybatisDaoUtil.userOperateTrail(c);
				return doc;
	}

	@Override
	public Document getUserInfosByUserIdService(CommonMedol c) {
		Document doc = DocumentHelper.createDocument(); 	
	try {
		c.setSql("sysAuthenticationDAO.getUserInfosByUserIdService");
		List<CommonMedol> ls = mybatisDaoUtil.getListData(c.getSql(), c);  
		Element rootR = doc.addElement("root");  
		Element Node = rootR.addElement("user");
		Node.addElement("user_cd").addText(ls.get(0).getParam1());
		Node.addElement("user_name").addText(ls.get(0).getParam2());
		Node.addElement("phone").addText(ls.get(0).getParam3());
		Node.addElement("fixed_phone").addText(ls.get(0).getParam4());
		Node.addElement("address").addText(ls.get(0).getParam5());
		Node.addElement("peculiarity_name").addText(ls.get(0).getParam6());
		Node.addElement("entry_time").addText(ls.get(0).getParam7());
		Node.addElement("create_date").addText(ls.get(0).getParam8());
		Node.addElement("in_operator").addText(ls.get(0).getParam9());
		Node.addElement("company_cd").addText(ls.get(0).getParam10());
		Node.addElement("company_name").addText(ls.get(0).getParam11());
		Node.addElement("peculiarity_cd").addText(ls.get(0).getParam12());
		Node.addElement("role_cd").addText(ls.get(0).getParam13());
		Node.addElement("position").addText(ls.get(0).getParam14()); 
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	return doc;
	}

	@Override
	public Document updateUserInfosByUserIdService(CommonMedol c) {

 		Document doc = DocumentHelper.createDocument(); 	
		try {
			c.setSql("sysAuthenticationDAO.updateSysUserInfoService");
			doc = mybatisDaoUtil.sysUpdateData(c.getSql(), c);  		
			c.setSql("sysAuthenticationDAO.updateUserToRoleRelation");
			doc = mybatisDaoUtil.sysUpdateData(c.getSql(), c);  	
			c.setSql("sysAuthenticationDAO.updateUserToPositionRelation");
			doc = mybatisDaoUtil.sysUpdateData(c.getSql(), c);  			
			c.setSql("sysAuthenticationDAO.updateUserToOrgRelationService");
			doc = mybatisDaoUtil.sysUpdateData(c.getSql(), c);  	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		c.setParam28(PubFun.getSysDateM());
		c.setParam31("updateUserInfosByUserIdService");
		c.setParam32("修改用户资料");
		c.setParam29(doc.selectSingleNode("/root/res").getText());
		c.setParam30(doc.selectSingleNode("/root/msg").getText());
		mybatisDaoUtil.userOperateTrail(c);
		return doc;
	}

	@Override
	public Document createNewSysOrgService(CommonMedol c){
		// TODO Auto-generated method stub

 		Document doc = DocumentHelper.createDocument(); 	
		try {
			c.setParam10(PubFun.getUUID());
			c.setParam11(PubFun.getSysDateM());
			c.setSql("sysAuthenticationDAO.createNewSysOrgService");
			doc = mybatisDaoUtil.sysUpdateData(c.getSql(), c);  			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		log.info(doc.asXML());
		return doc;
	}

	@Override
	public Document getOrgInfosByIdService(CommonMedol c){
		Document doc = DocumentHelper.createDocument(); 	
	try {
		c.setSql("sysAuthenticationDAO.getOrgInfosByIdService");
		List<CommonMedol> ls = mybatisDaoUtil.getListData(c.getSql(), c);  
		Element rootR = doc.addElement("root");  
		Element Node = rootR.addElement("org");
		Node.addElement("company_cd").addText(ls.get(0).getParam1());
		Node.addElement("company_name").addText(ls.get(0).getParam2());
		Node.addElement("level").addText(ls.get(0).getParam3());
		Node.addElement("upcompany_cd").addText(ls.get(0).getParam4());
		Node.addElement("upcompany_name").addText(ls.get(0).getParam5());
		Node.addElement("create_date").addText(ls.get(0).getParam6());
		Node.addElement("operator").addText(ls.get(0).getParam7());
		Node.addElement("address").addText(ls.get(0).getParam8());
		log.info(doc.asXML());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	return doc;
	}

	@Override
	public Document updateOrgInfosByOrgIdService(CommonMedol c)  {

 		Document doc = DocumentHelper.createDocument(); 	
		try {
			CommonMedol cTmp = new CommonMedol();
			cTmp.setParam2(c.getParam3());// 把机构编码赋值
			cTmp.setSql("sysAuthenticationDAO.getThisOrgLevel");// 等级再加一
			c.setParam5(String.valueOf(Integer.valueOf(((CommonMedol) mybatisDaoUtil.getOneData(cTmp.getSql(), cTmp)).getParam1())+1));  
				
			c.setSql("sysAuthenticationDAO.updateSysOrgInfoService");
			doc = mybatisDaoUtil.sysUpdateData(c.getSql(), c);  	 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		c.setParam28(PubFun.getSysDateM());
		c.setParam31("updateOrgInfosByOrgIdService");
		c.setParam32("修改组织机构资料");
		c.setParam29(doc.selectSingleNode("/root/res").getText());
		c.setParam30(doc.selectSingleNode("/root/msg").getText());
		mybatisDaoUtil.userOperateTrail(c);
		return doc;
	}

	@Override
	public Document qrySearchOrgUserTopListService(CommonMedol c) {
		Document doc = DocumentHelper.createDocument(); 	
		try {
			c.setSql("sysAuthenticationDAO.qrySearchOrgUserTopList");
			List<CommonMedol> ls = mybatisDaoUtil.getListData(c.getSql(), c);  
			Element rootR = doc.addElement("root");  
			for(int i=0;i<ls.size();i++){
				Element Node = rootR.addElement("staff");
				Node.addElement("user_cd").addText(ls.get(i).getParam1());
				Node.addElement("user_name").addText(ls.get(i).getParam2());
				Node.addElement("company_name").addText(ls.get(i).getParam4()); 
			}
			log.info(doc.asXML());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return doc;
	}

	@Override
	public Document qrySearchOrgUserTopListByKeyWordsService(CommonMedol c) {
		Document doc = DocumentHelper.createDocument(); 	
		try {
			c.setSql("sysAuthenticationDAO.qrySearchOrgUserTopListByKeyWordsService");
			List<CommonMedol> ls = mybatisDaoUtil.getListData(c.getSql(), c);  
			Element rootR = doc.addElement("root");  
			for(int i=0;i<ls.size();i++){
				Element Node = rootR.addElement("staff");
				Node.addElement("user_cd").addText(ls.get(i).getParam1());
				Node.addElement("user_name").addText(ls.get(i).getParam2());
				Node.addElement("company_name").addText(ls.get(i).getParam4()); 
			}
			log.info(doc.asXML());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return doc;
	}

	@Override
	public Document qrySearchStaffInfoByOrgTopTreeService(CommonMedol c) {
		Document doc = DocumentHelper.createDocument(); 	
		try {
			c.setSql("sysAuthenticationDAO.qrySearchStaffInfoByOrgTopTree");
			List<CommonMedol> ls = mybatisDaoUtil.getListData(c.getSql(), c);  
			Element rootR = doc.addElement("root");  
			Element Node = rootR.addElement("staff");
			Node.addElement("user_cd").addText(ls.get(0).getParam1());
			Node.addElement("user_name").addText(ls.get(0).getParam2());
			Node.addElement("post").addText(ls.get(0).getParam3()); 
			log.info(doc.asXML());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return doc;
	}

	@Override
	public Document userLoginService(CommonMedol c,HttpSession session) {
		// 用户登录
 		Document doc = DocumentHelper.createDocument(); 	
 		Element rootR = doc.addElement("root");
		try {
			c.setSql("sysAuthenticationDAO.userLoginService");
			List<CommonMedol> ls = mybatisDaoUtil.getListData(c.getSql(), c);  	 
			c.setSql("sysAuthenticationDAO.getThisUserAllInfo");
			List<CommonMedol> UserLis = mybatisDaoUtil.getListData(c.getSql(), c);  	 
	        if(Integer.valueOf(ls.get(0).getParam1())>0){  	
	        	LoginUser m = new LoginUser();
	        	m.setUser_cd(UserLis.get(0).getParam6());
	        	m.setOrg_name( UserLis.get(0).getParam1());
	        	m.setUser_phone( UserLis.get(0).getParam2());
	        	m.setOrg_cd(  UserLis.get(0).getParam3());
	        	m.setOrg_name( UserLis.get(0).getParam4());
	        	m.setParent_org_cd(  UserLis.get(0).getParam5());
	        	session.setAttribute("LoginUser", m);
	            rootR.addElement("res").addText("2");
	            rootR.addElement("msg").addText("登陆成功");
	            if(!checkUser(m)){// 检查用户资料
		            rootR.addElement("res").addText("1");
		            rootR.addElement("msg").addText("登录用户资料不全，请联系管理员");
	            }
	        }else{  	        	 
	            rootR.addElement("res").addText("1");
	            rootR.addElement("msg").addText("登陆失败，或用户不存在");
	        }  
				 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
        return doc;
	}
	public boolean checkUser(LoginUser m){
		if(m==null ||
				"".equals(m.getOrg_cd())||
				"".equals(m.getUser_cd())||
				"".equals(m.getUser_phone())||
				m.getOrg_cd()==null||
				m.getUser_cd()==null||
				m.getUser_phone()==null){
			return false;
		}
		return true;
	}
	@Override
	public Document createNewPositionService(CommonMedol c) {

 		Document doc = DocumentHelper.createDocument(); 	
		try {
			c.setSql("sysAuthenticationDAO.getMaxPositionCd");
			c.setParam4(((CommonMedol) mybatisDaoUtil.getOneData(c.getSql(), c)).getParam1());  
			c.setParam3(c.getParam4());
			c.setSql("sysAuthenticationDAO.createNewPositionService");
			doc = mybatisDaoUtil.sysUpdateData(c.getSql(), c);  			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		c.setParam28(PubFun.getSysDateM());
		c.setParam31("createNewPositionService");
		c.setParam32("新建岗位");
		c.setParam29(doc.selectSingleNode("/root/res").getText());
		c.setParam30(doc.selectSingleNode("/root/msg").getText());
		mybatisDaoUtil.userOperateTrail(c);
		return doc;
	}
 
	public Document getPositionListService(CommonMedol c) {
		Document doc = DocumentHelper.createDocument(); 	
		try {
			c.setSql("sysAuthenticationDAO.getPositionListService");
			List<CommonMedol> ls = mybatisDaoUtil.getListData(c.getSql(), c);  
			Element rootR = doc.addElement("root");  
			for(int i=0;i<ls.size();i++){
				Element Node = rootR.addElement("position");
				Node.addElement("fdid").addText(ls.get(i).getParam1());
				Node.addElement("position_cd").addText(ls.get(i).getParam2());
				Node.addElement("position_name").addText(ls.get(i).getParam3()); 
				Node.addElement("remark").addText(ls.get(i).getParam4()); 
				Node.addElement("state").addText(ls.get(i).getParam5());  
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return doc;
	}

	@Override
	public Document getPositionInfosByIdService(CommonMedol c) {
		Document doc = DocumentHelper.createDocument(); 	
		try {
			c.setSql("sysAuthenticationDAO.getPositionInfosById");
			List<CommonMedol> ls = mybatisDaoUtil.getListData(c.getSql(), c);  
			Element rootR = doc.addElement("root");  
			Element Node = rootR.addElement("position");
			Node.addElement("position_cd").addText(ls.get(0).getParam2());
			Node.addElement("position_name").addText(ls.get(0).getParam3());
			Node.addElement("position_remark").addText(ls.get(0).getParam4());
			Node.addElement("state").addText(ls.get(0).getParam5());  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return doc;
	}

	@Override
	public Document updatePositionInfosService(CommonMedol c) {

 		Document doc = DocumentHelper.createDocument(); 	
		try { 
			c.setSql("sysAuthenticationDAO.updatePositionInfosService");
			doc = mybatisDaoUtil.sysUpdateData(c.getSql(), c);  			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		c.setParam28(PubFun.getSysDateM());
		c.setParam31("updatePositionInfosService");
		c.setParam32("修改岗位资料");
		c.setParam29(doc.selectSingleNode("/root/res").getText());
		c.setParam30(doc.selectSingleNode("/root/msg").getText());
		mybatisDaoUtil.userOperateTrail(c);
		return doc;
	}
 
	public Document getFileListByForeginIdService(CommonMedol c) {
		Document doc = DocumentHelper.createDocument(); 	
		try {
			c.setSql("sysAuthenticationDAO.getFileListByForeginId");
			List<CommonMedol> ls = mybatisDaoUtil.getListData(c.getSql(), c);  
			Element rootR = doc.addElement("root");  
			for(int i=0;i<ls.size();i++){
				Element Node = rootR.addElement("file");
				Node.addElement("fileId").addText(ls.get(i).getParam1());
				Node.addElement("beforeFileName").addText(ls.get(i).getParam2()); 
				Node.addElement("createDate").addText(ls.get(i).getParam3()); 
				Node.addElement("creator").addText(ls.get(i).getParam4());  
				Node.addElement("afterFileName").addText(ls.get(i).getParam5());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return doc;
	}
	@Override
	public String getThisUserAuthListsV3(CommonMedol c) {
		List<CommonMedol> ls = new ArrayList<CommonMedol>();
		Document document = DocumentHelper.createDocument(); 		
		JSONObject jsonObj = new JSONObject();// 创建json格式的数据		
		Element rootR = document.addElement("root");  
		try {
			
			c.setSql("sysAuthenticationDAO.getThisUserAuthLists");
			ls = mybatisDaoUtil.getListData(c.getSql(), c);
 			JSONArray jsonArr = new JSONArray();// json格式的数组
	 		for(int i=0;i<ls.size();i++){
				JSONObject jsonObjArr = new JSONObject();
				jsonObjArr.put("func_name", ls.get(i).getParam1());
				jsonObjArr.put("p_id", ls.get(i).getParam2());
				jsonObjArr.put("level", ls.get(i).getParam3());
				jsonObjArr.put("address", ls.get(i).getParam4());
				jsonObjArr.put("func_id", ls.get(i).getParam5());
				jsonObjArr.put("target", ls.get(i).getParam6());
				jsonObjArr.put("rel", ls.get(i).getParam7());
				jsonArr.add(jsonObjArr);// 将json格式的数据放到json格式的数组里
	 		}
			jsonObj.put("list", jsonArr);// 再将这个json格式的的数组放到最终的json对象中。
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonObj.put("res", "2");
		jsonObj.put("msg", "执行成功");
		System.out.println(jsonObj.toString());
		return jsonObj.toString();
	}
	@Override
	public Document getThisUserAuthListsV2(CommonMedol c) {
		List<CommonMedol> ls = new ArrayList<CommonMedol>();
		Document document = DocumentHelper.createDocument(); 		
		JSONObject jsonObj = new JSONObject();// 创建json格式的数据		
		Element rootR = document.addElement("root");  
		try {
			
			c.setSql("sysAuthenticationDAO.getThisUserAuthLists");
			ls = mybatisDaoUtil.getListData(c.getSql(), c);
 			JSONArray jsonArr = new JSONArray();// json格式的数组
	 		for(int i=0;i<ls.size();i++){
				JSONObject jsonObjArr = new JSONObject();
				jsonObjArr.put("func_name", ls.get(i).getParam1());
				jsonObjArr.put("p_id", ls.get(i).getParam2());
				jsonObjArr.put("level", ls.get(i).getParam3());
				jsonObjArr.put("address", ls.get(i).getParam4());
				jsonObjArr.put("func_id", ls.get(i).getParam5());
				jsonObjArr.put("target", ls.get(i).getParam6());
				jsonObjArr.put("rel", ls.get(i).getParam7());
				jsonArr.add(jsonObjArr);// 将json格式的数据放到json格式的数组里//	 			
	 			Element Node= rootR.addElement("node");
	 			Node.addElement("func_name").addText(ls.get(i).getParam1());
	 			Node.addElement("p_id").addText(ls.get(i).getParam2());
	 			Node.addElement("level").addText(ls.get(i).getParam3());
	 			Node.addElement("address").addText(ls.get(i).getParam4());
	 			Node.addElement("func_id").addText(ls.get(i).getParam5());
	 			Node.addElement("target").addText(ls.get(i).getParam6());
	 			Node.addElement("rel").addText(ls.get(i).getParam7());
	 		}
			jsonObj.put("list", jsonArr);// 再将这个json格式的的数组放到最终的json对象中。
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonObj.put("res", "2");
		jsonObj.put("msg", "执行成功");
		return document;
	}
	private List getChildrenByParent(CommonMedol c) throws Exception{
		c.setSql("sysAuthenticationDAO.getAllOrgsUnderMyOrg"); 
		List<CommonMedol> lsTmp= mybatisDaoUtil.getListData(c.getSql(), c); 
		return lsTmp ;
	}
	public List getAllOrgsUnderMyOrgService(CommonMedol c) {
		List ls = new ArrayList();
		try {
			c.setSql("sysAuthenticationDAO.getAllOrgsService");
			List<CommonMedol> ls1 = mybatisDaoUtil.getListData(c.getSql(), c);  
			ls.add(ls1.get(0));
			c.setParam2(ls1.get(0).getParam1());			
			List<CommonMedol> ls2 = getChildrenByParent(c);		 
				for(int i=0;i<ls2.size();i++){
					 ls.add(ls2.get(i));
					 c.setParam2(ls2.get(i).getParam1());
					 ls2 = getChildrenByParent(c);		
					 for(int k=0;k<ls2.size();k++){
						 ls.add(ls2.get(k));
						 c.setParam2(ls2.get(k).getParam1());
						 ls2 = getChildrenByParent(c);	
						 for(int f=0;f<ls2.size();f++){
							 ls.add(ls2.get(f));
							 c.setParam2(ls2.get(f).getParam1());
							 ls2 = getChildrenByParent(c);		
							 for(int h=0;h<ls2.size();h++){
								 ls.add(ls2.get(h)); 
							}
						}
					}
				}
				
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
			return ls ;
	}
 
	public Document createForeignAndAttachmentRelationService(CommonMedol c) {
		Document doc = DocumentHelper.createDocument(); 	
		try { 
			c.setSql("sysAuthenticationDAO.createForeignAndAttachmentRelation");
			doc = mybatisDaoUtil.sysUpdateData(c.getSql(), c);  			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		c.setParam28(PubFun.getSysDateM());
		c.setParam31("createForeignAndAttachmentRelation");
		c.setParam32("附件上传");
		c.setParam29(doc.selectSingleNode("/root/res").getText());
		c.setParam30(doc.selectSingleNode("/root/msg").getText());
		mybatisDaoUtil.userOperateTrail(c);
		return doc;
	}

	@Override
	public List getWtItemsByOrg(CommonMedol c) {
		//据机构查询旗下工种
		List<CommonMedol> ls = new ArrayList<CommonMedol>();
		try {
			c.setSql("sysAuthenticationDAO.getWtItemsByOrg");
			 ls = mybatisDaoUtil.getListData(c.getSql(), c);  
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return ls;
	}

	@Override
	public String getTeachersItemsByOrg(CommonMedol c) {
		// 根据机构查询其下的教师
		List<CommonMedol> lsResault = new ArrayList<CommonMedol>();
		Document document = DocumentHelper.createDocument(); 		
		JSONArray arrayItem = new JSONArray();
		Element rootR = document.addElement("root");  
		try {
			c.setSql("sysAuthenticationDAO.getTeachersItemsByOrg");
			lsResault = mybatisDaoUtil.getListData(c.getSql(), c);
			JSONObject objItem = new JSONObject();
			for(int i=0;i<lsResault.size();i++){
				 objItem = new JSONObject();
				objItem.put ("id",((CommonMedol)lsResault.get(i)).getParam1()); 
				objItem.put ("name",((CommonMedol)lsResault.get(i)).getParam2()+"_"+((CommonMedol)lsResault.get(i)).getParam3()); 
				arrayItem.add(objItem);
			}  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayItem.toJSONString(); 
	}

	@Override
	public Document qrySearchClassInfoByOrgTopTree(CommonMedol c) { 
			Document document = DocumentHelper.createDocument(); 	
			List<CommonMedol> ls = new ArrayList<CommonMedol>();
			Element rootR = document.addElement("root");  
			try {
				c.setSql("sysAuthenticationDAO.qrySearchClassInfoByOrgTopTree");
				ls = mybatisDaoUtil.getListData(c.getSql(), c); 
		 		for(int i=0;i<ls.size();i++){
		 			Element Node= rootR.addElement("node");
		 			Node.addElement("class_name").addText(ls.get(i).getParam1());
		 			Node.addElement("type_name").addText(ls.get(i).getParam2());
		 			Node.addElement("company_name").addText(ls.get(i).getParam3());
		 			Node.addElement("state").addText(ls.get(i).getParam4());
		 			Node.addElement("state_value").addText(ls.get(i).getParam5());
		 			Node.addElement("class_cd").addText(ls.get(i).getParam6()); 
		 		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return document;
	} 
}
