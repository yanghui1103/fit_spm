package com.bw.fit.spm.card.service;

import org.json.simple.JSONObject;

import com.bw.fit.spm.model.Common;

public interface CardService {
	
	public JSONObject createSingleConsumeRecord(Common c);
	public JSONObject getPersonInfoByCard(Common c);
	public JSONObject qryCardConsumeRecords(Common c);
	public JSONObject changePersonCard(Common c);
	public JSONObject getFdIdByCardCode(Common c);
	public JSONObject qryConsumeList(Common c);
	public JSONObject qryCardConsumeRecords2(Common c);
}	
