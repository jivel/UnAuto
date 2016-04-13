package com.gleamsoft.developer.devunauto.model;

/**
 * Created by Developer on 12/04/2016.
 */
public class MisSitios {
    private String IdSitio;
    private String IdGoogle;
    private String username;
    private String Nombre;
    private String Direccion;
    private String LatLng;

    public MisSitios(String idSitio, String idGoogle, String username, String nombre, String direccion, String latLng) {
        IdSitio = idSitio;
        IdGoogle = idGoogle;
        this.username = username;
        Nombre = nombre;
        Direccion = direccion;
        LatLng = latLng;
    }

    public String getIdSitio() {
        return IdSitio;
    }

    public String getIdGoogle() {
        return IdGoogle;
    }

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getLatLng() {
        return LatLng;
    }
}
