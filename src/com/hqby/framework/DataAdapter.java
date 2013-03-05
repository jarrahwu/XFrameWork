package com.hqby.framework;

import org.json.JSONArray;

import com.hqby.widgets.BaseListItemView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @author jarrahwu
 * 使用复用的适配器
 */
public abstract class DataAdapter extends BaseAdapter{
	
	protected Context mContext;
	
	protected JSONArray mDataSource;
	
	public DataAdapter(Context context){
		mContext = context;
	}

	public int getCount() {
		return mDataSource == null ? 0 : mDataSource.length();
	}

	public Object getItem(int position) {

		return position;
	}

	public long getItemId(int position) {

		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		BaseListItemView itemView;
		if (convertView == null) {
			itemView = instanceItemView();
		}else {
			itemView = (BaseListItemView)convertView;
		}
		//放置数据
		itemView.setData(JSONUtil.getJsonObjByIndex(mDataSource, position), position);
		//设置item
		configureItem(itemView);
		
		return itemView;
	}
	
	
	/**
	 * 配置item 你可以设置每个item的点击事件什么的，都可以
	 * @param itemView
	 */
	public abstract void configureItem(BaseListItemView itemView);
	
	/**
	 * 初始化你的list中每个Item
	 * @return 返回你每个item的实例化的view (这个view要继承BaseListItemView);
	 */
	public abstract BaseListItemView instanceItemView();


	public void setData(JSONArray jsonArray) {
		mDataSource = jsonArray;
		notifyDataSetChanged();
	}
	
}
