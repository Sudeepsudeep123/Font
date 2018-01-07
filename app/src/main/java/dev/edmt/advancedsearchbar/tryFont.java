package dev.edmt.advancedsearchbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class tryFont extends AppCompatActivity {

    final FontFormat fontFormat = new FontFormat();
    boolean status = false;
    Button btnwrite;
    Button btnfont;
    Button btnformat;
    Button btnbackground;
    EditText tryFont;
    TextView Title;
    Background backGround = new Background();
    String fontName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_font);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);


            }
        });

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        fontName = sharedPref.getString("fontName", "Not Available");

        Title = (TextView) findViewById(R.id.fontTitle);
        Title.setText(fontName);

        btnwrite = (Button) findViewById(R.id.write);
        btnfont = (Button) findViewById(R.id.font);
        btnformat = (Button) findViewById(R.id.format);
        btnbackground = (Button) findViewById(R.id.background);
        tryFont = (EditText) findViewById(R.id.etTryfont);

        btnwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);

            }
        });


        btnformat.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if(!status){
                    fragmentTransaction.replace(R.id.actions, fontFormat);
                    //fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    //Toast.makeText(getBaseContext(),"hello",  Toast.LENGTH_SHORT).show();
                }

                String value = tryFont.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("value", value);
                fontFormat.setArguments(bundle);
            }

        });

        btnbackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                FragmentManager fm = getActivity().getSupportFragmentManager();
//                if(fm.getBackStackEntryCount()>0) {
//                    fm.popBackStack();
//                }
                //FragmentManager aFragmentManager = Context.getSupportFragmentManager();


                FragmentManager fragmentManager = getSupportFragmentManager();
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (!status) {
                    fragmentTransaction.replace(R.id.actions, backGround);
                    //fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

                String value = tryFont.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("value", value);
                backGround.setArguments(bundle);



            }
        });
    }

}
