package dev.emregecer.interview.gusto.loganalyser;

public class LogLineUtil {

    public static UserLog parseLogLine(String logLine) {
        return UserLog.builder()
                .method(extractFieldAsString(logLine, "method"))
                .path(extractFieldAsString(logLine, "path"))
                .responseTime(
                        extractFieldAsMillisecond(logLine, "connect") +
                                extractFieldAsMillisecond(logLine, "service")
                )
                .dyno(extractFieldAsString(logLine, "dyno"))
                .build();
    }

    private static String extractFieldAsString(String logLine, String fieldName) {
        return extractValue(logLine, fieldName);
    }

    private static Integer extractFieldAsMillisecond(String logLine, String fieldName) {
        return Integer.parseInt(extractValue(logLine, fieldName)
                .replace("ms", ""));
    }

    private static Integer extractFieldAsInt(String logLine, String fieldName) {
        return Integer.parseInt(extractValue(logLine, fieldName));
    }

    private static String extractValue(String logLine, String fieldName) {
        String searchText = fieldName + "=";
        int startFrom = logLine.indexOf(searchText) + searchText.length();
        int end = logLine.indexOf(' ', startFrom);

        return logLine.substring(startFrom, end);
    }
}
