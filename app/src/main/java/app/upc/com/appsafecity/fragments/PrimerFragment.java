package app.upc.com.appsafecity.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.upc.com.appsafecity.R;
import app.upc.com.appsafecity.adapters.NoticiasAdapter;
import app.upc.com.appsafecity.models.Noticia;

import static app.upc.com.appsafecity.Constants.URL_NOTICIAS;


public class PrimerFragment extends Fragment {

    View view,v;
    Context context;

    private static final String TAG = PrimerFragment.class.getSimpleName();
    private List<Noticia> noticias;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager managerLayout;
    private NoticiasAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = inflater.inflate(R.layout.fragment_primer, container, false);

        recyclerView =  v.findViewById(R.id.rvNews);
        noticias = new ArrayList<>();
        loadUrlDataNews();

        managerLayout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(managerLayout);

        adapter = new NoticiasAdapter(getContext(), noticias);
        recyclerView.setAdapter(adapter);
        return v;

    }


    private void loadUrlDataNews() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET,
                URL_NOTICIAS, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("data");

                    for (int i = 0; i < array.length(); i++){

                        JSONObject jo = array.getJSONObject(i);

                        Noticia noticia = new Noticia(
                            jo.getInt("id"),
                            jo.getString("titulo"),
                            jo.getString("contenido"),
                            jo.getString("fecha_publicacion"),
                            jo.getString("fuente"),
                            jo.getString("fotos")
                        );
                        noticias.add(noticia);
                    }

                    adapter = new NoticiasAdapter(getActivity().getApplicationContext(), noticias);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


}
