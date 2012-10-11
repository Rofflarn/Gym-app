package com.Grupp01.gymapp.View.History;

import java.util.LinkedList;
import java.util.List;

import com.Grupp01.gymapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HistoryAdapter extends ArrayAdapter<History>{
	
	//This is our list of history types
	private LinkedList<History> historyList;
	
	
	public HistoryAdapter(Context context, int textViewResourceId,
			List<History> objects) {
		super(context, textViewResourceId, objects);
		historyList = (LinkedList<History>) objects;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		
		View v = convertView;
		if(v == null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.history_list_layout, null);
		}
		
		History h = historyList.get(position);
		if(h != null){
			TextView name = (TextView) v.findViewById(R.id.historyName);
			TextView date = (TextView) v.findViewById(R.id.historyDate);
			name.setText(h.getName());
			date.setText(h.getDate());
			
		}
		return v;
	}

	
	
}
