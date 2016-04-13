package com.gleamsoft.developer.devunauto.ui.controller;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

/**
 * Created by Developer on 12/04/2016.
 */
public class Sitios {

    boolean add = true;

    public boolean Save(String IDGoogle, String username, String nombre, String direccion, String latlng) {
        ParseObject sitios = new ParseObject("MisSitios");
        sitios.put("IDGoogle", IDGoogle);
        sitios.put("username", username);
        sitios.put("Nombre", nombre);
        sitios.put("Direccion", direccion);
        sitios.put("LatLng", latlng);
        sitios.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    //si fue añadido correctamente
                    add = false;

                } else {
                    // si no fue añadido
                    add = true;
                }
            }
        });
        return add;
    }


}
