package com.hqby.test;

import org.json.JSONObject;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.hqby.framework.JSONUtil;
import com.hqby.widgets.AutoFitImageView;
import com.hqby.widgets.BaseListItemView;

public class CommentItemView extends BaseListItemView{
	
	AutoFitImageView mImageView;
	
	TextView mTextView;
	
	TextView mId;
	
	TextView mContent;
	
	public CommentItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CommentItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setupViews() {
		setContentView(R.layout.comment_item_view);
		mImageView = (AutoFitImageView) findViewById(R.id.comment_icon);
		mTextView = findTextView(R.id.comment_nick);
		mId = findTextView(R.id.comment_time);
		mContent = findTextView(R.id.comment_content);
	}

	@Override
	public void onDispatchData(JSONObject data) {
		JSONObject img = JSONUtil.getJsonObject(data, "image");
		ajaxImage(mImageView, JSONUtil.getString(img, "src"));
		mTextView.setText(JSONUtil.getString(data, "user_nick"));
		mId.setText(JSONUtil.getString(data, "_id"));
		mContent.setText( "" + JSONUtil.getInt(data, "comment"));
	}

}
