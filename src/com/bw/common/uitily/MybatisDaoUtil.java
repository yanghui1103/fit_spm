package com.bw.common.uitily;

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

import com.bw.common.model.CommonMedol;
import com.bw.fit.spm.model.Common;

@Transactional
@Repository
public class MybatisDaoUtil {
	private static Log log = LogFactory.getLog(MybatisDaoUtil.class);
	/*
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	*
	 * 该类主要完成， 系统中持久层保存更新数据 还有查询数据
	 */

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn =  sqlSessionFactory.getConfiguration().getEnvironment().getDataSource().getConnection() ; 
			// sqlSessionTemplate.getSqlSessionFactory().getConfiguration()
			//		.getEnvironment().getDataSource().getConnection();
			log.info(conn);
			if (conn == null) {
				log.error("数据库连接出错，请检查");
				return null;
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
	public Document getTheCheckResault(CommonMedol c) {
		Document document = DocumentHelper.createDocument();
		try {
			Connection conn = getConnection();
			Element rootR = document.addElement("root");
			if (conn == null) {
				rootR.addElement("res").addText("1");
				rootR.addElement("msg").addText("数据库连接失败，请联系系统管理员");
				return document;
			}

			CallableStatement proc = null;
			proc = conn
					.prepareCall("call BUSINESS_RULE_CHECK.system_check_enter(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			proc.setString(1, c.getParam1());
			proc.setString(2, c.getParam2());
			proc.setString(3, c.getParam3());
			proc.setString(4, c.getParam4());
			proc.setString(5, c.getParam5());
			proc.setString(6, c.getParam6());
			proc.setString(7, c.getParam7());
			proc.setString(8, c.getParam8());
			proc.setString(9, c.getParam9());
			proc.setString(10, c.getParam10());
			proc.setString(11, c.getParam11());
			proc.setString(12, c.getParam12());
			proc.setString(13, c.getParam13());
			proc.setString(14, c.getParam14());
			proc.setString(15, c.getParam15());
			proc.setString(16, c.getParam16());
			proc.setString(17, c.getParam17());
			proc.setString(18, c.getParam18());
			proc.setString(19, c.getParam19());
			proc.setString(20, c.getParam20());
			proc.setString(21, c.getParam21());
			proc.setString(22, c.getParam22());
			proc.setString(23, c.getParam23());
			proc.setString(24, c.getParam24());
			proc.setString(25, c.getParam25());
			proc.setString(26, c.getParam26());
			proc.setString(27, c.getParam27());
			proc.setString(28, c.getParam28());
			proc.setString(29, c.getParam29());
			proc.setString(30, c.getParam30());
			proc.setString(31, c.getParam31());
			proc.setString(32, c.getParam32());
			proc.setString(33, c.getParam33());
			proc.setString(34, c.getParam34());
			proc.setString(35, c.getParam35());

			proc.registerOutParameter(36, java.sql.Types.VARCHAR);
			proc.registerOutParameter(37, java.sql.Types.VARCHAR);
			/*
			 * 执行
			 */
			proc.execute();
			rootR.addElement("res").addText(proc.getString(36));
			rootR.addElement("msg").addText(proc.getString(37));
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

		Document document = getTheCheckResault((CommonMedol) param);
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
		Document document = getTheCheckResault((CommonMedol) param);
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
		Document doc = getTheCheckResault((CommonMedol) param);
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
		Document doc = getTheCheckResault((CommonMedol) param);
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
	public void userOperateTrail(CommonMedol c) {
		try {
			sysUpdateData("sysAuthenticationDAO.userOperateTrailSql", c);
		} catch (Exception ex) {
			log.info("用户轨迹保存异常");
			ex.printStackTrace();
		}
	}
}
