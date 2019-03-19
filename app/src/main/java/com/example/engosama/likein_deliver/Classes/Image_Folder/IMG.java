package com.example.engosama.likein_deliver.Classes.Image_Folder;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IMG {
    private String public_id;
    private String version;
    private String signature;
    private int width;
    private int height;
    private String format;
    private String resource_type;
    private String created_at;
    private List<String> tags;
    private int bytes;
    private String type;
    private String etag;
    private boolean placeholder;
    private String url;
    private String secure_url;
    private String original_filename;

    public IMG(JSONObject jsonObject){
        if(jsonObject == null){
            return;
        }
        public_id = (String) jsonObject.opt("public_id");
        version = (String) jsonObject.opt("version");
        signature = (String) jsonObject.opt("signature");
        width = (int) jsonObject.opt("width");
        height = (int) jsonObject.opt("height");
        format = (String) jsonObject.opt("format");
        resource_type = (String) jsonObject.opt("resource_type");
        created_at = (String) jsonObject.opt("created_at");
        /****Array********/
        tags= new ArrayList<String>();
        try {
            JSONArray tagsJson = jsonObject.getJSONArray("tags");
            for (int i=0; i<tagsJson.length() ;i++){
                tags.add(tagsJson.get(i).toString());
            }
        } catch (JSONException e) {
            Log.i("Tags-Error",e.getMessage());
        }

        /****************/
        bytes = (int) jsonObject.opt("bytes");
        type = (String) jsonObject.opt("type");
        etag = (String) jsonObject.opt("etag");
        placeholder = (boolean) jsonObject.opt("placeholder");
        url = (String) jsonObject.opt("url");
        secure_url = (String) jsonObject.opt("secure_url");
        original_filename = (String) jsonObject.opt("original_filename");

    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("public_id", public_id);
            jsonObject.put("version", version);
            jsonObject.put("signature", signature);
            jsonObject.put("width", width);
            jsonObject.put("height", height);
            jsonObject.put("format", format);
            jsonObject.put("resource_type", resource_type);
            jsonObject.put("created_at", created_at);
//            jsonObject.put("tags", tags);
            jsonObject.put("bytes", bytes);
            jsonObject.put("type", type);
            jsonObject.put("etag", etag);
            jsonObject.put("placeholder", placeholder);
            jsonObject.put("url", url);
            jsonObject.put("secure_url", secure_url);
            jsonObject.put("original_filename", original_filename);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }
}
