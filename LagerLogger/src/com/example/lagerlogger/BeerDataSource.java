package com.example.lagerlogger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class BeerDataSource {
	private SQLiteDatabase beerDB;
	private BeerDBHelper beerDBHelper;
	private String allColumns[] = {BeerDBHelper.COLUMN_ID, BeerDBHelper.COLUMN_NAME};
	
	public BeerDataSource(Context ctxt){
		beerDBHelper = new BeerDBHelper(ctxt);
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
		Cursor csr = beerDB.query(beerDBHelper.TABLE_BEER,
		        allColumns, beerDBHelper.COLUMN_ID + " = " + ID, null,
		        null, null, null);
		return csr;
	}
	public Beer cursorToBeer(Cursor csr){
		Beer beer = new Beer();
		beer.setID(csr.getLong(csr.getColumnIndex(BeerDBHelper.COLUMN_ID)));
		beer.setName(csr.getString(csr.getColumnIndex(BeerDBHelper.COLUMN_NAME)));
		return beer;
	}
	
	
}
