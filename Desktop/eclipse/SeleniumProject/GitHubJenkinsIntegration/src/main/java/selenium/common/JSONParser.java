/*package selenium.common;

*//***********************************************************************************************************************
 * FileName - JSONParser.java
 * 
 * (c) Disney. All rights reserved.
 * 
 * $Author: kaiy001 $
 * $Revision: #1 $
 * $Change: 715510 $
 * $Date: Sep 7, 2012 $
 ***********************************************************************************************************************//*  

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

*//**
 * A class filled with static functions that essentially supresses every JSON 
 * exception.
 * <p>
 * SAP - IMO, this class should be discouraged from any serious use since the 
 * caller will not receive feed back if an error is encountered.  Swallowing 
 * the exception is absurd because the functions can simply check for the 
 * existence of any field with {@link org.json.JSONObject.has(String)}.
 * 
 * @author kaiy001
 *//*
public class JSONParser {

    *//**
     * Given a file name within the ./json/ folder, it will read the file and
     * return the contents as a string.
     * 
     * @param
     * @return Contents of the file as a String
     *//*
    static public String parse(String fileName) {
        try {
            return Utils.readFileAsString(Constants.CURRENT_DIR + "json" + 
                                          Constants.DIR_SEPARATOR + fileName);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    *//**
     * Gets the String stored in the JSONObject with the given key
     * @param obj The JSONObject to use
     * @param key The locator key to use
     * @return Value as a String
     *//*
    static public String getString(JSONObject obj, String key){
        String output = "";
        
        try {
            output = obj.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.getDefaultLogger().warning("Failed to locate JSON object...");
        }
        
        return output;
    }
    
    *//**
     * Gets the Integer stored in the JSONObject with the given key
     * @param obj The JSONObject to use
     * @param key The locator key to use
     * @return Value as a Integer
     *//*
    static public int getInt(JSONObject obj, String key) {
        int output = -1;
        
        try {
            output = obj.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.getDefaultLogger().warning("Failed to locate JSON object...");
        }
        
        return output;
    }
    
    *//**
     * Gets the Boolean value stored in the JSONObject with the given key
     * @param obj The JSONObject to use
     * @param key The locator key to use
     * @return Value as a Boolean
     *//*
    static public boolean getBoolean(JSONObject obj, String key) {
        boolean output = false;
        
        try {
            output = obj.getBoolean(key);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.getDefaultLogger().warning("Failed to locate JSON object...");
        }
        
        return output;
    }
    
    *//**
     * Gets the JSONObject at given index within the JSONArray
     * @param array The JSONArray to use
     * @param index Index of where the JSONObject is
     * @return The JSONObject at the given index
     *//*
    static public JSONObject getObject(JSONArray array, int index) {
        JSONObject obj = null;;
        
        try {
            obj = (JSONObject) array.get(index);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.getDefaultLogger().warning("Failed to locate JSON array object...");
        }
        
        return obj;
    }
}


*/