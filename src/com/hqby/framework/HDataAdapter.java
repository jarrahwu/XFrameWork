package com.hqby.framework;

import com.hqby.widgets.BaseListItemView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author jarrahwu
 * 使用ViewHolder的适配器
 */
public abstract class HDataAdapter extends DataAdapter{

	public HDataAdapter(Context context) {
		super(context);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = instanceItemView();
			viewHolder.baseListItemView = (BaseListItemView) convertView;
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.baseListItemView.setData(JSONUtil.getJsonObjByIndex(mDataSource, position), position);
		configureItem(viewHolder.baseListItemView);
		return convertView;
	}
	
	static class ViewHolder {
		BaseListItemView baseListItemView;
	}

}
