package com.biojj.app.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CpfValidationResponse {
    @JsonProperty("success")
    private boolean success;

    @JsonProperty("data")
    private CpfData data;

    @Data
    public static class CpfData {
        @JsonProperty("status")
        private String status;

        @JsonProperty("cpfNumber")
        private String cpfNumber;
    }
}
