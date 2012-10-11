package com.Grupp01.gymapp.View.History;

import java.util.LinkedList;

import com.Grupp01.gymapp.R;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;

import android.os.Bundle;
import android.content.Intent;

public class ShowSingleHistoryActivity extends SherlockListActivity {
	
	//THis is the list with every performed workout, will be fetched from the database.
	private LinkedList<History> hList = new LinkedList<History>();
	
	//This is the adapter we use to adapt the list to the layout.
	private HistoryAdapter hAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_history);
        Intent intent = getIntent();
        setTitle(intent.getStringExtra("id"));
        createList();
    }

    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.activity_show_single_history, menu);
        getSupportActionBar().setHomeButtonEnabled(true);
        return true;
    }
	
	private void createList() {
		
		hList.add(new History("Övning 1", "10 kg"));
		hList.add(new History("Övning 2", "20 kg"));
		hList.add(new History("Övning 3", "30 kg"));
		hList.add(new History("Övning 4", "40 kg"));
		hList.add(new History("Övning 5", "50 kg"));
		hList.add(new History("Övning 6", "60 kg"));
		hList.add(new History("Övning 7", "70 kg"));
		hList.add(new History("Övning 8", "80 kg"));
		
		hAdapter = new HistoryAdapter(this, R.layout.history_list_layout, hList);
		setListAdapter(hAdapter);
		
	}
}
