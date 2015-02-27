package com.example.jum.map524lab3;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by jum on 06/02/15.
 */
public class TerminologyActivity extends ActionBarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminology);

        //Resource gathering
        final Resources res = getResources();
        final String[] terms = res.getStringArray(R.array.list_terms);
        final String[] terms_def = res.getStringArray(R.array.list_terms_def);

        //linking the terms and definitions together as key value pairs
        final HashMap<String,String> link = new HashMap<String,String>();
        for(int i = 0; i < terms.length && i <terms_def.length; i++)
            link.put(terms[i],terms_def[i]);

        //text view and adapter settings
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, terms);
        final AutoCompleteTextView tv = (AutoCompleteTextView) findViewById(R.id.term_list);
        tv.setAdapter(adapter);
        tv.setThreshold(3);

        tv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id)
            {

                String term = (String) parent.getItemAtPosition(position);
                String result = link.get(term);
                Toast toast = Toast.makeText(getApplicationContext(), term + " :" + result, Toast.LENGTH_LONG);

                TextView viewresult = (TextView) findViewById(R.id.results);

                //testing out toast location settings
                int loc[]=new int[2];
                tv.getLocationOnScreen(loc);
                toast.setGravity(Gravity.TOP | Gravity.LEFT, loc[0], loc[1]);
                toast.show();
                viewresult.setText(result);

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Toast.makeText(getApplicationContext(),R.string.aboutString,Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),R.string.helpStringTerms,Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
