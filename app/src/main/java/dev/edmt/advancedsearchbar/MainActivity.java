package dev.edmt.advancedsearchbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabPlus,fabSystem,fabGoogle;
    EditText etGoogle,etSystem;
    Animation fab_open,fab_close, rotate_clockwise, rotate_anticlockwise;
    boolean isopen= false;
    MaterialSearchView searchView;
    ListView lstView;


    private ArrayList<FamilyItem> familyItems;
    private LinkedHashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
    private String TAG = MainActivity.class.getSimpleName();
    private ExpandableListView lv;
    final Options op = new Options();
    int count = 0;
    ChildInfo name = new ChildInfo();
    private CustomAdapter adapter;
    ArrayList<HashMap<String, String>> contactList;
    private ArrayList<GroupInfo> deptList = new ArrayList<GroupInfo>();
    boolean status = false;
    //Options opt = new Options();


    String[] lstSource = new String[5000];
//
//            "Harry",
//            "Ron",
//            "Hermione",
//            "Snape",
//            "Malfoy",
//            "One",
//            "Two",
//            "Three",
//            "Four",
//            "Five",
//            "Six",
//            "Seven",
//            "Eight",
//            "Nine",
//            "Ten"
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Material Search");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        final  ActionBar action = getSupportActionBar();
        action.setDisplayHomeAsUpEnabled(true);
        action.setHomeAsUpIndicator(R.drawable.nav);

        etGoogle= (EditText) findViewById(R.id.txtGoogle);
        etSystem= (EditText) findViewById(R.id.txtSystem);

        fabPlus = (FloatingActionButton) findViewById(R.id.fab);
        fabSystem = (FloatingActionButton) findViewById(R.id.fabSystem);
        fabGoogle = (FloatingActionButton) findViewById(R.id.fabGoogle);

        fab_open= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        fab_close= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        rotate_anticlockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);

        fabPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isopen){
                    fabSystem.startAnimation(fab_close);
                    fabGoogle.startAnimation(fab_close);
                    fabPlus.startAnimation(rotate_anticlockwise);
                    fabSystem.setClickable(false);
                    fabGoogle.setClickable(false);
                    etGoogle.setVisibility(View.INVISIBLE);
                    etSystem.setVisibility(View.INVISIBLE);
                    isopen=false;
                }
                else{
                    fabSystem.startAnimation(fab_open);
                    fabGoogle.startAnimation(fab_open);
                    fabPlus.startAnimation(rotate_clockwise);
                    fabSystem.setClickable(true);
                    fabGoogle.setClickable(true);
                    etGoogle.setVisibility(View.VISIBLE);
                    etSystem.setVisibility(View.VISIBLE);
                   //lstView.setBackground().setalpha(51);
                    //lstView.getBackground().setAlpha(51);
                    isopen=true;
                    fabGoogle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(MainActivity.this,GoogleFonts.class);
                            startActivity(i);
                        }
                    });

                    fabSystem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(),"The system Font",Toast.LENGTH_LONG);
                        }
                    });
                }

            }
        });

        contactList = new ArrayList<>();
        lv = (ExpandableListView) findViewById(R.id.lstView);
        new GetContacts().execute();

        searchView = (MaterialSearchView)findViewById(R.id.search_view);
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

                //If closed Search View , lstView will return default
                //lstView = (ExpandableListView)findViewById(R.id.lstView);
              //   adapter = new CustomAdapter(MainActivity.this, lstSource);
                         //(MainActivity.this,android.R.layout.simple_list_item_1,lstSource);
                lv.setAdapter(adapter);
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null ||newText.toLowerCase() != null ||newText.toUpperCase() != null && !newText.isEmpty() ||!newText.toUpperCase().isEmpty()||!newText.toLowerCase().isEmpty()){
                    List<String> lstFound = new ArrayList<String>();
                    for(String item:lstSource){
                        if(item.contains(newText) ||item.contains(newText.toLowerCase()) || item.contains(newText.toUpperCase()))
                            lstFound.add(item);
                    }

                    ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,lstFound);
                    lv.setAdapter(adapter);
                }
                else{
                    //if search text is null
                    //return default
                    ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,lstSource);
                    lv.setAdapter(adapter);
                }
                return true;
            }

        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(MainActivity.this,Nav.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"The fonts are downloading",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://www.googleapis.com/webfonts/v1/webfonts?key=AIzaSyC05Jg8vTfHBNN9HnpSXv1DVFuen2LMpWo";
            String jsonStr = sh.makeServiceCall(url);
            familyItems= new ArrayList<FamilyItem>();

            // Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {

                try {

                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray items = jsonObj.getJSONArray("items");

                    // looping through All items
                    for (int i = 0; i < items.length(); i++) {
                        FamilyItem familyObject = new FamilyItem();

                        JSONObject c = items.getJSONObject(i);
                        familyObject.Name=c.getString("family");
                        familyObject.Version=c.getString("version");
                        familyObject.Category=c.getString("category");
                        JSONArray variants=c.getJSONArray("variants");
                        JSONObject files = c.getJSONObject("files");
                        ArrayList<FontItem> fontItems = new ArrayList<FontItem>();

                        for (int j=0;j<variants.length();j++) {
                            FontItem fontitem= new FontItem();
                            fontitem.FamilyName = familyObject.Name;
                            fontitem.FontType=variants.getString(j);
                            //Log.e("fontType",fontitem.FontType);
                            fontitem.FontName=fontitem.FamilyName +"-"+fontitem.FontType;
                            fontitem.DownloadURL = files.getString(fontitem.FontType);
                            fontItems.add(fontitem);
                        }
                        familyObject.FontList=fontItems;
                        familyItems.add(familyObject);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }


            } else {
                // Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't fetch data from server. Please Check Your internet Connection!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);

            loadData();
          //  lv = (ExpandableListView) findViewById(R.id.lstView);
            // create the adapter by passing your ArrayList data
            adapter = new CustomAdapter(MainActivity.this, deptList);
            lv.setAdapter(adapter);


            lv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    GroupInfo headerInfo = deptList.get(groupPosition);
                    return false;
                }
            });

            lv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    GroupInfo headerInfo = deptList.get(groupPosition);
                    ChildInfo detailInfo =  headerInfo.getProductList().get(childPosition);
                    Log.e("Url=",detailInfo.getChildUrl());

//                    for (String child : name.getName()){
//
//
//                    }

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    if(!status){
                        fragmentTransaction.replace(R.id.main,op);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                    }

                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Url",detailInfo.getChildUrl());
                    editor.putString("fontName",detailInfo.getName());
                    editor.commit();
                    return false;
                }
            });
        }

        public  ArrayList<HashMap<String, String>> CreateHashMapArrayFromList(){
            ArrayList<HashMap<String, String>> fList = new ArrayList<HashMap<String, String>>();
            return  fList;
        }

        public  void loadData(){
            for(FamilyItem family : familyItems){
                for(int i = 0;i<family.FontList.size();i++) {
                    lstSource[i] = family.FontList.get(i).FontName;
                    Log.e("data" + count++ , lstSource[i]);
                    addProduct(family.Name, family.FontList.get(i).FontName, family.FontList.get(i).DownloadURL);
                }
            }
        }

        public int addProduct(String department, String product,String url){

            int groupPosition = 0;
            HttpHandler sh = new HttpHandler();
            //check the hash map if the group already exists
            GroupInfo headerInfo = subjects.get(department);
            //add the group if doesn't exists
            if(headerInfo == null){
                headerInfo = new GroupInfo();
                headerInfo.setName(department);
                headerInfo.setUrl(url);
                subjects.put(department, headerInfo);
                // subjects.put(url, headerInfo);
                deptList.add(headerInfo);
            }



            //get the children for the group
            ArrayList<ChildInfo> productList = headerInfo.getProductList();
            //size of the children list
            int listSize = productList.size();
            //add to the counter
            listSize++;

            //create a new child and add that to the group
            ChildInfo detailInfo = new ChildInfo();

            detailInfo.setName(product);
            detailInfo.setChildUrl(url);

            productList.add(detailInfo);
            headerInfo.setProductList(productList);


            //find the group position inside the list
            groupPosition = deptList.indexOf(headerInfo);




            return groupPosition;
        }
    }
}
