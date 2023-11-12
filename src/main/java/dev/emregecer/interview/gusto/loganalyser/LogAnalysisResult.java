package dev.emregecer.interview.gusto.loganalyser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
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
