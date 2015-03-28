package com.example.search;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;


public class MyArrayAdapter extends ArrayAdapter<Oppertunity> {

	Context context;
	int layoutResourceId;
	ArrayList<Oppertunity> students = new ArrayList<Oppertunity>();

	public MyArrayAdapter(Context context, int layoutResourceId,
			ArrayList<Oppertunity> studs) {
		super(context, layoutResourceId, studs);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.students = studs;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View item = convertView;
		StudentWrapper StudentWrapper = null;

		if (item == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			item = inflater.inflate(layoutResourceId, parent, false);
			StudentWrapper = new StudentWrapper();
			StudentWrapper.title = (TextView) item.findViewById(R.id.textName);
			StudentWrapper.SponsorCollege = (TextView) item.findViewById(R.id.textAge);
			StudentWrapper.TargetGroup = (TextView) item.findViewById(R.id.textAddr);
			StudentWrapper.edit = (Button) item.findViewById(R.id.btnEdit);
			StudentWrapper.delete = (Button) item.findViewById(R.id.btnDelete);
			item.setTag(StudentWrapper);
		} else {
			StudentWrapper = (StudentWrapper) item.getTag();
		}

		Oppertunity student = students.get(position);
		StudentWrapper.title.setText(student.getName());
		StudentWrapper.SponsorCollege.setText(student.getSponsorCollege());
		StudentWrapper.TargetGroup.setText(student.getTargetGroup());
		
		StudentWrapper.edit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Edit", Toast.LENGTH_LONG).show();
			}
		});
		
		StudentWrapper.delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Toast.makeText(context, "Delete", Toast.LENGTH_LONG).show();
			}
		});

		return item;

	}

	static class StudentWrapper {
		TextView title;
		TextView SponsorCollege;
		TextView TargetGroup;
		Button edit;
		Button delete;
	}

}
