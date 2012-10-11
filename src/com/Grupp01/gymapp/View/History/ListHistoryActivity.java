/*This file is part of Gymapp.
*
*   Gymapp is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   Gymapp is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*  along with Gymapp.  If not, see <http://www.gnu.org/licenses/>.
*		
*			Copyright © 2012 GivDev
*/
package com.Grupp01.gymapp.View.History;

import java.util.LinkedList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.Grupp01.gymapp.R;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.*;

public class ListHistoryActivity extends SherlockListActivity {

	private LinkedList<History> hList = new LinkedList<History>();
	private HistoryAdapter hAdapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_history);
        createList();
        
    }

    private void createList() {
    	hList.add(new History("Test 1", "2009-01-01"));
		hList.add(new History("Test 2", "2009-01-02"));
		hList.add(new History("Test 3", "2009-01-03"));
		hList.add(new History("Test 4", "2009-01-04"));
		hList.add(new History("Test 5", "2009-01-05"));
		hList.add(new History("Test 6", "2009-01-06"));
		hList.add(new History("Test 7", "2009-01-07"));
		hList.add(new History("Test 8", "2009-01-08"));
		hAdapter = new HistoryAdapter(this, R.layout.history_list_layout, hList);
		setListAdapter(hAdapter);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getSupportMenuInflater().inflate(R.menu.activity_list_history, menu);
    	getSupportActionBar().setHomeButtonEnabled(true);
        return true;
    }
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		History test = (History) getListAdapter().getItem(position);
		Intent intent = new Intent(this, ShowSingleHistoryActivity.class);
		intent.putExtra("id", test.getName());
		startActivity(intent);
	}
}