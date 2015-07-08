package com.piotr.piotr.vocabtrainer;

import android.content.ClipData;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;


public class DrillCreation extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drill_creation);

        TextView itemsView = (TextView) findViewById(R.id.textViewSingleItem);

        String allItemsDisplay = "";
        DictDBHelper db = new DictDBHelper(DrillCreation.this);
        List<DictItem> allItems = db.getAllDictItems();

        for(int i=0; i<allItems.size(); ++i) {
            allItemsDisplay = allItems.get(i).toString() + "\n";
        }

        itemsView.setText(""+ allItemsDisplay);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drill_creation, menu);
        return true;
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

        return super.onOptionsItemSelected(item);
    }
}
