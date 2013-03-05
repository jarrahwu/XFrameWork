package com.hqby.framework;

import android.content.Context;
import android.widget.Toast;

public class UICore {

	
	private static UICore mInstance;

	public static UICore getInstance() {
		if (mInstance == null) {
			mInstance = new UICore();
		}
		return mInstance;
	}

	public void makeToast(Context context, String text) {
		Toast.makeText(context, text, 2000).show();
	}
}
