package app.upc.com.appsafecity.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import app.upc.com.appsafecity.R;


public class SegundoFragment extends Fragment {

    private static final int REQUEST_CALL = 1 ;
    private EditText mEditextNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.fragment_segundo, container, false);

        String[] menuItems = {"Serenazgo - 994 671 777","Comisaria 1 - (01) 2641932","Comisaria 2 - (01) 2856498", "Bomberos zona 1 - 3991111"};


        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItems
        );

        ListView listView = (ListView) view.findViewById(R.id.listaTelefonos);

        listView.setAdapter(listViewAdapter);

        mEditextNumber = view.findViewById(R.id.edit_text_number);
        ImageView imageCall = view.findViewById(R.id.image_call);

        imageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();

            }
        });


        return view;
    }

    private void makePhoneCall(){
        String number = mEditextNumber.getText().toString();
        if (number.trim().length()>0){
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);

            }else {
                String dial= "tel:"+ number;
                startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
            }

        }else{
            Toast.makeText(getContext(),"ingrese numero",Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else {
                Toast.makeText(getContext(),"PERMISO DENEGADO",Toast.LENGTH_SHORT).show();
            }

        }
    }

    

}
