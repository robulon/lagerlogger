package com.example.lagerlogger;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

public class Beer implements Parcelable{

	private String name, brewery, color, type, note;
	private double abv, og, fg;
	private long ID;
	
	public Beer(){
		name = "beer_name";
		brewery = "brewery_name";
		color = "beer_color";
		type = "beer_type";
		note = "beer_notes";
		 
		abv = 0.0;
		og = 1.000;
		fg = 1.000;
	}
	public Beer(String name, String brewery, String color, String type,
			String note, double abv, double og, double fg) {
		this.name = name;
		this.brewery = brewery;
		this.color = color;
		this.type = type;
		this.note = note;

		this.abv = abv;
		this.og = og;
		this.fg = fg;
	}
	
	public Beer(Parcel in){
		this.name = (String)in.readString();
		this.brewery = (String)in.readString();
		this.color = (String)in.readString();
		this.type = (String)in.readString();
		this.note = (String)in.readString();
		
		this.abv = (double)in.readDouble();
		this.og = (double)in.readDouble();
		this.fg = (double)in.readDouble();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(brewery);
		dest.writeString(color);
		dest.writeString(type);
		dest.writeString(note);
		
		dest.writeDouble(abv);
		dest.writeDouble(og);
		dest.writeDouble(fg);
	}
	
	public ContentValues writeCVStub (){
		ContentValues cv = new ContentValues();
		cv.put(BeerDBHelper.COLUMN_NAME, name);
		
		return cv;
	}
	
	public String getName (){
		return this.name;
	}
	
	public String getBrewery (){
		return this.brewery;
	}
	
	public String getColor (){
		return this.color;
	}
	
	public String getType (){
		return this.type;
	}
	
	public String getNote (){
		return this.note;
	}
	
	public Double getABV (){
		return this.abv;
	}
	
	public Double getOG (){
		return this.og;
	}
	
	public Double getFG (){
		return this.fg;
	}
	
	public long getID(){
		return this.ID;
	}
	public void setName (String name){
		this.name = name;
	}
	
	public void setBrewery (String brewery){
		this.brewery = brewery;
	}
	
	public void setColor (String color){
		this.color = color;
	}
	
	public void setType (String type) {
		this.type = type;
	}
	
	public void setNote (String note){
		this.note = note;
	}
	
	public void setABV (double abv){
		this.abv = abv;
	}
	
	public void setOG (double og){
		this.og = og;
	}
	
	public void setFG (double fg){
		this.fg = fg;
	}
	
	public void setID (long ID){
		this.ID = ID;
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
