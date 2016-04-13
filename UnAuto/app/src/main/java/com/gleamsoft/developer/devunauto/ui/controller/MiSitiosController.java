package com.gleamsoft.developer.devunauto.ui.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gleamsoft.developer.devunauto.R;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * Created by Developer on 12/04/2016.
 */
public class MiSitiosController {
    Context context;
    Sitios sitios;

    public MiSitiosController(Context ctx) {
        this.context = ctx;
    }

    public void GuardarMiSitio(final Place place) {
        //capturar el usuario en session
        ParseUser obtenerUsuario = ParseUser.getCurrentUser();
        final String mUserName = obtenerUsuario.getUsername();// usuario capturado
        final String mIdGoogle = place.getId();
        CharSequence s = place.getAddress();
        final String mAddress = s.toString();
        LatLng latLng = place.getLatLng();
        final String mCordenadas = latLng.toString();
        LayoutInflater li = LayoutInflater.from(context);
        View filaSitio = li.inflate(R.layout.dialo_nombre_sitio, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(filaSitio);
        final EditText userInput = (EditText) filaSitio
                .findViewById(R.id.edtNombreSitio);
        userInput.setText(place.getName());
        alertDialogBuilder.setTitle(R.string.nombre_lugar_dialog);
        alertDialogBuilder
                // .setMessage("Desea cambiar el nombre?")
                .setCancelable(false)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String nombresitio = userInput.getText().toString();
                        ParseObject sitios = new ParseObject("MisSitios");
                        sitios.put("IDGoogle", mIdGoogle);
                        sitios.put("username", mUserName);
                        sitios.put("Nombre", nombresitio);
                        sitios.put("Direccion", mAddress);
                        sitios.put("LatLng", mCordenadas);
                        sitios.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    //si fue añadido correctamente
                                    Toast.makeText(context, "Sitio guardado correctamente!", Toast.LENGTH_SHORT).show();

                                } else {
                                    // si no fue añadido
                                    Toast.makeText(context, "Sorry!,no se pudo guardar el sitio!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        //boolean estado=sitios.Save(mIdGoogle,mUserName,nombresitio,mAddress,mCordenadas);

                        //   Log.d("ID ",  mIdGoogle + " nombre " + nombresitio + " usuario" + mUsername+ " direc "+ mAddress + "cord" + mCordenadas );
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
