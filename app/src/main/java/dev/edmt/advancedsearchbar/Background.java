package dev.edmt.advancedsearchbar;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import yuku.ambilwarna.AmbilWarnaDialog;

import static android.app.Activity.RESULT_OK;


public class Background extends Fragment {
    private EditText et;
    private ImageView imgChooser;
    ImageView imgCamera;
    ImageView imgGallery;
    String value;
    private static final int SELECTED_PIC = 1;
    gallery g = new gallery();
    private static final int CAMERA_REQUEST = 1888;
    public Background() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_background, container, false);


        return inflater.inflate(R.layout.fragment_background, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et = (EditText) getActivity().findViewById(R.id.etTryfont);

        // mDefaultColor = ContextCompat.getColor(getContext(), R.color.colorPrimary);
        imgChooser = (ImageView) view.findViewById(R.id.palette);
        imgCamera = (ImageView) view.findViewById(R.id.imgCamera);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        value = getArguments().getString("value");
        et.setText(value);

        imgChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AmbilWarnaDialog colorpicker = new AmbilWarnaDialog(getContext(), Color.BLACK, new AmbilWarnaDialog.OnAmbilWarnaListener() {

                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        et.setBackgroundColor(color);
                    }
                });
                colorpicker.show();

            }
        });
        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }

            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
                    Drawable photo = (Drawable) data.getExtras().get("data");

                    et.setBackground(photo);
                }
            }
        });

        imgGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, SELECTED_PIC);

               // g.operation(value);
            }


//            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//                super.onActivityResult(requestCode, resultCode, data);
//                switch (requestCode) {
//                    case SELECTED_PIC:
//                        if (resultCode == RESULT_OK) {
//                            Uri uri = data.getData();
//                            String[] projection = {MediaStore.Images.Media.DATA};
//
//                            Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
//                            cursor.moveToFirst();
//
//                            int columnIndex = cursor.getColumnIndex(projection[0]);
//                            String filepath = cursor.getString(columnIndex);
//                            cursor.close();
//
//                            Bitmap bitmap = BitmapFactory.decodeFile(filepath);
//                            Drawable drawable = new BitmapDrawable(bitmap);
//                            et.setBackground(drawable);
//                        }
//                        break;
//                    default:
//                        break;
//                }
//            }

        });

    }

}






