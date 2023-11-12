package dev.emregecer.interview.gusto.loganalyser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogAnalysis {

    private int count;
    private List<Double> responseTimes = new ArrayList<>();
    private String dynoMode;

    public void incrementCount() {
        this.count++;
    }

    public void addResponseTime(double responseTime) {
        this.responseTimes.add(responseTime);
    }

    public double getResponseTimeMean() {
        return responseTimes.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    public double getResponseTimeMedian() {
        int size = responseTimes.size();
        if (size % 2 == 0) {
            int middle1 = size / 2 - 1;
            int middle2 = size / 2;
            return (responseTimes.get(middle1) + responseTimes.get(middle2)) / 2.0;
        }
        return responseTimes.get(size / 2);
    }
}