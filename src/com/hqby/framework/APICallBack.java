package com.hqby.framework;


import org.json.JSONObject;

/**
 * @author JarrahWu
 *	api回调
 */
public interface APICallBack {
	
	/**
	 * 成功回调
	 * @param obj
	 */
	public void callBack(JSONObject obj);
	
	/**
	 * 出错
	 * @param httpCode
	 */
	public void onError(int httpCode);
}
