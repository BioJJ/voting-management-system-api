package com.biojj.app.domain.enums;

import lombok.Getter;

@Getter
public enum VoteType {
    SIM("sim"),
    NAO("nao");

    private final String value;

    VoteType(String value) {
        this.value = value;
    }

}
