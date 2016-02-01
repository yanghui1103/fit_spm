package com.bw.fit.common.DaoUtil;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bw.fit.spm.model.Common;

@Transactional
@Repository
public class BusinessDaoUtil {
	private static Log log = LogFactory.getLog(BusinessDaoUtil.class);
	/*@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	 * 该类主要完成， 系统中持久层保存更新数据 还有查询数据
	 */
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public Connection getConnection() {		
		Connection conn = null;
		try {
			conn =  sqlSessionFactory.getConfiguration().getEnvironment().getDataSource().getConnection() ;  ;
			// sqlSessionTemplate.getSqlSessionFactory().getConfiguration().getEnvironment().getDataSource().getConnection();
			if(conn==null){
				log.error("数据库连接出错，请检查");
				return null ;
			}
		} catch (Exception e) {
			log.error("数据库连接出错，请检查");
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * getTheCheckResualt()
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	public Document getTheCheckResault(Common c) {
		Document document = DocumentHelper.createDocument();
		try { 
			Connection conn = getConnection();
			Element rootR = document.addElement("root");
			if (conn == null) {
				rootR.addElement("res").addText("1");
				rootR.addElement("msg").addText("数据库连接失败，请联系系统管理员");
				rootR.addElement("data").addText("-9");
				return document;
			}

			CallableStatement proc = null;
			 proc = conn.prepareCall("call BUSINESS_RULE_CHECK.business_check_enter(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		      proc.setString(1, c.getAccount_id());
		      proc.setString(2,c.getAccount_name());
		      proc.setDouble(3,c.getAccount_fee());
		      proc.setString(4,c.getArea_code());
		      proc.setString(5,c.getArea_name());
		      proc.setString(6,c.getFdid());
		      proc.setString(7,c.getCreate_time());
		      proc.setString(8,c.getVersion_time());
		      proc.setString(9,c.getCreator());
		      proc.setString(10,c.getOperator());
		      proc.setString(11,c.getTemp_str1());
		      proc.setString(12,c.getTemp_str2());
		      proc.setString(13,c.getTemp_str3());
		      proc.setString(14,c.getTemp_str4());
		      proc.setString(15,c.getState());
		      proc.setString(16,c.getIsDeleted());
		      proc.setString(17,c.getCard_id());
		      proc.setString(18,c.getPerson_name());
		      proc.setString(19,c.getCard_code());
		      proc.setString(20,c.getCard_phone());
		      proc.setString(21,c.getCard_sfz());
		      proc.setString(22,c.getCompany_type_id());
		      proc.setString(23,c.getCompany_type_name());
		      proc.setString(24,c.getCompany_id());
		      proc.setString(25,c.getCompany_name());
		      proc.setString(26,c.getCompany_address());
		      proc.setString(27,c.getCompany_admin());
		      proc.setString(28,c.getCompany_admin_phone());
		      proc.setString(29,c.getCompany_level());
		      proc.setString(30,c.getParent_company_id());
		      proc.setString(31,c.getCompany_type_code());
		      proc.setString(32,c.getCompany_area_code());
		      proc.setString(33,c.getFee_use_id());
		      proc.setDouble(34,c.getFee_use_fee());
		      proc.setString(35,c.getFee_use_card());
		      proc.setString(36,c.getFee_usetype_id());
		      proc.setString(37,c.getFee_usetype_name());
		      proc.setString(38,c.getFormula());
		      proc.setString(39,c.getFee_usetype_company());
		      proc.setString(40,c.getFunction_id());
		      proc.setString(41,c.getFunction_name());
		      proc.setString(42,c.getFunction_desp());
		      proc.setString(43,c.getParent_function_id());
		      proc.setString(44,c.getFunction_level());
		      proc.setString(45,c.getAction());
		      proc.setString(46,c.getTarget());
		      proc.setString(47,c.getRel());
		      proc.setString(48,c.getHouse_account_id());
		      proc.setString(49,c.getHouse_account_name());
		      proc.setDouble(50,c.getHouse_account_fee());
		      proc.setString(51,c.getHouse_id());
		      proc.setString(52,c.getHouse_name());
		      proc.setString(53,c.getRole_id());
		      proc.setString(54,c.getRole_name());
		      proc.setString(55,c.getRole_desp());
		      proc.setString(56,c.getStaff_number());
		      proc.setString(57,c.getStaff_name());
		      proc.setString(58,c.getStaff_phone());
		      proc.setString(59,c.getStaff_address());
		      proc.setString(60,c.getStaff_passwd());
		      proc.setString(61,c.getStaff_type_cd());
		      proc.setString(62,c.getVillage_id());
		      proc.setString(63,c.getVillage_name());
		      proc.setString(64,c.getVillage_address());
		      proc.registerOutParameter(65, java.sql.Types.VARCHAR);
		      proc.registerOutParameter(66, java.sql.Types.VARCHAR);
		      proc.setString(67,c.getAction_name());
		      proc.setString(68,c.getSql());
		      
		      /*
		       * 执行
		       */
		      proc.execute();
		      rootR.addElement("res").addText(proc.getString(65));
		      rootR.addElement("msg").addText(proc.getString(66));
			proc.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	/*
	 * 得到一个数据 yangh
	 */
	public Object getOneData(String sql, Object param) {

		Document document = getTheCheckResault((Common) param);
		if (!"2".equals(document.selectSingleNode("root/res").getText())) {
			return null;
		}
		SqlSession session = sqlSessionFactory.openSession();
		Object obj = session.selectOne(sql, param);
		session.close();
		return obj;
	}

	/*
	 * 得到list数据
	 */
	public List getListData(String sql, Object param) throws Exception {
		Document document = getTheCheckResault((Common) param);
		if (!"2".equals(document.selectSingleNode("root/res").getText())) {
			return null;
		}
		SqlSession session = sqlSessionFactory.openSession();
		List ls = session.selectList(sql, param);
		session.close();
		return ls;
	}

	/*
	 * 插入或更新数据 yangh
	 */
	public Document sysUpdateData(String sql, Object param) throws Exception {

		SqlSession session = sqlSessionFactory.openSession();
		Document document = DocumentHelper.createDocument();
		Element rootR = document.addElement("root");
		Document doc = getTheCheckResault((Common) param);
		if (!"2".equals(doc.selectSingleNode("root/res").getText())) {
			return doc;
		}
		try {
			// 开始事物
			int re = session.update(sql, param);
			if (re < 1) {
				// 执行失败
				rootR.addElement("res").addText("1");
				rootR.addElement("msg").addText("执行失败");
				return document;
			} else {
				rootR.addElement("res").addText("2");
				rootR.addElement("msg").addText("执行成功");
				return document;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			rootR.addElement("res").addText("1");
			rootR.addElement("msg").addText("执行异常");
			return document;
		} finally {
			session.close();
		}

	}

	public Document sysDeleteData(String sql, Object param) throws Exception {
		Document doc = getTheCheckResault((Common) param);
		if (!"2".equals(doc.selectSingleNode("root/res").getText())) {
			return doc;
		}
		SqlSession session = sqlSessionFactory.openSession();
		Document document = DocumentHelper.createDocument();

		Element rootR = document.addElement("root");
		try {
			// 开始事物
			int re = session.delete(sql, param);
			if (re < 1) {
				// 执行失败
				rootR.addElement("res").addText("1");
				rootR.addElement("msg").addText("执行失败");
				return document;
			} else {
				rootR.addElement("res").addText("2");
				rootR.addElement("msg").addText("执行成功");
				return document;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			rootR.addElement("res").addText("1");
			rootR.addElement("msg").addText("执行异常");
			return document;
		} finally {
			session.close();
		}

	}

	/**
	 * 将用户本次提交的数据，及反馈数据 日志到库中的用户操作轨迹表
	 * 
	 * @author yh
	 * @throws Exception
	 */
	public void userOperateTrail(Common c) {
		try {
			sysUpdateData("sysAuthenticationDAO.userOperateTrailSql", c);
		} catch (Exception ex) {
			log.info("用户轨迹保存异常");
			ex.printStackTrace();
		}
	}
}
