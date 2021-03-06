package ginie.common;

import com.google.common.collect.ImmutableMap;
import ginie.enums.Result;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dhruvr on 5/8/16.
 */
public class JsonUtils {


    public static List<String> getList(JSONArray jArray) {
        List<String> listdata = new ArrayList<String>();
        if (jArray != null) {
            for (int i = 0; i < jArray.length(); i++) {
                listdata.add(jArray.get(i).toString());
            }
        }
        return listdata;
    }


    public static List<String> getListInLower(JSONArray jArray) {
        List<String> listdata = new ArrayList<String>();
        if (jArray != null) {
            for (int i = 0; i < jArray.length(); i++) {
                listdata.add(jArray.get(i).toString().toLowerCase());
            }
        }
        return listdata;
    }


    public static String getResultJson(String key, Result restValues) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key, restValues);
        return jsonObject.toString();
    }


    public static String getResultJson(String key, String restValues) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key, restValues);
        return jsonObject.toString();
    }


}
