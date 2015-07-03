package com.piotr.piotr.vocabtrainer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ItemCreation extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_creation);

        final EditText item = (EditText) findViewById(R.id.editTextCreateItem);
        final EditText translate = (EditText) findViewById(R.id.editTextCreateItemTranslate);
        Button add = (Button) findViewById(R.id.btnCreateNewItem);

        DictDBHelper db = new DictDBHelper(ItemCreation.this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DictDBHelper db = new DictDBHelper(ItemCreation.this);
                DictItem newDictItem = new DictItem();
                newDictItem.setItem(""+item.getText());
                newDictItem.setTranslation("" + translate.getText());
                db.addDictItem(newDictItem);
                Toast.makeText(ItemCreation.this,  "New item added to your dictionary",
Toast.LENGTH_LONG).show();
            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_creation, menu);
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
