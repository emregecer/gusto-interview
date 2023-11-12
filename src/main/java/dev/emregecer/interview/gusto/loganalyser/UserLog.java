package dev.emregecer.interview.gusto.loganalyser;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLog {

    private String method;

    private String path;

    private double responseTime;

    private String dyno;

    public String getRequestIdentifier() {
        return method + " " + path.replaceAll("/\\d+(?=/|$)", "/{user_id}");
    }
}
