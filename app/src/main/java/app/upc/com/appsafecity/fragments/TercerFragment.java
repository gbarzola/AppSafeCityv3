package app.upc.com.appsafecity.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.upc.com.appsafecity.R;

import static android.app.Activity.RESULT_OK;
import static android.os.Environment.getExternalStoragePublicDirectory;


public class TercerFragment extends Fragment {

    Button botonFoto;
    ImageView imagenFoto;
    String rutaFoto;
    View v;
    Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         // Inflate the layout for this fragment
         v = inflater.inflate(R.layout.fragment_tercer, container, false);
         botonFoto = v.findViewById(R.id.btnCapturaFoto);
         imagenFoto = v.findViewById(R.id.imgIncidenciaPrincipal);
         spinner = v.findViewById(R.id.spnTipo);
         List<String> list = new ArrayList<String>();
         list.add("Robo");
         list.add("Atropello");
         list.add("Consumo de drogas");
         list.add("Pelea callejera   ");
         ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,list);
         arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
         spinner.setAdapter(arrayAdapter);


        if (Build.VERSION.SDK_INT >=23 ){
            requestPermissions(new  String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
        };

        botonFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dispatchPictureTakeAction();
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == 1){
                Bitmap bitmap = BitmapFactory.decodeFile(rutaFoto);
                imagenFoto.setImageBitmap(bitmap);
            }
        }
    }

    public void dispatchPictureTakeAction(){
        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePic.resolveActivity(getContext().getPackageManager()) != null) {
            File photoFile = null;
            photoFile = createPhotofile();

            if (photoFile != null) {
                rutaFoto = photoFile.getAbsolutePath();
                Uri photoURI = FileProvider.getUriForFile( getContext(),"app.upc.com.appsafecity.fileprovider",photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                startActivityForResult(takePic,1);
            }
        }
    }

    private File createPhotofile() {
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(name, ".jpg",storageDir);
        } catch (IOException e) {
            Log.d("my log","Excep : " + e.toString());

        }
        return image;

    }
}
