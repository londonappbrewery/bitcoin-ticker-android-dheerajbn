package com.londonappbrewery.bitcointicker;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dheeraj on 09-Mar-19 at 7:52 PM.
 */

public class Model {

    private String mCurrency;

    public static Model fromJson(JSONObject jsonObject){

        try {
            Model model = new Model();
            model.mCurrency = jsonObject.getString("ask");
            return model;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getCurrency() {
        return mCurrency;
    }
}
