package helper;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONHelper {
    public static String[] parseJSONToStringArray (String jsonArrayString) {
        if (jsonArrayString == null) {
            return new String[0];
        }
        
        String[] arr = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonArrayString);

            arr = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                arr[i] = jsonArray.getString(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    
        return arr;
    }
}
