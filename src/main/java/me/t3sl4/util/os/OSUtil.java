package me.t3sl4.util.os;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class OSUtil {

    /**
     * Retrieves a stored preference value from the specified node and key.
     *
     * <p>This method accesses the given preference node and fetches the value associated
     * with the specified key. If the key is not found, it returns an empty string as the default value.</p>
     *
     * @param nodeName The name of the preference node to search in.
     * @param key      The key whose value needs to be retrieved.
     * @return The value associated with the key, or an empty string if the key does not exist.
     */
    public static String getPrefData(String nodeName, String key) {
        Preferences prefs = Preferences.userRoot().node(nodeName);
        return prefs.get(key, "");
    }

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

    /**
     * Updates the stored version data in preferences if it differs from the provided new data.
     *
     * <p>This method checks if the existing value for the given key is different from
     * the new data. If they are different, it updates the key with the new data.</p>
     *
     * @param nodeName   The name of the preference node.
     * @param versionKey The key to store the version information.
     * @param newData    The new data to be stored if it differs from the current value.
     */
    public static void updateLocalVersion(String nodeName, String versionKey, String newData) {
        Preferences prefs = Preferences.userRoot().node(nodeName);
        String currentData = prefs.get(versionKey, "");

        if (!currentData.equals(newData)) {
            prefs.put(versionKey, newData);
        }
    }
}