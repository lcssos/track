package com.cs.track;

import android.content.Context;

import com.ab.db.orm.dao.AbDBDaoImpl;
import com.cs.track.db.DBSDHelper;
import com.cs.track.model.Location;

public class LocationDao extends AbDBDaoImpl<Location> {

	public LocationDao(Context context) {
		super(new DBSDHelper(context),Location.class);
	}

}
