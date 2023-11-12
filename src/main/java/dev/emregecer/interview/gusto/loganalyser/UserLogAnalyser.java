package dev.emregecer.interview.gusto.loganalyser;

import dev.emregecer.interview.gusto.loganalyser.dto.LogAnalysis;
import dev.emregecer.interview.gusto.loganalyser.dto.LogAnalysisResult;
import dev.emregecer.interview.gusto.loganalyser.dto.UserLog;
import dev.emregecer.interview.gusto.loganalyser.util.LogUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserLogAnalyser {

    public static final String URL_PREFIX = "/api/users";

    public List<LogAnalysisResult> analyse(String logUrl) {
        List<UserLog> userLogs = convertToLogList(logUrl);

        // Group log entries by http_path
        Map<String, LogAnalysis> groupedEntries = new HashMap<>();
        for (UserLog userLog : userLogs) {
            String requestIdentifier = userLog.getRequestIdentifier();
            if (!groupedEntries.containsKey(requestIdentifier)) {
                groupedEntries.put(requestIdentifier, new LogAnalysis());
            }
            groupedEntries.get(requestIdentifier).incrementCount();
            groupedEntries.get(requestIdentifier).addResponseTime(userLog.getResponseTime());
            groupedEntries.get(requestIdentifier).setDynoMode(userLog.getDyno());
        }

        // Generate analysis for each http path
        return getLogAnalysisResults(groupedEntries);
    }

    private static List<LogAnalysisResult> getLogAnalysisResults(Map<String, LogAnalysis> groupedEntries) {
        List<LogAnalysisResult> analysisResults = new ArrayList<>();
        for (Map.Entry<String, LogAnalysis> entry : groupedEntries.entrySet()) {
            String path = entry.getKey();
            LogAnalysis data = entry.getValue();
            LogAnalysisResult analysis = new LogAnalysisResult(
                    path, data.getCount(), data.getResponseTimeMean(), data.getResponseTimeMedian(), data.getDynoMode()
            );
            analysisResults.add(analysis);
        }

        return analysisResults;
    }

    private List<UserLog> convertToLogList(String logUrl) {
        List<UserLog> userLogs = new ArrayList<>();
        try {
            URL logFile = new URL(logUrl);
            BufferedReader in = new BufferedReader(new InputStreamReader(logFile.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                UserLog userLog = parseLogLine(inputLine);
                if (userLog.getPath().contains(URL_PREFIX)) {
                    userLogs.add(userLog);
                }
            }

            in.close();
        } catch (IOException e) {
            throw new RuntimeException("Log file does not exist");
        }

        return userLogs;
    }

    public static UserLog parseLogLine(String logLine) {
        return UserLog.builder()
                .method(LogUtils.extractFieldAsString(logLine, "method"))
                .path(LogUtils.extractFieldAsString(logLine, "path"))
                .responseTime(
                        LogUtils.extractFieldAsMillisecond(logLine, "connect") +
                                LogUtils.extractFieldAsMillisecond(logLine, "service")
                )
                .dyno(LogUtils.extractFieldAsString(logLine, "dyno"))
                .build();
    }
}
