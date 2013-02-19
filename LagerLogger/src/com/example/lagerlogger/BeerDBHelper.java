package com.example.lagerlogger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
	
	private SQLiteDatabase beerDB;
	private BeerDBHelper beerDBHelper;
	private String allColumns[] = {COLUMN_ID, COLUMN_NAME, COLUMN_BREWERY, COLUMN_COLOR, COLUMN_TYPE
									, COLUMN_NOTE, COLUMN_ABV, COLUMN_OG, COLUMN_FG};
	
	
	private static final String DATABASE_CREATE = "create table "
		      + TABLE_BEER + "(" + COLUMN_ID
		      + " integer primary key autoincrement, " + COLUMN_NAME
		      + " text not null, " + COLUMN_BREWERY + " text not null, "
		      + COLUMN_COLOR + " text not null, " + COLUMN_TYPE
		      + " text not null, " + COLUMN_NOTE + " text not null, " 
		      + COLUMN_ABV + " real not null, " + COLUMN_OG
		      + " real not null, " + COLUMN_FG + " real not null);";
	
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
	
	public void openWrite() throws SQLiteException {
		beerDB = beerDBHelper.getWritableDatabase();
	}
	
	public void openRead() throws SQLiteException {
		beerDB = beerDBHelper.getReadableDatabase();
	}
	
	public void close() {
		beerDB.close();
	}
	
	public void addBeer(Beer beer){
		ContentValues cv = new ContentValues(beer.writeCVStub());
		long insertID = beerDB.insert(BeerDBHelper.TABLE_BEER, null, cv);
		beer.setID(insertID);
	}
	
	public Cursor findBeerByID(long ID){
		Cursor csr = beerDB.query(TABLE_BEER, allColumns,COLUMN_ID + " = " + ID, null,
		        null, null, null);
		return csr;
	}
}
