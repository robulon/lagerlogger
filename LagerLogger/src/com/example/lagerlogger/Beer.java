package com.example.lagerlogger;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class Beer implements Parcelable{

	private String mName, mBrewery, mColor, mType, mNote;
	private double mAbv, mOg, mFg;
	private long mRowId;
	
	public Beer(){
		mName = "beer_name";
		mBrewery = "brewery_name";
		mColor = "beer_color";
		mType = "beer_type";
		mNote = "beer_notes";
		 
		mAbv = 0.0;
		mOg = 1.000;
		mFg = 1.000;
	}
	public Beer(String name, String brewery, String color, String type,
			String note, double abv, double og, double fg) {
		this.mName = name;
		this.mBrewery = brewery;
		this.mColor = color;
		this.mType = type;
		this.mNote = note;

		this.mAbv = abv;
		this.mOg = og;
		this.mFg = fg;
	}
	
	public Beer(Parcel in){
		this.mName = (String)in.readString();
		this.mBrewery = (String)in.readString();
		this.mColor = (String)in.readString();
		this.mType = (String)in.readString();
		this.mNote = (String)in.readString();
		
		this.mAbv = (double)in.readDouble();
		this.mOg = (double)in.readDouble();
		this.mFg = (double)in.readDouble();
	}
	
	public Beer(Cursor in){
		this.mRowId = in.getLong(in.getColumnIndex(BeerDbAdapter.COLUMN_ID));
		
		this.mName = in.getString(in.getColumnIndex(BeerDbAdapter.COLUMN_NAME));
		this.mBrewery = in.getString(in.getColumnIndex(BeerDbAdapter.COLUMN_BREWERY));
		this.mColor = in.getString(in.getColumnIndex(BeerDbAdapter.COLUMN_COLOR));
		this.mType = in.getString(in.getColumnIndex(BeerDbAdapter.COLUMN_TYPE));
		this.mNote = in.getString(in.getColumnIndex(BeerDbAdapter.COLUMN_NOTE));
		
		this.mAbv = in.getFloat(in.getColumnIndex(BeerDbAdapter.COLUMN_ABV));
		this.mOg = in.getFloat(in.getColumnIndex(BeerDbAdapter.COLUMN_OG));
		this.mFg = in.getFloat(in.getColumnIndex(BeerDbAdapter.COLUMN_FG));
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mName);
		dest.writeString(mBrewery);
		dest.writeString(mColor);
		dest.writeString(mType);
		dest.writeString(mNote);
		
		dest.writeDouble(mAbv);
		dest.writeDouble(mOg);
		dest.writeDouble(mFg);
	}
	
	public ContentValues writeCV (){
		ContentValues cv = new ContentValues();
		
		cv.put(BeerDbAdapter.COLUMN_NAME, mName);
		cv.put(BeerDbAdapter.COLUMN_BREWERY, mBrewery);
		cv.put(BeerDbAdapter.COLUMN_COLOR, mColor);
		cv.put(BeerDbAdapter.COLUMN_TYPE, mType);
		cv.put(BeerDbAdapter.COLUMN_NOTE, mNote);
		cv.put(BeerDbAdapter.COLUMN_ABV, mAbv);
		cv.put(BeerDbAdapter.COLUMN_OG, mOg);
		cv.put(BeerDbAdapter.COLUMN_FG, mFg);
		
		return cv;
	}
	
	public String getName (){
		return this.mName;
	}
	
	public String getBrewery (){
		return this.mBrewery;
	}
	
	public String getColor (){
		return this.mColor;
	}
	
	public String getType (){
		return this.mType;
	}
	
	public String getNote (){
		return this.mNote;
	}
	
	public Double getABV (){
		return this.mAbv;
	}
	
	public Double getOG (){
		return this.mOg;
	}
	
	public Double getFG (){
		return this.mFg;
	}
	
	public long getID(){
		return this.mRowId;
	}
	public void setName (String name){
		this.mName = name;
	}
	
	public void setBrewery (String brewery){
		this.mBrewery = brewery;
	}
	
	public void setColor (String color){
		this.mColor = color;
	}
	
	public void setType (String type) {
		this.mType = type;
	}
	
	public void setNote (String note){
		this.mNote = note;
	}
	
	public void setABV (double abv){
		this.mAbv = abv;
	}
	
	public void setOG (double og){
		this.mOg = og;
	}
	
	public void setFG (double fg){
		this.mFg = fg;
	}
	
	public void setID (long ID){
		this.mRowId = ID;
	}
	public static final Parcelable.Creator<Beer> CREATOR = new Parcelable.Creator<Beer>() {
		public Beer createFromParcel(Parcel in) {
			return new Beer(in);
		}

		public Beer[] newArray(int size) {
			return new Beer[size];
		}
	};
	
}
