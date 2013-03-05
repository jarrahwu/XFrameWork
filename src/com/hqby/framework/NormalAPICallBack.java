package com.hqby.framework;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

/**
 * @author JarrahWu
 * 普通的Tapi回调，已经处理好失败的情况
 */
public abstract class NormalAPICallBack implements APICallBack {
	
	/**
	 * 调用API的名字
	 */
	private String mOperationName;

	private Context mContext;
	
	/**
	 * @param context
	 * @param operationName 操作名字。可以随便填
	 */
	public NormalAPICallBack(Context context,String operationName) {
		mContext = context;
		mOperationName = operationName;
	}
	
	public NormalAPICallBack(Context context){
		mContext = context;
		mOperationName = "default operation";
	}
	
	public void callBack(JSONObject obj) {
		handleCallBack(obj);
	}


	public void onError(int httpCode) {
		if (httpCode == 403) {
			UICore.getInstance().makeToast(mContext, "数据授权失败,请重新登陆.");
		} else if (httpCode == -101) {
			//如果多次-101请检查AndroidManifest有没有网络权限
			UICore.getInstance().makeToast(mContext, "你的网络不给力");
		}else if(httpCode >= 500 && httpCode < 600){
			UICore.getInstance().makeToast(mContext, "服务器有异常,请稍后再试.");
		}else {
			Log.e("Call back fail", "" + httpCode);
			UICore.getInstance().makeToast(mContext, mOperationName + "失败.");
		}
	}
	
	/**
	 * 处理回调
	 * @param obj
	 */
	public abstract void handleCallBack(JSONObject obj);

}
