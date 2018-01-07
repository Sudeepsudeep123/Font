package dev.edmt.advancedsearchbar;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Options extends Fragment {
    Button btnTry;
    Button btnDetails;
    Button btnInstall;
    Button btnShare;
    Button btnCancel;

    String url;
    String fontName;
    String jsonStr;

    Intent shareIntent,redirect;

    public Options() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_options, container, false);
        btnTry = (Button)view.findViewById(R.id.btnTry);
        btnDetails=(Button)view.findViewById(R.id.btnDetails);
        btnInstall=(Button)view.findViewById(R.id.btnInstall);
        btnShare=(Button)view.findViewById(R.id.btnShare);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);

        btnTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getContext(),tryFont.class);
                startActivity(i);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                url = sharedPref.getString("Url", "Not Available");
                fontName = sharedPref.getString("fontName", "Not Available");
                shareIntent= new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,fontName);
                shareIntent.putExtra(Intent.EXTRA_TEXT,url);
                startActivity(Intent.createChooser(shareIntent, "Share Via"));
            }
        });

        btnInstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                url = sharedPref.getString("Url", "Not Available");
                fontName = sharedPref.getString("fontName", "Not Available");

                Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

                //new Download().execute();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                if(fm.getBackStackEntryCount()>0) {
                    fm.popBackStack();
                }
            }
        });
        return view;
    }
}