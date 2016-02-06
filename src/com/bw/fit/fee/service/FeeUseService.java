package com.bw.fit.fee.service;

import org.json.simple.JSONObject;

import com.bw.fit.spm.model.Common;

public interface FeeUseService {
	/**
	 * 机构与兑换关联
	 */
	public JSONObject createCompanyFeeUse(Common c);
}
