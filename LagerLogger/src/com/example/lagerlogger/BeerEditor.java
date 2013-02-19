package com.example.lagerlogger;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class BeerEditor extends SherlockFragmentActivity {
	private static final String BEER_TO_EDIT = "beer";
	private static final String BREWERY_TO_EDIT = "brewery";
	
	private Beer beer = new Beer();
	private EditText et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = this.getIntent();
		if (i.hasExtra(BEER_TO_EDIT) == true) {
			beer = (Beer)(i.getExtras().getParcelable(BEER_TO_EDIT));
			setContentView(R.layout.activity_beer_edit);
			showBeerInfo();
		} else {
			beer = new Beer();
			setContentView(R.layout.activity_beer_edit);
		}
	}
	
	public void showBeerInfo (){
		et = (EditText)findViewById(R.id.editTextName);
		et.setText(beer.getName());
		et = (EditText)findViewById(R.id.editTextBrewery);
		et.setText(beer.getBrewery());
		et = (EditText)findViewById(R.id.editTextColor);
		et.setText(beer.getType());
		et = (EditText)findViewById(R.id.editTextType);
		et.setText(beer.getColor());
		et = (EditText)findViewById(R.id.editTextOG);
		et.setText("O.G.: " + beer.getOG().toString());
		et = (EditText)findViewById(R.id.editTextABV);
		et.setText("ABV: " + beer.getABV().toString());
		et = (EditText)findViewById(R.id.editTextNote);
		et.setText(beer.getNote());
	}
}
