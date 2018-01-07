package dev.edmt.advancedsearchbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import yuku.ambilwarna.AmbilWarnaDialog;


public class FontFormat extends Fragment {
    SeekBar seekzoom;
    EditText text;
    Button color;
    Intent shareIntent;
    ImageView alignLeft, alignCenter, alighRight;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_font_format, container, false);
        return inflater.inflate(R.layout.fragment_font_format, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        text = (EditText)getActivity().findViewById(R.id.etTryfont);
        seekzoom = (SeekBar) view.findViewById(R.id.zoom);
        color = (Button) view.findViewById(R.id.btnColor);
        alignLeft = (ImageView) view.findViewById(R.id.alignleft);
        alignCenter = (ImageView) view.findViewById(R.id.aligncenter);
        alighRight = (ImageView) view.findViewById(R.id.alignright);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String value = getArguments().getString("value");
        text.setText(value);

        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AmbilWarnaDialog colorpicker = new AmbilWarnaDialog(getContext(), Color.BLACK, new AmbilWarnaDialog.OnAmbilWarnaListener() {

                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        text.setTextColor(color);
                    }
                });
                colorpicker.show();
            }
        });


        seekzoom.setProgress((int)text.getTextSize());
        seekzoom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                //Log.e("Progress", String.valueOf(progress));
                text.setTextSize((float)progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        alignLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setGravity(Gravity.LEFT);
            }
        });

        alignCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setGravity(Gravity.CENTER_HORIZONTAL);
            }
        });

        alighRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setGravity(Gravity.RIGHT);
            }
        });

    }
}
