package com.example.engosama.likein_deliver.Classes.Client;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


/************************* Mo’min J.Abusaada *************************/
//
//	point.java


public class User {

    private String id;
    private String address;
//    private String city;
//    private String createAt;
//    private String email;
    private String name;
//    private String image;
//    private boolean isBlock;
//    private boolean isVerify;
//    private float lat;
//    private float lng;
    private String phoneNumber;
//    private String token;
//    private String password;
//    private String fcmToken;
//    private String verifyCode;
    private int wallet;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
//    public void setCity(String city){
//        this.city = city;
//    }
//    public String getCity(){
//        return this.city;
//    }
//    public void setCreateAt(String createAt){
//        this.createAt = createAt;
//    }
//    public String getCreateAt(){
//        return this.createAt;
//    }
//    public void setEmail(String email){
//        this.email = email;
//    }
//    public String getEmail(){
//        return this.email;
//    }
    public void setFullName(String name){
        this.name = name;
    }
    public String getFullName(){
        return this.name;
    }
//    public void setImage(String image){
//        this.image = image;
//    }
//    public String getImage(){
//        return this.image;
//    }
//    public void setIsBlock(boolean isBlock){
//        this.isBlock = isBlock;
//    }
//    public boolean isIsBlock()
//    {
//        return this.isBlock;
//    }
//    public void setIsVerify(boolean isVerify){
//        this.isVerify = isVerify;
//    }
//    public boolean isIsVerify()
//    {
//        return this.isVerify;
//    }
//    public void setLat(float lat){
//        this.lat = lat;
//    }
//    public float getLat(){
//        return this.lat;
//    }
//    public void setLng(float lng){
//        this.lng = lng;
//    }
//    public float getLng(){
//        return this.lng;
//    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
//    public void setToken(String token){
//        this.token = token;
//    }
//    public String getToken(){
//        return this.token;
//    }
//    public void setVerifyCode(String verifyCode){
//        this.verifyCode = verifyCode;
//    }
//    public String getVerifyCode(){
//        return this.verifyCode;
//    }
//    public void setWallet(int wallet){
//        this.wallet = wallet;
//    }
//    public int getWallet(){
//        return this.wallet;
//    }
//    public boolean isBlock() {
//        return isBlock;
//    }
//    public void setBlock(boolean block) {
//        isBlock = block;
//    }
//    public boolean isVerify() {
//        return isVerify;
//    }
//    public void setVerify(boolean verify) {
//        isVerify = verify;
//    }
//    public String getFcmToken() {
//        return fcmToken;
//    }
//    public void setFcmToken(String fcmToken) {
//        this.fcmToken = fcmToken;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public User(){}
    public User(JSONObject jsonObject){
        if(jsonObject == null){
            return;
        }
        id = (String) jsonObject.opt("_id");
        address = jsonObject.opt("address").toString();
////        city = jsonObject.opt("city").toString();
//        createAt = jsonObject.opt("createAt").toString();
//        email = jsonObject.opt("email").toString();
////        password = jsonObject.opt("password").toString();
        name = jsonObject.opt("full_name").toString();
//        image = jsonObject.opt("image").toString();
//        isBlock = jsonObject.optBoolean("isBlock");
////        isVerify = jsonObject.optBoolean("isVerify");
//        lat = jsonObject.optLong("lat");
//        lng = jsonObject.optLong("lng");
        phoneNumber = jsonObject.opt("phone_number").toString();
//        try {
//            token = jsonObject.opt("token").toString();
//        }catch (Exception exc){}
//        fcmToken = jsonObject.opt("fcmToken").toString();
//        verifyCode = jsonObject.opt("verify_code").toString();
        wallet = jsonObject.optInt("wallet");
    }

    /**
     * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
     */
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("_id", id);
            jsonObject.put("address", address);
////            jsonObject.put("city", city);
//            jsonObject.put("createAt", createAt);
//            jsonObject.put("email", email);
            jsonObject.put("name", name);
//            jsonObject.put("image", image);
//            jsonObject.put("isBlock", isBlock);
//            jsonObject.put("isVerify", isVerify);
//            jsonObject.put("lat", lat);
//            jsonObject.put("lng", lng);
            jsonObject.put("phone_number", phoneNumber);
//            jsonObject.put("token", token);
//            jsonObject.put("password", password);
////            jsonObject.put("verify_code", verifyCode);
//            jsonObject.put("wallet", wallet);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }
}