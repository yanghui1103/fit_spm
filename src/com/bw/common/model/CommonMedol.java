package com.bw.common.model;

import java.util.List;

public class CommonMedol {  
	/*
	 * button,company
	 * staff,card_id（身份证）
	 * 信息为在存储过程中
	 * 使用，可以在一个口径下校验
	 * 的阻止--1，提示--3规则
	 */
	private String button_id;
	private String sql ;
	private String company_number ;
	private String staff_number ;
	private String card_id ;
	private String proc_name ;
	private String action_name;
	
	public String getAction_name() {
		if("".equals(action_name)||action_name==null)
			return "-9";
		return action_name;
	}
	public void setAction_name(String action_name) {
		if("".equals(action_name)||action_name==null)
			this.action_name = "-9";
		this.action_name = action_name;
	}
	public String getProc_name() {
		return proc_name;
	}
	public void setProc_name(String proc_name) {
		this.proc_name = proc_name;
	}
	private String param1 ;
	private String param2 ;
	private String param3 ;
	private String param4 ;
	private String param5 ;
	private String param6 ;
	private String param7 ;
	private String param8 ;
	private String param9 ;
	private String param10 ;
	private String param11 ;
	private String param12 ;
	private String param13 ;
	private String param14 ;
	private String param15 ;
	private String param16 ;
	private String param17 ;
	private String param18 ;
	private String param19 ;
	private String param20 ;
	private String param21 ;
	private String param22 ;
	private String param23 ;
	private String param24 ;
	private String param25 ;
	private String param26 ;
	private String param27 ;
	private String param28 ;
	private String param29 ;
	private String param30 ;
	private String param31 ;
	private String param32 ;
	private String param33 ;
	private String param34 ;
	private String param35 ;
	private String param36 ;
	private int param37;
	private int param38 ;
	public int getParam37() { 
		return param37;
	}
	public void setParam37(int param37) {
		this.param37 = param37;
	}
	public int getParam38() {
		return param38;
	}
	public void setParam38(int param38) {
		this.param38 = param38;
	}
	public int getParam39() {
		return param39;
	}
	public void setParam39(int param39) {
		this.param39 = param39;
	}
	public int getParam40() {
		return param40;
	}
	public void setParam40(int param40) {
		this.param40 = param40;
	}
	private int param39;
	private int param40;
	
	public String getParam23() {
		if("".equalsIgnoreCase(param23)||param23 ==null) 
			return "-9" ;
		return param23;
	}
	public void setParam23(String param23) {
		if("".equalsIgnoreCase(param23)||param23 ==null) 
			this.param23="-9";
		this.param23 = param23;
	}
	public String getParam24() {
		if("".equalsIgnoreCase(param24)||param24 ==null) 
			return "-9" ;
		return param24;
	}
	public void setParam24(String param24) {
		if("".equalsIgnoreCase(param24)||param24 ==null) 
			this.param24="-9";
		this.param24 = param24;
	}
	public String getParam25() {
		if("".equalsIgnoreCase(param25)||param25 ==null) 
			return "-9" ;
		return param25;
	}
	public void setParam25(String param25) {
		if("".equalsIgnoreCase(param25)||param25 ==null) 
			this.param25="-9";
		this.param25 = param25;
	}
	public String getParam26() {
		if("".equalsIgnoreCase(param26)||param26 ==null) 
			return "-9" ;
		return param26;
	}
	public void setParam26(String param26) {
		if("".equalsIgnoreCase(param26)||param26 ==null) 
			this.param26="-9";
		this.param26 = param26;
	}
	public String getParam27() {
		if("".equalsIgnoreCase(param27)||param27 ==null) 
			return "-9" ;
		return param27;
	}
	public void setParam27(String param27) {
		this.param27 = param27;
	}
	public String getParam28() {
		if("".equalsIgnoreCase(param28)||param28 ==null) 
			return "-9" ;
		return param28;
	}
	public void setParam28(String param28) {
		this.param28 = param28;
	}
	public String getParam29() {
		if("".equalsIgnoreCase(param29)||param29 ==null) 
			return "-9" ;
		return param29;
	}
	public void setParam29(String param29) {
		this.param29 = param29;
	}
	public String getParam30() {
		if("".equalsIgnoreCase(param30)||param30 ==null) 
			return "-9" ;
		return param30;
	}
	public void setParam30(String param30) {
		this.param30 = param30;
	}
	public String getParam31() {
		if("".equalsIgnoreCase(param31)||param31 ==null) 
			return "-9" ;
		return param31;
	}
	public void setParam31(String param31) {
		this.param31 = param31;
	}
	public String getParam32() {
		if("".equalsIgnoreCase(param32)||param32 ==null) 
			return "-9" ;
		return param32;
	}
	public void setParam32(String param32) {
		this.param32 = param32;
	}
	public String getParam33() {
		if("".equalsIgnoreCase(param33)||param33 ==null) 
			return "-9" ;
		return param33;
	}
	public void setParam33(String param33) {
		this.param33 = param33;
	}
	public String getParam34() {
		if("".equalsIgnoreCase(param34)||param34 ==null) 
			return "-9" ;
		return param34;
	}
	public void setParam34(String param34) {
		this.param34 = param34;
	}
	public String getParam35() {
		if("".equalsIgnoreCase(param35)||param35 ==null) 
			return "-9" ;
		return param35;
	}
	public void setParam35(String param35) {
		this.param35 = param35;
	}
	public String getParam36() {
		if("".equalsIgnoreCase(param36)||param36 ==null) 
			return "-9" ;
		return param36;
	}
	public void setParam36(String param36) {
		this.param36 = param36;
	}
	public String getParam16() {
		if("".equalsIgnoreCase(param16)||param16 ==null) 
			return "-9" ;
		return param16;
	}
	public void setParam16(String param16) {
		if("".equalsIgnoreCase(param16)||param16 ==null) 
			this.param16="-9";
		this.param16 = param16;
	}
	public CommonMedol(){}
	public String getParam1() {
		if("".equalsIgnoreCase(param1)||param1 ==null) 
			return "-9" ;
		return param1;
	}
	public void setParam1(String param1) {
		if("".equalsIgnoreCase(param1)||param1 ==null) 
			this.param1="-9";
		this.param1 = param1;
	}
	public String getParam2() {
		if("".equalsIgnoreCase(param2)||param2 ==null) 
			return "-9" ;
		return param2;
	}
	public void setParam2(String param2) {
		if("".equalsIgnoreCase(param2)||param2 ==null) 
			this.param2="-9";
		this.param2 = param2;
	}
	public String getParam3() {
		if("".equalsIgnoreCase(param3)||param3 ==null) 
			return "-9" ;
		return param3;
	}
	public void setParam3(String param3) {
		if("".equalsIgnoreCase(param3)||param3 ==null) 
			this.param3="-9";
		this.param3 = param3;
	}
	public String getParam4() {
		if("".equalsIgnoreCase(param4)||param4 ==null) 
			return "-9" ;
		return param4;
	}
	public void setParam4(String param4) {
		if("".equalsIgnoreCase(param4)||param4 ==null) 
			this.param4="-9";
		this.param4 = param4;
	}
	public String getParam5() {
		if("".equalsIgnoreCase(param5)||param5 ==null) 
			return "-9" ;
		return param5;
	}
	public void setParam5(String param5) {
		if("".equalsIgnoreCase(param5)||param5 ==null) 
			this.param5="-9";
		this.param5 = param5;
	}
	public String getParam6() {
		if("".equalsIgnoreCase(param6)||param6 ==null) 
			return "-9" ;
		return param6;
	}
	public void setParam6(String param6) {
		if("".equalsIgnoreCase(param6)||param6 ==null) 
			this.param6="-9";
		this.param6 = param6;
	}
	public String getParam7() {
		if("".equalsIgnoreCase(param7)||param7 ==null) 
			return "-9" ;
		return param7;
	}
	public void setParam7(String param7) {
		if("".equalsIgnoreCase(param7)||param7 ==null) 
			this.param7="-9";
		this.param7 = param7;
	}
	public String getParam8() {
		if("".equalsIgnoreCase(param8)||param8 ==null) 
			return "-9" ;
		return param8;
	}
	public void setParam8(String param8) {
		if("".equalsIgnoreCase(param8)||param8 ==null) 
			this.param8="-9";
		this.param8 = param8;
	}
	public String getParam9() {
		if("".equalsIgnoreCase(param9)||param9 ==null) 
			return "-9" ;
		return param9;
	}
	public void setParam9(String param9) {
		if("".equalsIgnoreCase(param9)||param9 ==null) 
			this.param9="-9";
		this.param9 = param9;
	}
	public String getParam10() {
		if("".equalsIgnoreCase(param10)||param10 ==null) 
			return "-9" ;
		return param10;
	}
	public void setParam10(String param10) {
		if("".equalsIgnoreCase(param10)||param10 ==null) 
			this.param10="-9";
		this.param10 = param10;
	}
	public String getParam11() {
		if("".equalsIgnoreCase(param11)||param11 ==null) 
			return "-9" ;
		return param11;
	}
	public void setParam11(String param11) {
		if("".equalsIgnoreCase(param11)||param11 ==null) 
			this.param11="-9";
		this.param11 = param11;
	}
	public String getParam12() {
		if("".equalsIgnoreCase(param12)||param12 ==null) 
			return "-9" ;
		return param12;
	}
	public void setParam12(String param12) {
		if("".equalsIgnoreCase(param12)||param12 ==null) 
			this.param12="-9";
		this.param12 = param12;
	}
	public String getParam13() {
		if("".equalsIgnoreCase(param13)||param13 ==null) 
			return "-9" ;
		return param13;
	}
	public void setParam13(String param13) {
		if("".equalsIgnoreCase(param13)||param13 ==null) 
			this.param13="-9";
		this.param13 = param13;
	}
	public String getParam14() {
		if("".equalsIgnoreCase(param14)||param14 ==null) 
			return "-9" ;
		return param14;
	}
	public void setParam14(String param14) {
		if("".equalsIgnoreCase(param14)||param14 ==null) 
			this.param14="-9";
		this.param14 = param14;
	}
	public String getParam15() {
		if("".equalsIgnoreCase(param15)||param15 ==null) 
			return "-9" ;
		return param15;
	}
	public void setParam15(String param15) {
		if("".equalsIgnoreCase(param15)||param15 ==null) 
			this.param15="-9";
		this.param15 = param15;
	}
	public String getParam17() {
		if("".equalsIgnoreCase(param17)||param17 ==null) 
			return "-9" ;
		return param17;
	}
	public void setParam17(String param17) {
		if("".equalsIgnoreCase(param17)||param17 ==null) 
			this.param17="-9";
		this.param17 = param17;
	}
	public String getParam18() {
		if("".equalsIgnoreCase(param18)||param18 ==null) 
			return "-9" ;
		return param18;
	}
	public void setParam18(String param18) {
		if("".equalsIgnoreCase(param18)||param18 ==null) 
			this.param18="-9";
		this.param18 = param18;
	}
	public String getParam19() {
		if("".equalsIgnoreCase(param19)||param19 ==null) 
			return "-9" ;
		return param19;
	}
	public void setParam19(String param19) {
		if("".equalsIgnoreCase(param19)||param19 ==null) 
			this.param19="-9";
		this.param19 = param19;
	}
	public String getParam20() {
		if("".equalsIgnoreCase(param20)||param20 ==null) 
			return "-9" ;
		return param20;
	}
	public void setParam20(String param20) {
		if("".equalsIgnoreCase(param20)||param20 ==null) 
			this.param20="-9";
		this.param20 = param20;
	}
	public String getButton_id() {
		if("".equalsIgnoreCase(button_id)||button_id ==null) 
			return "-9" ;
		return button_id;
	}
	public void setButton_id(String button_id) {
		this.button_id = button_id;
	}


	public String getSql() {
		if("".equalsIgnoreCase(sql)||sql ==null) 
			return "-9" ;
		return sql;
	}
	public void setSql(String sql) {
		if("".equalsIgnoreCase(sql)||sql ==null) 
			this.sql="-9";
		this.sql = sql;
	}
	public String getCompany_number() {
		if("".equalsIgnoreCase(company_number)||company_number ==null) 
			return "-9" ;
		return company_number;
	}
	public void setCompany_number(String company_number) {
		if("".equalsIgnoreCase(company_number)||company_number ==null) 
			this.company_number="-9";
		this.company_number = company_number;
	}
	public String getStaff_number() {
		if("".equalsIgnoreCase(staff_number)||staff_number ==null) 
			return "-9" ;
		return staff_number;
	}
	public void setStaff_number(String staff_number) {
		if("".equalsIgnoreCase(staff_number)||staff_number ==null) 
			this.staff_number = "-9" ;
		this.staff_number = staff_number;
	}
	public String getCard_id() {
		if("".equalsIgnoreCase(card_id)||card_id ==null) 
			return "-9" ;
		return card_id;
	}
	public void setCard_id(String card_id) {
		if("".equalsIgnoreCase(card_id)||card_id ==null) 
			this.card_id="-9";
		this.card_id = card_id;
	}
	public String getParam21() {
		if("".equalsIgnoreCase(param21)||param21 ==null) 
			return "-9";
		return param21;
	}
	public void setParam21(String param21) {
		if("".equalsIgnoreCase(param21)||param21 ==null) 
			this.param21 = "-9" ;
		this.param21 = param21;
	}
	public String getParam22() {
		if("".equalsIgnoreCase(param22)||param22 ==null) 
			return  "-9" ;
		return param22;
	}
	public void setParam22(String param22) {
		if("".equalsIgnoreCase(param22)||param22 ==null) 
			this.param22 = "-9" ;
		this.param22 = param22;
	}
}
