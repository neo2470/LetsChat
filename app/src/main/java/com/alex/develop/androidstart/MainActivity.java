package com.alex.develop.androidstart;

import android.os.Bundle;

/**
 * App入口
 * @author Created by alex 2014/10/23
 */
public class MainActivity extends BaseActivity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		changeThemeByTime();
		setContentView(R.layout.main_activity);
	}
}