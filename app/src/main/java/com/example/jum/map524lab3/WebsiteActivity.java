package com.example.jum.map524lab3;

import android.app.ActionBar;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by jum on 06/02/15.
 */
public class WebsiteActivity extends ActionBarActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_website);

        ListView urls = (ListView) findViewById(R.id.UrlList);
        final Resources res = getResources();
        String[] values = res.getStringArray(R.array.list_urls);
        String[] links = res.getStringArray(R.array.list_urls_links);
        final HashMap<String,String> link = new HashMap<String,String>();
        for(int i = 0; i < values.length && i <links.length; i++)
            link.put(values[i],links[i]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        urls.setAdapter(adapter);

        urls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String urlvalue = (String) parent.getItemAtPosition(position);
                String urllink = link.get(urlvalue);
                Toast.makeText(getApplicationContext(), urllink, Toast.LENGTH_LONG).show();
                WebView wb = new WebView(getApplicationContext());
                wb.setWebViewClient(new WebViewClient());
                wb.loadUrl(urllink);

                setContentView(wb);
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
            Toast.makeText(getApplicationContext(),R.string.helpStringWebsite,Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
