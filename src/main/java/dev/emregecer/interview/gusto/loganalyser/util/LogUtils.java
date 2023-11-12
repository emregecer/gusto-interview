package dev.emregecer.interview.gusto.loganalyser.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LogUtils {

    public static String extractFieldAsString(String logLine, String fieldName) {
        return extractValue(logLine, fieldName);
    }

    public static Integer extractFieldAsMillisecond(String logLine, String fieldName) {
        return Integer.parseInt(extractValue(logLine, fieldName)
                .replace("ms", ""));
    }

    public static Integer extractFieldAsInt(String logLine, String fieldName) {
        return Integer.parseInt(extractValue(logLine, fieldName));
    }

    public static String extractValue(String logLine, String fieldName) {
        String searchText = fieldName + "=";
        int startFrom = logLine.indexOf(searchText) + searchText.length();
        int end = logLine.indexOf(' ', startFrom);

        return logLine.substring(startFrom, end);
    }
}
