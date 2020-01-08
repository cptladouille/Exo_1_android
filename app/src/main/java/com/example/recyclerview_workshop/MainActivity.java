package com.example.recyclerview_workshop;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddPeopleFragment.AddPeopleListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PeopleAdapter myPeopleAdapter;
    private  ArrayList<People> myPeopleList;
    private PeopleDatabase myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        myPeopleList = People.InitPeoples(5);
        myPeopleAdapter = new PeopleAdapter(myPeopleList);
        recyclerView.setAdapter(myPeopleAdapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {showDialog();}
        });
        myDataBase = new PeopleDatabase(this);
    }
    private void showDialog() {
        AddPeopleFragment AddPeopleFragmentDialog = AddPeopleFragment.newInstance();
        AddPeopleFragmentDialog.show(getSupportFragmentManager(), "AddPeopleFragment");

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void notifyDataChangeOnUiThread()
    {
        runOnUiThread (new Thread(new Runnable() {
            public void run() {
                myPeopleAdapter.notifyDataSetChanged();
            }
        }));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_clear) {
            myPeopleList.clear();
            notifyDataChangeOnUiThread();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreatePeopleListener(People p)
    {
        myPeopleAdapter.myPeople.add(p);
    }


}
