package com.hqby.framework;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

/**
 * @author jarrahwu
 * AQ的各种请求调用
 */
public class AQHttpManager {

	private AQuery mAq;

	public AQHttpManager(AQuery aq) {
		mAq = aq;
	}

	/**
	 * 调用AQ的post方法
	 * @param data
	 * @param url
	 * @param cb
	 */
	public void post(HashMap<String, Object> data, String url, final APICallBack cb, String cookie) {
		JSONObject object = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		StringEntity entity;
		try {
			object = putData(object, data);
			entity = new StringEntity(object.toString(), "utf-8");
			params.put(AQuery.POST_ENTITY, entity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//cookie为空的时候不处理
		if (cookie == null || cookie.length() == 0) {
			mAq.ajax(url, params, JSONObject.class, new AQAjaxCallBack(cb));
		}else {
			mAq.ajax(url, params, JSONObject.class, new AQAjaxCallBack(cb).cookie("token", cookie));
		}
		
	}
	
	/**
	 * 调用AQ的get方法
	 * @param url
	 * @param cb
	 */
	public void get(String url, final APICallBack cb) {
		mAq.ajax(url, JSONObject.class, new AQAjaxCallBack(cb));
	}
	
	public void put() {
		
	}
	
	public void delete() {
		
	}
	
	/**
	 * 把参数放进jsonobject里面并作为http参数调用网络借口
	 * @param obj 需要放参数的jsonobject
	 * @param data 参数
	 * @return 放好参数的json object
	 */
	private JSONObject putData(JSONObject obj,HashMap<String, Object> data){
		//遍历map 并 设置参数
		Iterator<Entry<String, Object>> iter = data.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iter.next();
			try {
				obj.put(entry.getKey(), entry.getValue());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	

	
	/**
	 * @author jarrahwu
	 * 将AQ的callback进一步封装,不用在处理code的各种情况
	 */
	private class AQAjaxCallBack extends AjaxCallback<JSONObject> {
		
		private APICallBack cb;

		public AQAjaxCallBack(APICallBack cb) {
			this.cb = cb;
		}
		
		@Override
		public void callback(String url, JSONObject object, AjaxStatus status) {
			if (status.getCode() >= 200 && status.getCode() < 300) {
				cb.callBack(object);
			} else {
				cb.onError(status.getCode());
			}
			super.callback(url, object, status);
		}
	}
}
