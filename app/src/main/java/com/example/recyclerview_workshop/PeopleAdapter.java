package com.example.recyclerview_workshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>{
    public ArrayList<People> myPeople;

    public class PeopleViewHolder extends RecyclerView.ViewHolder
    {
        public View MainTextView;
        public TextView peopleNameView;
        public PeopleViewHolder(View v)
        {
            super(v);
            peopleNameView = v.findViewById(R.id.textView_1);

        }
    }

    public PeopleAdapter(ArrayList<People> myPeople)
    {
        this.myPeople = myPeople;

    }
    @Override
    public PeopleAdapter.PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View peopleView = inflater.inflate(R.layout.people_layout, parent, false);

        return new PeopleViewHolder(peopleView);

    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {

        TextView peopleNameView = holder.peopleNameView;
        peopleNameView.setText(myPeople.get(position).ShowPeople());

    }

    @Override
    public int getItemCount() {
        return myPeople.size();
    }



}