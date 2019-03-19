package com.example.engosama.likein_deliver.Classes.Req_Classes.RequestDetailsClass;

import com.example.engosama.likein_deliver.Classes.Client.User;
import com.example.engosama.likein_deliver.Fragments.RequestDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestDetailsClass {
    private String _id;
    private double deliveryCost;
    private double subTotal;
    private double Total;
    private int StatusId;
    private int paymentType;
    private int isNotFound;
    private String createAt;
    private String Notes;
    private String coupon;
    private String BasketId;
    private String delivery_date;
    /*******************************/
    private String delev_id;
    private String delev_name;
    private String deliv_option_id;
    private String deliv_option_name;

    private String deliv_date;
    private String deliv_time;
    private String Seller_id;
    private String Seller_phone_number;
    private String Seller_full_name;
    private String Seller_address;
    private String hyber_name;
    private String req_date;
    private String reqState;
    private User userSeller = new User();
    /*****************************/

    private JSONObject delivery_id_Ob;
    private JSONObject deliveryOption_id_Ob;



//    private String addressDetails;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public int getIsNotFound() {
        return isNotFound;
    }

    public void setIsNotFound(int isNotFound) {
        this.isNotFound = isNotFound;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getBasketId() {
        return BasketId;
    }

    public void setBasketId(String basketId) {
        BasketId = basketId;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getDelev_id() {
        return delev_id;
    }

    public void setDelev_id(String delev_id) {
        this.delev_id = delev_id;
    }

    public String getDelev_name() {
        return delev_name;
    }

    public void setDelev_name(String delev_name) {
        this.delev_name = delev_name;
    }

    public String getDeliv_option_id() {
        return deliv_option_id;
    }

    public void setDeliv_option_id(String deliv_option_id) {
        this.deliv_option_id = deliv_option_id;
    }

    public String getDeliv_option_name() {
        return deliv_option_name;
    }

    public void setDeliv_option_name(String deliv_option_name) {
        this.deliv_option_name = deliv_option_name;
    }

    public String getDeliv_date() {
        return deliv_date;
    }

    public void setDeliv_date(String deliv_date) {
        this.deliv_date = deliv_date;
    }

    public String getDeliv_time() {
        return deliv_time;
    }

    public void setDeliv_time(String deliv_time) {
        this.deliv_time = deliv_time;
    }

    public String getSeller_id() {
        return Seller_id;
    }

    public void setSeller_id(String seller_id) {
        Seller_id = seller_id;
    }

    public String getSeller_phone_number() {
        return Seller_phone_number;
    }

    public void setSeller_phone_number(String seller_phone_number) {
        Seller_phone_number = seller_phone_number;
    }

    public String getSeller_full_name() {
        return Seller_full_name;
    }

    public void setSeller_full_name(String seller_full_name) {
        Seller_full_name = seller_full_name;
    }

    public String getSeller_address() {
        return Seller_address;
    }

    public void setSeller_address(String seller_address) {
        Seller_address = seller_address;
    }

    public String getHyber_name() {
        return hyber_name;
    }

    public void setHyber_name(String hyber_name) {
        this.hyber_name = hyber_name;
    }

    public String getReq_date() {
        return req_date;
    }

    public void setReq_date(String req_date) {
        this.req_date = req_date;
    }

    public String getReqState() {
        return reqState;
    }

    public void setReqState(String reqState) {
        this.reqState = reqState;
    }

    public User getUserSeller() {
        return userSeller;
    }

    public void setUserSeller(User userSeller) {
        this.userSeller = userSeller;
    }

    public JSONObject getDelivery_id_Ob() {
        return delivery_id_Ob;
    }

    public void setDelivery_id_Ob(JSONObject delivery_id_Ob) {
        this.delivery_id_Ob = delivery_id_Ob;
    }

    public JSONObject getDeliveryOption_id_Ob() {
        return deliveryOption_id_Ob;
    }

    public void setDeliveryOption_id_Ob(JSONObject deliveryOption_id_Ob) {
        this.deliveryOption_id_Ob = deliveryOption_id_Ob;
    }

    public RequestDetailsClass(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        
        _id = jsonObject.optString("_id");
        deliveryCost = jsonObject.optDouble("deliveryCost");
        subTotal = jsonObject.optDouble("subTotal");
        Total = jsonObject.optDouble("Total");
        StatusId = jsonObject.optInt("StatusId");
        paymentType = jsonObject.optInt("paymentType");
        createAt = jsonObject.optString("createAt");
        Notes = jsonObject.optString("Notes");
        isNotFound = jsonObject.optInt("isNotFound");
        coupon = jsonObject.optString("coupon");
        BasketId = jsonObject.optString("BasketId");
        req_date = createAt.split("T")[0];
        delivery_date = jsonObject.optString("delivery_date");
        /********** Delivery Date and time***********************/
        if(delivery_date != null && !delivery_date.equals("null")){
            String tmp =delivery_date;
            deliv_date = tmp.split("T")[0];
            tmp = delivery_date;
            deliv_time = tmp.split("T")[1].substring(0, 5);
        }

        /****************************For sub Object*******************************/
        try {
            Seller_id = jsonObject.getJSONObject("user_id").getString("_id");
            Seller_phone_number = jsonObject.getJSONObject("user_id").getString("phone_number");
            Seller_full_name = jsonObject.getJSONObject("user_id").getString("full_name");
            Seller_address = jsonObject.getJSONObject("user_id").getString("address");
            hyber_name = jsonObject.getJSONArray("items").getJSONObject(1).getString("name");

            delivery_id_Ob = jsonObject.getJSONObject("delivery_id");
            delev_id = delivery_id_Ob.getString("_id");
            delev_name = delivery_id_Ob.getString("name");
            deliveryOption_id_Ob = jsonObject.getJSONObject("deliveryOption_id");
            deliv_option_id = deliveryOption_id_Ob.getString("_id");
            deliv_option_name = deliveryOption_id_Ob.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /**********************************************************/




    }
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
//            jsonObject.put("paymentType", paymentType);
            jsonObject.put("StatusId", StatusId);
            jsonObject.put("delivery_id_array", delivery_id_Ob);
            jsonObject.put("deliveryOption_id_array", deliveryOption_id_Ob);
            jsonObject.put("_id", _id);
            jsonObject.put("createAt", createAt);
            jsonObject.put("Notes", Notes);
            jsonObject.put("coupon", coupon);
            jsonObject.put("BasketId", BasketId);
            jsonObject.put("delivery_date", delivery_date);
            jsonObject.put("isNotFound", isNotFound);
            jsonObject.put("deliveryCost", deliveryCost);
            jsonObject.put("subTotal", subTotal);
            jsonObject.put("Total", Total);
            jsonObject.getJSONArray("deliveryOption_id").put(0, deliv_option_id);
            jsonObject.getJSONArray("deliveryOption_id").put(1, deliv_option_name);
            jsonObject.getJSONArray("delivery_id").put(0, delev_id);
            jsonObject.getJSONArray("delivery_id").put(1, delev_name);
//

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

//    private void setDetailsDataMap() {
//        RequestDetails.DetailsDataMap.put("_id", _id);
//        RequestDetails.DetailsDataMap.put("reqStatusId", reqState);
//        RequestDetails.DetailsDataMap.put("reqDate", req_date);
//        RequestDetails.DetailsDataMap.put("sellerName", userSeller.getFullName());
//        RequestDetails.DetailsDataMap.put("sellerPhone", userSeller.getPhoneNumber());
//        RequestDetails.DetailsDataMap.put("sellerAddress", userSeller.getAddress());
////        RequestDetails.DetailsDataMap.put("sellerNotes", Notes);
//        switch (StatusId){
//            case 4 :
//                reqState = "بانتظار استلام السائق";
//                break;
//            case 5 :
//                reqState = "قيد التوصيل";
//                break;
//            case 6 :
//                reqState = "تم الوصيل";
//                break;
//            case 8 :
//                reqState = "ملغي من قبل الادارة";
//                break;
//            case 9 :
//                reqState = "ملغي من قبل العميل";
//                break;
//            default:
//                break;
//        }
//        String paymentType;
//        switch (paymentType) {
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
