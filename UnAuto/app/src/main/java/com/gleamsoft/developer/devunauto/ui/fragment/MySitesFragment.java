package com.gleamsoft.developer.devunauto.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gleamsoft.developer.devunauto.R;
import com.gleamsoft.developer.devunauto.adaptador.AdaptadorMisSitios;
import com.gleamsoft.developer.devunauto.model.MisSitios;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MySitesFragment extends Fragment {
    private ListView mSitios;
    private List<MisSitios> mLIstaSitios;
    AdaptadorMisSitios adaptadorMisSitios;

    public MySitesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View viewFragmento = inflater.inflate(R.layout.fragment_my_sites, container, false);

        mSitios = (ListView) viewFragmento.findViewById(R.id.list_mis_sitios);
        mLIstaSitios = new ArrayList<>();
        adaptadorMisSitios = new AdaptadorMisSitios(getActivity(), mLIstaSitios);
        //capturar el usuario en session
        ParseUser obtenerUsuario = ParseUser.getCurrentUser();
        String userName = obtenerUsuario.getUsername();// usuario capturado

        ParseQuery<ParseObject> query = ParseQuery.getQuery("MisSitios");
        query.whereEqualTo("username", userName);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> favoritoList, ParseException e) {
                if (e == null) {
                    Log.d("Mis sitios", "Sitios " + favoritoList.size() + " Sitios");

                    for (ParseObject object : favoritoList) {
                        String idSitio = (String) object.getObjectId();
                        String usename = (String) object.get("username");
                        String idGoogle = (String) object.get("IDGoogle");
                        String nombre = (String) object.get("Nombre");
                        String direccion = (String) object.get("Direccion");
                        String LatLng = (String) object.get("LatLng");
                        MisSitios misSitios = new MisSitios(idSitio, idGoogle, usename, nombre, direccion, LatLng);

                        mLIstaSitios.add(misSitios);
                    }
                    mSitios.setAdapter(adaptadorMisSitios);
                    adaptadorMisSitios.notifyDataSetChanged();
                } else {
                    Log.d("Mis sitios", "Sitios: " + e.getMessage());
                }
            }
        });

        return viewFragmento;


    }

}
