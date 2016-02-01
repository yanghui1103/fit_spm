package com.bw.fit.spm.model;

import java.util.Date;

public class Common {

	private String account_id;
	private String account_name;
	private double account_fee;
	private String area_code;
	private String area_name;
	public String fdid;
	public String create_time ;
	public String version_time;
	public String creator	;
	public String operator;
	private String temp_str1;
	private String temp_str2;
	private String temp_str3;
	private String temp_str4;
	private String action_name; 
	
	private String sql;
	public String state;
	public String isDeleted;
	private String card_id;
	private String person_name;
	private String card_code;
	private String card_phone;
	private String card_sfz;
	private String company_type_id;
	private String  company_type_name;
	private String company_id;
	private String company_name;
	private String company_address;
	private String company_admin;
	private String company_admin_phone;
	private String company_level;
	private String parent_company_id;
	private String company_type_code;
	private String company_area_code;
	private String Fee_use_id;
	private double Fee_use_fee;
	private String Fee_use_card;
	private String fee_usetype_id;
	private String fee_usetype_name;
	private String formula;
	private String fee_usetype_company;
	private String function_id;
	private String function_name;
	private String function_desp;
	private String parent_function_id;
	private String function_level;
	private String action;
	private String target;
	private String rel;

	private String house_account_id;
	private String house_account_name;
	private double house_account_fee;
	private String house_id;
	private String house_name;
	private String role_id;
	private String role_name;
	private String role_desp ;
	private String staff_number ;
	private String staff_name ;
	private String staff_phone ;
	private String staff_address ;
	private String staff_passwd ;
	private String staff_type_cd;
	private String village_id;
	private String village_name;
	private String village_address;
	
	public String getSql() {
		return this.sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public double getAccount_fee() {
		return account_fee;
	}
	public void setAccount_fee(double account_fee) {
		this.account_fee = account_fee;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public String getFdid() {
		return fdid;
	}
	public void setFdid(String fdid) {
		this.fdid = fdid;
	}
	
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getVersion_time() {
		return version_time;
	}
	public void setVersion_time(String version_time) {
		this.version_time = version_time;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public String getCard_code() {
		return card_code;
	}
	public void setCard_code(String card_code) {
		this.card_code = card_code;
	}
	public String getCard_phone() {
		return card_phone;
	}
	public void setCard_phone(String card_phone) {
		this.card_phone = card_phone;
	}
	public String getCard_sfz() {
		return card_sfz;
	}
	public void setCard_sfz(String card_sfz) {
		this.card_sfz = card_sfz;
	}
	public String getCompany_type_id() {
		return company_type_id;
	}
	public void setCompany_type_id(String company_type_id) {
		this.company_type_id = company_type_id;
	}
	public String getCompany_type_name() {
		return company_type_name;
	}
	public void setCompany_type_name(String company_type_name) {
		this.company_type_name = company_type_name;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getCompany_admin() {
		return company_admin;
	}
	public void setCompany_admin(String company_admin) {
		this.company_admin = company_admin;
	}
	public String getCompany_admin_phone() {
		return company_admin_phone;
	}
	public void setCompany_admin_phone(String company_admin_phone) {
		this.company_admin_phone = company_admin_phone;
	}
	public String getCompany_level() {
		return company_level;
	}
	public void setCompany_level(String company_level) {
		this.company_level = company_level;
	}
	public String getParent_company_id() {
		return parent_company_id;
	}
	public void setParent_company_id(String parent_company_id) {
		this.parent_company_id = parent_company_id;
	}
	public String getCompany_type_code() {
		return company_type_code;
	}
	public void setCompany_type_code(String company_type_code) {
		this.company_type_code = company_type_code;
	}
	public String getCompany_area_code() {
		return company_area_code;
	}
	public void setCompany_area_code(String company_area_code) {
		this.company_area_code = company_area_code;
	}
	public String getFee_use_id() {
		return Fee_use_id;
	}
	public void setFee_use_id(String fee_use_id) {
		Fee_use_id = fee_use_id;
	}
	public double getFee_use_fee() {
		return Fee_use_fee;
	}
	public void setFee_use_fee(double fee_use_fee) {
		Fee_use_fee = fee_use_fee;
	}
	public String getFee_use_card() {
		return Fee_use_card;
	}
	public void setFee_use_card(String fee_use_card) {
		Fee_use_card = fee_use_card;
	}
	public String getFee_usetype_id() {
		return fee_usetype_id;
	}
	public void setFee_usetype_id(String fee_usetype_id) {
		this.fee_usetype_id = fee_usetype_id;
	}
	public String getFee_usetype_name() {
		return fee_usetype_name;
	}
	public void setFee_usetype_name(String fee_usetype_name) {
		this.fee_usetype_name = fee_usetype_name;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getAction_name() {
		return action_name;
	}
	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}
	public String getFee_usetype_company() {
		return fee_usetype_company;
	}
	public void setFee_usetype_company(String fee_usetype_company) {
		this.fee_usetype_company = fee_usetype_company;
	}
	public String getFunction_id() {
		return function_id;
	}
	public void setFunction_id(String function_id) {
		this.function_id = function_id;
	}
	public String getFunction_name() {
		return function_name;
	}
	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}
	public String getFunction_desp() {
		return function_desp;
	}
	public void setFunction_desp(String function_desp) {
		this.function_desp = function_desp;
	}
	public String getParent_function_id() {
		return parent_function_id;
	}
	public void setParent_function_id(String parent_function_id) {
		this.parent_function_id = parent_function_id;
	}
	public String getFunction_level() {
		return function_level;
	}
	public void setFunction_level(String function_level) {
		this.function_level = function_level;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getHouse_account_id() {
		return house_account_id;
	}
	public void setHouse_account_id(String house_account_id) {
		this.house_account_id = house_account_id;
	}
	public String getHouse_account_name() {
		return house_account_name;
	}
	public void setHouse_account_name(String house_account_name) {
		this.house_account_name = house_account_name;
	}
	public double getHouse_account_fee() {
		return house_account_fee;
	}
	public void setHouse_account_fee(double house_account_fee) {
		this.house_account_fee = house_account_fee;
	}
	public String getHouse_id() {
		return house_id;
	}
	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}
	public String getHouse_name() {
		return house_name;
	}
	public void setHouse_name(String house_name) {
		this.house_name = house_name;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_desp() {
		return role_desp;
	}
	public void setRole_desp(String role_desp) {
		this.role_desp = role_desp;
	}
	public String getStaff_number() {
		return staff_number;
	}
	public void setStaff_number(String staff_number) {
		this.staff_number = staff_number;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getStaff_phone() {
		return staff_phone;
	}
	public void setStaff_phone(String staff_phone) {
		this.staff_phone = staff_phone;
	}
	public String getStaff_address() {
		return staff_address;
	}
	public void setStaff_address(String staff_address) {
		this.staff_address = staff_address;
	}
	public String getStaff_passwd() {
		return staff_passwd;
	}
	public void setStaff_passwd(String staff_passwd) {
		this.staff_passwd = staff_passwd;
	}
	public String getStaff_type_cd() {
		return staff_type_cd;
	}
	public void setStaff_type_cd(String staff_type_cd) {
		this.staff_type_cd = staff_type_cd;
	}
	public String getVillage_id() {
		return village_id;
	}
	public void setVillage_id(String village_id) {
		this.village_id = village_id;
	}
	public String getVillage_name() {
		return village_name;
	}
	public void setVillage_name(String village_name) {
		this.village_name = village_name;
	}
	public String getVillage_address() {
		return village_address;
	}
	public void setVillage_address(String village_address) {
		this.village_address = village_address;
	}
	public String getTemp_str1() {
		return temp_str1;
	}
	public void setTemp_str1(String temp_str1) {
		this.temp_str1 = temp_str1;
	}
	public String getTemp_str2() {
		return temp_str2;
	}
	public void setTemp_str2(String temp_str2) {
		this.temp_str2 = temp_str2;
	}
	public String getTemp_str3() {
		return temp_str3;
	}
	public void setTemp_str3(String temp_str3) {
		this.temp_str3 = temp_str3;
	}
	public String getTemp_str4() {
		return temp_str4;
	}
	public void setTemp_str4(String temp_str4) {
		this.temp_str4 = temp_str4;
	}
	
}
