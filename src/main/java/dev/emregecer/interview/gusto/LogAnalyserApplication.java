package dev.emregecer.interview.gusto;

import dev.emregecer.interview.gusto.loganalyser.dto.LogAnalysisResult;
import dev.emregecer.interview.gusto.loganalyser.UserLogAnalyser;

import java.util.List;

public class LogAnalyserApplication {

    public static void main(String[] args) {
        UserLogAnalyser userLogAnalyser = new UserLogAnalyser();

        List<LogAnalysisResult> analysisResults = userLogAnalyser.analyse("https://gist.githubusercontent.com/bss/6dbc7d4d6d2860c7ecded3d21098076a/raw/244045d24337e342e35b85ec1924bca8425fce2e/sample.small.log");
        System.out.println(analysisResults);
    }
}