package com.pahanaedu.librarymanagement.util;

public class UtilMatters {
    public static String getJsonValue(String json, String key) {
        String searchKey = "\"" + key + "\"";
        int keyIndex = json.indexOf(searchKey);

        if (keyIndex == -1) return "";

        int colonIndex = json.indexOf(":", keyIndex);
        int valueStart = colonIndex + 1;

        if (json.charAt(valueStart) == '"') {
            int startQuote = valueStart + 1;
            int endQuote = json.indexOf("\"", startQuote);
            return json.substring(startQuote, endQuote);
        }

        int end = json.indexOf(",", valueStart);
        if (end == -1) end = json.indexOf("}", valueStart);
        return json.substring(valueStart, end).trim();
    }

}
