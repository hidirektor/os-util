package me.t3sl4.util.os;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class OSUtil {
    /**
     * Retrieves all stored keys and values under a given preference node.
     *
     * @param nodeName The name of the preference node.
     * @return A list of key-value pairs stored under the specified node.
     */
    public static List<String> getAllData(String nodeName) {
        Preferences prefs = Preferences.userRoot().node(nodeName);
        List<String> data = new ArrayList<>();
        try {
            for (String key : prefs.keys()) {
                data.add(key + "=" + prefs.get(key, ""));
            }
        } catch (Exception e) {
            throw new RuntimeException("Veri alınamadı: " + e.getMessage());
        }
        return data;
    }

    /**
     * Updates or sets a preference key-value pair under the specified node.
     *
     * @param nodeName The name of the preference node.
     * @param key      The key to update or add.
     * @param value    The value to associate with the key.
     */
    public static void updatePrefData(String nodeName, String key, String value) {
        Preferences prefs = Preferences.userRoot().node(nodeName);
        prefs.put(key, value);
    }

    /**
     * Saves a JSON string into preferences under a specified key and node.
     *
     * @param nodeName The name of the preference node.
     * @param key      The key to store the JSON string.
     * @param json     The JSON string to save.
     */
    public static void saveJsonIntoPref(String nodeName, String key, String json) {
        Preferences prefs = Preferences.userRoot().node(nodeName);
        prefs.put(key, json);
    }

    /**
     * Saves a JSONObject into preferences under a specified key and node.
     *
     * @param nodeName The name of the preference node.
     * @param key      The key to store the JSON object.
     * @param jsonObject The JSONObject to save.
     */
    public static void saveJsonIntoPref(String nodeName, String key, JSONObject jsonObject) {
        Preferences prefs = Preferences.userRoot().node(nodeName);
        prefs.put(key, jsonObject.toString());
    }
}