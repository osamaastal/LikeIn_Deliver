package com.example.engosama.likein_deliver.Classes.Req_Classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.engosama.likein_deliver.Activities.Requests;
import com.example.engosama.likein_deliver.Classes.Client.User;
import com.example.engosama.likein_deliver.Fragments.RequestDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReqCompletedClass {
    //    private String delev_id;
//    private String delev_name;
//    private String deliv_option_id;
//    private String deliv_option_name;
//    private String Notes;
//    private String coupon;
//    private String BasketId;
//    private String delivery_date;
//    private double deliveryCost;
//    private double subTotal;
//    private int paymentType;
//    private JSONObject delivery_id_array;
//    private JSONObject deliveryOption_id_array;
    private User userSeller;
    private String  username;
    private String req_time;
    private String req_date;
    private String addressDetails;
    private String createAt;
    private String _id;
    private int StatusId;
    private double Total;
    private double lat;
    private double lng;
    private int isNotFound;
    private int no_of_products;
    private boolean isBasket;
    private String hyber_name;
    public static String reqState;
    private String UserPhoneNumber;

    public ReqCompletedClass(String reqNumber, String username, String hyber_name,
                             String req_time, String req_date, String addressDetails,
                             int no_of_products) {
        this._id = reqNumber;
        this.username = username;
        this.hyber_name=  hyber_name;
        this.req_time = req_time;
        this.req_date = req_date;
        this.addressDetails = addressDetails;
        this.no_of_products = no_of_products;
//        this.Total = Total;
    }

    public ReqCompletedClass() {
    }

//    public String getReqNumber() {
//        return reqNumber;
//    }
//
//    public void setReqNumber(String reqNumber) {
//        this.reqNumber = reqNumber;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReq_time() {
        return req_time;
    }

    public void setReq_time(String req_time) {
        this.req_time = req_time;
    }

    public String getReq_date() {
        return req_date;
    }

    public void setReq_date(String req_date) {
        this.req_date = req_date;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public int getNo_of_products() {
        return no_of_products;
    }

    public void setNo_of_products(int no_of_products) {
        this.no_of_products = no_of_products;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
//
//    public String getNotes() {
//        return Notes;
//    }
//
//    public void setNotes(String notes) {
//        Notes = notes;
//    }
//
//    public String getCoupon() {
//        return coupon;
//    }
//
//    public void setCoupon(String coupon) {
//        this.coupon = coupon;
//    }
//
//    public String getBasketId() {
//        return BasketId;
//    }
//
//    public void setBasketId(String basketId) {
//        BasketId = basketId;
//    }
//
//    public String getDelivery_date() {
//        return delivery_date;
//    }
//
//    public void setDelivery_date(String delivery_date) {
//        this.delivery_date = delivery_date;
//    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

//    public double getDeliveryCost() {
//        return deliveryCost;
//    }
//
//    public void setDeliveryCost(double deliveryCost) {
//        this.deliveryCost = deliveryCost;
//    }
//
//    public double getSubTotal() {
//        return subTotal;
//    }
//
//    public void setSubTotal(double subTotal) {
//        this.subTotal = subTotal;
//    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public int getIsNotFound() {
        return isNotFound;
    }

    public void setIsNotFound(int isNotFound) {
        this.isNotFound = isNotFound;
    }

//    public int getPaymentType() {
//        return paymentType;
//    }
//
//    public void setPaymentType(int paymentType) {
//        this.paymentType = paymentType;
//    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }

    public boolean isBasket() {
        return isBasket;
    }

    public void setBasket(boolean basket) {
        isBasket = basket;
    }

//    public JSONObject getDelivery_id_map() {
//        return delivery_id_array;
//    }
//
//    public void setDelivery_id_map(JSONObject delivery_id_array) {
//        this.delivery_id_array = delivery_id_array;
//    }
//
//    public JSONObject getDeliveryOption_id_map() {
//        return deliveryOption_id_array;
//    }
//
//    public void setDeliveryOption_id_map(JSONObject deliveryOption_id_array) {
//        this.deliveryOption_id_array = deliveryOption_id_array;
//    }
//
//    public String getDelev_id() {
//        return delev_id;
//    }
//
//    public void setDelev_id(String delev_id) {
//        this.delev_id = delev_id;
//    }
//
//    public String getDelev_name() {
//        return delev_name;
//    }
//
//    public void setDelev_name(String delev_name) {
//        this.delev_name = delev_name;
//    }
//
//    public String getDeliv_option_id() {
//        return deliv_option_id;
//    }
//
//    public void setDeliv_option_id(String deliv_option_id) {
//        this.deliv_option_id = deliv_option_id;
//    }
//
//    public String getDeliv_option_name() {
//        return deliv_option_name;
//    }
//
//    public void setDeliv_option_name(String deliv_option_name) {
//        this.deliv_option_name = deliv_option_name;
//    }
//
//    public JSONObject getDelivery_id_array() {
//        return delivery_id_array;
//    }
//
//    public void setDelivery_id_array(JSONObject delivery_id_array) {
//        this.delivery_id_array = delivery_id_array;
//    }
//
//    public JSONObject getDeliveryOption_id_array() {
//        return deliveryOption_id_array;
//    }
//
//    public void setDeliveryOption_id_array(JSONObject deliveryOption_id_array) {
//        this.deliveryOption_id_array = deliveryOption_id_array;
//    }

    public User getUserSeller() {
        return userSeller;
    }

    public void setUserSeller(User userSeller) {
        this.userSeller = userSeller;
    }

    public String getHyber_name() {
        return hyber_name;
    }

    public void setHyber_name(String hyber_name) {
        this.hyber_name = hyber_name;
    }

    public static String getReqState() {
        return reqState;
    }

    public static void setReqState(String reqState) {
        ReqCompletedClass.reqState = reqState;
    }

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */

    public ReqCompletedClass(JSONObject jsonObject,int quantity) {
        if (jsonObject == null) {
            return;
        }

//        paymentType = jsonObject.optInt("paymentType");
//        deliveryCost = jsonObject.optDouble("deliveryCost");
//        subTotal = jsonObject.optDouble("subTotal");
//        Notes = jsonObject.opt("Notes").toString();
//        coupon = jsonObject.opt("coupon").toString();
//        if (jsonObject.opt("BasketId") != null)
//            BasketId = jsonObject.opt("BasketId").toString();
//        delivery_date = jsonObject.opt("delivery_date").toString();
        /** JSONObjects **/
//        delivery_id_array = jsonObject.optJSONObject("delivery_id");
//        deliveryOption_id_array = jsonObject.optJSONObject("deliveryOption_id");
        _id = jsonObject.opt("_id").toString();
        addressDetails = jsonObject.opt("addressDetails").toString();
        createAt = jsonObject.opt("createAt").toString();
        isNotFound = jsonObject.optInt("isNotFound");
        StatusId = jsonObject.optInt("StatusId");
        lat = jsonObject.optDouble("lat");
        lng = jsonObject.optDouble("lng");
        Total = jsonObject.optDouble("Total");
        isBasket = jsonObject.optBoolean("isBasket");
        userSeller = new User();
        try {
            username = jsonObject.getJSONObject("user_id").optString("full_name");

            userSeller.setFullName(jsonObject.getJSONObject("user_id").optString("full_name"));
            userSeller.setAddress(jsonObject.getJSONObject("user_id").optString("address"));
            userSeller.setPhoneNumber(jsonObject.getJSONObject("user_id").optString("phone_number"));
        } catch (JSONException e) {
            e.printStackTrace();
        }




//        userDriver = new User(jsonObject.optJSONObject("user_id"));

        /**#########################################################################*/
        try {
            hyber_name = jsonObject.getJSONArray("items").getJSONObject(0)
                    .getJSONObject("supplier_id").opt("name").toString();
        } catch (Exception e) {
            Toast.makeText(Requests.context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        /**********************/
        /** I will change this soon*/
        if(isBasket){
            no_of_products = 1;
        }else {
            no_of_products = quantity;
        }

//        SharedPreferences sp = Requests.context.getSharedPreferences("Login", Context.MODE_PRIVATE);
//        username = sp.getString("userName",null);
        /****************************/

//        delev_id = delivery_id_array.opt("_id").toString();
//        delev_name = delivery_id_array.opt("name").toString();
//        deliv_option_id = deliveryOption_id_array.opt("_id").toString();
//        deliv_option_name = deliveryOption_id_array.opt("name").toString();
        /** For req_date and req_time */
        String tmp = createAt;
        req_date = (tmp.split("T"))[0];
        tmp = createAt;
        req_time = (tmp.split("T"))[1].substring(0, 5);
//        Toast.makeText(Requests.context, req_date, Toast.LENGTH_SHORT).show();
        /******************************/
        Requests.ReqType = StatusId;

//        setDetailsDataMap();
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
//            jsonObject.put("user_id", User);
            jsonObject.put("isNotFound", isNotFound);
//            jsonObject.put("paymentType", paymentType);
            jsonObject.put("StatusId", StatusId);
            jsonObject.put("isBasket", isBasket);
//            jsonObject.put("delivery_id_array", delivery_id_array);
//            jsonObject.put("deliveryOption_id_array", deliveryOption_id_array);
            jsonObject.put("_id", _id);
            jsonObject.put("addressDetails", addressDetails);
            jsonObject.put("createAt", addressDetails);
//            jsonObject.put("Notes", Notes);
//            jsonObject.put("coupon", coupon);
//            jsonObject.put("BasketId", BasketId);
//            jsonObject.put("delivery_date", delivery_date);
            jsonObject.put("lat", lat);
            jsonObject.put("lng", lng);
//            jsonObject.put("deliveryCost", deliveryCost);
//            jsonObject.put("subTotal", subTotal);
            jsonObject.put("Total", Total);
//            jsonObject.getJSONArray("deliveryOption_id").put(0, deliv_option_id);
//            jsonObject.getJSONArray("deliveryOption_id").put(1, deliv_option_name);
//            jsonObject.getJSONArray("delivery_id").put(0, delev_id);
//            jsonObject.getJSONArray("delivery_id").put(1, delev_name);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

//    private void setDetailsDataMap() {
//        RequestDetails.DetailsDataMap.put("reqNumber", _id);
//        switch (StatusId) {
//            case 6:
//                reqState = "مكتمل";
//                break;
//            case 4:
//                reqState = "جديد";
//                break;
//            case 5:
//                reqState = "حالي";
//                break;
//            default:
//                reqState = "null";
//                break;
//        }
//        RequestDetails.DetailsDataMap.put("reqStatusId", reqState);
//        RequestDetails.DetailsDataMap.put("reqDate", req_date);
//        RequestDetails.DetailsDataMap.put("sellerName", userSeller.getFullName());
//        RequestDetails.DetailsDataMap.put("sellerPhone", userSeller.getPhoneNumber());
//        RequestDetails.DetailsDataMap.put("sellerAddress", userSeller.getAddress());
////        RequestDetails.DetailsDataMap.put("sellerNotes", Notes);
//        String paymentType;
//        switch (StatusId) {
//            case 1:
//                paymentType = "البطاقة الإئتمانية";
//                break;
//            case 2:
//                paymentType = "فيزا";
//                break;
//            case 3:
//                paymentType = "الرصيد";
//                break;
//            default:
//                paymentType = "null";
//                break;
//        }
//        RequestDetails.DetailsDataMap.put("paymentType", paymentType);
////        RequestDetails.DetailsDataMap.put("DeliveryTimeDate", delivery_date);
////        RequestDetails.DetailsDataMap.put("deliveryOptions", deliv_option_name);
////        RequestDetails.DetailsDataMap.put("deliveryWay", delev_name);
//        RequestDetails.DetailsDataMap.put("replaceOptions", "test-replaceOptions");
////        RequestDetails.DetailsDataMap.put("couponCode", coupon);
////        RequestDetails.DetailsDataMap.put("subTotalCost", subTotal);
////        RequestDetails.DetailsDataMap.put("deliveryCost", deliveryCost);
//        RequestDetails.DetailsDataMap.put("TotalCost", Total);
//        RequestDetails.DetailsDataMap.put("hyberName", hyber_name);
//        String replace;
//        switch (isNotFound) {
//            case 1:
//                replace = "استبدال";
//                break;
//            case 2:
//                replace = "لا تستبدل";
//                break;
//            case 3:
//                replace = "لا شيء";
//                break;
//            default:
//                replace = "null";
//        }
//        RequestDetails.DetailsDataMap.put("replace", replace);
//
//    }
}

