package com.example.lagerlogger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BeerDBHelper extends SQLiteOpenHelper {
	public static final String TABLE_BEER = "Beer Table";
	
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_BREWERY = "brewery";
	public static final String COLUMN_COLOR = "color";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_NOTE = "note";
	public static final String COLUMN_ABV = "abv";
	public static final String COLUMN_OG = "og";
	public static final String COLUMN_FG = "fg";
	
	private static final String DATABASE_NAME = "beer.db";
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE = "create table "
		      + TABLE_BEER + "(" + COLUMN_ID
		      + " integer primary key autoincrement, " + COLUMN_NAME
		      + " text not null);";
	public BeerDBHelper(Context ctxt){
		super(ctxt, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(BeerDBHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BEER);
		onCreate(db);
	}
	
	
}
