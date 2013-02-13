package com.example.lagerlogger;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListActivity;

public class BeerList extends SherlockListActivity {
	
	static final String[] BEER_LIST_TEST = new String[] { "East India Pale Ale", "Flower Power", "Flounder Pounder"
															,"Lagunitas IPA", "Lil' Sumpin' Sumpin' Ale", "Arrogant Bastard"
															,"Sublimely Self-Righteous"};
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,BEER_LIST_TEST));
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
	}

	
	
}
