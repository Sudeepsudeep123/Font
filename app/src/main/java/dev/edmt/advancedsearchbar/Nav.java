package dev.edmt.advancedsearchbar;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Nav extends AppCompatActivity {
    ListView lstView1;
    ListView list;

    String[] web = {

            "  Share App",
            "  Rate Us",
            "  Feedback",
            "  Tutorial",

    };

    Integer[] imageId = {
            R.drawable.sharepic,
            R.drawable.rate,
            R.drawable.feedback,
            R.drawable.tutorial

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarSetting);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        final ActionBar action = getSupportActionBar();
        action.setDisplayHomeAsUpEnabled(true);
        action.setHomeAsUpIndicator(R.drawable.back);


        CustomList adapter = new
                CustomList(Nav.this, web, imageId);
        list=(ListView)findViewById(R.id.lstViewSetting);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(Nav.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });


//        lstView1 = (ListView)findViewById(R.id.lstViewSetting);
//        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,lstSource);
//        lstView1.setAdapter(adapter);
   }
//
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(Nav.this,MainActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
