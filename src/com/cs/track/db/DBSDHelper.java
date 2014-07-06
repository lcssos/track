package com.cs.track.db;

import android.content.Context;

import com.ab.db.orm.AbSDDBHelper;
import com.cs.track.model.Location;

public class DBSDHelper extends AbSDDBHelper {
	// 数据库名
	private static final String DBNAME = "track.db";
	// 数据库 存放路径
    private static final String DBPATH = "TrackDB";
    
    // 当前数据库的版本
	private static final int DBVERSION = 1;
	// 要初始化的表
	private static final Class<?>[] clazz = { Location.class};

	public DBSDHelper(Context context) {
		super(context,DBPATH, DBNAME, null, DBVERSION, clazz);
	}

}



