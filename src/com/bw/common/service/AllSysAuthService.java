package com.bw.common.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;

import com.bw.common.model.CommonMedol;

public interface AllSysAuthService {
	public String getTeachersItemsByOrg(CommonMedol c);
	/*
	 * getWtItemsByOrg
	 * 根据机构查询旗下工种
	 */
	public List getWtItemsByOrg(CommonMedol c);
	/**
	 * 获取用户功能列表
	 */
	public List getThisUserAuthLists(CommonMedol c);
	/**
	 * 获取用户功能列表V2
	 */
	public Document getThisUserAuthListsV2(CommonMedol c);
	public String getThisUserAuthListsV3(CommonMedol c);
	/**
	 * 获取下拉跨的键值对
	 */
	public List getDragWindowList(CommonMedol c);
	/*
	 * getRoleExistAndNoexitAuthList
	 * 获取已经存在和不存的功能树
	 */
	public String getRoleExistAndNoexitAuthList(CommonMedol c);
	
	public List getUserListUnderTheStation(CommonMedol c);
	/*
	 * 创建角色
	 */
	public Document createNewRoleService(CommonMedol c);
	// 新建岗位
	public Document createNewPositionService(CommonMedol c);
	/*
	 * getAllOrgsService
	 */
	public List getAllOrgsService(CommonMedol c);
	/*
	 * getOrgInfoTreeService
	 * 获取所有机构信息
	 */
	public List getOrgInfoTreeService(CommonMedol c);
	/*
	 * getThisRoleAuthTreeJsonService
	 */
	public List getThisRoleAuthTreeJsonService(CommonMedol c);
	/*
	 * giveAuthorityToRoleService赋权
	 */
	public Document giveAuthorityToRoleService(CommonMedol c);
	/*
	 * getAuthorityBtnsByThisUserService
	 */
	public String getAuthorityBtnsByThisUserService(CommonMedol c);
	public Document qrySearchClassInfoByOrgTopTree(CommonMedol c);
	
	public Document createNewSysUserService(CommonMedol c);
	/*
	 * getUserInfosByUserIdService 根据userid查询资料
	 */
	public Document getUserInfosByUserIdService(CommonMedol c);
	// updateUserInfosByUserIdService 更新用户资料
	public Document updateUserInfosByUserIdService(CommonMedol c);
	//createNewSysOrgService 新建组织
	public Document createNewSysOrgService(CommonMedol c);
	// getOrgInfosByIdService 查询机构资料单笔
	public Document getOrgInfosByIdService(CommonMedol c);
	//updateOrgInfosByOrgIdService 更新机构资料
	public Document updateOrgInfosByOrgIdService(CommonMedol c); 
	//qrySearchOrgUserTopListService 
	public Document qrySearchOrgUserTopListService(CommonMedol c);
	
	public List getAllOrgsUnderMyOrgService(CommonMedol c);
	//
	public Document qrySearchOrgUserTopListByKeyWordsService(CommonMedol c);
	//qrySearchStaffInfoByOrgTopTreeService
	public Document qrySearchStaffInfoByOrgTopTreeService(CommonMedol c);
	// 用户登录
	public Document userLoginService(CommonMedol c,HttpSession session);
	// getPositionListService
	public Document getPositionListService(CommonMedol c);
	//getPositionInfosByIdService 根据ID查询岗位信息
	public Document getPositionInfosByIdService(CommonMedol c);
	// 更新岗位说明
	public Document updatePositionInfosService(CommonMedol c);
	// getFileListByForeginIdService 查询出所有文件
	public Document getFileListByForeginIdService(CommonMedol c);
	// 外键与附件关联
	public Document createForeignAndAttachmentRelationService(CommonMedol c);
}
