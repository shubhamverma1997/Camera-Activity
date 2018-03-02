package com.example.shubham.nextvac;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shubham on 1/21/18.
 */

public class ParseJson {

    public static String parse(String response)
    {
        if(response==null)
        {
            return null;
        }
        String image="";
        String type="";
        try {
            JSONObject jsonObject=new JSONObject(response);
            image=jsonObject.getString("image");
            type=jsonObject.getString("type");
        }
        catch (JSONException j)
        {
            j.printStackTrace();
        }
        if (image!="")
        return image;
        else
            return null;
    }

    public static String retImage(JSONObject jsonObject)
    {
        JSONObject js=jsonObject;
        String img="";
        try {
            img = js.getString("image");
        }
        catch (JSONException j)
        {
            j.printStackTrace();
        }
        return img;
    }

}
