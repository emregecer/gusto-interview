package dev.emregecer.interview.gusto.loganalyser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogAnalysisResult {

    @JsonProperty("request_identifier")
    private String requestIdentifier;

    @JsonProperty("called")
    private long called;

    @JsonProperty("response_time_mean")
    private double responseTimeMean;

    @JsonProperty("response_time_median")
    private double responseTimeMedian;

    @JsonProperty("dyno_mode")
    private String dynoMode;
}
