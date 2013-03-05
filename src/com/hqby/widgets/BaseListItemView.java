package com.hqby.widgets;

import org.json.JSONObject;

import android.content.Context;
import android.util.AttributeSet;

import com.hqby.framework.XBaseView;

/**
 * @author JarrahWu
 * listView 的 每个 item 的基类
 */
public abstract class BaseListItemView extends XBaseView{
	
	protected JSONObject mData;
	
	protected int mPosition;
	
	public BaseListItemView(Context context) {
		super(context);
	}

	public BaseListItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}	
	/**
	 * 设置数据
	 * @param data
	 * @param position
	 */
	public void setData(JSONObject data, int position) {
		mData = data;
		mPosition = position;
		onDispatchData(data);
	}

	public int getPosition() {
		// TODO Auto-generated method stub
		return mPosition;
	}
	
	public JSONObject getData() {
		return mData;
	}
	
	/**
	 * 分配数据
	 * @param data
	 */
	public abstract void onDispatchData(JSONObject data);

}
