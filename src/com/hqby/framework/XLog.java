package com.hqby.framework;

import android.util.Log;

public class XLog {
	
	public static void red(Object obj) {
		Log.e("XLog", obj.toString());
	}
	
	public static void yellow(Object obj) {
		Log.w("XLog", obj.toString());
	}
	
}
