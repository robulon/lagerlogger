package com.example.lagerlogger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class BeerViewer extends SherlockFragmentActivity {
	private static final String BEER_TO_EDIT = "beer";
	private static final String BREWERY_TO_EDIT = "brewery";
	private String name = "East India Pale Ale";
	private String brewery = "Brooklyn Brewery";
	private String color = "Amber" ;
	private String type = "India Pale Ale" ;
	private String note = "notes go here";
	
	private double abv = 5.9;
	private double og = 1.060;
	private double fg = 1.010;
	private Beer beer = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		beer = new Beer(name, brewery, color, type, note, abv, og, fg);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beer_viewer);
		showBeerInfo(beer);
	}
	
	public void setTextViewText (String string, TextView tv){
		CharSequence cs = string.subSequence(0, string.length());
		tv.setText(cs);
	}
	
	public void showBeerInfo (Beer beer){
		TextView tv = (TextView)findViewById(R.id.textViewName);
		tv.setText(beer.getName());
		tv = (TextView)findViewById(R.id.textViewBrewery);
		tv.setText(beer.getBrewery());
		tv = (TextView)findViewById(R.id.textViewColorType);
		tv.setText(beer.getColor() + " " + beer.getType());
		tv = (TextView)findViewById(R.id.textViewOG);
		tv.setText("O.G.: " + beer.getOG().toString());
		tv = (TextView)findViewById(R.id.textViewABV);
		tv.setText("ABV: " + beer.getABV().toString());
		tv = (TextView)findViewById(R.id.textViewNote);
		tv.setText(beer.getNote());
	}
	
	public void editBeerEntry(View view){
		Intent i = new Intent(this, BeerEditor.class);
		i.putExtra(BEER_TO_EDIT, beer);
		startActivity(i);
	}

}
