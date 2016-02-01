package com.bw.common.model;

public class LoginUser {
	private String user_cd ;
	private String user_name ;
	private String user_phone;
	private String org_cd;
	private String org_name ;
	private String parent_org_cd ;
	public LoginUser(){}
	public String getUser_cd() {
		return user_cd;
	}
	public void setUser_cd(String user_cd) {
		this.user_cd = user_cd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getOrg_cd() {
		return org_cd;
	}
	public void setOrg_cd(String org_cd) {
		this.org_cd = org_cd;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getParent_org_cd() {
		return parent_org_cd;
	}
	public void setParent_org_cd(String parent_org_cd) {
		this.parent_org_cd = parent_org_cd;
	}
}
