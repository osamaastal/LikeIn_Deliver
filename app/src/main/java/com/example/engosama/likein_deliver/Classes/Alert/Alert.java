package com.example.engosama.likein_deliver.Classes.Alert;

import org.json.JSONException;
import org.json.JSONObject;

public class Alert {
    String title;
    String msg;
    String dt_date;
    String date;
    String time;
    String _id;
    String from;
    String user_id;
    int type;
    String body_parms;
    boolean isRead;
//    String alertImg;

    public Alert() {
    }

    public Alert(String title, String msg, String dt_date, String _id, String from,
                 String user_id, int type, String body_parms, boolean isRead, String alertImg) {
        this.title = title;
        this.msg = msg;
        this.dt_date = dt_date;
        this._id = _id;
        this.from = from;
        this.user_id = user_id;
        this.type = type;
        this.body_parms = body_parms;
        this.isRead = isRead;
//        this.alertImg = alertImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDt_date() {
        return dt_date;
    }

    public void setDt_date(String dt_date) {
        this.dt_date = dt_date;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBody_parms() {
        return body_parms;
    }

    public void setBody_parms(String body_parms) {
        this.body_parms = body_parms;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
//    public String getAlertImg() {
//        return alertImg;
//    }
//
//    public void setAlertImg(String alertImg) {
//        this.alertImg = alertImg;
//    }

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */

    public Alert(JSONObject jsonObject){
        if(jsonObject == null){
            return;
        }
        type = jsonObject.optInt("type");
        title = jsonObject.opt("title").toString();
        msg = jsonObject.opt("msg").toString();
        dt_date = jsonObject.opt("dt_date").toString();
        /***********date and time*********/
        String tmp = dt_date;
        date = (tmp.split("T"))[0];
        tmp = dt_date;
        time = (tmp.split("T"))[1].substring(0, 5);
        /**********************/
        _id = jsonObject.opt("_id").toString();
        from = jsonObject.opt("from").toString();
        user_id = jsonObject.opt("user_id").toString();
        body_parms = jsonObject.opt("body_parms").toString();
//        alertImg = "http://images6.fanpop.com/image/photos/39700000/cute-wallpaper-flowerdrop-teen-titans-vs-young-justice-39712975-1024-768.jpg";
        isRead = jsonObject.optBoolean("isRead");

    }

    /**
     * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
     */

    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", title);
            jsonObject.put("msg", msg);
            jsonObject.put("dt_date", dt_date);
            jsonObject.put("_id", _id);
            jsonObject.put("from", from);
            jsonObject.put("user_id", user_id);
            jsonObject.put("type", type);
            jsonObject.put("body_parms", body_parms);
            jsonObject.put("isRead", isRead);
//            jsonObject.put("alertImg", alertImg);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }
}
