package com.example.lagerlogger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;


public class BeerViewer extends SherlockFragmentActivity {
	private static final String BEER_TO_EDIT = "beer";
	private static final String BREWERY_TO_EDIT = "brewery";

	
	private Long mRowId;
	private Beer mBeer = null;
	
	private BeerDbAdapter mBeerDbAdapter; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beer_viewer);
		
		mRowId = (savedInstanceState == null) ? null : (Long) savedInstanceState.getSerializable(BeerDbAdapter.COLUMN_ID);
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(BeerDbAdapter.COLUMN_ID): null;
		}
		
		mBeer = new Beer(mBeerDbAdapter.findBeerByID(mRowId));
		showBeerInfo(mBeer);
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
		i.putExtra(BeerDbAdapter.COLUMN_ID, mBeer.getID());
		startActivity(i);
	}

}
