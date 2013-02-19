package com.example.lagerlogger;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;

import com.actionbarsherlock.app.SherlockListActivity;


public class BeerList extends SherlockListActivity {
	
	
	static final String[] BEER_LIST_TEST = new String[] { "East India Pale Ale", "Flower Power", "Flounder Pounder"
															,"Lagunitas IPA", "Lil' Sumpin' Sumpin' Ale", "Arrogant Bastard"
															,"Sublimely Self-Righteous"};
	
	private BeerDbAdapter mDbAdapter;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beer_list);
		mDbAdapter = new BeerDbAdapter(this);
		mDbAdapter.open();
		fillListData();
		registerForContextMenu(getListView());
	}
	
	@SuppressWarnings("deprecation")
	public void fillListData(){
		Cursor csr = mDbAdapter.getBeerList();
		startManagingCursor(csr);
		String[] from = new String[] {BeerDbAdapter.COLUMN_NAME};
		int[] to = {R.id.textView_beerItem};
		
		SimpleCursorAdapter beerList = new SimpleCursorAdapter(this,R.layout.beer_list_item, csr, from, to);
		setListAdapter(beerList);
	}
	
}
