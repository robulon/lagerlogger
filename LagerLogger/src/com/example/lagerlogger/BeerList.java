package com.example.lagerlogger;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.internal.widget.IcsAdapterView.AdapterContextMenuInfo;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;




public class BeerList extends SherlockListActivity {
	
	private static final int INSERT_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;
	
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

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, INSERT_ID, 0, R.string.menu_add_beer);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case INSERT_ID:
			addBeer();
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, R.string.ctxt_delete_beer);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DELETE_ID:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
					.getMenuInfo();
			mBeerDbAdapter.deleteNote(info.id);
			fillListData();
			return true;
		}
		return super.onContextItemSelected((android.view.MenuItem) item);
	}
	
	public void addBeer(){
		
	}
	
}
