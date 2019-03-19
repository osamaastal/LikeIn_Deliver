package com.example.engosama.likein_deliver.Classes.Client;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class User_Info {
    private String id,email,token;
private Long lat,lng;
    public User_Info(Context mcontext) {
        final SharedPreferences sp=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
        id=sp.getString("id",null);
        token=sp.getString("token",null);
        email=sp.getString("email",null);
        lat=sp.getLong("lat",0);
        lng=sp.getLong("lng",0);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public Long getLng() {
        return lng;
    }

    public void setLng(Long lng) {
        this.lng = lng;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
