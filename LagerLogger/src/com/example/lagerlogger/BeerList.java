package com.example.lagerlogger;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.internal.widget.IcsAdapterView.AdapterContextMenuInfo;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;






public class BeerList extends SherlockListActivity {
//	private String name = "East India Pale Ale";
//	private String brewery = "Brooklyn Brewery";
//	private String color = "Amber" ;
//	private String type = "India Pale Ale" ;
//	private String note = "notes go here";
//	
//	private double abv = 5.9;
//	private double og = 1.060;
//	private double fg = 1.010;
	
	private static final int INSERT_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;
	
	private static final int ACTIVITY_ADD_BEER = 0;
	private static final int ACTIVITY_EDIT_BEER = 1;
	
	private BeerDbAdapter mBeerDbAdapter;
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beer_list);
		mBeerDbAdapter = new BeerDbAdapter(this);
		mBeerDbAdapter.open();
		fillListData();
		registerForContextMenu(getListView());
	}
	
	@SuppressWarnings("deprecation")
	public void fillListData(){
		Cursor csr = mBeerDbAdapter.getBeerList();
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
	public boolean onContextItemSelected(android.view.MenuItem item) {
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
		Intent i = new Intent(this, BeerEditor.class);
		startActivityForResult(i, ACTIVITY_ADD_BEER);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this, BeerViewer.class);
		i.putExtra(BeerDbAdapter.COLUMN_ID, id);
		startActivity(i);
	}
	
}
